<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <a href="/admin/usuarios">Gestionar Usuarios</a>
            <a href="/admin/anuncios">Gestionar Anuncios</a>
            <a href="/reportes">Reportes</a>
        </div>
    </div>
</nav>

<main>
    <div class="form-container">
        <h2><c:choose>
            <c:when test="${anuncio.id != null}">Editar Anuncio</c:when>
            <c:otherwise>Crear Nuevo Anuncio</c:otherwise>
        </c:choose></h2>
        
        <form action="/admin/anuncios/guardar" method="post">
            <input type="hidden" name="id" value="${anuncio.id}">
            
            <div class="form-group">
                <label for="titulo">Título del Anuncio:</label>
                <input type="text" 
                       id="titulo" 
                       name="titulo" 
                       value="${anuncio.titulo}"
                       placeholder="Título del anuncio" 
                       required>
            </div>

            <div class="form-group">
                <label for="mensaje">Mensaje:</label>
                <textarea id="mensaje" 
                          name="mensaje" 
                          rows="4" 
                          placeholder="Contenido del anuncio..." 
                          required>${anuncio.mensaje}</textarea>
            </div>

            <div class="form-group">
                <label>
                    <input type="checkbox" 
                           id="activo" 
                           name="activo" 
                           value="true" 
                           ${anuncio.activo || anuncio.id == null ? 'checked' : ''}>
                    Anuncio Activo
                </label>
            </div>

            <div class="form-group">
                <label for="fechaInicio">Fecha Inicio:</label>
                <input type="date" 
                       id="fechaInicio" 
                       name="fechaInicio" 
                       value="${anuncio.fechaInicio}"
                       required>
            </div>

            <div class="form-group">
                <label for="fechaFin">Fecha Fin:</label>
                <input type="date" 
                       id="fechaFin" 
                       name="fechaFin" 
                       value="${anuncio.fechaFin}"
                       required>
            </div>

            <div style="display: flex; gap: 10px;">
                <button type="submit" class="btn">
                    <c:choose>
                        <c:when test="${anuncio.id != null}">Actualizar Anuncio</c:when>
                        <c:otherwise>Guardar Anuncio</c:otherwise>
                    </c:choose>
                </button>
                
                <c:if test="${anuncio.id != null}">
                    <a href="/admin/anuncios" class="btn" style="background: #6c757d;">Cancelar</a>
                </c:if>
            </div>
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
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty anuncios}">
                <tr>
                    <td colspan="8" style="text-align: center;">No hay anuncios registrados</td>
                </tr>
            </c:if>
            <c:forEach items="${anuncios}" var="anun">
                <tr>
                    <td>${anun.id}</td>
                    <td>${anun.titulo}</td>
                    <td style="max-width: 300px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
                        ${anun.mensaje}
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${anun.activo}">
                                <span style="color: green; font-weight: bold;">✓ Sí</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: red; font-weight: bold;">✗ No</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${anun.fechaInicio}</td>
                    <td>${anun.fechaFin}</td>
                    <td>
                        <a href="/admin/anuncios/editar/${anun.id}" class="btn btn-small">Editar</a>
                    </td>
                    <td>
                        <a href="/admin/anuncios/eliminar/${anun.id}"
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