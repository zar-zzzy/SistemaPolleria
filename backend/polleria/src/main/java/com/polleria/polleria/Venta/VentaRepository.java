package com.polleria.polleria.Venta;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class VentaRepository implements VentaDAO {

    private final JdbcTemplate jdbcTemplate;

    public VentaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Venta> ventaRowMapper = (rs, rowNum) -> {
        return new Venta(
            rs.getLong("id"),
            rs.getTimestamp("fecha").toLocalDateTime(),
            rs.getDouble("total"),
            rs.getString("cliente"),
            rs.getString("metodo_pago")
        );
    };

    @Override
    public List<Venta> findAll() {
        String query = "SELECT id, fecha, total, cliente, metodo_pago FROM ventas ORDER BY fecha DESC";
        return jdbcTemplate.query(query, ventaRowMapper);
    }

    @Override
    public Optional<Venta> findById(Long id) {
        String query = "SELECT id, fecha, total, cliente, metodo_pago FROM ventas WHERE id = ?";
        try {
            Venta venta = jdbcTemplate.queryForObject(query, ventaRowMapper, id);
            return Optional.ofNullable(venta);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Venta save(Venta venta) {
        String query = "INSERT INTO ventas (fecha, total, cliente, metodo_pago) VALUES (?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(venta.getFecha()));
            ps.setDouble(2, venta.getTotal());
            ps.setString(3, venta.getCliente());
            ps.setString(4, venta.getMetodoPago());
            return ps;
        }, keyHolder);
        
        venta.setId(keyHolder.getKey().longValue());
        return venta;
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM ventas WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public List<Venta> findVentasHoy() {
        String query = "SELECT id, fecha, total, cliente, metodo_pago " +
                      "FROM ventas " +
                      "WHERE CAST(fecha AS DATE) = CURRENT_DATE " +
                      "ORDER BY fecha DESC";
        return jdbcTemplate.query(query, ventaRowMapper);
    }

    @Override
    public List<Venta> findVentasSemana() {
        String query = "SELECT id, fecha, total, cliente, metodo_pago " +
                      "FROM ventas " +
                      "WHERE fecha >= DATEADD('DAY', -7, CURRENT_DATE) " +
                      "ORDER BY fecha DESC";
        return jdbcTemplate.query(query, ventaRowMapper);
    }

    @Override
    public List<Venta> findVentasMes() {
        String query = "SELECT id, fecha, total, cliente, metodo_pago " +
                      "FROM ventas " +
                      "WHERE fecha >= DATEADD('DAY', -30, CURRENT_DATE) " +
                      "ORDER BY fecha DESC";
        return jdbcTemplate.query(query, ventaRowMapper);
    }
}