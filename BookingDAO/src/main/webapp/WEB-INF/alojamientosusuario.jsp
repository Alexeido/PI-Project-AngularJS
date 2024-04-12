<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mis alojamientos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reservas.css">
</head>
<body>

    <header>
        <div class="left-content">
            <h1>Booking.com</h1>
        </div>
        <div class="right-content">
            <div class="right-icons">
                <img src="recursos/idioma.jpg" alt="Idiomas" width="30" height="30">
                <img src="recursos/ayuda.jpg" alt="Ayuda" width="30" height="30">
            </div>
        </div>
    </header>

    <div class="container">
        <h1>Alojamientos subidos</h1>
        <div class="help-message">
            <p>¿No encuentra un alojamiento?</p>
        </div>
        <div class="Elemento-contatiner">
        <c:forEach var="alojamientouser" items="${listaalojamiento}">
        
            <div class="c2_alojamiento">
                <div class="Elemento-c2">
                    <div class="foto-c2">
                        <img src="recursos/alojamiento1.png" alt="Google Maps">
                    </div>

                    <div class="Texto-c2">
                        <div class="location">
                            <h1>Madrid</h1>
                            </div>
                        <h2>Hospedería del Valle Boutique Apartments </h2>
                    </div>
                    <div class="datos">
                        <button>Ver habitaciones</button>
                        <button>Editar alojamiento</button>
                        <button class="eliminar">Eliminar Alojamiento</button> 
                    </div>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</body>
</html>
