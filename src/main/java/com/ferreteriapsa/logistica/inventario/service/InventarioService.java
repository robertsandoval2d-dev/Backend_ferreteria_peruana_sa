package com.ferreteriapsa.logistica.inventario.service;

import com.ferreteriapsa.logistica.inventario.repository.*;
import com.ferreteriapsa.logistica.inventario.model.*;
import com.ferreteriapsa.logistica.inventario.dto.response.InventarioDTO;

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
    public List<InventarioDTO> listarInventarioLinea(Long trabajadorId){

        return inventarioRepository.buscarProductosPorJefeId(trabajadorId);
    }
}
