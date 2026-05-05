package com.ferreteriapsa.logistica.catalogo.service;

import org.springframework.stereotype.Service;

import com.ferreteriapsa.logistica.catalogo.dto.response.*;
import com.ferreteriapsa.logistica.catalogo.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

@Service
public class CatalogoService {

    private final ProductoRepository productoRepository;

    public CatalogoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    public List<CatalogoResponse> obtenerCatalogo(Long trabajadorId) {

        List<CatalogoProjection> filas =
                productoRepository.obtenerCatalogoPorTrabajador(trabajadorId);

        Map<Long, CatalogoResponse> mapa = new LinkedHashMap<>();

        for (CatalogoProjection row : filas) {

            mapa.putIfAbsent(
                row.getProductoId(),
                new CatalogoResponse(
                    row.getProductoId(),
                    row.getProductoNombre(),
                    new ArrayList<>()
                )
            );

            if (row.getProveedorId() != null) {
                mapa.get(row.getProductoId())
                    .getProveedores()
                    .add(new ProveedorDTO(
                            row.getProveedorId(),
                            row.getProveedorNombre()
                    ));
            }
        }

        return new ArrayList<>(mapa.values());
    }
}
