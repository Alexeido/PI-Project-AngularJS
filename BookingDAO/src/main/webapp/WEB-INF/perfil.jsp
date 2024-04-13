<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil de Usuario</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/perfil.css">
    
</head>
<body>
    <header>
        <div class="left-content">
            <button class="start_button"><a href="<c:url value='IndexLinkServlet.do'/>">Booking.com</a></button>
        </div>
        <div class="right-content">
            <div class="right-icons">
                <img src="recursos/idioma.jpg" alt="Idiomas" width="30" height="30">
                <img src="recursos/ayuda.jpg" alt="Ayuda" width="30" height="30">
            </div>
        </div>
    </header>

    <div class="profile-container">
        <h2>Perfil de Usuario</h2>
        <div class="profile-info">
            <label for="email">Correo:</label>
            <input type="text" id="email" value=${user.email} readonly>
        </div>
        <div class="profile-info">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" value=${user.name} readonly>
        </div>
        <div class="profile-info">
            <label for="apellido">Apellido:</label>
            <input type="text" id="apellido" value=${user.surname} readonly>
        </div>
        <button class="view-reservations-btn">Ver Reservas</button>
        <a href="<c:url value='CerrarSesionServlet.do'/>"><button class="view-reservations-btn">Cerrar Sesion</button></a>
        <a href="<c:url value='EliminarUsuarioServlet.do'/>"><button class="view-reservations-btn">Eliminar Cuenta</button></a>
        <a href="<c:url value='AlojamientoUsuarioLinkServlet.do'/>"><button class="view-reservations-btn">Ver mis alojamientos</button></a>
        
    </div>
</body>
</html>
