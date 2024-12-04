
# PI-Project-AngularJS

**PI-Project-AngularJS** es un clon de la web de **Booking**, desarrollado como parte de la asignatura **Programación en Internet** de **3º de Ingeniería Informática del Software** en la **Universidad de Extremadura (UEx)** durante el curso 2023-2024.

Este proyecto, creado por **Alejandro Barrena Millán** y **Pablo Natera Muñoz**, obtuvo una calificación de **10**. Además, incluye una rama alternativa con la implementación en **JSP**, que también recibió la misma nota en su entrega.

## Tecnologías utilizadas

- **Frontend**: AngularJS
- **Backend**: API REST desarrollada en Java
- **Base de datos**: SQLite
- **Servidor web**: Apache

## Funcionalidades

La aplicación incluye las siguientes funcionalidades principales:

1. **Página principal**: Interfaz limpia y funcional para los usuarios.
2. **Búsquedas por texto**: Encuentra hoteles mediante palabras clave.
3. **Ordenación de resultados**: Organiza los resultados según diferentes criterios.
4. **Añadir hoteles y habitaciones**: Permite a los administradores agregar nuevos hoteles y habitaciones.
5. **Reservar habitaciones**: Los usuarios pueden gestionar sus reservas de manera sencilla.
6. **Gestión de reservas**: Consulta, modifica o elimina reservas existentes.

## Estructura del proyecto

### Rama principal: AngularJS
La implementación principal utiliza AngularJS para el frontend, con una API REST en Java que se conecta a una base de datos SQLite y se despliega en **Apache**.

### Rama alternativa: JSP
En esta rama, el frontend está desarrollado utilizando **JSP** en lugar de AngularJS. También obtuvo una calificación de **10**.

## Instalación y ejecución

### Requisitos previos
- Java JDK (versión compatible con el backend)
- Node.js y npm (para gestionar AngularJS)
- SQLite (para la base de datos)
- Apache (como servidor web)

### Pasos para la instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Alexeido/PI-Project-AngularJS.git
   cd PI-Project-AngularJS
   ```

2. Configura la base de datos SQLite:
   - Encuentra el archivo de la base de datos en `Documentos/sqlite_dbs/Booking.db`.

3. Configura el servidor Apache:
   - Asegúrate de que Apache está instalado y en ejecución.
   - Copia los archivos del frontend al directorio raíz configurado de Apache.
   - Configura Apache para que redirija las solicitudes al backend REST si es necesario.

4. Ejecuta el backend:
   - Ve al directorio del backend y compílalo:
     ```bash
     javac -cp .:lib/* Main.java
     java -cp .:lib/* Main
     ```

5. Accede a la aplicación:
   - Abre tu navegador y ve a [http://localhost](http://localhost).

## Contribuciones

Este proyecto fue desarrollado exclusivamente para fines educativos. No se aceptan contribuciones en este momento.

## Autores

- **Alejandro Barrena Millán**
- **Pablo Natera Muñoz**

## Licencia

Este proyecto no cuenta con una licencia pública y fue desarrollado únicamente como parte de un proyecto académico.

---

¡Gracias por visitar nuestro proyecto!
