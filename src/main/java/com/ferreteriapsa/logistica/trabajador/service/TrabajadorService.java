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
    
    public TrabajadorService(AutenticacionInterface autenticacionService, TrabajadorRepository trabajadorRepository,
        AlmacenRepository almacenRepository, TiendaRepository tiendaRepository, LineaProductoRepository lineaRepository) {
        this.autenticacionService = autenticacionService;
        this.trabajadorRepository = trabajadorRepository;
        this.almacenRepository = almacenRepository;
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

        // 3. Obtener rol
        String rol = usuario.getRol().getNombre().toLowerCase();

        // 4. Asignación según rol
        switch (rol) {

            case "almacenero":
                Almacen almacen = almacenRepository.findById(request.getIdReferencia())
                        .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));

                trabajador.setAlmacen(almacen);

                trabajador = trabajadorRepository.save(trabajador);
                break;

            case "administrador_de_tienda":
                Tienda tienda = tiendaRepository.findById(request.getIdReferencia())
                        .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));

                tienda.setAdministrador(trabajador);

                tiendaRepository.save(tienda);
                break;

            case "jefe_de_linea":
                LineaProducto linea = lineaRepository.findById(request.getIdReferencia())
                        .orElseThrow(() -> new RuntimeException("Línea no encontrada"));

                linea.setJefeDeLinea(trabajador);

                lineaRepository.save(linea);
                break;

            case "admin":
                trabajador = trabajadorRepository.save(trabajador);
                break;

            default:
                throw new RuntimeException("Rol no válido para asignación");
        }

        return new TrabajadorDTO(trabajador.getNombre(), usuario.getUsername(), usuario.getRol().getNombre());
    }
}
