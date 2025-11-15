<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Anuncios - Sistema de Ventas Pollería</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<header>
    <div class="header-content">
        <h1>Sistema de Ventas - Pollería</h1>
        <p>Panel de Administración - Anuncios</p>
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
        <h2>Crear Nuevo Anuncio</h2>
        <form action="/admin/anuncios/guardar" method="post">
            <div class="form-group">
                <label for="titulo">Título del Anuncio:</label>
                <input type="text" id="titulo" name="titulo" placeholder="Título del anuncio" required>
            </div>

            <div class="form-group">
                <label for="mensaje">Mensaje:</label>
                <textarea id="mensaje" name="mensaje" rows="4" placeholder="Contenido del anuncio..." required></textarea>
            </div>

            <div class="form-group">
                <label>
                    <input type="checkbox" id="activo" name="activo" value="true" checked>
                    Anuncio Activo
                </label>
            </div>

            <div class="form-group">
                <label for="fechaInicio">Fecha Inicio:</label>
                <input type="date" id="fechaInicio" name="fechaInicio" required>
            </div>

            <div class="form-group">
                <label for="fechaFin">Fecha Fin:</label>
                <input type="date" id="fechaFin" name="fechaFin" required>
            </div>

            <button type="submit" class="btn">Guardar Anuncio</button>
        </form>
    </div>

    <div class="table-container">
        <h2>Anuncios Registrados</h2>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Mensaje</th>
                <th>Activo</th>
                <th>Fecha Inicio</th>
                <th>Fecha Fin</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty anuncios}">
                <tr>
                    <td colspan="7" style="text-align: center;">No hay anuncios registrados</td>
                </tr>
            </c:if>
            <c:forEach items="${anuncios}" var="anuncio">
                <tr>
                    <td>${anuncio.id}</td>
                    <td>${anuncio.titulo}</td>
                    <td style="max-width: 300px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
                        ${anuncio.mensaje}
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${anuncio.activo}">
                                <span style="color: green; font-weight: bold;">Sí</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: red; font-weight: bold;">No</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><fmt:formatDate value="${anuncio.fechaInicio}" pattern="dd/MM/yyyy"/></td>
                    <td><fmt:formatDate value="${anuncio.fechaFin}" pattern="dd/MM/yyyy"/></td>
                    <td>
                        <a href="/admin/anuncios/eliminar/${anuncio.id}"
                           class="btn btn-small"
                           onclick="return confirm('¿Está seguro de eliminar este anuncio?');">
                            Eliminar
                        </a>
                    </td>
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
