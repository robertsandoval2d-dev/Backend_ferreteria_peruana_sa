package com.ferreteriapsa.logistica.inventario.service;

import com.ferreteriapsa.logistica.inventario.repository.*;
import com.ferreteriapsa.logistica.inventario.model.*;
import com.ferreteriapsa.logistica.inventario.dto.response.InventarioDTO;
import com.ferreteriapsa.logistica.auth.service.AutenticacionInterface;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {
    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }


    @Transactional
    public List<InventarioDTO> listarInventarioLinea(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        // 2. Extraemos el Principal (sabemos que es tu clase CustomUserPrincipal)
        com.ferreteriapsa.logistica.auth.config.CustomUserPrincipal principal =
                (com.ferreteriapsa.logistica.auth.config.CustomUserPrincipal) authentication.getPrincipal();

        // 3. Ahora sí, le pedimos amablemente el nombre de usuario real
        String usernameReal = principal.getUsername();

        // (Opcional) Tu mensaje de victoria en consola
        System.out.println("✅ ÉXITO - El usuario real es: '" + usernameReal + "'");

        // 4. Se lo mandamos al Repository
        return inventarioRepository.buscarProductosPorUsernameJefe(usernameReal);
    }
}
