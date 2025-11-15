<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Platos - Pollería</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<header>
    <div class="header-content">
        <h1>Sistema de Ventas - Pollería</h1>
        <p>Gestión completa de inventario y ventas</p>
    </div>
</header>

<nav>
    <div class="nav-container">
        <div class="nav-menu">
            <a href="/">Inicio</a>
            <a href="/insumos">Registro de Insumos</a>
            <a href="/platos">Registro de Platos</a>
            <a href="/ventas">Ventas</a>
            <a href="/reportes">Reportes</a>
            <a href="/contacto">Contacto</a>
            <a href="/publicidad">Publicidad</a>
        </div>
    </div>
</nav>

<main>
    <div class="form-container">
        <h2>Registro de Platos</h2>
        <form id="form-plato">
            <div class="form-group">
                <label for="nombre-plato">Nombre del Plato:</label>
                <input type="text" id="nombre-plato" name="nombre-plato" placeholder="Ej: Pollo a la Brasa, Papas Fritas..." required>
            </div>

            <div class="form-group">
                <label for="categoria">Categoría:</label>
                <select id="categoria" name="categoria" required>
                    <option value="">Seleccionar categoría</option>
                    <option value="Pollo">Pollo</option>
                    <option value="Guarnición">Guarnición</option>
                    <option value="Ensalada">Ensalada</option>
                    <option value="Bebida">Bebida</option>
                </select>
            </div>

            <div class="form-group">
                <label for="precio">Precio de Venta (S/):</label>
                <input type="number" id="precio" name="precio" min="0" step="0.01" placeholder="0.00" required>
            </div>

            <div class="form-group">
                <label for="descripcion">Descripción (Opcional):</label>
                <textarea id="descripcion" name="descripcion" rows="3" placeholder="Descripción breve del plato..."></textarea>
            </div>

            <button type="submit" class="btn">Registrar Plato</button>
        </form>
    </div>

    <div class="table-container">
        <h2>Platos Registrados</h2>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Categoría</th>
                <th>Precio (S/)</th>
                <th>Actualizar</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>P001</td>
                <td>Pollo a la Brasa</td>
                <td>Pollo</td>
                <td>18.00</td>
                <td><button class="btn btn-small">Actualizar</button></td>
                <td><button class="btn btn-small" disabled>Eliminar</button></td>
            </tr>
            <tr>
                <td>P002</td>
                <td>Pollo Broaster</td>
                <td>Pollo</td>
                <td>15.00</td>
                <td><button class="btn btn-small">Actualizar</button></td>
                <td><button class="btn btn-small" disabled>Eliminar</button></td>
            </tr>
            </tbody>
        </table>
    </div>
</main>

<footer>
    <p>Proyecto académico - Pollería</p>
</footer>
<script src="/js/config.js"></script>
<script src="/js/platos.js"></script>
</body>
</html>
