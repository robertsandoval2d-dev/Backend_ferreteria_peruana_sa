package com.ferreteriapsa.logistica.trabajador.service;

import org.springframework.stereotype.Service;

import com.ferreteriapsa.logistica.auth.model.Usuario;
import com.ferreteriapsa.logistica.auth.service.AutenticacionInterface;
import com.ferreteriapsa.logistica.trabajador.dto.request.TrabajadorRequest;
import com.ferreteriapsa.logistica.trabajador.dto.response.TrabajadorDTO;
import com.ferreteriapsa.logistica.trabajador.model.*;  
import com.ferreteriapsa.logistica.trabajador.repository.*;

import jakarta.transaction.Transactional;


@Service
public class TrabajadorService {
    private final AutenticacionInterface autenticacionService;
    private final TrabajadorRepository trabajadorRepository;
    private final AlmacenRepository almacenRepository;
    private final TiendaRepository tiendaRepository;
    private final LineaProductoRepository lineaRepository;
    private final TrabajadorAlmacenRepository trabajadorAlmacenRepository;
    private final TrabajadorTiendaRepository trabajadorTiendaRepository;
    private final TrabajadorLineaRepository trabajadorLineaRepository;
    
    public TrabajadorService(AutenticacionInterface autenticacionService, TrabajadorRepository trabajadorRepository,
        AlmacenRepository almacenRepository, TiendaRepository tiendaRepository, LineaProductoRepository lineaRepository,
        TrabajadorAlmacenRepository trabajadorAlmacenRepository, TrabajadorTiendaRepository trabajadorTiendaRepository,
        TrabajadorLineaRepository trabajadorLineaRepository) {
        this.autenticacionService = autenticacionService;
        this.trabajadorRepository = trabajadorRepository;
        this.almacenRepository = almacenRepository;
        this.tiendaRepository = tiendaRepository;
        this.lineaRepository = lineaRepository;
        this.trabajadorAlmacenRepository = trabajadorAlmacenRepository;
        this.trabajadorTiendaRepository = trabajadorTiendaRepository;
        this.trabajadorLineaRepository = trabajadorLineaRepository;

    }

    @Transactional
    public TrabajadorDTO registrarTrabajadorCompleto(TrabajadorRequest request) {

        // 1. Crear usuario
        Usuario usuario = autenticacionService.registrarUsuario(request.getUsername(), request.getPassword(), request.getRol());

        // 2. Crear trabajador base
        Trabajador trabajador = new Trabajador();
        trabajador.setNombre(request.getNombre());
        trabajador.setDni(request.getDni());
        trabajador.setUsuario(usuario);

        trabajador = trabajadorRepository.save(trabajador);

        // 3. Obtener rol
        String rol = usuario.getRol().getNombre().toLowerCase();

        // 4. Asignación según rol
        switch (rol) {

            case "almacenero":
                Almacen almacen = almacenRepository.findById(request.getIdReferencia())
                        .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));

                TrabajadorAlmacen ta = new TrabajadorAlmacen();
                ta.setTrabajador(trabajador);
                ta.setAlmacen(almacen);

                trabajadorAlmacenRepository.save(ta);
                break;

            case "administrador_de_tienda":
                Tienda tienda = tiendaRepository.findById(request.getIdReferencia())
                        .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));

                TrabajadorTienda tt = new TrabajadorTienda();
                tt.setTrabajador(trabajador);
                tt.setTienda(tienda);

                trabajadorTiendaRepository.save(tt);
                break;

            case "jefe_de_linea":
                LineaProducto linea = lineaRepository.findById(request.getIdReferencia())
                        .orElseThrow(() -> new RuntimeException("Línea no encontrada"));

                TrabajadorLinea tl = new TrabajadorLinea();
                tl.setTrabajador(trabajador);
                tl.setLinea(linea);

                trabajadorLineaRepository.save(tl);
                break;

            default:
                throw new RuntimeException("Rol no válido para asignación");
        }

        return new TrabajadorDTO(trabajador.getNombre(), usuario.getUsername(), usuario.getRol().getNombre());
    }
}
