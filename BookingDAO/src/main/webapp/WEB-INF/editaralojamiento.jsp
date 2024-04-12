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
                <button><a href="<c:url value='registroAlojamientoLinkServlet.do'/>">Registra tu alojamiento</a></button>
                <button class="white-button">Hazte una cuenta</button>
                <button class="white-button"><a href="<c:url value='IniciarSesionLinkServlet.do'/>">Inicia sesión</a></button>
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
                <h2>Editar Alojamiento</h2>
                <form action="editaralojamientoLinkServlet.do" method="post">
                    <label for="name">Nombre Alojamiento:</label>
                    <input type="text" id="name" name="name" placeholder="${alojamiento.name}"><br><br>

                    <label for="address">Dirección:</label>
                    <input type="text" id="address" name="address" placeholder="${alojamiento.address}"><br><br>

                    <label for="telephone">Teléfono:</label>
                    <input type="tel" id="telephone" name="telephone" placeholder="${alojamiento.name}"><br><br>

                    <label for="city">Ciudad:</label>
                    <input type="text" id="city" name="city" placeholder="${alojamiento.city}"><br><br>

                    <label for="centerDistance">Distancia al centro:</label>
                    <input type="number" id="centerDistance" name="centerDistance" placeholder="${alojamiento.centerDistance}"><br><br>

                    <label for="descripcion">Descripción:</label><br>
                    <textarea id="description" name="description" rows="4" cols="50" placeholder="${alojamiento.description}"></textarea><br><br>
                    
                    <label for="available">Disponibles:</label>
                    <input type="number" id="available" name="available" step="0.1" placeholder="${alojamiento.available}"><br><br>
                    
                    <label>Servicios o instalaciones ofrecidos:</label><br>
                    <input type="checkbox" id="wifi" name="wifi">
                    <label for="wifi">Wifi</label><br>
                    <input type="checkbox" id="piscina" name="piscina">
                    <label for="piscina">Piscina</label><br>
                    <input type="checkbox" id="gym" name="gym">
                    <label for="gym">Gimnasio</label><br><br>
                    <label for="serviciosad">Añada servicios adicionales:</label>
                    <textarea id="serviciosad" name="serviciosad" rows="4" cols="50" placeholder="Servicios adicionales ofrecidos"></textarea><br><br>

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
