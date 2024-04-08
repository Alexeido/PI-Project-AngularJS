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
            <button class="start_button" onclick="window.location.href='index.html'" >Booking.com</button>
            <div class="right-section">
                <button>EUR</button>
                <button class="image-button"><img src="${pageContext.request.contextPath}/images/es.png" alt="Language"></button>
                <button class="image-button"><img src="${pageContext.request.contextPath}/images/questionmarkW.png" alt="Ayuda"></button>
                <button>Registra tu alojamiento</button>
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
    
    <div class="colorheader">
    </div>
    
    <div class="container">
        <div class="header2">
            <h1>Encuentra tu próxima estancia</h1>
            <p>Busca ofertas en hoteles, casas y mucho más...</p>
            <form action="IndexLinkServlet.do" METHOD="post">
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
            <div class="oferta-header">
                <h1>Ofertas</h1>
                <h2>Promociones, descuentos y ofertas para ti</h2>
            </div>
            <div class="oferta-container">
                <div class="oferta1">
                    <div class="oferta-texto">
                        <h2>Vuela al destino de tus sueños</h2>
                        <p>Inspírate, compara y reserva vuelos con más flexibilidad</p>
                        <form action="trending.html">
                            <button type="submit">Elige tu estancia</button>
                        </form>
                        
                    </div>
                </div>
                <div class="oferta2">
                    <div class="oferta-texto">
                        <h2>Año nuevo, aventuras nuevas</h2>
                        <p>Ahorra un 15% o más al reservar y alojarte hasta el 1 de abril de 2024</p>
                        <form action="trending.html">
                            <button type="submit">Encuentra una oferta</button>
                        </form>
                        
                    </div>
                    <div class="oferta-imagen">
                        <img src="${pageContext.request.contextPath}/images/avion.png" alt="Año nuevo, aventuras nuevas">
                    </div>
                </div>
            </div>
        </div>
        <div class="alojamiento-header">
            <h1>Busca por alojamientos</h1>
        </div>
        <div class="alojamientos">
            <div class="tipos">
                <a href="alojamiento.html">
                    <img src="${pageContext.request.contextPath}/images/playa.jpg" alt="Hotel 1">
                </a>
                <p>Hotel en la playa</p>
            </div>
            <div class="tipos">
                <a href="alojamiento.html">
                    <img src="${pageContext.request.contextPath}/images/casarural.jpg" alt="Casa 1">
                </a>
                <p>Casa rural con encanto</p>
            </div>
            <div class="tipos">
                <a href="alojamiento.html">
                    <img src="${pageContext.request.contextPath}/images/ciudad.jpg" alt="Apartamento 1">
                </a>
                <p>Apartamento en la ciudad</p>
            </div>
            <div class="tipos">
                <a href="alojamiento.html">
                    <img src="${pageContext.request.contextPath}/images/villa.jpg" alt="Apartamento 1">
                </a>
                <p>Apartamento en la ciudad</p>
            </div>
        </div>
        

    </div>
</body>

</html>
