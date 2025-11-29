package com.polleria.polleria.Usuario;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.findAll();
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioDAO.findById(id);
    }

    @Override
    public Optional<Usuario> autenticar(String username, String password) {
        return usuarioDAO.findByUsernameAndPassword(username, password);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        if (usuario.getId() == null) {
            return usuarioDAO.save(usuario);
        } else {
            return usuarioDAO.update(usuario);
        }
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioDAO.deleteById(id);
    }
}