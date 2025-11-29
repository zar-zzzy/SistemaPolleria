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
            <a href="/contacto">Contacto</a>
            <a href="/publicidad">Publicidad</a>
        </div>
    </div>
</nav>

<main>
    <div class="form-container">
        <h2><c:choose>
            <c:when test="${plato.idPlato != null}">Editar Plato</c:when>
            <c:otherwise>Registro de Platos</c:otherwise>
        </c:choose></h2>
        
        <form action="/platos/guardar" method="post">
            <!-- Campo oculto para el ID (necesario para editar) -->
            <input type="hidden" name="idPlato" value="${plato.idPlato}">
            
            <div class="form-group">
                <label for="nombre">Nombre del Plato:</label>
                <input type="text" 
                       id="nombre" 
                       name="nombre" 
                       value="${plato.nombre}"
                       placeholder="Ej: Pollo a la Brasa, Papas Fritas..." 
                       required>
            </div>

            <div class="form-group">
                <label for="categoria">Categoría:</label>
                <select id="categoria" name="categoria.idCategoria" required>
                    <option value="">Seleccionar categoría</option>
                    <c:forEach items="${categorias}" var="cat">
                        <option value="${cat.idCategoria}" 
                                ${plato.categoria.idCategoria == cat.idCategoria ? 'selected' : ''}>
                            ${cat.nombre}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="precio">Precio de Venta (S/):</label>
                <input type="number" 
                       id="precio" 
                       name="precio" 
                       value="${plato.precio}"
                       min="0" 
                       step="0.01" 
                       placeholder="0.00" 
                       required>
            </div>

            <div class="form-group">
                <label for="descripcion">Descripción (Opcional):</label>
                <textarea id="descripcion" 
                          name="descripcion" 
                          rows="3" 
                          placeholder="Descripción breve del plato...">${plato.descripcion}</textarea>
            </div>

            <div style="display: flex; gap: 10px;">
                <button type="submit" class="btn">
                    <c:choose>
                        <c:when test="${plato.idPlato != null}">Actualizar Plato</c:when>
                        <c:otherwise>Registrar Plato</c:otherwise>
                    </c:choose>
                </button>
                
                <c:if test="${plato.idPlato != null}">
                    <a href="/platos" class="btn" style="background: #6c757d;">Cancelar</a>
                </c:if>
            </div>
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
                <th>Descripción</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty platos}">
                <tr>
                    <td colspan="7" style="text-align: center;">No hay platos registrados</td>
                </tr>
            </c:if>
            <c:forEach items="${platos}" var="p">
                <tr>
                    <td>${p.idPlato}</td>
                    <td>${p.nombre}</td>
                    <td>${p.categoria.nombre}</td>
                    <td>S/. ${p.precio}</td>
                    <td>${p.descripcion}</td>
                    <td>
                        <a href="/platos/editar/${p.idPlato}" class="btn btn-small">Editar</a>
                    </td>
                    <td>
                        <a href="/platos/eliminar/${p.idPlato}" 
                           class="btn btn-small"
                           onclick="return confirm('¿Está seguro de eliminar este plato?');">Eliminar</a>
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