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
    <form action="ActualizarPerfilServlet.do" method="post">
        <h2>Perfil de Usuario</h2>
        <div class="profile-info">
            <label for="email">Correo:</label>
            <input type="text" id="email"  name="email" value=${user.email} >
        </div>
        <div class="profile-info">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value=${user.name} >
        </div>
        <div class="profile-info">
            <label for="apellido">Apellido:</label>
            <input type="text" id="apellido"  name="apellido" value=${user.surname} >
        </div>
        <button type="submit" class="view-reservations-btn">Actualizar Perfil</button>
        <a href="<c:url value='EliminarUsuarioServlet.do'/>"><button class="view-reservations-btn">Eliminar Cuenta</button></a>                
        </form>
    </div>
</body>
</html>
