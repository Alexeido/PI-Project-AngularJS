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
            <div class="upload-photos">
                <h2>Subir Fotos</h2>
                <form>
                    <input type="file" id="photo" name="photo" accept="image/*" multiple>
                    <input type="submit" value="Subir">
                </form>
            </div>
            <div class="edit-form">
                <h2>Editar Alojamiento</h2>
                <form>
                    <label for="nombre-alojamiento">Nombre Alojamiento:</label>
                    <input type="text" id="nombre-alojamiento" name="nombre-alojamiento"><br><br>

                    <label for="direccion">Dirección:</label>
                    <input type="text" id="direccion" name="direccion"><br><br>

                    <label for="telefono">Teléfono:</label>
                    <input type="tel" id="telefono" name="telefono"><br><br>

                    <label for="distancia-centro">Distancia al centro:</label>
                    <input type="number" id="distancia-centro" name="distancia-centro"><br><br>

                    <label for="valoracion-media">Valoración media:</label>
                    <input type="number" id="valoracion-media" name="valoracion-media" step="0.1"><br><br>

                    <label for="descripcion">Descripción:</label><br>
                    <textarea id="descripcion" name="descripcion" rows="4" cols="50"></textarea><br><br>

                    <label>Servicios o instalaciones ofrecidos:</label><br>
                    <input type="checkbox" id="wifi" name="wifi">
                    <label for="wifi">Wifi</label><br>
                    <input type="checkbox" id="piscina" name="piscina">
                    <label for="piscina">Piscina</label><br>
                    <input type="checkbox" id="gym" name="gym">
                    <label for="gym">Gimnasio</label><br><br>
                    <label for="serviciosad">Añada servicios adicionales:</label>
                    <textarea id="serviciosad" name="serviciosad" rows="4" cols="50"></textarea><br><br>

                    <label>Permite mascotas:</label><br>
                    <input type="radio" id="si-mascotas" name="mascotas" value="si">
                    <label for="si-mascotas">Sí</label>
                    <input type="radio" id="no-mascotas" name="mascotas" value="no">
                    <label for="no-mascotas">No</label><br><br>

                    <input type="submit" value="Guardar">
                </form>
            </div>
        </section>
    </div>
</body>
</html>
