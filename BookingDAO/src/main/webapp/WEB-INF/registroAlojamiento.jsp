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
                <button class="image-button"><a href="<c:url value='CarritoLinkServlet.do'/>"><img src="${pageContext.request.contextPath}/images/cart.svg" alt="Carrito"></a></button>
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
            <div class="upload-photos">
                <h2>Subir Fotos</h2>
                <form>
                    <input type="file" id="photo" name="photo" accept="image/*" multiple>
                    <input type="submit" value="Subir">
                </form>
            </div>
            <div class="edit-form">
                <h2>Registrar Alojamiento</h2>
                <form action="registroAlojamientoLinkServlet.do" method="post">
                    <label for="name">Nombre Alojamiento:</label>
                    <input type="text" id="name" name="name"><br><br>

                    <label for="address">Dirección:</label>
                    <input type="text" id="address" name="address"><br><br>

                    <label for="telephone">Teléfono:</label>
                    <input type="tel" id="telephone" name="telephone"><br><br>

                    <label for="city">Ciudad:</label>
                    <input type="text" id="city" name="city"><br><br>

                    <label for="centerDistance">Distancia al centro:</label>
                    <input type="number" id="centerDistance" name="centerDistance"><br><br>

                    <label for="descripcion">Descripción:</label><br>
                    <textarea id="description" name="description" rows="4" cols="50"></textarea><br><br>
                    
                    
                    <label>Servicios o instalaciones ofrecidos:</label><br>
					<input type="checkbox" id="wifi" name="wifi" value="1">
					<label for="wifi">Wifi</label><br>
					<input type="checkbox" id="piscina" name="piscina" value="1">
					<label for="piscina">Piscina</label><br>
					<input type="checkbox" id="gym" name="gym" value="1">
					<label for="gym">Gimnasio</label><br>
					<input type="checkbox" id="spa" name="spa" value="1">
					<label for="spa">Spa</label><br>
					<input type="checkbox" id="desayuno" name="desayuno" value="1">
					<label for="desayuno">Desayuno</label><br>
					<input type="checkbox" id="restaurante" name="restaurante" value="1">
					<label for="restaurante">Restaurante</label><br><br>


                    <label>Permite mascotas:</label><br>
                    <input type="radio" id="si-mascotas" name="mascotas" value="si">
                    <label for="si-mascotas">Sí</label>
                    <input type="radio" id="no-mascotas" name="mascotas" value="no">
                    <label for="no-mascotas">No</label><br><br>

                    <button type="submit">Continuar</button>
                </form>
            </div>
        </section>
    </div>
</body>
</html>
