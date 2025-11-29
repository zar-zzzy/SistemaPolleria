<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reportes de Ventas - Pollería</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<header>
    <div class="header-content">
        <h1>Sistema de Ventas - Pollería</h1>
        <p>Reportes y Estadísticas</p>
    </div>
</header>

<nav>
    <div class="nav-container">
        <div class="nav-menu">
            <a href="/admin/usuarios">Gestionar Usuarios</a>
            <a href="/admin/anuncios">Gestionar Anuncios</a>
            <a href="/reportes">Reportes</a>
        </div>
    </div>
</nav>

<main>
    <div class="form-container">
        <h2>Reportes de Ventas</h2>
        
        <div class="form-group">
            <label for="periodo">Seleccionar Período:</label>
            <select id="periodo" onchange="cambiarPeriodo(this.value)">
                <option value="hoy" ${periodo == 'hoy' ? 'selected' : ''}>Hoy</option>
                <option value="semana" ${periodo == 'semana' ? 'selected' : ''}>Esta Semana</option>
                <option value="mes" ${periodo == 'mes' ? 'selected' : ''}>Este Mes</option>
            </select>
        </div>

        <div style="display: grid; grid-template-columns: repeat(2, 1fr); gap: 20px; margin: 30px 0;">
            <div style="background: #fff; padding: 20px; border-radius: 8px; border: 2px solid #27ae60;">
                <h3 style="margin: 0 0 10px 0; color: #27ae60;">Total Ventas</h3>
                <p style="font-size: 32px; font-weight: bold; margin: 0;">S/ ${totalVentas}</p>
            </div>

            <div style="background: #fff; padding: 20px; border-radius: 8px; border: 2px solid #3498db;">
                <h3 style="margin: 0 0 10px 0; color: #3498db;">Cantidad Ventas</h3>
                <p style="font-size: 32px; font-weight: bold; margin: 0;">${cantidadVentas}</p>
            </div>

            <div style="background: #fff; padding: 20px; border-radius: 8px; border: 2px solid #e67e22;">
                <h3 style="margin: 0 0 10px 0; color: #e67e22;">Ticket Promedio</h3>
                <p style="font-size: 32px; font-weight: bold; margin: 0;">S/ ${String.format("%.2f", ticketPromedio)}</p>
            </div>

            <div style="background: #fff; padding: 20px; border-radius: 8px; border: 2px solid #9b59b6;">
                <h3 style="margin: 0 0 10px 0; color: #9b59b6;">Plato Más Vendido</h3>
                <p style="font-size: 24px; font-weight: bold; margin: 0;">${platoMasVendido}</p>
            </div>
        </div>
    </div>

    <div class="table-container">
        <h2>Desglose de Ventas</h2>
        <table>
            <thead>
            <tr>
                <th>Plato</th>
                <th>Cantidad</th>
                <th>Total</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty desglose}">
                <tr>
                    <td colspan="3" style="text-align: center;">No hay ventas en este período</td>
                </tr>
            </c:if>
            <c:forEach items="${desglose}" var="entry">
                <tr>
                    <td>${entry.key}</td>
                    <td>${entry.value.cantidad}</td>
                    <td>S/ ${String.format("%.2f", entry.value.total)}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>

<footer>
    <p>Proyecto académico - Pollería</p>
</footer>

<script>
function cambiarPeriodo(periodo) {
    window.location.href = '/reportes?periodo=' + periodo;
}
</script>
</body>
</html>