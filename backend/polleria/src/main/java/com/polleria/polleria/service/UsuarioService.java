package com.polleria.polleria.service;

import com.polleria.polleria.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listarUsuarios();
    Optional<Usuario> obtenerUsuarioPorId(Long id);
    Optional<Usuario> autenticar(String username, String password);
    Usuario guardarUsuario(Usuario usuario);
    void eliminarUsuario(Long id);
}