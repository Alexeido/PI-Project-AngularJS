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
            <button class="start_button"><a href="<c:url value='/IndexLinkServlet.do'/>">Booking.com</a></button>
            <div class="right-section">
                <button>EUR</button>
                <button class="image-button"><img src="${pageContext.request.contextPath}/images/es.png" alt="Language"></button>
                <button class="image-button"><a href="<c:url value='user/CarritoLinkServlet.do'/>"><img src="${pageContext.request.contextPath}/images/cart.svg" alt="Carrito"></a></button>
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
                
                <div class="precio">
                    <p class="precio-title">Desglose del precio</p>
                    <div class="total">
                        <p class="precio-max">Total</p>
                        <div class="right">
                            <p class="precio-max">€ ${sumaPrecios}</p>
                            <p class="subtext">Incluye impuestos y cargos</p>
                        </div>
                    </div>
                </div>
            
	            <c:forEach var="habitacion" items="${habitaciones}" varStatus="loop">
                <div class="hotel">
                    <p class="hotel-name">Habitación ${habitacion.name} en ${habitacion.prop.name}</p>
                    <p class="hotel-name">Precio: ${habitacion.price}€</p>
                    <p class="hotel-address">${habitacion.prop.address}, ${habitacion.prop.city}</p>
                    <p class="hotel-address">Telefono: ${habitacion.prop.telephone}</p>
                    <p class="hotel-rating">
						<c:choose>
						    <c:when test="${habitacion.prop.centerDistance < 0.2}">
						        <p class="hotel-rating">Ubicación excelente</p>
						    </c:when>
						    <c:when test="${habitacion.prop.centerDistance < 1}">
						        <p class="hotel-rating">Ubicación buena</p>
						    </c:when>
						    <c:when test="${habitacion.prop.centerDistance < 5.0}">
						        <p class="hotel-rating">Ubicación promedio</p>
						    </c:when>
						    <c:otherwise>
						        <p class="hotel-rating">Ubicación normal</p>
						    </c:otherwise>
						</c:choose>
                    </p>
                    <div class="rating-box">
                        <p class="rating-note">${habitacion.prop.gradesAverage}</p>
                        <p class="rating-text">
	                        <c:choose>
			                    <c:when test="${habitacion.prop.gradesAverage == 5}">
			                        Excepcional
			                    </c:when>
			                    <c:when test="${habitacion.prop.gradesAverage > 4.5}">
			                        Fantástico
			                    </c:when>
			                    <c:when test="${habitacion.prop.gradesAverage > 4}">
			                        Fabuloso
			                    </c:when>
			                    <c:when test="${habitacion.prop.gradesAverage > 3.5}">
			                        Muy bien
			                    </c:when>
			                    <c:otherwise>
			                        Puntuación
			                    </c:otherwise>
			                </c:choose>
						</p>
                    </div>
                    <div class="amenities">
		                <c:if test="${habitacion.prop.petFriendly == 1}">
				       		<p><img src="${pageContext.request.contextPath}/images/dog.svg" alt="Icono de perro">Permite mascotas</p>
						</c:if>
                    </div>
                    <div class= "delete">
                		<form action="AnularBookServlet.do" method="post">
    						<input type="hidden" name="ida" value="${habitacion.id}">
    						<input type="hidden" name="idb" value="${idb}">
                        	<button class="del">Anular esta habitación</button>
                       </form>
                    </div>
                </div>
	            </c:forEach>
            
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
                    <p class="seleccionado">Has seleccionado: <span class="bold">${accommodation.name}</span></p>
                    <p class="cambiar">Cambia tu selección</p>
                </div>
            </div>
            <div class="datos">
                <p class="max">Datos de tu reserva</p>
                <form action="AnularBookServlet.do" method="post">
				    <input type="hidden" name="idb" value="${idb}">
				    <div class="nameAp">
				        <div>
				            <label for="nombre">Nombre:</label>
				            <span id="nombre">${user.name}</span>
				        </div>
				        <div>
				            <label for="apellidos">Apellidos:</label>
				            <span id="apellidos">${user.surname}</span>
				        </div>
				    </div>
				    
				    <label for="email">Correo electrónico:</label>
				    <span id="email">${user.email}</span>
				    <label for="phone">Teléfono:</label>
				    <span id="phone">927-456-789</span> 
				    <button class="del" type="submit">Anular toda la reserva</button>
				</form>
            </div>

        </div>

    </div>




</body>
</html>

