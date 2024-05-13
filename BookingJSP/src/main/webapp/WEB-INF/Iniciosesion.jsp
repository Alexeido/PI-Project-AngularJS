<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión - Booking.com</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/iniciosesion.css">
    
    

</head>

<body>
    <header>
        <div class="left-content">
            <button class="start_button"><a href="${pageContext.request.contextPath}/pages/Index.html">Booking.com</a></button>
        </div>
        <div class="right-content">
            <div class="right-icons">
                <img src="${pageContext.request.contextPath}/images/idioma.jpg" alt="Idiomas" width="30" height="30">
                <img src="${pageContext.request.contextPath}/images/ayuda.jpg" alt="Ayuda" width="30" height="30">
            </div>
        </div>
    </header>

    <main>
    	<form action="IniciarSesionLinkServlet.do" method="post">
        <table>
            <tr>
                <td>
                    <h2 class="less-margin">Inicia sesión</h2>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="nombre">Email</label>
                    <input type="text" id="nombre" name="nombre" class="email-input" required placeholder="Introduce tu correo electrónico">     
                </td>
            </tr>
            <tr>
                <td>
                    <label for="password">Contraseña</label>
                    <input type="password" id="password" name="password" class="password-input" required placeholder="Introduce tu contraseña">
                </td>
            </tr>
            <tr>
                <td>
                    <button class="iSsubmit" type="submit">Continuar</button>                    
                </td>
            </tr>
            <tr>
                <td  class="center-text">
                    <div class="lineizq"></div>
                    <span>o usa una de estas opciones</span>
                    <div class="lineder"></div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="images-container">
                        <a href="<c:url value='RegistrarseLinkServlet.do'/>"><img src="${pageContext.request.contextPath}/images/facebook.png" alt="Facebook" width="30"
                                height="30"></a>
                        <a href="<c:url value='RegistrarseLinkServlet.do'/>"><img src="${pageContext.request.contextPath}/images/google.png" alt="Google" width="30" height="30"></a>
                        <a href="<c:url value='RegistrarseLinkServlet.do'/>"><img src="${pageContext.request.contextPath}/images/apple.png" alt="Apple" width="30" height="30"></a>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="textochico">Todos los derechos reservados.<br>Copyright (2006 - 2024)
                    - Booking.com™</td>
            </tr>
        </table>
        </form>
    </main>

</body>

</html>