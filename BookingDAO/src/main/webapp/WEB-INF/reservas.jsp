<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reserva de Viaje</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reservas.css">
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
        <h1>Reservas y Viajes</h1>
        <div class="help-message">
            <p>¿No encuentra una reserva?</p>
        </div>
        <div class="Elemento-contatiner">
            <div class="location">
                <h1>Madrid</h1>
                <h2>23 Nov 2023 - 2 Enero 2024</h2>
            </div>
            <div class="c2_alojamiento">
                <div class="Elemento-c2">
                    <div class="foto-c2">
                        <img src="${pageContext.request.contextPath}/images/alojamiento1.png" alt="Google Maps">
                    </div>
                    <div class="Texto-c2">
                        <h2>Hospedería del Valle Boutique Apartments </h2>
                        <p>23 Nov 2023 - 02 Enero 2024</p>
                        <p>Cancelada</p>
                    </div>
                    <div class="datos">
                        <p>2 noches, 2 adultos</p>
                        <h1>805€</h1>
                        <button>Ver Estado de la reserva</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="Elemento-contatiner">
            <div class="location">
                <h1>Caceres</h1>
                <h2>4 Ene 2024 - 08 Enero 2024</h2>
            </div>
            <div class="c2_alojamiento">
                <div class="Elemento-c2">
                    <div class="foto-c2">
                        <img src="${pageContext.request.contextPath}/images/alojamiento2.png" alt="Google Maps">
                    </div>
                    <div class="Texto-c2">
                        <h2>Hospedería del Valle Boutique Apartments </h2>
                        <p>23 Nov 2023 - 02 Enero 2024</p>
                        <p>Cancelada</p>
                    </div>
                    <div class="datos">
                        <p>2 noches, 2 adultos</p>
                        <h1>1005€</h1>
                        <button>Ver Estado de la reserva</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="Elemento-contatiner">
            <div class="location">
                <h1>Barcelona</h1>
                <h2>2 Feb 2024 - 8 Feb 2024</h2>
            </div>
            <div class="c2_alojamiento">
                <div class="Elemento-c2">
                    <div class="foto-c2">
                        <img src="${pageContext.request.contextPath}/images/alojamiento3.png" alt="Google Maps">
                    </div>
                    <div class="Texto-c2">
                        <h2>Hospedería del Valle Boutique Apartments </h2>
                        <p>23 Nov 2023 - 02 Enero 2024</p>
                        <p>Cancelada</p>
                    </div>
                    <div class="datos">
                        <p>2 noches, 2 adultos</p>
                        <h1>1409€</h1>
                        <button>Ver Estado de la reserva</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="Elemento-contatiner">
            <div class="location">
                <h1>Valencia</h1>
                <h2>23 Marzo 2024 - 2 Abril 2024</h2>
            </div>
            <div class="c2_alojamiento">
                <div class="Elemento-c2">
                    <div class="foto-c2">
                        <img src="${pageContext.request.contextPath}/images/alojamiento4.png" alt="Google Maps">
                    </div>
                    <div class="Texto-c2">
                        <h2>Hospedería del Valle Boutique Apartments </h2>
                        <p>23 Nov 2023 - 02 Enero 2024</p>
                        <p>Cancelada</p>
                    </div>
                    <div class="datos">
                        <p>2 noches, 2 adultos</p>
                        <h1>900€</h1>
                        <button>Ver Estado de la reserva</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>