package com.polleria.polleria.Anuncio;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class AnuncioRepository implements AnuncioDAO {

    private final JdbcTemplate jdbcTemplate;

    public AnuncioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Anuncio> anuncioRowMapper = (rs, rowNum) -> {
        return new Anuncio(
            rs.getLong("id"),
            rs.getString("titulo"),
            rs.getString("mensaje"),
            rs.getBoolean("activo"),
            rs.getDate("fecha_inicio").toLocalDate(),
            rs.getDate("fecha_fin").toLocalDate()
        );
    };

    @Override
    public List<Anuncio> findAll() {
        String query = "SELECT id, titulo, mensaje, activo, fecha_inicio, fecha_fin FROM anuncio ORDER BY fecha_inicio DESC";
        return jdbcTemplate.query(query, anuncioRowMapper);
    }

    @Override
    public Optional<Anuncio> findById(Long id) {
        String query = "SELECT id, titulo, mensaje, activo, fecha_inicio, fecha_fin FROM anuncio WHERE id = ?";
        try {
            Anuncio anuncio = jdbcTemplate.queryForObject(query, anuncioRowMapper, id);
            return Optional.ofNullable(anuncio);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Anuncio> findByActivoTrueAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(LocalDate inicio, LocalDate fin) {
        String query = "SELECT id, titulo, mensaje, activo, fecha_inicio, fecha_fin " +
                      "FROM anuncio " +
                      "WHERE activo = true " +
                      "AND fecha_inicio <= ? " +
                      "AND fecha_fin >= ? " +
                      "ORDER BY fecha_inicio DESC";
        return jdbcTemplate.query(query, anuncioRowMapper, Date.valueOf(inicio), Date.valueOf(fin));
    }

    @Override
    public Anuncio save(Anuncio anuncio) {
        String query = "INSERT INTO anuncio (titulo, mensaje, activo, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, anuncio.getTitulo());
            ps.setString(2, anuncio.getMensaje());
            ps.setBoolean(3, anuncio.isActivo());
            ps.setDate(4, Date.valueOf(anuncio.getFechaInicio()));
            ps.setDate(5, Date.valueOf(anuncio.getFechaFin()));
            return ps;
        }, keyHolder);
        
        anuncio.setId(keyHolder.getKey().longValue());
        return anuncio;
    }

    @Override
    public Anuncio update(Anuncio anuncio) {
        String query = "UPDATE anuncio SET titulo = ?, mensaje = ?, activo = ?, fecha_inicio = ?, fecha_fin = ? WHERE id = ?";
        jdbcTemplate.update(query, 
            anuncio.getTitulo(),
            anuncio.getMensaje(),
            anuncio.isActivo(),
            Date.valueOf(anuncio.getFechaInicio()),
            Date.valueOf(anuncio.getFechaFin()),
            anuncio.getId()
        );
        return anuncio;
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM anuncio WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}