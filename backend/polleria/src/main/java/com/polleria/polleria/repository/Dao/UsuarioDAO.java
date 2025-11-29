package com.polleria.polleria.repository.dao;

import com.polleria.polleria.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioDAO {
    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByUsernameAndPassword(String username, String password);

    Usuario save(Usuario usuario);

    Usuario update(Usuario usuario);

    void deleteById(Long id);
}