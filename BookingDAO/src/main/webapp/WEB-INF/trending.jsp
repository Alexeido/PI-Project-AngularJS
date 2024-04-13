<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>  
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Encuentra tu próxima estancia</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/trending.css">



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
        <div class="Destinos">
            <div class="moda-header">
                <h1>Destinos de moda</h1>
                <h2>Opciones más populares entre la comunidad viajera de España
                </h2>
            </div>
            <div class="oferta1">
                <div class="imagengrande">
                    <h1>Madrid </h1>
                    <img src="${pageContext.request.contextPath}/images/madrid.jpg" alt="madrid">
                </div>
                <div class="imagengrande">
                    <h1>Sevilla</h1>
                    <img src="${pageContext.request.contextPath}/images/sevilla.jpg" alt="sevilla">
                </div>
            </div>
            <div class="oferta1">
                <div class="imagenpequeña">
                    <h1>Barcelona</h1>
                    <img src="${pageContext.request.contextPath}/images/Barcelona.jpg" alt="Barcelona">
                </div>
                <div class="imagenpequeña">
                    <h1>Granada</h1>
                    <img src="${pageContext.request.contextPath}/images/Granada.jpg" alt="Granada">
                </div>
                <div class="imagenpequeña">
                    <h1>Valencia</h1>
                    <img src="${pageContext.request.contextPath}/images/Valencia.jpg" alt="Valencia">
                </div>
            </div>
        </div>
        <div class="alojamiento-header">
            <h1>Explora España</h1>
        </div>
        <div class="alojamientos">
            <div class="tipos_españa">
                <img src="${pageContext.request.contextPath}/images/cadiz.jpg" alt="Hotel 1">
                <h2>Cádiz</h2>
                <p>687 disponibles</p>
            </div>
            <div class="tipos_españa">
                <img src="${pageContext.request.contextPath}/images/jaen.jpg" alt="Casa 1">
                <h2>Jaen</h2>                   
                <p>234 disponibles</p>
            </div>
            <div class="tipos_españa">
                <img src="${pageContext.request.contextPath}/images/cordoba.jpg" alt="Apartamento 1">
                <h2>Cordoba</h2>
                <p>145 disponibles</p>
            </div>
            <div class="tipos_españa">
                <img src="${pageContext.request.contextPath}/images/Huelva.jpg" alt="Apartamento 1">
                <h2>Huelva</h2>
                <p>530 disponibles</p>
            </div>
            <div class="tipos_españa">
                <img src="${pageContext.request.contextPath}/images/malaga.jpg" alt="Apartamento 1">
                <h2>Malaga</h2>
                <p>530 disponibles</p>
            </div><div class="tipos_españa">
                <img src="${pageContext.request.contextPath}/images/granada2.jpg" alt="Apartamento 1">
                <h2>Granada</h2>
                <p>530 disponibles</p>
            </div>
        </div>
        <div class="alojamiento-header">
            <h1>Busca por alojamientos</h1>
        </div>
        <div class="alojamientos">
            <div class="tipos">
                <img src="${pageContext.request.contextPath}/images/playa.jpg" alt="Hotel 1">
                <h2>Hotel en la playa</h2>
                <p>6 Mar-15 Mar, 2 adultos</p>
                <p>687 disponibles</p>

            </div>
            <div class="tipos">
                <img src="${pageContext.request.contextPath}/images/casarural.jpg" alt="Casa 1">
                <h2>Casa rural con encanto</h2>
                <p>6 Mar-15 Mar, 2 adultos</p>
                <p>234 disponibles</p>
            </div>
            <div class="tipos">
                <img src="${pageContext.request.contextPath}/images/ciudad.jpg" alt="Apartamento 1">
                <h2>Apartamento en el campo</h2>
                <p>6 Mar-15 Mar, 2 adultos</p>
                <p>145 disponibles</p>
            </div>
            <div class="tipos">
                <img src="${pageContext.request.contextPath}/images/villa.jpg" alt="Apartamento 1">
                <h2>Apartamento en la ciudad</h2>
                <p>6 Mar-15 Mar, 2 adultos</p>
                <p>530 disponibles</p>
            </div>
        </div>
        <h1>Destinos que más nos gustan</h1>
        <div class="botones">
            <button>Regiones</button>
            <button>Ciudades</button>
            <button>Lugares de intestes</button>
        </div>
        <div class="row">
            <div class="city">
                <h2>Lanzarote</h2>
                <p>3.960 alojamientos</p>
            </div>
            <div class="city">
                <h2>Tenerife</h2>
                <p>9.623 alojamientos</p>
            </div>
            <div class="city">
                <h2>La Gomera</h2>
                <p>490 alojamientos</p>
            </div>
            <div class="city">
                <h2>Fuerteventura</h2>
                <p>59 alojamientos</p>
            </div>
            <div class="city">
                <h2>Sierra Norte de Madrid</h2>
                <p>158 alojamientos</p>
            </div>
        </div>
        
        <div class="row">
            <div class="city">
                <h2>Algarve</h2>
                <p>12.550 alojamientos</p>
            </div>
            <div class="city">
                <h2>Costa de Almería</h2>
                <p>2.912 alojamientos</p>
            </div>
            <div class="city">
                <h2>Galicia</h2>
                <p>6.561 alojamientos</p>
            </div>
            <div class="city">
                <h2>Cantabria</h2>
                <p>1.925 alojamientos</p>
            </div>
            <div class="city">
                <h2>Ibiza</h2>
                <p>1.641 alojamientos</p>
            </div>
        </div>
    
        <div class="row">
            <div class="city">
                <h2>Formentera</h2>
                <p>399 alojamientos</p>
            </div>
            <div class="city">
                <h2>Mallorca</h2>
                <p>10.777 alojamientos</p>
            </div>
            <div class="city">
                <h2>El Hierro</h2>
                <p>333 alojamientos</p>
            </div>
            <div class="city">
                <h2>Gran Canaria</h2>
                <p>5.976 alojamientos</p>
            </div>
            <div class="city">
                <h2>Isla de La Graciosa</h2>
                <p>49 alojamientos</p>
            </div>
        </div>
    
        <div class="row">
            <div class="city">
                <h2>Menorca</h2>
                <p>1.698 alojamientos</p>
            </div>
            <div class="city">
                <h2>Asturias</h2>
                <p>3.312 alojamientos</p>
            </div>
            <div class="city">
                <h2>Tenerife Sur</h2>
                <p>6.815 alojamientos</p>
            </div>
            <div class="city">
                <h2>Costa Brava</h2>
                <p>10.262 alojamientos</p>
            </div>
            <div class="city">
                <h2>Wakanda</h2>
                <p>9.162 alojamientos</p>
            </div>
        </div>
    </div>

</body>

</html>