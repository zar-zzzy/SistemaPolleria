<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Ventas - Pollería</title>
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
            <a href="/contacto">Contacto</a>
            <a href="/anuncios">Publicidad</a>
        </div>
    </div>
</nav>

<main>
    <div class="form-container">
        <h2>Registro de Ventas</h2>
        <form action="/ventas/guardar" method="post" onsubmit="return validarFormulario()">
            <div class="form-group">
                <label for="cliente">Nombre del Cliente (Opcional):</label>
                <input type="text" id="cliente" name="cliente" placeholder="Nombre completo del cliente">
            </div>

            <div class="form-group">
                <label for="idPlato">Seleccionar Plato:</label>
                <select id="idPlato" name="idPlato" required onchange="calcularTotal()">
                    <option value="">Seleccionar plato</option>
                    <c:forEach items="${platos}" var="plato">
                        <option value="${plato.idPlato}" data-precio="${plato.precio}">
                            ${plato.nombre} - S/ ${plato.precio}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="cantidad">Cantidad:</label>
                <input type="number" id="cantidad" name="cantidad" min="1" value="1" required onchange="calcularTotal()">
            </div>

            <div class="form-group">
                <label for="total">Total a Pagar:</label>
                <input type="text" id="total" name="total" placeholder="S/ 0.00" readonly style="background:#f5f5f5; font-weight:bold;">
            </div>

            <div class="form-group">
                <label for="metodoPago">Método de Pago:</label>
                <select id="metodoPago" name="metodoPago" required>
                    <option value="">Seleccionar método</option>
                    <option value="Efectivo">Efectivo</option>
                    <option value="Tarjeta">Tarjeta</option>
                    <option value="Yape">Yape</option>
                    <option value="Plin">Plin</option>
                </select>
            </div>

            <button type="submit" class="btn">Registrar Venta</button>
        </form>
    </div>

    <div class="table-container">
        <h2>Historial de Ventas</h2>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Cliente</th>
                <th>Plato</th>
                <th>Cantidad</th>
                <th>Precio Unit.</th>
                <th>Total</th>
                <th>Método Pago</th>
                <th>Fecha</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty ventas}">
                <tr>
                    <td colspan="9" style="text-align: center;">No hay ventas registradas</td>
                </tr>
            </c:if>
            <c:forEach items="${ventas}" var="venta">
                <c:forEach items="${venta.detalles}" var="detalle" varStatus="status">
                    <tr>
                        <c:if test="${status.first}">
                            <td rowspan="${venta.detalles.size()}">${venta.id}</td>
                            <td rowspan="${venta.detalles.size()}">${venta.cliente}</td>
                        </c:if>
                        <td>${detalle.plato.nombre}</td>
                        <td>${detalle.cantidad}</td>
                        <td>S/ ${detalle.precio}</td>
                        <c:if test="${status.first}">
                            <td rowspan="${venta.detalles.size()}" style="font-weight: bold;">S/ ${venta.total}</td>
                            <td rowspan="${venta.detalles.size()}">${venta.metodoPago}</td>
                            <td rowspan="${venta.detalles.size()}">
                                <c:set var="formatter" value="<%= DateTimeFormatter.ofPattern(\"dd/MM/yyyy HH:mm\") %>" />
                                ${venta.fecha.format(formatter)}
                            </td>
                            <td rowspan="${venta.detalles.size()}">
                                <a href="/ventas/eliminar/${venta.id}" 
                                   class="btn btn-small"
                                   onclick="return confirm('¿Está seguro de eliminar esta venta?');">
                                    Eliminar
                                </a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>

<footer>
    <p>Proyecto académico - Pollería</p>
</footer>

<script>
function calcularTotal() {
    const select = document.getElementById('idPlato');
    const cantidad = document.getElementById('cantidad').value;
    const totalInput = document.getElementById('total');
    
    if (select.value && cantidad) {
        const precio = select.options[select.selectedIndex].getAttribute('data-precio');
        const total = (precio * cantidad).toFixed(2);
        totalInput.value = 'S/ ' + total;
    } else {
        totalInput.value = 'S/ 0.00';
    }
}

function validarFormulario() {
    const plato = document.getElementById('idPlato').value;
    const metodoPago = document.getElementById('metodoPago').value;
    
    if (!plato) {
        alert('Por favor selecciona un plato');
        return false;
    }
    
    if (!metodoPago) {
        alert('Por favor selecciona un método de pago');
        return false;
    }
    
    return true;
}
</script>
</body>
</html>