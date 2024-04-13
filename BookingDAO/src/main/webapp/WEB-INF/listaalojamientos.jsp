<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Busqueda de Alojamientos en ${lugar}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/buscador.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/listaalojamientos.css">
</head>

<body>
    <header>
        <nav class="principal">
            <button class="start_button"><a href="<c:url value='IndexLinkServlet.do'/>">Booking.com</a></button>
            <div class="right-section">
                <button>EUR</button>
                <button class="image-button"><img src="${pageContext.request.contextPath}/images/es.png" alt="Language"></button>
                <button class="image-button"><img src="${pageContext.request.contextPath}/images/questionmarkW.png" alt="Ayuda"></button>
                <button><a href="<c:url value='registroAlojamientoLinkServlet.do.do'/>">Registra tu alojamiento</a></button>
                <button class="white-button">Hazte una cuenta</button>
                <button class="white-button"><a href="<c:url value='IniciarSesionLinkServlet.do'/>">Inicia sesión</a></button>
            </div>
        </nav>

        <nav class="secondary">
            <button><img src="${pageContext.request.contextPath}/images/Bed.svg" alt="Alojamiento"><span>Alojamiento</span></button>
            <button><img src="${pageContext.request.contextPath}/images/avion.svg" alt="Vuelos"><span>Vuelos</span></button>
            <button><img src="${pageContext.request.contextPath}/images/vuelo+hotel.svg" alt="Vuelo + Hotel"><span>Vuelo + Hotel</span></button>
            <button><img src="${pageContext.request.contextPath}/images/alquilercoche.svg" alt="Alquiler de coches"><span>Alquiler de
                    coches</span></button>
            <button><img src="${pageContext.request.contextPath}/images/ferris-wheel-svgrepo-com.svg" alt="Atracciones turísticas"><span>Atracciones
                    turísticas</span></button>
            <button><img src="${pageContext.request.contextPath}/images/taxi.png" alt="Taxis aeropuerto"><span>Taxis aeropuerto</span></button>
        </nav>
    </header>


    <div class="container">
    	<form action="BusquedaLugarServlet.do" METHOD="post">
	        <div class="buscador-container">
	            <div class="elemento-buscador-container">
	                <img src="${pageContext.request.contextPath}/images/bed-solid.svg" alt="Ícono" width="16" height="16">
	                <input name="Lugar" type="text" placeholder="Lugar">
	            </div>
	            <div class="elemento-buscador-container">
	                <img src="${pageContext.request.contextPath}/images/calendar-days-solid.svg" alt="Ícono" width="16" height="16">
	                <input type="date" value="2024-02-26">
	                <input type="date" value="2024-02-27">
	            </div>
	            <div class="elemento-buscador-container">
	                <div class="elemento-buscador-container2">
	                    <img src="${pageContext.request.contextPath}/images/user-solid.svg" alt="Ícono" width="16" height="16">
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


        <div class="location-section">
            <span><a href="<c:url value='IndexLinkServlet.do'/>">Inicio</a> > <a href="<c:url value='trendingLinkServlet.do'/>">Hoteles</a> > <a href="<c:url value='trendingLinkServlet.do'/>">España</a> > Ofertas en ${lugar}</span>
        </div>
        <div class="gridAlojamiento">
            <div class="container1">
                <div class="mapa">
                    <img src="${pageContext.request.contextPath}/images/mapa.png" alt="Google Maps">
                </div>
                <div class="filtro">
                    <div class="titulofiltro">
                        <h1>Filtrar por:</h1>
                        <hr style="border-top: 1px solid #ccc; margin-top: 5px;margin-left: 0px;">
                    </div>
                    <div class="elemnos-filtro">
                        <h1>Tu presupuesto(por noche)</h1>
                        <div class="grafico">
                            <img src="${pageContext.request.contextPath}/images/grafico.png" alt="grafico">
                        </div>
                        <input type="range" id="precio" name="precio" min="0" max="1000" step="10">
                    </div>
                    <div class="elemnos-filtro">

                        <h1>Filtro populares</h1>
                        <div class="filtros-pupulares">
                            <label for="estrellas-4"><input type="checkbox" id="estrellas-4" name="estrellas"> 4
                                estrellas</label>
                            <label for="Villas"><input type="checkbox" id="Villas" name="tipo"> Villas</label>
                            <label for="Habitaciones-sin-humo"><input type="checkbox" id="Habitaciones-sin-humo"
                                    name="humo"> Habitaciones sin humo</label>
                            <label for="Enchufe-cerca-de-la-cama"><input type="checkbox" id="Enchufe-cerca-de-la-cama"
                                    name="enchufe1"> Enchufe cerca de la cama</label>
                            <label for="Servicio-de-habitaciones"><input type="checkbox" id="Servicio-de-habitaciones"
                                    name="servicios"> Servicio de habitaciones</label>
                        </div>
                    </div>
                    <div class="titulofiltro">
                        <hr style="border-top: 1px solid #ccc; margin-top: 5px;margin-left: 0px;">
                    </div>
                    <div class="elemnos-filtro">

                        <h1>Tipos de alojamiento</h1>
                        <div class="filtros-pupulares">
                            <label for="Casas-y-chalets"><input type="checkbox" id="Casas-y-chalets" name="casas"> Casas
                                y chalets</label>
                            <label for="Apartamentos"><input type="checkbox" id="Apartamentos" name="casas">
                                Apartamentos</label>
                            <label for="Casas-Rurales"><input type="checkbox" id="Casas-Rurales" name="casas"> Casas
                                Rurales</label>
                            <label for="Hoteles"><input type="checkbox" id="Hoteles" name="casas"> Hoteles</label>
                            <label for="Casa-de-Montaña"><input type="checkbox" id="Casa-de-Montaña" name="casas"> Casa
                                de Montaña</label>
                        </div>
                    </div>
                    <hr style="border-top: 1px solid #ccc; margin-top: 5px;margin-left: 0px;">
                    <div class="elemnos-filtro">

                        <h1>Servicios de Habitaciones</h1>
                        <div class="filtros-pupulares">
                            <label for="enchufe2"><input type="checkbox" id="enchufe2"
                                    name="enchufe"> Enchufe cerca de la cama</label>
                            <label for="Piscina-privada"><input type="checkbox" id="Piscina-privada"
                                    name="Piscina-privada"> Piscina privada</label>
                            <label for="Aire-acondicionado"><input type="checkbox" id="Aire-acondicionado"
                                    name="Aire-acondicionado"> Aire acondicionado</label>
                            <label for="Barbacoa"><input type="checkbox" id="Barbacoa" name="Barbacoa"> Barbacoa</label>
                            <label for="Cafetera"><input type="checkbox" id="Cafetera" name="Cafetera"> Cafetera</label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container2">
                <h1>${lugar} ${listaalojamiento.size()} alojamientos encontrados</h1>
                <div class="Ordenar_por">
                    <select id="Ordenar-por" name="Ordenar-por" required>
                        <option value="">Ordenar por:</option>
                        <option value="Ordenar por : Casa y apartamentos">Ordenar por : Casa y apartamentos</option>
                        <option value="Ordenar por : Mejor destacado">Ordenar por : Mejor destacado </option>
                        <option value="Ordenar por : Mejor valorado">Ordenar por : Mejor valorado </option>
                        <option value="Ordenar por : Precio">Ordenar por : Precio </option>
                        <option value="Ordenar por : Distancia del centro">Ordenar por : Distancia del centro </option>
                    </select>
                </div>
                <div class="c2_alojamiento">
                <c:forEach var="alojamiento" items="${listaalojamiento}">
                    <div class="Elemento-c2">
                        <div class="foto-c2">
                            <img src="${pageContext.request.contextPath}/images/alojamiento1.png" alt="Google Maps">
                        </div>
                        <div class="Texto-c2">
                            <h2>${alojamiento.name}</h2>
                            <div class="estrellas">
                                <img src="${pageContext.request.contextPath}/images/star.svg" alt="Estrella">
                                <img src="${pageContext.request.contextPath}/images/star.svg" alt="Estrella">
                                <img src="${pageContext.request.contextPath}/images/star.svg" alt="Estrella">
                                <img src="${pageContext.request.contextPath}/images/star.svg" alt="Estrella">
                            </div>
                            <p>${alojamiento.description}</p>
                        </div>

                        <div>
                            <div class="valoraciones">
                                <div class="valoCol1">
                                    <h1>Fantastico</h1>
                                    <p>132 comentario</p>
                                </div>
                                
                                <div class="Puntuacion">
                                    9.2
                                </div>
                            </div>

                            <div class="datos">
                                <p>2 noches, 2adultos</p>
                                <h1>405€</h1>
                                <p>incluye impuestos y cargos</p>
                                <form class="form-button" action="<c:url value='AlojamientoLinkServlet.do'/>" method="POST">
								    <input type="hidden" name="idp" value="${alojamiento.id}">
								    <input type="submit" value="Ver disponibilidad">
								</form>
                            </div>
                        </div>
                    </div>
                   </c:forEach>
                </div>
            </div>
        </div>
    </div>


</body>

</html>
