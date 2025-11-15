<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reportes - Sistema de Ventas Pollería</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<header>
    <div class="header-content">
        <h1>Sistema de Ventas - Pollería</h1>
        <p>Reportes y Estadísticas (ADMIN)</p>
    </div>
</header>

<nav>
    <div class="nav-container">
        <div class="nav-menu">
            <a href="/admin/usuarios">Gestionar usuarios</a>
            <a href="/admin/anuncios">Gestionar anuncios</a>
            <a href="/reportes">Ver métricas</a>
        </div>
    </div>
</nav>

<main>
    <div class="form-container">
        <h2>Reportes de Ventas</h2>
        
        <div class="form-group">
            <label for="periodo">Seleccionar Período:</label>
            <select id="periodo" name="periodo" onchange="cambiarPeriodo()">
                <option value="dia">Hoy</option>
                <option value="semana">Esta Semana</option>
                <option value="mes">Este Mes</option>
                <option value="todo">Histórico Completo</option>
            </select>
        </div>

        <div class="stats-grid">
            <div class="stat-card">
                <h3>Total Ventas</h3>
                <p class="stat-value">S/ 1,250.00</p>
            </div>
            <div class="stat-card">
                <h3>Cantidad Ventas</h3>
                <p class="stat-value">47</p>
            </div>
            <div class="stat-card">
                <h3>Ticket Promedio</h3>
                <p class="stat-value">S/ 26.60</p>
            </div>
            <div class="stat-card">
                <h3>Plato Más Vendido</h3>
                <p class="stat-value">Pollo a la Brasa</p>
            </div>
        </div>

        <div class="table-container" style="margin-top: 30px;">
            <h3>Desglose de Ventas</h3>
            <table>
                <thead>
                <tr>
                    <th>Plato</th>
                    <th>Cantidad</th>
                    <th>Total</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Pollo a la Brasa</td>
                    <td>25</td>
                    <td>S/ 450.00</td>
                </tr>
                <tr>
                    <td>Pollo Broaster</td>
                    <td>15</td>
                    <td>S/ 225.00</td>
                </tr>
                <tr>
                    <td>Papas Fritas</td>
                    <td>30</td>
                    <td>S/ 240.00</td>
                </tr>
                <tr>
                    <td>Ensalada Mixta</td>
                    <td>20</td>
                    <td>S/ 120.00</td>
                </tr>
                <tr>
                    <td>Chicha Morada</td>
                    <td>35</td>
                    <td>S/ 105.00</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</main>

<footer>
    <p>Proyecto académico - Pollería</p>
</footer>
<script>
    function cambiarPeriodo() {
        const periodo = document.getElementById('periodo').value;
        console.log('Cambiando a período:', periodo);
        // Aquí se implementaría la lógica para recargar los datos según el período
        alert('Función en desarrollo: Mostrará datos de ' + periodo);
    }
</script>
</body>
</html>
