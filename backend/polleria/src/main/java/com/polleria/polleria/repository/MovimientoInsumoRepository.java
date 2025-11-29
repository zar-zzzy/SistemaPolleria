package com.polleria.polleria.repository;

import com.polleria.polleria.entity.Insumo;
import com.polleria.polleria.entity.MovimientoInsumo;
import com.polleria.polleria.entity.TipoMovimiento;
import com.polleria.polleria.repository.Dao.MovimientoInsumoDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class MovimientoInsumoRepository implements MovimientoInsumoDAO {

    private final JdbcTemplate jdbcTemplate;

    public MovimientoInsumoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<MovimientoInsumo> movimientoRowMapper = (rs, rowNum) -> {
        MovimientoInsumo movimiento = new MovimientoInsumo();
        movimiento.setId(rs.getLong("id_movimiento"));
        
        Insumo insumo = new Insumo();
        insumo.setId(rs.getLong("insumo_id"));
        insumo.setNombre(rs.getString("insumo_nombre"));
        movimiento.setInsumo(insumo);
        
        movimiento.setTipo(TipoMovimiento.valueOf(rs.getString("tipo_movimiento")));
        movimiento.setCantidad(rs.getDouble("cantidad"));
        movimiento.setMotivo(rs.getString("descripcion"));
        movimiento.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
        
        return movimiento;
    };

    @Override
    public List<MovimientoInsumo> findAll() {
        String query = "SELECT m.id_movimiento, m.id_insumo as insumo_id, i.nombre as insumo_nombre, " +
                      "m.tipo_movimiento, m.cantidad, m.descripcion, m.fecha " +
                      "FROM movimiento_insumo m " +
                      "JOIN insumos i ON m.id_insumo = i.id " +
                      "ORDER BY m.fecha DESC";
        return jdbcTemplate.query(query, movimientoRowMapper);
    }

    @Override
    public List<MovimientoInsumo> findTop10ByOrderByFechaDesc() {
        String query = "SELECT m.id_movimiento, m.id_insumo as insumo_id, i.nombre as insumo_nombre, " +
                      "m.tipo_movimiento, m.cantidad, m.descripcion, m.fecha " +
                      "FROM movimiento_insumo m " +
                      "JOIN insumos i ON m.id_insumo = i.id " +
                      "ORDER BY m.fecha DESC " +
                      "LIMIT 10";
        return jdbcTemplate.query(query, movimientoRowMapper);
    }

    @Override
    public List<MovimientoInsumo> findByInsumoId(Long insumoId) {
        String query = "SELECT m.id_movimiento, m.id_insumo as insumo_id, i.nombre as insumo_nombre, " +
                      "m.tipo_movimiento, m.cantidad, m.descripcion, m.fecha " +
                      "FROM movimiento_insumo m " +
                      "JOIN insumos i ON m.id_insumo = i.id " +
                      "WHERE m.id_insumo = ? " +
                      "ORDER BY m.fecha DESC";
        return jdbcTemplate.query(query, movimientoRowMapper, insumoId);
    }

    @Override
    public MovimientoInsumo save(MovimientoInsumo movimiento) {
        String query = "INSERT INTO movimiento_insumo (id_insumo, tipo_movimiento, cantidad, descripcion, fecha) " +
                      "VALUES (?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, movimiento.getInsumo().getId());
            ps.setString(2, movimiento.getTipo().name());
            ps.setDouble(3, movimiento.getCantidad());
            ps.setString(4, movimiento.getMotivo());
            ps.setTimestamp(5, Timestamp.valueOf(movimiento.getFecha()));
            return ps;
        }, keyHolder);
        
        movimiento.setId(keyHolder.getKey().longValue());
        return movimiento;
    }
}