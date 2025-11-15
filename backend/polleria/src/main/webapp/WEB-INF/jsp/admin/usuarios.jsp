<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Usuarios - Sistema de Ventas Pollería</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<header>
    <div class="header-content">
        <h1>Sistema de Ventas - Pollería</h1>
        <p>Panel de Administración - Usuarios</p>
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
        <h2>Crear Nuevo Usuario</h2>
        <form action="/admin/usuarios/guardar" method="post">
            <div class="form-group">
                <label for="username">Usuario:</label>
                <input type="text" id="username" name="username" placeholder="Nombre de usuario" required>
            </div>

            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" placeholder="Contraseña" required>
            </div>

            <div class="form-group">
                <label for="rol">Rol:</label>
                <select id="rol" name="rol" required>
                    <option value="">Seleccionar rol</option>
                    <option value="ADMIN">Administrador</option>
                    <option value="TRABAJADOR">Trabajador</option>
                </select>
            </div>

            <button type="submit" class="btn">Guardar Usuario</button>
        </form>
    </div>

    <div class="table-container">
        <h2>Usuarios Registrados</h2>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Usuario</th>
                <th>Rol</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty usuarios}">
                <tr>
                    <td colspan="4" style="text-align: center;">No hay usuarios registrados</td>
                </tr>
            </c:if>
            <c:forEach items="${usuarios}" var="u">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.username}</td>
                    <td>${u.rol}</td>
                    <td>
                        <a href="/admin/usuarios/eliminar/${u.id}"
                           class="btn btn-small"
                           onclick="return confirm('¿Está seguro de eliminar este usuario?');">
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
