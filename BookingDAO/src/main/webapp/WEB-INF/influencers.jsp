<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>  
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Encuentra tu próxima estancia</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/paginaprincipal.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/buscador.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">


</head>

<body>
    <header>
        <nav class="principal">
            <button class="start_button"><a href="<c:url value='IndexLinkServlet.do'/>">Booking.com</a></button>
            <div class="right-section">
                <button>EUR</button>
                <button class="image-button"><img src="${pageContext.request.contextPath}/images/es.png" alt="Language"></button>
                <button class="image-button"><a href="<c:url value='user/CarritoLinkServlet.do'/>"><img src="${pageContext.request.contextPath}/images/cart.svg" alt="Carrito"></a></button>
                <c:choose>
            		<c:when test="${not empty user.id}">
            			<button class="white-button"><a href="<c:url value='user/UsuarioLinkServlet.do'/>">Ver Perfil</a></button>
            		    <button><a href="<c:url value='user/registroAlojamientoLinkServlet.do'/>">Registra tu alojamiento</a></button>
            		
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
    
    <div class="colorheader">
    </div>
    
    <div class="container">
        <div class="header2">
            <h1>Encuentra tu próxima estancia</h1>
            <p>Busca ofertas en hoteles, casas y mucho más...</p>
            <form action="BusquedaLugarServlet.do" method="post">
                <div class="buscador-container">
                    <div class="elemento-buscador-container">
                        <img src="${pageContext.request.contextPath}/images/bed-solid.svg" alt="ícono" width="16" height="16">
                        <input name="Lugar" type="text" placeholder="Lugar">
                    </div>
                    <div class="elemento-buscador-container">
                        <img src="${pageContext.request.contextPath}/images/calendar-days-solid.svg" alt="ícono" width="16" height="16">
                        <input type="date" value="2024-02-26">
                        <input type="date" value="2024-02-27">
                    </div>
                    <div class="elemento-buscador-container">
                        <div class="elemento-buscador-container2">
                            <img src="${pageContext.request.contextPath}/images/user-solid.svg" alt="ícono" width="16" height="16">
                            <p>Adultos</p>
                            <input type="number" min="0">
                            <p>Niños</p>
                            <input type="number" min="0">
                        </div>
                    </div>
                    <div class="boton-buscador-container">
                        <button type="submit">Buscar</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="checkbox">
            <input type="checkbox" id="viajo_por_trabajo" name="viajo_por_trabajo">
            <label for="viajo_por_trabajo">Viajo por trabajo</label>

            <input type="checkbox" id="busco_vuelos" name="busco_vuelos">
            <label for="busco_vuelos">Busco vuelos</label>
        </div>
        <div id="ofertas">
          
                <h1>Usuario influencer: ${influencer.name} idu: ${influencer.id}</h1>
                
                <c:if test="${numReviews<2}">
                	<h2>Número de reviews:${numReviews}</h2>
                </c:if>
                <c:if test="${numReviews>=2}">
                	<h2>Número de reviews: <span style="color: yellow;">${numReviews}</span></h2>
                </c:if>
        </div>
    </div>
</body>
</html>
