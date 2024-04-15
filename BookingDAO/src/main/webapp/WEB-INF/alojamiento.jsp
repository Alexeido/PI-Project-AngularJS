<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${alojamiento.name} | Booking.com</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alojamiento.css">
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
        <div class="location-section">
            <span><a href="<c:url value='IndexLinkServlet.do'/>">Inicio</a> > <a href="<c:url value='trendingLinkServlet.do'/>">Hoteles</a> > <a href="<c:url value='trendingLinkServlet.do'/>">España</a> > <a href="<c:url value='ListaAlojamientosLinkServlet.do'/>">${alojamiento.city}</a> > ${alojamiento.name}</span>
        </div>
        <div id="vista-general" class= "gridAlojamiento">
            <div class="g1">
                <div class="g1-1">
                    <button><img src="${pageContext.request.contextPath}/images/ticket.svg" alt="ticket"><span> Igualamos el precio</span></button>
                </div>
                <div class="buscador">
                    <form action="BusquedaLugarServlet.do" METHOD="post">
                        <h2>Buscar</h2>
                        <label for="destino">Destino/Nombre del alojamiento</label>
                        <div class="input-container">
                            <img src="${pageContext.request.contextPath}/images/lupa.svg" alt="lupa" class="lupa-icon">
                            <input type="text" id="destino" name="Lugar" placeholder="Benidorm">
                        </div>
                        <label for="fecha_entrada">Fecha de entrada</label><br>
                        <input type="date" id="fecha_entrada" name="fecha_entrada"><br>
                        <label for="fecha_salida">Fecha de salida</label><br>
                        <input type="date" id="fecha_salida" name="fecha_salida">
                        <label for="cantidad_personas">Cantidad de personas</label><br>
                        <select id="cantidad_personas" name="cantidad_personas">
                            <option value="1">1 adulto · 0 niños · 1 habitación</option>
                            <option value="2">2 adultos · 0 niños · 1 habitación</option>
                            <!-- Otras opciones aquí -->
                        </select><br>
                        <input type="checkbox" id="casas_apartamentos" name="casas_apartamentos">
                        <label for="casas_apartamentos">Casas y apartamentos enteros</label><br>
                        <input type="checkbox" id="viajo_trabajo" name="viajo_trabajo">
                        <label for="viajo_trabajo">Viajo por trabajo</label><br>
                        <button type="submit">Buscar</button>
                    </form>
                </div>
                <div class="mapa">
                    <img src="${pageContext.request.contextPath}/images/mapa.png" alt="Google Maps">
                </div>
                
            </div>
            <div class="g2">
                <div class="g2-1">
                    <button>Vista general</button>
                    <button>Descripción</button>
                    <button>Servicios</button>
                    <button>Info y precios</button>
                    <button>Comentarios de clientes</button>
                    
                </div>
                <div class="g2-2">
                    <div class="g2-2F1">
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
                        <div class="right-section">
	                        <c:if test="${!fav}">
	                     		<a href="<c:url value='user/GuardarFavoritoServlet.do?idalojamiento=${alojamiento.id}'/>">
	                        	   <button class="me-gusta"><img src="${pageContext.request.contextPath}/images/heart.svg" alt="like"></button>
						        </a>
							</c:if>
	                        <c:if test="${fav}">
	                     		<a href="<c:url value='user/EliminarFavoritoServlet.do?idalojamiento=${alojamiento.id}'/>">
	                        	   <button class="me-gusta"><img src="${pageContext.request.contextPath}/images/heartFill.svg" alt="like"></button>
						        </a>
							</c:if>
	                        
						    <form action="#disponibilidad" method="get">
							    <button type="submit" class="reserva">Reserva ahora</button>
							</form>

                              
                        </div>
                    </div>
                    <div class="g2-2F2">
                        ${alojamiento.name}
                    </div>
                    <div class="g2-2F3">
                        <img src="${pageContext.request.contextPath}/images/gps.svg" alt="GPS" class="gps-icon">
                        <p>
                            <a href="#">${alojamiento.city}, ${alojamiento.address}</a> – 
                            <c:choose>
							    <c:when test="${alojamiento.centerDistance < 0.2}">
							        <a href="#">Ubicación excelente</a>
							    </c:when>
							    <c:when test="${alojamiento.centerDistance < 1}">
							        <a href="#">Ubicación buena</a>
							    </c:when>
							    <c:when test="${alojamiento.centerDistance < 5.0}">
							        <a href="#">Ubicación promedio</a>
							    </c:when>
							    <c:otherwise>
							        <a href="#">Ubicación normal</a>
							    </c:otherwise>
							</c:choose>
                            <a href="#">Ver mapa</a>
                        </p>
                    </div>
                </div>
                <div class="g2-3">
                    <div class="grid-imagenes">
                        <div class="img1f">
                            <div class="imgssecundarias">
                                <div class="imagen-secundaria">
                                    <img src="${pageContext.request.contextPath}/images/hotel/2.jpg" alt="Imagen secundaria 1">
                                </div>
                                <div class="imagen-secundaria">
                                    <img src="${pageContext.request.contextPath}/images/hotel/3.jpg" alt="Imagen secundaria 2">
                                </div>
                            </div>
                            <div class="imagen-principal">
                                <img src="${pageContext.request.contextPath}/images/hotel/1.jpg" alt="Imagen principal">
                            </div>
                        </div>
                        <div class="img2f">
                            <div class="imagen-pequena">
                                <img src="${pageContext.request.contextPath}/images/hotel/4.jpg" alt="Imagen pequeña 1">
                            </div>
                            <div class="imagen-pequena">
                                <img src="${pageContext.request.contextPath}/images/hotel/5.jpg" alt="Imagen pequeña 2">
                            </div>
                            <div class="imagen-pequena">
                                <img src="${pageContext.request.contextPath}/images/hotel/6.jpg" alt="Imagen pequeña 3">
                            </div>
                            <div class="imagen-pequena">
                                <img src="${pageContext.request.contextPath}/images/hotel/7.jpg" alt="Imagen pequeña 4">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="descripcion" class="descripcion">
            <div class="d1">
            	<h3>Descripción</h3>
                <p>El ${alojamiento.name} es un ${alojamiento.description} que se encuentra a ${alojamiento.centerDistance} km del centro de ${alojamiento.city}.</p>
                <p id="servicios" class="minitext">Las distancias en la descripción del alojamiento se calculan con OpenStreetMap©</p>
                <h3>Servicios más populares</h3>
                <ul>
	                <c:if test="${alojamiento.restaurante == 1}">
			       		<li><img src="${pageContext.request.contextPath}/images/restaurant.svg" alt="Icono de restaurante">Restaurante</li>
					</c:if>
	                <c:if test="${alojamiento.desayuno == 1}">
			       		<li><img src="${pageContext.request.contextPath}/images/coffee.svg" alt="Icono de desayuno">Desayuno incluido</li>
					</c:if>
	                <c:if test="${alojamiento.wifi == 1}">
			       		<li><img src="${pageContext.request.contextPath}/images/wifi.svg" alt="Icono de WiFi">WiFi gratis</li>
					</c:if>
	                <c:if test="${alojamiento.gym == 1}">
			       		<li><img src="${pageContext.request.contextPath}/images/gym.svg" alt="Icono de gimnasio">Gimnasio</li>
					</c:if>
	                <c:if test="${alojamiento.piscina == 1}">
			       		<li><img src="${pageContext.request.contextPath}/images/swim.svg" alt="Icono de piscina">Piscina</li>
					</c:if>
	                <c:if test="${alojamiento.spa == 1}">
			       		<li><img src="${pageContext.request.contextPath}/images/spa.svg" alt="Icono de spa">Spa y centro de bienestar</li>
					</c:if>
	                <c:if test="${alojamiento.petFriendly == 1}">
			       		<li><img src="${pageContext.request.contextPath}/images/dog.svg" alt="Icono de perro">Permite mascotas</li>
					</c:if>
                </ul>
            </div>
            <div class="d2">
                <h3>Puntos fuertes del alojamiento</h3>
                <c:if test="${alojamiento.petFriendly == 1}">
		       		<p><img src="${pageContext.request.contextPath}/images/dog.svg" alt="Icono de perro">Permite mascotas</p>
				</c:if>
                <c:if test="${alojamiento.centerDistance < 1}">
				    <p><img src="${pageContext.request.contextPath}/images/gps_B.svg" alt="Icono">Situado a menos de 1km del centro de ${alojamiento.city}.</p>
				</c:if>
                <c:if test="${alojamiento.gradesAverage > 4}">
					<h3>Clientes fieles</h3>
	                <p><img src="${pageContext.request.contextPath}/images/check.svg" alt="Icono">Los clientes repiten más aquí que en otros alojamientos.</p>
				</c:if>
            
                
                <form action="#disponibilidad" method="get">
				    <button type="submit" class="reserva">Reserva ahora</button>
				</form>
                <button class="reserva"><img src="${pageContext.request.contextPath}/images/tlf.svg" alt="icono de telefono"> ${alojamiento.telephone}</button>
            </div>
            
        </div>
        <div id="disponibilidad" class="disponibilidad">
            <p class="titulo">Disponibilidad</p>
            <p class="mensaje"> Indica las fechas para ver la disponibilidad y los precios del alojamiento</p>
            <div class="disp-form">
                <form action="listaalojamientos.html">
                    <label for="fecha_entrada">Fecha de entrada:</label>
                    <input type="date" id="fecha_entradaDisp" name="fecha_entrada">
                    
                    <label for="fecha_salida">Fecha de salida:</label>
                    <input type="date" id="fecha_salidaDisp" name="fecha_salida">
                    
                    <label for="adultos">Adultos:</label>
                    <select id="adultos" name="adultos">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                    </select>
                    
                    <label for="ninos">Niños:</label>
                    <select id="ninos" name="ninos">
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                    
                    <label for="habitaciones">Habitaciones:</label>
                    <select id="habitaciones" name="habitaciones">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                    <button type="submit">Buscar</button>
                </form>
            </div>
            <div class="disp-types">
                <table>
                    <thead>
                        <tr>
                            <th>Tipo de habitación</th>
                            <th>Disponibles</th>
                            <th>Reservas</th>
                        </tr>
                    </thead>
                    <tbody>
                    
                    <c:forEach var="accommodation" items="${listaAccommodations}">
			            <tr>
			                <td>
			                    ${accommodation.name}
			                    <br>
			                    <p>${accommodation.description}</p>
			                </td>
			                <td>
			                    <p>${accommodation.numAccommodations} Unidades</p>
			                </td>
			                <td>
			                    <c:choose>
			                        <c:when test="${accommodation.numAccommodations > 0}">
			                            <form action="user/CarritoSessionAddServlet.do" method="post">
								    		<input type="hidden" name="ida" value="${accommodation.id}">
			                                <button class="reserva">Añade al carrito por ${accommodation.price}€</button>
			                            </form>
			                        </c:when>
			                        <c:otherwise>
			                            <button class="reservaSoldOut">Agotado</button>
			                        </c:otherwise>
			                    </c:choose>
			                </td>
			            </tr>
			        </c:forEach>
              
                    </tbody>
                </table>
            </div>
        </div>
		<c:if test="${not empty listaReviews}">
		    <div id="comentarios" class="comentarios">
		        <h2 style="font-weight: bold; font-size: 24px;">Comentarios de los clientes</h2>
		        <div class="media-valoracion">
		            <div class="media-valoracion-texto">${alojamiento.gradesAverage}</div>
		            <div class="media-valoracion-texto-derecha">
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
		            </div>
		        </div>
		        <div class="comentario-container">
		            <c:forEach var="review" items="${listaReviews}" varStatus="loop">
	                    <div class="comentario">
	                    	<div class="a-pachas">
	                    		<div class="mini-valoracion-texto">${review.grade}</div> 
	                    		<div class="useerr"> ${nombresUsuarios[loop.index]}</div>
	                    	</div>
	                        
	                        <div class="nombre-pais"> 
	                            <img src="${pageContext.request.contextPath}/images/idioma.jpg" alt="España"> España
	                        </div>
	                        <div class="comentario-texto">
	                            "${review.review}"
	                        </div>
	                    </div>
		            </c:forEach>
		        </div>
		    </div>
		</c:if>
		
		<c:if test="${alojamiento.idu!=user.id&& !commented}">
		   <h2>Escribe tu propia Reseña</h2>
		    <form action="user/AddReviewServlet.do" method="post">
		   		<input type="hidden" name="idp" value="${alojamiento.id}">
		        <textarea id="texto" name="texto" rows="4" cols="50"></textarea><br>
		        <label for="nota">Nota (del 0 al 5):</label>
		        <output id="output" name="output" for="nota">0</output><br>
		        <input type="range" id="nota" name="nota" min="0" max="5" step="1" value="0" oninput="output.value = nota.value"><br>
		        <button class="reserva">Enviar reseña</button>
		    </form>
		</c:if>
</div>
</body>
</html>
