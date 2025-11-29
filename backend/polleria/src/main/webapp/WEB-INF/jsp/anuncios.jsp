<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Anuncios - Sistema de Ventas Pollería</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<header>
    <div class="header-content">
        <h1>Sistema de Ventas - Pollería</h1>
        <p>Anuncios y Comunicados Internos</p>
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
        <h2>Anuncios Activos para Trabajadores</h2>

        <c:if test="${empty anuncios}">
            <div style="text-align: center; padding: 40px; background: #f5f5f5; border-radius: 8px;">
                <p style="font-size: 18px; color: #666;">No hay anuncios activos en este momento</p>
            </div>
        </c:if>

        <c:forEach items="${anuncios}" var="anuncio">
            <div class="anuncio-card" style="background: #fff; border: 2px solid #e74c3c; border-radius: 8px; padding: 20px; margin-bottom: 20px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                <h3 style="color: #e74c3c; margin-bottom: 10px;">
                    📢 ${anuncio.titulo}
                </h3>
                <p style="font-size: 16px; color: #333; line-height: 1.6; margin-bottom: 15px;">
                    ${anuncio.mensaje}
                </p>
                <div style="font-size: 14px; color: #666; border-top: 1px solid #eee; padding-top: 10px;">
                    <strong>Vigencia:</strong>
                    <fmt:formatDate value="${anuncio.fechaInicio}" pattern="dd/MM/yyyy"/> -
                    <fmt:formatDate value="${anuncio.fechaFin}" pattern="dd/MM/yyyy"/>
                </div>
            </div>
        </c:forEach>
    </div>
</main>

<footer>
    <p>Proyecto académico - Pollería</p>
</footer>
</body>
</html>
