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
                <div class="Mostrar_por">
                    <form id="formMostrar" action="ListaAlojamientosLinkServlet.do" method="post">
					    <select id="Mostrar-por" name="Mostrar-por" required>
					        <option value="Todos">Mostrar por: Todos</option>
					        <option value="Disponibles">Mostrar por: Disponibles</option>
					        <option value="No Disponibles">Mostrar por: No Disponibles</option>
					    </select>
					    <button type="submit">Mostrar</button>
					</form>

                </div>
                <div class="c2_alojamiento">
                <c:forEach var="alojamiento" items="${listaalojamiento}" varStatus="loop">
                    <div class="Elemento-c2">
                        <div class="foto-c2">
                            <img src="${pageContext.request.contextPath}/images/alojamiento1.png" alt="Google Maps">
                        </div>
                        <div class="Texto-c2">
                            <h2>${alojamiento.name}</h2>
                            <div class="estrellas">
							    <c:set var="numEstrellas" value="${alojamiento.gradesAverage / 1}" />
							    <c:forEach begin="1" end="5" var="i">
							        <c:choose>
							            <c:when test="${i <= numEstrellas}">
							                <img src="${pageContext.request.contextPath}/images/star.svg" alt="Estrella">
							            </c:when>
							            <c:otherwise>
							                <img src="${pageContext.request.contextPath}/images/star-empty.svg" alt="Estrella vacía">
							            </c:otherwise>
							        </c:choose>
							    </c:forEach>
							</div>
                            <p>El ${alojamiento.name} es un ${alojamiento.description} que se encuentra a ${alojamiento.centerDistance} km del centro de ${alojamiento.city}.</p>
                        </div>

                        <div>
                        <div class="derechon">
                            <div class="valoraciones">
                                <div class="valoCol1">
                                    <h1>
	                                    <c:choose>
						                    <c:when test="${alojamiento.gradesAverage == 5}">
						                        Excepcional
						                    </c:when>
						                    <c:when test="${alojamiento.gradesAverage > 4.5}">
						                        Fantástico
						                    </c:when>
						                    <c:when test="${alojamiento.gradesAverage > 4}">
						                        Fabuloso
						                    </c:when>
						                    <c:when test="${alojamiento.gradesAverage > 3.5}">
						                        Muy bien
						                    </c:when>
						                    <c:otherwise>
						                        Puntuación
						                    </c:otherwise>
						                </c:choose>
		               			 	</h1>
                                </div>
                                
                                <div class="Puntuacion">
                                    ${alojamiento.gradesAverage}
                                </div>
                            </div>
					</div>
                            <div class="datos">
                                <p>${alojamiento.address}, ${alojamiento.city}</p>
                                <h1>Desde ${preciosBajos[loop.index]}€</h1>
                                <p>incluye impuestos y cargos</p>
								<a href="<c:url value='AlojamientoLinkServlet.do?idp=${alojamiento.id}'/>">
						            <button class="disponibilidad">Ver Disponibilidad</button>
								</a>
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
