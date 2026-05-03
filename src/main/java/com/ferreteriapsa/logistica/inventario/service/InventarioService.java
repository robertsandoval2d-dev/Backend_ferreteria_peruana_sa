package com.ferreteriapsa.logistica.inventario.service;

import com.ferreteriapsa.logistica.inventario.repository.*;
import com.ferreteriapsa.logistica.inventario.model.*;
import com.ferreteriapsa.logistica.inventario.dto.response.InventarioDTO;
import com.ferreteriapsa.logistica.auth.config.CustomUserPrincipal;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventarioService {
    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }
    @Transactional(readOnly = true)
    public List<InventarioDTO> listarInventarioLinea(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        // CustomUserPrincipal)
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) authentication.getPrincipal();

        // Pedimos el ID del trabajador
        Long trabajadorId = customUserPrincipal.getTrabajadorId();

        // Se lo mandamos al Repository
        return inventarioRepository.buscarProductosPorJefeId(trabajadorId);
    }
}
