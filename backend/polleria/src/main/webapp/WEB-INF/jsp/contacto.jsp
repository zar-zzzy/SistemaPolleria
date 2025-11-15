<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contacto - Sistema de Ventas Pollería</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<header>
    <div class="header-content">
        <h1>Sistema de Ventas - Pollería</h1>
        <p>Contacto de Superiores</p>
    </div>
</header>

<nav>
    <div class="nav-container">
        <div class="nav-menu">
            <c:if test="${sessionScope.rol == 'TRABAJADOR'}">
                <a href="/">Inicio</a>
                <a href="/insumos">Registro de Insumos</a>
                <a href="/platos">Registro de Platos</a>
                <a href="/ventas">Ventas</a>
                <a href="/reportes-dia">Reportes</a>
                <a href="/contacto">Contacto</a>
                <a href="/anuncios">Publicidad</a>
            </c:if>

            <c:if test="${sessionScope.rol == 'ADMIN'}">
                <a href="/admin/usuarios">Gestionar usuarios</a>
                <a href="/admin/anuncios">Gestionar anuncios</a>
                <a href="/reportes">Ver métricas</a>
            </c:if>
        </div>
    </div>
</nav>

<main>
    <div class="form-container">
        <h2>Contactos de Superiores</h2>
        <table>
            <thead>
            <tr>
                <th>Cargo</th>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Teléfono</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Administrador General</td>
                <td>María López</td>
                <td>maria.lopez@polleria.com</td>
                <td>987-654-321</td>
            </tr>
            <tr>
                <td>Jefe de Almacén</td>
                <td>Carlos Pérez</td>
                <td>carlos.perez@polleria.com</td>
                <td>988-123-456</td>
            </tr>
            <tr>
                <td>Jefe de Ventas</td>
                <td>Lucía Torres</td>
                <td>lucia.torres@polleria.com</td>
                <td>989-321-654</td>
            </tr>
            </tbody>
        </table>
    </div>
</main>

<footer>
    <p>Proyecto académico - Pollería</p>
</footer>
</body>
</html>
