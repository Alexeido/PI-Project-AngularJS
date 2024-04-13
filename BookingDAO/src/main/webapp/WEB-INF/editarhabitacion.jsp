<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edición de Alojamiento</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registroAlojamiento.css">
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
        <section class="edit-alojamiento">
            <div class="edit-form">
                <h2>Editar Alojamiento ${idAlojamiento} adios</h2>
                <form action="ActualizarHabitacionServlet.do" method="post">
                    <label for="name">Nombre Habitacion:</label>
                    <input type="text" id="name" name="name" value="${habitacion.name}"><br><br>

                    <label for="address">Precio:</label>
                    <input type="number" id="price" name="price" value="${habitacion.price}"><br><br>

                    <label for="descripcion">Descripción:</label><br>
                    <textarea id="description" name="description" rows="4" cols="50">${habitacion.description}</textarea><br><br>
                                        
                    <label for="available">Numero:</label>
                    <input type="number" id=available name="available" step="0.1" value="${habitacion.numAccommodations}"><br><br>
                    
                    <input type="hidden" id="idhabitacion" name="idhabitacion" value="${habitacion.id}"><br><br>
                    <button type="submit">Continuar</button>
                </form>
            </div>
        </section>
    </div>
</body>
</html>
