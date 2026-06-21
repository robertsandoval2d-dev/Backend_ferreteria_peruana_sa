package com.ferreteriapsa.logistica.analisis.service;

import com.ferreteriapsa.logistica.inventario.model.Inventario;
import com.ferreteriapsa.logistica.inventario.model.ZonaAlmacen;
import com.ferreteriapsa.logistica.inventario.repository.InventarioRepository;
import com.ferreteriapsa.logistica.inventario.repository.ZonaAlmacenRepository;
import com.ferreteriapsa.logistica.analisis.dto.response.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalisisService {

    private final ZonaAlmacenRepository zonaAlmacenRepository;
    private final InventarioRepository inventarioRepository;

    public AnalisisService(ZonaAlmacenRepository zonaAlmacenRepository, InventarioRepository inventarioRepository) {
        this.zonaAlmacenRepository = zonaAlmacenRepository;
        this.inventarioRepository = inventarioRepository;
    }

    @Transactional(readOnly = true)
    public List<SaturacionZonaResponse> obtenerSaturacionZonas(Long trabajadorId) {
        List<ZonaAlmacen> zonas = zonaAlmacenRepository.findZonasByTrabajadorId(trabajadorId);

        return zonas.stream()
                .map(zona -> {
                    int max = zona.getCapacidadMaxima();
                    int actual = zona.getCapacidadActual();
                    
                    // La lógica matemática vive aquí
                    double porcentaje = (max == 0) ? 0.0 : 
                        Math.round(((double) actual / max) * 100.0 * 100.0) / 100.0;

                    return new SaturacionZonaResponse(
                            zona.getCategoria(),
                            max,
                            actual,
                            porcentaje 
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ValorInmovilizadoResponse> obtenerCostoInmovilizadoPorTrabajador(Long trabajadorId) {
        
        List<Inventario> inventarios = inventarioRepository.findInventarioPorLineaYTiendaDelTrabajador(trabajadorId);

        return inventarios.stream()
                .map(inv -> {
                    BigDecimal precioVenta = inv.getProducto().getPrecioVenta();
                    BigDecimal stockActual = new BigDecimal(inv.getStock());

                    BigDecimal valorMonetario = precioVenta.multiply(stockActual);

                    String rotacionString;
                    try {
                        Object rotacionObj = inv.getClass().getMethod("getRotacion").invoke(inv);
                        rotacionString = (rotacionObj != null) ? rotacionObj.toString() : null;
                    } catch (Exception e) {
                        rotacionString = null;
                    }

                    return new ValorInmovilizadoResponse(
                            inv.getProducto().getNombre(),
                            valorMonetario,
                            rotacionString,
                            inv.getProducto().getCategoria()
                    );
                })
                // Opcional: Si quieres que el gráfico ya reciba los datos ordenados de mayor a menor costo
                .sorted((dto1, dto2) -> dto2.getValorMonetario().compareTo(dto1.getValorMonetario()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<VentasStockResponse> obtenerRelacionVentasStock(Long trabajadorId) {

        LocalDateTime fechaLimite = LocalDateTime.now().minusMonths(3);

        return inventarioRepository.findVentasYStockPorTrabajador(trabajadorId, fechaLimite);
    }

}
