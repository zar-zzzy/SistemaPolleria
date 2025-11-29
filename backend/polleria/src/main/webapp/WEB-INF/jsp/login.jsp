<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Sistema de Ventas Pollería</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<header>
    <div class="header-content">
        <h1>Sistema de Ventas - Pollería</h1>
        <p>Iniciar Sesión</p>
    </div>
</header>

<main>
    <div class="login-container">
        <h2>Iniciar Sesión</h2>

        <!-- Mensaje de error -->
        <c:if test="${not empty error}">
            <div style="color: red; margin-bottom: 15px; padding: 10px; background: #ffe6e6; border-radius: 5px;">
                <strong>${error}</strong>
            </div>
        </c:if>

        <form action="/login" method="post" class="login-form">
            <div class="form-group">
                <label for="username">Usuario:</label>
                <input type="text" id="username" name="username" placeholder="Ingrese su usuario" required>
            </div>

            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" placeholder="Ingrese su contraseña" required>
            </div>

            <button type="submit" class="btn btn-login">Iniciar Sesión</button>
        </form>

        <div class="login-note">
            <p style="margin-top: 15px; color: #666;">
                <strong>Usuarios de prueba:</strong><br>
                Admin: admin / 123456<br>
                Trabajador: trabajador / 123456
            </p>
        </div>
    </div>
</main>

<footer>
    <p>Proyecto académico - Pollería</p>
</footer>
</body>
</html>
