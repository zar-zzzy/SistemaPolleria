package com.polleria.polleria.Usuario;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements UsuarioDAO {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Usuario> usuarioRowMapper = (rs, rowNum) -> {
        return new Usuario(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("password"),
            Rol.valueOf(rs.getString("rol"))
        );
    };

    @Override
    public List<Usuario> findAll() {
        String query = "SELECT id, username, password, rol FROM usuario ORDER BY id";
        return jdbcTemplate.query(query, usuarioRowMapper);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        String query = "SELECT id, username, password, rol FROM usuario WHERE id = ?";
        try {
            Usuario usuario = jdbcTemplate.queryForObject(query, usuarioRowMapper, id);
            return Optional.ofNullable(usuario);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        String query = "SELECT id, username, password, rol FROM usuario WHERE username = ?";
        try {
            Usuario usuario = jdbcTemplate.queryForObject(query, usuarioRowMapper, username);
            return Optional.ofNullable(usuario);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Usuario> findByUsernameAndPassword(String username, String password) {
        String query = "SELECT id, username, password, rol FROM usuario WHERE username = ? AND password = ?";
        try {
            Usuario usuario = jdbcTemplate.queryForObject(query, usuarioRowMapper, username, password);
            return Optional.ofNullable(usuario);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Usuario save(Usuario usuario) {
        String query = "INSERT INTO usuario (username, password, rol) VALUES (?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol().name());
            return ps;
        }, keyHolder);
        
        usuario.setId(keyHolder.getKey().longValue());
        return usuario;
    }

    @Override
    public Usuario update(Usuario usuario) {
        String query = "UPDATE usuario SET username = ?, password = ?, rol = ? WHERE id = ?";
        jdbcTemplate.update(query, 
            usuario.getUsername(),
            usuario.getPassword(),
            usuario.getRol().name(),
            usuario.getId()
        );
        return usuario;
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM usuario WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}