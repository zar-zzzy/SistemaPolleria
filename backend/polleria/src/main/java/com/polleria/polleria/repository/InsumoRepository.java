package com.polleria.polleria.repository;

import com.polleria.polleria.entity.Insumo;
import com.polleria.polleria.repository.dao.InsumoDAO;
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
public class InsumoRepository implements InsumoDAO {

    private final JdbcTemplate jdbcTemplate;

    public InsumoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Insumo> insumoRowMapper = (rs, rowNum) -> {
        return new Insumo(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("unidad_medida"),
                rs.getDouble("stock"));
    };

    @Override
    public List<Insumo> findAll() {
        String query = "SELECT id, nombre, unidad_medida, stock FROM insumos ORDER BY id";
        return jdbcTemplate.query(query, insumoRowMapper);
    }

    @Override
    public Optional<Insumo> findById(Long id) {
        String query = "SELECT id, nombre, unidad_medida, stock FROM insumos WHERE id = ?";
        try {
            Insumo insumo = jdbcTemplate.queryForObject(query, insumoRowMapper, id);
            return Optional.ofNullable(insumo);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Insumo save(Insumo insumo) {
        String query = "INSERT INTO insumos (nombre, unidad_medida, stock) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, insumo.getNombre());
            ps.setString(2, insumo.getUnidadMedida());
            ps.setDouble(3, insumo.getStock());
            return ps;
        }, keyHolder);

        insumo.setId(keyHolder.getKey().longValue());
        return insumo;
    }

    @Override
    public Insumo update(Insumo insumo) {
        String query = "UPDATE insumos SET nombre = ?, unidad_medida = ?, stock = ? WHERE id = ?";

        jdbcTemplate.update(query,
                insumo.getNombre(),
                insumo.getUnidadMedida(),
                insumo.getStock(),
                insumo.getId());

        return insumo;
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM insumos WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public int count() {
        String query = "SELECT COUNT(*) FROM insumos";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}