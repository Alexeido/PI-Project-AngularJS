<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ambassador Playa I, Benidorm | Booking.com</title>
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
        <div class="location-section">
            <span><a href="#">Inicio</a> > <a href="#">Hoteles</a> > <a href="#">España</a> > <a href="#">Comunidad Valenciana</a> > <a href="#">Benidorm</a> > Ofertas en Ambassador Playa I (Hotel), Benidorm (España)</span>
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
                            <img src="${pageContext.request.contextPath}/images/star.svg" alt="Estrella">
                            <img src="${pageContext.request.contextPath}/images/star.svg" alt="Estrella">
                            <img src="${pageContext.request.contextPath}/images/star.svg" alt="Estrella">
                            <img src="${pageContext.request.contextPath}/images/star.svg" alt="Estrella">
                        </div>
                        <div class="right-section">
                            <button class="me-gusta"><img src="${pageContext.request.contextPath}/images/heart.svg" alt="like"></button>
                            <form action="carrito.html">
                                <button class="reserva">Reserva ahora</button>
                            </form>
                              
                        </div>
                    </div>
                    <div class="g2-2F2">
                        Ambassador Playa I
                    </div>
                    <div class="g2-2F3">
                        <img src="${pageContext.request.contextPath}/images/gps.svg" alt="GPS" class="gps-icon">
                        <p>
                            <a href="#">Gerona, 41, 03503 Benidorm, España</a> – 
                            <a href="#">Ubicación excelente</a> - 
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
                <p>El Ambassador Playa I está situado en el centro de Benidorm, a 350 metros de la playa de Levante. Ofrece piscina y restaurante. Este hotel cuenta con sauna y centro de fitness.</p>
                <p>Las habitaciones presentan una decoración sencilla y disponen de balcón pequeño. El baño privado incluye ducha de agua fría y caliente, secador de pelo y artículos de aseo gratuitos. Hay TV de pantalla plana vía satélite y wifi gratuita.</p>
                <p>Se organizan numerosas actividades de ocio, entre ellas conciertos y espectáculos en directo cada semana. El personal de la recepción 24 horas presta un servicio de información turística. </p>
                <p>El Ambassador Playa I se encuentra a 10 minutos en coche del parque temático Tierra Mítica y a 2,6 km del parque acuático Aqualandia. El aeropuerto de Alicante está a 59 km.</p>
                <p id="servicios" class="minitext">Las distancias en la descripción del alojamiento se calculan con OpenStreetMap©</p>
                <h3>Servicios más populares</h3>
                <ul>
                    <li><img src="${pageContext.request.contextPath}/images/swim.svg" alt="Icono de piscina">2 piscinas</li>
                    <li><img src="${pageContext.request.contextPath}/images/parking.svg" alt="Icono de parking">Parking privado</li>
                    <li><img src="${pageContext.request.contextPath}/images/wifi.svg" alt="Icono de WiFi">WiFi gratis</li>
                    <li><img src="${pageContext.request.contextPath}/images/spa.svg" alt="Icono de spa">Spa y centro de bienestar</li>
                    <li><img src="${pageContext.request.contextPath}/images/restaurant.svg" alt="Icono de restaurante">Restaurante</li>
                    <li><img src="${pageContext.request.contextPath}/images/gym.svg" alt="Icono de gimnasio">Gimnasio</li>
                    <li><img src="${pageContext.request.contextPath}/images/smoke.svg" alt="Icono de prohibido fumar">Habitaciones sin humo</li>
                    <li><img src="${pageContext.request.contextPath}/images/reception.svg" alt="Icono de recepción">Recepción 24 horas</li>
                    <li><img src="${pageContext.request.contextPath}/images/bar.svg" alt="Icono de bar">Bar</li>
                    <li><img src="${pageContext.request.contextPath}/images/dog.svg" alt="Icono de perro">Permite mascotas</li>
                </ul>
            </div>
            <div class="d2">
                <h3>Puntos fuertes del alojamiento</h3>
                <p><img src="${pageContext.request.contextPath}/images/gps_B.svg" alt="Icono">Situado en la zona mejor valorada de Benidorm, este hotel tiene una excelente puntuación en ubicación (9,2).</p>
                <p><img src="${pageContext.request.contextPath}/images/parking_B.svg" alt="Icono">Hay parking privado en el hotel.</p>
            
                <h3>Clientes fieles</h3>
                <p><img src="${pageContext.request.contextPath}/images/check.svg" alt="Icono">Los clientes repiten más aquí que en otros alojamientos.</p>
            
                <form action="carrito.html">
                    <button class="reserva">Reserva ahora</button>
                </form>
                <button class="reserva"><img src="${pageContext.request.contextPath}/images/tlf.svg" alt="icono de telefono"> 123 456 789</button>
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
                            <th>Número de personas</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                Habitación Doble con vistas a la piscina
                                <br>
                                <p>2 camas individuales<img src="${pageContext.request.contextPath}/images/Bed.svg" alt="Icono de cama"><img src="${pageContext.request.contextPath}/images/Bed.svg" alt="Icono de cama"></p>
                                </td>
                            <td>
                                <img src="${pageContext.request.contextPath}/images/person.svg" alt="Person">
                                <img src="${pageContext.request.contextPath}/images/person.svg" alt="Person">
                            </td>
                            <td>
                                <form action="carrito.html">
                                    <button class="reserva">Reserva ahora</button>
                                </form>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Habitación Doble con vistas a la ciudad
                                <br>
                                <p>2 camas individuales<img src="${pageContext.request.contextPath}/images/Bed.svg" alt="Icono de cama"><img src="${pageContext.request.contextPath}/images/Bed.svg" alt="Icono de cama"></p>
                                </td>
                            <td>
                                <img src="${pageContext.request.contextPath}/images/person.svg" alt="Person">
                                <img src="${pageContext.request.contextPath}/images/person.svg" alt="Person">
                            </td>
                            <td><form action="carrito.html">
                                <button class="reserva">Reserva ahora</button>
                            </form></td>
                        </tr>
                        <tr>
                            <td>
                                Habitación Individual
                                <br>
                                <p>1 cama individual<img src="${pageContext.request.contextPath}/images/Bed_B.svg" alt="Icono de cama"></p>
                                </td>
                            <td>
                                <img src="${pageContext.request.contextPath}/images/person.svg" alt="Person">
                            </td>
                            <td><form action="carrito.html">
                                <button class="reserva">Reserva ahora</button>
                            </form></td>
                        </tr>
                        <tr>
                            <td>
                                Habitación Individual con vistas a la piscina
                                <br>
                                <p>1 cama individual<img src="${pageContext.request.contextPath}/images/Bed_B.svg" alt="Icono de cama"></p>
                                </td>
                            <td>
                                <img src="${pageContext.request.contextPath}/images/person.svg" alt="Person">
                            </td>
                            <td><form action="carrito.html">
                                <button class="reserva">Reserva ahora</button>
                            </form></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div id="comentarios" class="comentarios">
            <h2 style="font-weight: bold; font-size: 24px;">Comentarios de los clientes</h2>
            <div class="media-valoracion">
                <div class="media-valoracion-texto">9,1</div>
                <div class="media-valoracion-texto-derecha">Fantástico</div>
            </div>
            <div class="comentario-container">
                <div class="comentario">
                    <div class="nombre-pais"> 
                        <img src="${pageContext.request.contextPath}/images/idioma.jpg" alt="España"> España
                    </div>
                    <div class="coment-persona">
                        <img src="${pageContext.request.contextPath}/images/person.svg" alt="persona">Magdalena
                    </div>
                    <div class="comentario-texto">
                        “Me gustó mucho el hotel, estaba super limpio la comida bien, era variada el personal muy amable, lo que me gustó menos era la música del bar era toda en inglés en español nada de nada pero bien, volvería sin dudarlo”
                    </div>
                </div>
                <div class="comentario">
                    <div class="nombre-pais"> 
                        <img src="${pageContext.request.contextPath}/images/idioma.jpg" alt="España"> España
                    </div>
                    <div class="coment-persona">
                        <img src="${pageContext.request.contextPath}/images/person.svg" alt="persona">Natera
                    </div>
                    <div class="comentario-texto">
                        “Limpio, céntrico, buena cocina y excelente servicio.”
                    </div>
                </div>
                <div class="comentario">
                    <div class="nombre-pais"> 
                        <img src="${pageContext.request.contextPath}/images/idioma.jpg" alt="España"> España
                    </div>
                    <div class="coment-persona">
                        <img src="${pageContext.request.contextPath}/images/person.svg" alt="persona">Alexeido
                    </div>
                    <div class="comentario-texto">
                        “Todo muy bien, instalaciones, limpieza, buffet variado y personal muy amable”
                    </div>
                </div>
            </div>
            <button class="reserva">Leer todos los comentarios</button>
        </div>
        
</div>
</body>
</html>
