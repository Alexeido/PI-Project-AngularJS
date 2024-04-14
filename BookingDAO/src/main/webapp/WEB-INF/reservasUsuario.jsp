<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservas realizadas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alojamientosuser.css">
</head>
<body>

    <header>
        <div class="left-content">
            <button class="start_button"><a href="<c:url value='IndexLinkServlet.do'/>">Booking.com</a></button>
        </div>
        <div class="right-content">
            <div class="right-icons">
                <img src="${pageContext.request.contextPath}/images/idioma.jpg" alt="Idiomas" width="30" height="30">
                <img src="${pageContext.request.contextPath}/images/ayuda.jpg" alt="Ayuda" width="30" height="30">
            </div>
        </div>
    </header>

    <div class="container">
        <h1>Reservas realizadas</h1>
        <div class="help-message">
            <p>¿Tiene algún problema?</p>
        </div>
        <div class="Elemento-contatiner">
        <c:forEach var="reserva" items="${userBooks}" varStatus="loop">
        
            <div class="c2_alojamiento">
                <div class="Elemento-c2">
                    <div class="foto-c2">
                        <img src="${pageContext.request.contextPath}/images/alojamiento1.png" alt="Google Maps">
                    </div>
 
                    <div class="Texto-c2">
                        <div class="location">
                            <h1>${userBookedAccommodations[loop.index].prop.name} en ${userBookedAccommodations[loop.index].prop.city}</h1>
                            </div>
							<p>Desglose total: ${reserva.totalPrice}€</p>

                    </div>
					<div class="datos">
					    <div class="button-row">
						    <form action="reservasDetalleLinkServlet.do" method="post">
						        <input type="hidden" name="idb" value="${reserva.id}">
						        <button type="submit">Ver en detalle</button>
						    </form>
						</div>
						<div class="button-row">
						    <form action="AnularBookServlet.do" method="post">
						        <input type="hidden" name="idb" value="${reserva.id}">
						        <button type="submit" class="eliminar">Anular Reserva</button>
						    </form>
						</div>
					</div>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</body>
</html>
