package com.ferreteriapsa.logistica.mensajeria.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import com.ferreteriapsa.logistica.mensajeria.repository.MensajeRepository;
import com.ferreteriapsa.logistica.auth.repository.UsuarioRepository;
import com.ferreteriapsa.logistica.auth.model.Usuario;
import com.ferreteriapsa.logistica.mensajeria.dto.request.*;
import com.ferreteriapsa.logistica.mensajeria.dto.response.*;
import com.ferreteriapsa.logistica.mensajeria.model.Mensaje;

import jakarta.transaction.Transactional;

@Service
public class MensajeriaService {

    private final MensajeRepository mensajeRepository;
    private final UsuarioRepository usuarioRepository;

    public MensajeriaService(MensajeRepository mensajeRepository, UsuarioRepository usuarioRepository) {
        this.mensajeRepository = mensajeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void registrarMensaje(MensajeRequest request, Long trabajadorId){
        Usuario receptor = usuarioRepository.findByUsername(request.getUsernameDestino())
            .orElseThrow(() -> new ResponseStatusException(//404 NOT FOUND
                HttpStatus.NOT_FOUND,
                "Usuario no encontrado"
            ));

        Usuario emisor = usuarioRepository.findByTrabajador_TrabajadorId(trabajadorId).orElse(null);

        Mensaje nuevoMensaje = new Mensaje();
        nuevoMensaje.setEmisor(emisor);
        nuevoMensaje.setReceptor(receptor);
        nuevoMensaje.setTitulo(request.getTitulo());
        nuevoMensaje.setMensaje(request.getMensaje());
        nuevoMensaje.setLeido(false);
        nuevoMensaje.setFechaEnvio(LocalDateTime.now());

        mensajeRepository.save(nuevoMensaje);
    }

    public List<MensajeResponse> listarMensajesUsuario(Long trabajadorId){

        Usuario usuario = usuarioRepository.findByTrabajador_TrabajadorId(trabajadorId).orElse(null);
        
        List<Mensaje> mensajes = mensajeRepository.listarMensajesPorUsuario(usuario.getUsuarioId());

        List<MensajeResponse> response = mensajes.stream()
            .map(mensaje -> {
                MensajeResponse mr = new MensajeResponse();
                mr.setMensajeId(mensaje.getMensajeId());
                mr.setTitulo(mensaje.getTitulo());
                mr.setMensaje(mensaje.getMensaje());
                mr.setEmisorId(mensaje.getEmisor().getUsuarioId());
                mr.setEmisorUsername(mensaje.getEmisor().getUsername());
                mr.setReceptorId(mensaje.getReceptor().getUsuarioId());
                mr.setReceptorUsername(mensaje.getReceptor().getUsername());
                mr.setFechaEnvio(mensaje.getFechaEnvio());
                mr.setLeido(mensaje.getLeido());
                return mr;
            }).toList();
        
        return response;
    }

    @SuppressWarnings("null")
    @Transactional
    public MensajeResponse marcarMensajeComoLeido(Long mensajeId){
        Mensaje mensaje = mensajeRepository.findById(mensajeId)
            .orElseThrow(() -> new ResponseStatusException(//404 NOT FOUND
                HttpStatus.NOT_FOUND,
                "Mensaje no encontrado"
            ));

        mensaje.setLeido(true);

        Mensaje mensajeGuardado = mensajeRepository.save(mensaje);

        MensajeResponse response = new MensajeResponse();

        response.setEmisorId(mensajeGuardado.getMensajeId());
        response.setTitulo(mensajeGuardado.getTitulo());
        response.setMensaje(mensajeGuardado.getMensaje());
        response.setEmisorId(mensajeGuardado.getEmisor().getUsuarioId());
        response.setEmisorUsername(mensaje.getEmisor().getUsername());
        response.setReceptorId(mensaje.getReceptor().getUsuarioId());
        response.setReceptorUsername(mensaje.getReceptor().getUsername());
        response.setFechaEnvio(mensaje.getFechaEnvio());
        response.setLeido(mensaje.getLeido());

        return response;
    }
    
}
