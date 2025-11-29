package com.polleria.polleria.DetalleVenta;

import com.polleria.polleria.Categoria.Categoria;
import com.polleria.polleria.Plato.Plato;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

// Repository que ejecuta SQL en la tabla 'detalle_venta'
@Repository
public class DetalleVentaRepository implements DetalleVentaDAO {

    private final JdbcTemplate jdbcTemplate; // Para ejecutar SQL

    public DetalleVentaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapea cada fila a un objeto DetalleVenta (incluye plato y categoría)
    private final RowMapper<DetalleVenta> detalleRowMapper = (rs, rowNum) -> {
        DetalleVenta detalle = new DetalleVenta();
        detalle.setId(rs.getLong("id"));
        detalle.setCantidad(rs.getInt("cantidad"));
        detalle.setSubtotal(rs.getDouble("subtotal"));
        detalle.setPrecio(rs.getDouble("precio"));

        Categoria categoria = new Categoria(
                rs.getLong("id_categoria"),
                rs.getString("categoria_nombre"));

        Plato plato = new Plato(
                rs.getLong("id_plato"),
                rs.getString("plato_nombre"),
                rs.getString("plato_descripcion"),
                rs.getDouble("plato_precio"),
                categoria);

        detalle.setPlato(plato);

        return detalle;
    };

    // SELECT detalles de una venta (JOIN con platos y categorias)
    @Override
    public List<DetalleVenta> findByVentaId(Long ventaId) {
        String query = "SELECT dv.id, dv.cantidad, dv.subtotal, dv.precio, " +
                "p.id_plato, p.nombre as plato_nombre, p.descripcion as plato_descripcion, p.precio as plato_precio, " +
                "c.id_categoria, c.nombre as categoria_nombre " +
                "FROM detalle_venta dv " +
                "JOIN platos p ON dv.id_plato = p.id_plato " +
                "JOIN categorias c ON p.id_categoria = c.id_categoria " +
                "WHERE dv.id_venta = ?";
        return jdbcTemplate.query(query, detalleRowMapper, ventaId);
    }

    // INSERT nuevo detalle de venta
    @Override
    public DetalleVenta save(DetalleVenta detalle) {
        String query = "INSERT INTO detalle_venta (id_venta, id_plato, cantidad, precio, subtotal) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, detalle.getVenta().getId()); // ID de la venta
            ps.setLong(2, detalle.getPlato().getIdPlato()); // ID del plato
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecio());
            ps.setDouble(5, detalle.getSubtotal());
            return ps;
        }, keyHolder);

        detalle.setId(keyHolder.getKey().longValue());
        return detalle;
    }

    // DELETE todos los detalles de una venta
    @Override
    public void deleteByVentaId(Long ventaId) {
        String query = "DELETE FROM detalle_venta WHERE id_venta = ?";
        jdbcTemplate.update(query, ventaId);
    }
}