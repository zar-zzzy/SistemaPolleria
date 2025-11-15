<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Insumos - Pollería</title>
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
        <h2>Registro de Insumos</h2>
        <form action="/insumos/guardar" method="post">
            <div class="form-group">
                <label for="nombre">Nombre del Insumo:</label>
                <input type="text" id="nombre" name="nombre" placeholder="Ej: Pollo, Papas, Aceite..." required>
            </div>

            <div class="form-group">
                <label for="unidadMedida">Unidad de Medida:</label>
                <select id="unidadMedida" name="unidadMedida" required>
                    <option value="">Seleccionar unidad</option>
                    <option value="kg">Kilogramos (kg)</option>
                    <option value="g">Gramos (g)</option>
                    <option value="l">Litros (l)</option>
                    <option value="ml">Mililitros (ml)</option>
                    <option value="unidad">Unidad</option>
                    <option value="paquete">Paquete</option>
                    <option value="caja">Caja</option>
                </select>
            </div>

            <div class="form-group">
                <label for="stock">Cantidad Inicial:</label>
                <input type="number" id="stock" name="stock" min="0" step="0.01" placeholder="0.00" required>
            </div>

            <button type="submit" class="btn">Registrar Insumo</button>
        </form>
    </div>

    <div class="table-container">
        <h2>Stock Actual de Insumos</h2>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Unidad</th>
                <th>Cantidad</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty insumos}">
                <tr>
                    <td colspan="6" style="text-align: center;">No hay insumos registrados</td>
                </tr>
            </c:if>
            <c:forEach items="${insumos}" var="ins">
                <tr>
                    <td>${ins.id}</td>
                    <td>${ins.nombre}</td>
                    <td>${ins.unidadMedida}</td>
                    <td>${ins.stock}</td>
                    <td>
                        <a href="/insumos/editar/${ins.id}" class="btn btn-small">Editar</a>
                    </td>
                    <td>
                        <a href="/insumos/eliminar/${ins.id}"
                           class="btn btn-small"
                           onclick="return confirm('¿Está seguro de eliminar este insumo?');">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Tabla de Registro de Movimientos -->
    <div class="table-container" style="margin-top: 40px;">
        <h2>Registro de Movimientos (Últimos 10)</h2>
        <table>
            <thead>
            <tr>
                <th>Fecha</th>
                <th>Insumo</th>
                <th>Tipo</th>
                <th>Cantidad</th>
                <th>Motivo</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty movimientos}">
                <tr>
                    <td colspan="5" style="text-align: center;">No hay movimientos registrados</td>
                </tr>
            </c:if>
            <c:forEach items="${movimientos}" var="mov">
                <tr>
                    <td><fmt:formatDate value="${mov.fecha}" pattern="dd/MM/yyyy HH:mm"/></td>
                    <td>${mov.insumo.nombre}</td>
                    <td>
                        <c:choose>
                            <c:when test="${mov.tipo.name() == 'ENTRADA'}">
                                <span style="color: green; font-weight: bold;">${mov.tipo}</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: red; font-weight: bold;">${mov.tipo}</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${mov.cantidad}</td>
                    <td>${mov.motivo}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>

<footer>
    <p>Proyecto académico - Pollería</p>
</footer>
</body>
</html>
