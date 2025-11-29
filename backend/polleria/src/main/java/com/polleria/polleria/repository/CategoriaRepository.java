package com.polleria.polleria.repository;

import com.polleria.polleria.entity.Categoria;
import com.polleria.polleria.repository.dao.CategoriaDAO;
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
public class CategoriaRepository implements CategoriaDAO {

    private final JdbcTemplate jdbcTemplate;

    public CategoriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Categoria> categoriaRowMapper = (rs, rowNum) -> {
        return new Categoria(
                rs.getLong("id_categoria"),
                rs.getString("nombre"));
    };

    @Override
    public List<Categoria> findAll() {
        String query = "SELECT id_categoria, nombre FROM categorias ORDER BY id_categoria";
        return jdbcTemplate.query(query, categoriaRowMapper);
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        String query = "SELECT id_categoria, nombre FROM categorias WHERE id_categoria = ?";
        try {
            Categoria categoria = jdbcTemplate.queryForObject(query, categoriaRowMapper, id);
            return Optional.ofNullable(categoria);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Categoria save(Categoria categoria) {
        String query = "INSERT INTO categorias (nombre) VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, categoria.getNombre());
            return ps;
        }, keyHolder);

        categoria.setIdCategoria(keyHolder.getKey().longValue());
        return categoria;
    }

    @Override
    public Categoria update(Categoria categoria) {
        String query = "UPDATE categorias SET nombre = ? WHERE id_categoria = ?";
        jdbcTemplate.update(query, categoria.getNombre(), categoria.getIdCategoria());
        return categoria;
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM categorias WHERE id_categoria = ?";
        jdbcTemplate.update(query, id);
    }
}