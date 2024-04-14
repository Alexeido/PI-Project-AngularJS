<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <div class="edit-form">
                <h2>Editar Alojamiento</h2>
                <form action="ActualizarAlojamientoServlet.do" method="post">
                    <label for="name">Nombre Alojamiento:</label>
                    <input type="text" id="name" name="name" value="${alojamiento.name}"><br><br>

                    <label for="address">Dirección:</label>
                    <input type="text" id="address" name="address" value="${alojamiento.address}"><br><br>

                    <label for="telephone">Teléfono:</label>
                    <input type="tel" id="telephone" name="telephone" value="${alojamiento.telephone}"><br><br>

                    <label for="city">Ciudad:</label>
                    <input type="text" id="city" name="city" value="${alojamiento.city}"><br><br>

                    <label for="centerDistance">Distancia al centro:</label>
                    <input type="number" id="centerDistance" name="centerDistance" value="${alojamiento.centerDistance}"><br><br>

                    <label for="descripcion">Descripción:</label><br>
                    <textarea id="description" name="description" rows="4" cols="50">${alojamiento.description}</textarea><br><br>
                    
                    <label>Servicios o instalaciones ofrecidos:</label><br>
                    <c:choose>
                        <c:when test="${alojamiento.restaurante == 1}">
                            <input type="checkbox" id="restaurante" name="restaurante" checked>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" id="restaurante" name="restaurante">
                        </c:otherwise>
                    </c:choose>
                    <label for="restaurante">Restaurante</label><br>

                    <c:choose>
                        <c:when test="${alojamiento.desayuno == 1}">
                            <input type="checkbox" id="desayuno" name="desayuno" checked>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" id="desayuno" name="desayuno">
                        </c:otherwise>
                    </c:choose>
                    <label for="desayuno">Desayuno</label><br>

                    <c:choose>
                        <c:when test="${alojamiento.wifi == 1}">
                            <input type="checkbox" id="wifi" name="wifi" checked>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" id="wifi" name="wifi">
                        </c:otherwise>
                    </c:choose>
                    <label for="wifi">Wifi</label><br>

                    <c:choose>
                        <c:when test="${alojamiento.gym == 1}">
                            <input type="checkbox" id="gym" name="gym" checked>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" id="gym" name="gym">
                        </c:otherwise>
                    </c:choose>
                    <label for="gym">Gimnasio</label><br>

                    <c:choose>
                        <c:when test="${alojamiento.piscina == 1}">
                            <input type="checkbox" id="piscina" name="piscina" checked>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" id="piscina" name="piscina">
                        </c:otherwise>
                    </c:choose>
                    <label for="piscina">Piscina</label><br>

                    <c:choose>
                        <c:when test="${alojamiento.spa == 1}">
                            <input type="checkbox" id="spa" name="spa" checked>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" id="spa" name="spa">
                        </c:otherwise>
                    </c:choose>
                    <label for="spa">Spa</label><br><br>

                    <label>Permite mascotas:</label><br>
                    <c:choose>
                        <c:when test="${alojamiento.petFriendly == 1}">
                            <input type="radio" id="si-mascotas" name="mascotas" value="si" checked>
                            <label for="si-mascotas">Sí</label>
                            <input type="radio" id="no-mascotas" name="mascotas" value="no">
                            <label for="no-mascotas">No</label><br><br>
                            
                        </c:when>
                        <c:otherwise>
                            <input type="radio" id="si-mascotas" name="mascotas" value="si">
                            <label for="si-mascotas">Sí</label>
                            <input type="radio" id="no-mascotas" name="mascotas" value="no" checked>
                            <label for="no-mascotas">No</label><br><br>
                            
                        </c:otherwise>
                    </c:choose>
					<div class="button-container">
					   <button type="submit">Continuar</button>
					</div>
                </form>
            </div>
    </div>
</body>
</html>
