package com.polleria.polleria.Plato;

import com.polleria.polleria.Categoria.Categoria;

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
public class PlatoRepository implements PlatoDAO {

    private final JdbcTemplate jdbcTemplate;

    public PlatoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Plato> platoRowMapper = (rs, rowNum) -> {
        Categoria categoria = new Categoria(
            rs.getLong("id_categoria"),
            rs.getString("categoria_nombre")
        );
        
        return new Plato(
            rs.getLong("id_plato"),
            rs.getString("nombre"),
            rs.getString("descripcion"),
            rs.getDouble("precio"),
            categoria
        );
    };

    @Override
    public List<Plato> findAll() {
        String query = "SELECT p.id_plato, p.nombre, p.descripcion, p.precio, " +
                      "p.id_categoria, c.nombre as categoria_nombre " +
                      "FROM platos p " +
                      "JOIN categorias c ON p.id_categoria = c.id_categoria " +
                      "ORDER BY p.id_plato";
        return jdbcTemplate.query(query, platoRowMapper);
    }

    @Override
    public Optional<Plato> findById(Long id) {
        String query = "SELECT p.id_plato, p.nombre, p.descripcion, p.precio, " +
                      "p.id_categoria, c.nombre as categoria_nombre " +
                      "FROM platos p " +
                      "JOIN categorias c ON p.id_categoria = c.id_categoria " +
                      "WHERE p.id_plato = ?";
        try {
            Plato plato = jdbcTemplate.queryForObject(query, platoRowMapper, id);
            return Optional.ofNullable(plato);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Plato save(Plato plato) {
        String query = "INSERT INTO platos (nombre, descripcion, precio, id_categoria) VALUES (?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, plato.getNombre());
            ps.setString(2, plato.getDescripcion());
            ps.setDouble(3, plato.getPrecio());
            ps.setLong(4, plato.getCategoria().getIdCategoria());
            return ps;
        }, keyHolder);
        
        plato.setIdPlato(keyHolder.getKey().longValue());
        return plato;
    }

    @Override
    public Plato update(Plato plato) {
        String query = "UPDATE platos SET nombre = ?, descripcion = ?, precio = ?, id_categoria = ? WHERE id_plato = ?";
        jdbcTemplate.update(query, 
            plato.getNombre(),
            plato.getDescripcion(),
            plato.getPrecio(),
            plato.getCategoria().getIdCategoria(),
            plato.getIdPlato()
        );
        return plato;
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM platos WHERE id_plato = ?";
        jdbcTemplate.update(query, id);
    }
}