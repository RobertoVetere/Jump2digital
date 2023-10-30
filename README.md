
# API de Skins para Videojuegos

Esta API permite a las personas usuarias consultar, adquirir, modificar y eliminar skins para un videojuego. Está construida en Java Spring Boot y utiliza Swagger para la documentación. Los usuarios pueden interactuar con la API a través de Postman para probar sus funcionalidades.

## Requisitos

Antes de utilizar esta API, asegúrate de cumplir con los siguientes requisitos:

- Java JDK instalado.
- Maven instalado.
- MySQL configurado y accesible.

## Modelo de Skin

La estructura de datos para representar las skins incluye los siguientes campos:

- `id`: Identificador único de la skin.
- `nombre`: Nombre de la skin.
- `tipo`: Tipo de skin.
- `precio`: Precio de la skin.
- `color`: Color de la skin.
- `description`: Descripción de la skin.

## Configuración de la Base de Datos

Esta API se conecta a una base de datos para almacenar las skins adquiridas por los usuarios. Yo he legido MySQL para la configuración. Para configurar la base de datos, asegúrate de que la conexión esté definida en `application.properties`.

1- Asegurate de haber creado tu db schema previamente. 

2- En tu IDE, en application properties, cambia la url que accede a la base de datos y añade tu username y tu password.

![image](https://github.com/RobertoVetere/jump-2-digital-prueba/assets/42187726/ae212d4b-58bb-4d06-86bb-813774068d9a)

## Rutas de la API

La API ofrece las siguientes rutas:

- `GET /skins/avaible`: Devuelve una lista de todas las skins disponibles para comprar. **(Nota: el nombre "avaible" se mantiene según los requerimientos originales, aunque parece haber un error tipográfico en los requerimientos).**
- `POST /skins/buy`: Permite a los usuarios adquirir una skin y guardarla en la base de datos.
- `GET /skins/myskins`: Devuelve una lista de las skins compradas por el usuario.
- `PUT /skins/color`: Permite a los usuarios cambiar el color de una skin comprada.
- `DELETE /skins/delete/{id}`: Permite a los usuarios eliminar una skin comprada.
- `GET /skin/getskin/{id}`: Devuelve una determinada skin.

## Ejecución del Proyecto

Sigue estos pasos para ejecutar el proyecto:

1. Clona este repositorio en tu máquina local.

```bash
git clone https://github.com/tuusuario/tu-repo.git
cd tu-repo
mvn spring-boot:run

La aplicación se ejecutará en http://localhost:8080. Puedes acceder a la documentación de Swagger y probar la API o bien usar Postman tal y como indico a continuación.

```
## Documentación de Postman

Dispones de una colección de Postman que te permitirá probar las rutas de la API de forma sencilla. Sigue estos pasos para descargar e importar la colección en tu aplicación Postman:

1. Descarga la colección de Postman haciendo clic en el siguiente enlace:

   [Descargar Colección de Postman](https://drive.google.com/file/d/15UNKrJSqkRU7815EdgzybatZLOCKzBVq/view?usp=drive_link)

2. Abre la aplicación Postman en tu computadora.

3. En la parte superior izquierda de la interfaz de Postman, haz clic en el botón "Import" para cargar la colección.

4. Selecciona el archivo descargado y confirma la importación.

5. Una vez importada la colección, verás un conjunto de peticiones preparadas para probar todas las rutas de la API.

6. Asegúrate de que el servidor de tu aplicación Spring Boot esté en ejecución.

7. Haz clic en cada solicitud en la colección y ajusta los parámetros según sea necesario.

8. Ejecuta las solicitudes para interactuar con la API y verificar su funcionamiento.

Esta colección de Postman te facilitará la interacción y prueba de la API de Skins para Videojuegos.

## Documentación de Swagger

Puedes acceder a la documentación de la API y probar sus rutas utilizando Swagger. Para acceder a la documentación, ejecuta el proyecto y visita la siguiente URL en tu navegador:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/doc/swagger-ui/index.html)

Swagger proporciona una interfaz interactiva para explorar y probar todas las funcionalidades de la API.

Desarrollado por Roberto Vetere
Contacto: [roberto.vetere@gmail.com](mailto:roberto.vetere@gmail.com) | Teléfono: 615-605-208

