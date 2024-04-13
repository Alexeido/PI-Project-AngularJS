<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrito</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrito.css">
</head>
<body>
    <header>
        <nav class="principal">
            <button class="start_button"><a href="<c:url value='IndexLinkServlet.do'/>">Booking.com</a></button>
            <div class="right-section">
                <button>EUR</button>
                <button class="image-button"><img src="${pageContext.request.contextPath}/images/es.png" alt="Language"></button>
                <button class="image-button"><img src="${pageContext.request.contextPath}/images/questionmarkW.png" alt="Ayuda"></button>
                <c:choose>
            		<c:when test="${not empty user.id}">
            			<button class="white-button"><a href="<c:url value='UsuarioLinkServlet.do'/>">Ver Perfil</a></button>
            		    <button><a href="<c:url value='registroAlojamientoLinkServlet.do'/>">Registra tu alojamiento</a></button>
            		
            		</c:when>
            	<c:otherwise>
            		
                	<button class="white-button"><a href="<c:url value='RegistrarseLinkServlet.do'/>">Hazte una cuenta</a></button>
                	<button class="white-button"><a href="<c:url value='IniciarSesionLinkServlet.do'/>">Inicia sesión</a></button>
               	</c:otherwise>
               	</c:choose>
            </div>
        </nav>

        <nav class="secondary">
            <button><img src="${pageContext.request.contextPath}/images/Bed.svg" alt="Alojamiento"><span>Alojamiento</span></button>
            <button><img src="${pageContext.request.contextPath}/images/avion.svg" alt="Vuelos"><span>Vuelos</span></button>
            <button><img src="${pageContext.request.contextPath}/images/vuelo+hotel.svg" alt="Vuelo + Hotel"><span>Vuelo + Hotel</span></button>
            <button><img src="${pageContext.request.contextPath}/images/alquilercoche.svg" alt="Alquiler de coches"><span>Alquiler de coches</span></button>
            <button><img src="${pageContext.request.contextPath}/images/ferris-wheel-svgrepo-com.svg" alt="Atracciones turísticas"><span>Atracciones turísticas</span></button>
            <button><img src="${pageContext.request.contextPath}/images/taxi.png" alt="Taxis aeropuerto"><span>Taxis aeropuerto</span></button>
        </nav>
    </header>
    <div class="container">

        <div class="carrito">
            <div class="reserva">
                <div class="hotel">
                    <p class="hotel-name">Ambassador Playa I</p>
                    <p class="hotel-address">Gerona, 41, 03503 Benidorm, España</p>
                    <p class="hotel-rating">Excelente ubicación - 9.2</p>
                    <div class="rating-box">
                        <p class="rating-note">9.1</p>
                        <p class="rating-text">Fantástico</p>
                        <p class="rating-comments">3823 Comentarios</p>
                    </div>
                    <div class="amenities">
                        <p><img src="${pageContext.request.contextPath}/images/wifi.svg" alt="Icono de WiFi">WiFi gratis</p>
                        <p><img src="${pageContext.request.contextPath}/images/spa.svg" alt="Icono de spa">Spa y centro de bienestar</p>
                        <p><img src="${pageContext.request.contextPath}/images/dog.svg" alt="Icono de perro">Permite mascotas</p>
                    </div>
                </div>
            
                <div class="fecha">
                    <p class="reserva-title">Los datos de tu reserva</p>
                    <div class="inout">
                        <div class="entrada">
                            <p class="entrada-title">Entrada</p>
                            <p class="entrada-date">mar, 12 mar 2024</p>
                            <p class="entrada-time">15:00 - 23:00</p>
                        </div>
                        <div class="salida">
                            <p class="salida-title">Salida</p>
                            <p class="salida-date">mié, 13 mar 2024</p>
                            <p class="salida-time">A 11:00</p>
                        </div>
                    </div>
                    <p class="duracion">Duración total de la estancia: 1 noche</p>
                    <hr class="separator">
                    <p class="seleccionado">Has seleccionado: <span class="bold">1 Habitación para 2 adultos</span></p>
                    <p class="cambiar">Cambia tu selección</p>
                </div>
                
                <div class="precio">
                    <p class="precio-title">Desglose del precio</p>
                    <div class="total">
                        <p class="precio-max">Total</p>
                        <div class="right">
                            <p class="precio-max">€ 59,06</p>
                            <p class="subtext">Incluye impuestos y cargos</p>
                        </div>
                    </div>
                    <p class="precio-title">Información sobre el precio</p>
                    <p class="subtext">Incluye € 5,37 de impuestos y cargos</p>
                </div>
            </div>
            <div class="datos">
                <p class="max">Introduce tus datos</p>
                <form class="persona">
                    <div class="nameAp">
                        <div>
                            <label for="nombre">Nombre:</label>
                            <input type="text" id="nombre" name="name">
                        </div>
                        <div>
                            <label for="apellidos">Apellidos:</label>
                            <input type="text" id="apellidos" name="subname">
                        </div>
                    </div>
                    
                    <label for="email">Correo eléctronico:</label>
                    <input type="text" id="email" name="email">
                    <label for="phone">Teléfono (móvil si es posible):</label>
                    <input type="text" id="phone" name="phone">
                    
                    <label for="motivo">Selecciona el motivo del viaje:</label>
                    <select id="motivo" name="motivo">
                        <option value="1">Viajo por vacaciones</option>
                        <option value="2">Viajo por trabajo</option>
                    </select>

                    <label for="who">Para quien es la reserva?:</label>
                    <select id="who" name="who">
                        <option value="1">Para mi</option>
                        <option value="2">Para otra persona</option>
                    </select>
                    <button class="submit" type="submit">Proceder al pago</button>
                </form>
            </div>

        </div>

    </div>




</body>
</html>

