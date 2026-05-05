package com.ferreteriapsa.logistica.trabajador.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

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
    private final TiendaRepository tiendaRepository;
    private final LineaProductoRepository lineaRepository;
    
    public TrabajadorService(AutenticacionInterface autenticacionService, TrabajadorRepository trabajadorRepository,
        TiendaRepository tiendaRepository, LineaProductoRepository lineaRepository) {
        this.autenticacionService = autenticacionService;
        this.trabajadorRepository = trabajadorRepository;
        this.tiendaRepository = tiendaRepository;
        this.lineaRepository = lineaRepository;

    }

    @SuppressWarnings("null")
    @Transactional
    public TrabajadorDTO registrarTrabajadorCompleto(TrabajadorRequest request) {

        // 1. Crear usuario
        Usuario usuario = autenticacionService.registrarUsuario(request.getUsername(), request.getPassword(), request.getRol());

        // 2. Crear trabajador base
        Trabajador trabajador = new Trabajador();
        trabajador.setNombre(request.getNombre());
        trabajador.setDni(request.getDni());
        trabajador.setUsuario(usuario);

        if (!request.getRol().equalsIgnoreCase("admin")) {

            if (request.getTiendaId() == null) {
                throw new ResponseStatusException(  //400 BAD REQUEST
                    HttpStatus.BAD_REQUEST,
                    "Este rol requiere una tienda"
                );
            }

            Tienda tienda = tiendaRepository.findById(request.getTiendaId())
                .orElseThrow(() -> new ResponseStatusException( //404 NOT FOUND
                    HttpStatus.NOT_FOUND,
                    "Tienda no encontrada"
                ));

            trabajador.setTienda(tienda);
        }

        // 3. Obtener rol
        String rol = usuario.getRol().getNombre().toLowerCase();

        trabajador = trabajadorRepository.save(trabajador);

        // 4. Asignación según rol
        switch (rol) {

            case "almacenero":
                break;

            case "administrador_de_tienda":
                break;

            case "jefe_de_linea":
                if (request.getLineaId() == null) {
                    throw new ResponseStatusException(//400 BAD REQUEST
                        HttpStatus.BAD_REQUEST,
                        "Debe proporcionar idLinea para jefe de línea"
                    );
                }
                LineaProducto linea = lineaRepository.findById(request.getLineaId())
                        .orElseThrow(() -> new ResponseStatusException( //404 NOT FOUND
                    HttpStatus.NOT_FOUND,
                    "Línea no encontrada"
                ));

                linea.setJefeDeLinea(trabajador);

                lineaRepository.save(linea);
                break;

            case "admin":
                break;

            default:
                throw new ResponseStatusException( //409 CONFLICT
                    HttpStatus.CONFLICT,
                    "Rol no permitido"
                );
        }

        return new TrabajadorDTO(trabajador.getNombre(), usuario.getUsername(), usuario.getRol().getNombre());
    }
}
