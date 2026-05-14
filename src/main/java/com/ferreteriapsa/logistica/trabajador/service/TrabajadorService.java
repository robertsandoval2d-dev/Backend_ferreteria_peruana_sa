package com.ferreteriapsa.logistica.trabajador.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.ferreteriapsa.logistica.auth.model.Usuario;
import com.ferreteriapsa.logistica.auth.service.AutenticacionInterface;
import com.ferreteriapsa.logistica.trabajador.dto.request.TrabajadorRequest;
import com.ferreteriapsa.logistica.trabajador.dto.request.TrabajadorUpdateRequest;
import com.ferreteriapsa.logistica.trabajador.dto.response.LineaProductoResponse;
import com.ferreteriapsa.logistica.trabajador.dto.response.TiendaResponse;
import com.ferreteriapsa.logistica.trabajador.dto.response.TrabajadorResponse;
import com.ferreteriapsa.logistica.trabajador.dto.response.TrabajadorUpdateResponse;
import com.ferreteriapsa.logistica.trabajador.model.*;  
import com.ferreteriapsa.logistica.trabajador.repository.*;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class TrabajadorService {
    private final AutenticacionInterface autenticacionService;
    private final TrabajadorRepository trabajadorRepository;
    private final TiendaRepository tiendaRepository;
    private final LineaProductoRepository lineaRepository;
    private final AsignacionRepository asignacionRepository;
    
    public TrabajadorService(AutenticacionInterface autenticacionService, TrabajadorRepository trabajadorRepository,
        TiendaRepository tiendaRepository, LineaProductoRepository lineaRepository, AsignacionRepository asignacionRepository) {
        this.autenticacionService = autenticacionService;
        this.trabajadorRepository = trabajadorRepository;
        this.tiendaRepository = tiendaRepository;
        this.lineaRepository = lineaRepository;
        this.asignacionRepository = asignacionRepository;
    }

    @SuppressWarnings("null")
    @Transactional
    public TrabajadorResponse registrarTrabajadorCompleto(TrabajadorRequest request) {

        // 1. Crear usuario
        Usuario usuario = autenticacionService.registrarUsuario(request.getUsername(), request.getPassword(), request.getRol());

        // 2. Crear trabajador base
        Trabajador trabajador = new Trabajador();
        trabajador.setNombre(request.getNombre());
        trabajador.setDni(request.getDni());
        trabajador.setUsuario(usuario);

        // 3. Preparar los datos comunes para la Asignación
        trabajador = trabajadorRepository.save(trabajador);

        String rol = usuario.getRol().getNombre().toLowerCase();

        if (!rol.equalsIgnoreCase("admin")) {
            if (request.getTiendaId() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este rol requiere una tienda");
            }

            Tienda tienda = tiendaRepository.findById(request.getTiendaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tienda no encontrada"));

            // 4. Lógica específica según el rol dentro de la Asignación
            switch (rol) {
                case "almacenero":
                case "administrador_de_tienda":
                    // Para roles generales, creamos la asignación normal
                    Asignacion asigGeneral = new Asignacion();
                    asigGeneral.setTrabajador(trabajador);
                    asigGeneral.setTienda(tienda);
                    asigGeneral.setFechaInicio(LocalDate.now());
                    asigGeneral.setActivo(true);
                    asignacionRepository.save(asigGeneral);
                    break;

                case "jefe_de_linea":
                    if (request.getLineaId() == null) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe proporcionar lineaId para jefe de línea");
                    }
                    
                    LineaProducto linea = lineaRepository.findById(request.getLineaId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Línea no encontrada"));

                    // --- LÓGICA DE ROTACIÓN AUTOMÁTICA ---
                    // Buscamos si ya hay alguien activo en esa tienda para esa misma línea
                    Optional<Asignacion> asignacionPrevia = asignacionRepository
                        .findByTiendaAndLineaProductoAndActivoTrue(tienda, linea);

                    if (asignacionPrevia.isPresent()) {
                        Asignacion anterior = asignacionPrevia.get();
                        if(anterior.getTrabajador()==null){
                            // CASO 1: La línea existe en la tienda pero está VACANTE. Reutilizamos el registro.
                            anterior.setTrabajador(trabajador);
                            anterior.setFechaInicio(LocalDate.now()); //inicia hoy
                            asignacionRepository.save(anterior);
                        }
                        else if (!anterior.getTrabajador().getId().equals(trabajador.getId())) {
                            // CASO 2: La línea está OCUPADA por otro. Inactivamos (rotación) y creamos nuevo.
                            anterior.setActivo(false);
                            anterior.setFechaFin(LocalDate.now());
                            asignacionRepository.save(anterior);

                            Asignacion nuevaAsig = new Asignacion();
                            nuevaAsig.setTrabajador(trabajador);
                            nuevaAsig.setTienda(tienda);
                            nuevaAsig.setLineaProducto(linea);
                            nuevaAsig.setFechaInicio(LocalDate.now());
                            nuevaAsig.setActivo(true);
                            asignacionRepository.save(nuevaAsig);
                        }
                    }else{
                        // CASO 3: La tienda no tiene esta línea registrada en su catálogo.
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                            "Error: La tienda " + tienda.getNombre() + " no tiene habilitada la línea " + linea.getNombre());
                    }
                    break;

                default:
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Rol no permitido para asignación de tienda");
            }
        }

        return new TrabajadorResponse(
            trabajador.getId(), 
            usuario.getRol().getNombre(), 
            usuario.getUsername(),
            trabajador.getNombre(),
            trabajador.getDni()
        );
    }

    public List<TrabajadorResponse> listarTrabajadores(){
        return trabajadorRepository.listarTrabajadores();
    }

    @SuppressWarnings("null")
    @Transactional
    public TrabajadorUpdateResponse actualizarTrabajador(TrabajadorUpdateRequest request, Long trabajadorId){
        // 1. Buscar el trabajador
        Trabajador trabajador = trabajadorRepository.findById(trabajadorId)
            .orElseThrow(() -> new ResponseStatusException( //404 NOT FOUND
                    HttpStatus.NOT_FOUND,
                    "Trabajador no encontrado"
            ));

        // 2. Obtener el Rol actual del trabajador (Desde su Usuario)
        String rol = trabajador.getUsuario().getRol().getNombre().toLowerCase();

        // 3. Validar coherencia entre Rol y Request
        if (rol.equals("jefe_de_linea") && request.getLineaId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Un Jefe de Línea debe tener una línea asignada.");
        }
        
        if (!rol.equals("jefe_de_linea") && request.getLineaId() != null) {
            // Si no es jefe de línea, ignoramos el lineaId o lanzamos error para evitar datos inconsistentes
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solo los Jefes de Línea pueden tener una línea de productos.");
        }

        // 4. Actualizar datos básicos
        trabajador.setNombre(request.getNombre());
        trabajador.setDni(request.getDni());
        trabajador = trabajadorRepository.save(trabajador);

        // 5. Gestionar la rotación/cambio de tienda o línea
        // Buscamos si ya tiene una asignación activa actualmente
        Asignacion asignacionActual = asignacionRepository.findByTrabajadorAndActivoTrue(trabajador)
            .orElse(null);

        Tienda nuevaTienda = tiendaRepository.findById(request.getTiendaId())
            .orElseThrow(() -> new ResponseStatusException( //404 NOT FOUND
                    HttpStatus.NOT_FOUND,
                    "Tienda no encontrada"
            ));

        // Verificamos si los datos de asignación han cambiado
        boolean cambioTienda = (asignacionActual == null) || !asignacionActual.getTienda().getId().equals(request.getTiendaId());
        boolean cambioLinea = false;

        if (rol.equals("jefe_de_linea") && asignacionActual != null) {
            Long lineaActualId = (asignacionActual.getLineaProducto() != null) ? asignacionActual.getLineaProducto().getId() : null;
            cambioLinea = !request.getLineaId().equals(lineaActualId);
        }

        // Si hubo algún cambio en tienda o línea, cerramos la anterior y creamos una nueva
        if (cambioTienda || cambioLinea) {
            
            // A) Cerrar la asignación anterior del trabajador (Trazabilidad)
            if (asignacionActual != null) {
                asignacionActual.setActivo(false);
                asignacionActual.setFechaFin(LocalDate.now());
                asignacionRepository.save(asignacionActual);
            }

            // B) Lógica de ocupación de nuevo puesto
            if (rol.equals("jefe_de_linea")) {
                LineaProducto linea = lineaRepository.findById(request.getLineaId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Línea no encontrada"));

                // Buscamos la configuración de esa línea en esa nueva tienda
                Optional<Asignacion> asignacionPreviaOpt = asignacionRepository
                    .findByTiendaAndLineaProductoAndActivoTrue(nuevaTienda, linea);

                if (asignacionPreviaOpt.isPresent()) {
                    Asignacion previa = asignacionPreviaOpt.get();

                    if (previa.getTrabajador() == null) {
                        // CASO 1: Puesto Vacante -> Lo ocupamos
                        previa.setTrabajador(trabajador);
                        previa.setFechaInicio(LocalDate.now());
                        asignacionRepository.save(previa);
                    } else if (!previa.getTrabajador().getId().equals(trabajador.getId())) {
                        // CASO 2: Puesto Ocupado por OTRO -> Inactivamos al anterior y creamos nuevo
                        previa.setActivo(false);
                        previa.setFechaFin(LocalDate.now());
                        asignacionRepository.save(previa);

                        Asignacion nuevaAsignacion = new Asignacion();
                        nuevaAsignacion.setTrabajador(trabajador);
                        nuevaAsignacion.setTienda(nuevaTienda);
                        nuevaAsignacion.setLineaProducto(linea);
                        nuevaAsignacion.setFechaInicio(LocalDate.now());
                        nuevaAsignacion.setActivo(true);
                        asignacionRepository.save(nuevaAsignacion);
                    }
                } else {
                    // CASO 3: Línea no habilitada en la tienda
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                        "Error: La tienda " + nuevaTienda.getNombre() + " no tiene habilitada la línea " + linea.getNombre());
                }
            } else {
                // Para roles generales (Almacenero, Admin Tienda)
                Asignacion nuevaAsignacion = new Asignacion();
                nuevaAsignacion.setTrabajador(trabajador);
                nuevaAsignacion.setTienda(nuevaTienda);
                nuevaAsignacion.setFechaInicio(LocalDate.now());
                nuevaAsignacion.setActivo(true);
                asignacionRepository.save(nuevaAsignacion);
            }
        }

        // 6. Construir respuesta
        TrabajadorUpdateResponse response = new TrabajadorUpdateResponse();
        response.setTrabajadorId(trabajador.getId());
        response.setNombre(trabajador.getNombre());
        response.setDni(trabajador.getDni());
        response.setNombreTienda(nuevaTienda.getNombre());
        
        if (rol.equals("jefe_de_linea")) {
            LineaProducto lp = lineaRepository.findById(request.getLineaId()).get();
            response.setNombreLinea(lp.getNombre());
        }

        return response;
    }

    public List<TiendaResponse> listarTiendasConLineas(){
        List<Tienda> tiendas = tiendaRepository.listarTiendasConLineas();

        return tiendas.stream()
            .map(tienda -> new TiendaResponse(
                tienda.getId(),
                tienda.getNombre(),
                tienda.getAsignaciones().stream()
                    // 1. Filtramos solo las asignaciones que tienen línea y están activas
                    .filter(asig -> asig.isActivo() && asig.getLineaProducto() != null)
                    // 2. Mapeamos la Línea de Producto al Response
                    .map(asig -> {
                        LineaProducto lp = asig.getLineaProducto();
                        return new LineaProductoResponse(
                            lp.getId(),
                            lp.getNombre()
                        );
                    })
                    // 3. Eliminamos duplicados si una línea tiene varios jefes (opcional pero recomendado)
                    .distinct() 
                    .toList()
            ))
            .toList();
    }
}
