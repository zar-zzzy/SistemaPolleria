<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Ventas - Pollería</title>
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
            <!-- Menú TRABAJADOR -->
            <c:if test="${sessionScope.rol == 'TRABAJADOR'}">
                <a href="/">Inicio</a>
                <a href="/insumos">Registro de Insumos</a>
                <a href="/platos">Registro de Platos</a>
                <a href="/ventas">Ventas</a>
                <a href="/contacto">Contacto</a>
                <a href="/anuncios">Publicidad</a>
            </c:if>

            <!-- Menú ADMIN -->
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
        <h2>Bienvenido al Sistema de Ventas</h2>
        <p>Este sistema te permite gestionar de manera eficiente:</p>

        <div style="margin-top: 2rem;">
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 1.5rem;">
                <div style="background: #f8f9fa; padding: 1.5rem; border-radius: 8px; border-left: 4px solid #4a7c59;">
                    <h3 style="color: #2c5530; margin-bottom: 0.5rem;">📦 Insumos</h3>
                    <p>Registra y controla el stock de ingredientes y materiales necesarios para la preparación de platos.</p>
                </div>

                <div style="background: #f8f9fa; padding: 1.5rem; border-radius: 8px; border-left: 4px solid #4a7c59;">
                    <h3 style="color: #2c5530; margin-bottom: 0.5rem;">🍗 Platos</h3>
                    <p>Define los platos del menú con sus ingredientes y precios para facilitar las ventas.</p>
                </div>

                <div style="background: #f8f9fa; padding: 1.5rem; border-radius: 8px; border-left: 4px solid #4a7c59;">
                    <h3 style="color: #2c5530; margin-bottom: 0.5rem;">💰 Ventas</h3>
                    <p>Registra las ventas realizadas y mantén un historial completo de transacciones.</p>
                </div>

                <div style="background: #f8f9fa; padding: 1.5rem; border-radius: 8px; border-left: 4px solid #4a7c59;">
                    <h3 style="color: #2c5530; margin-bottom: 0.5rem;">📊 Reportes</h3>
                    <p>Consulta el estado del inventario y analiza el historial de ventas.</p>
                </div>
            </div>
        </div>

        <div style="text-align: center; margin-top: 2rem;">
            <p style="font-size: 1.1rem; color: #666;">
                Utiliza el menú de navegación superior para acceder a cada sección del sistema.
            </p>
        </div>
    </div>
</main>

<footer>
    <p>Proyecto académico - Pollería</p>
</footer>
</body>
</html>
