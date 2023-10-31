
# API de Skins para Videojuegos

Esta API permite a las personas usuarias consultar, adquirir, modificar y eliminar skins para un videojuego. Está construida en Java Spring Boot y utiliza Swagger para la documentación. Los usuarios pueden interactuar con la API a través de Postman para probar sus funcionalidades.

---

## Tabla de contenidos
- [Requisitos](#requisitos)
- [Dependencias del Proyecto](#dependencias-del-proyecto)
- [Modelo de Skin](#modelo-de-skin)
- [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
- [Rutas de la API](#rutas-de-la-api)
- [Ejecución del Proyecto](#ejecución-del-proyecto)
- [Documentación de Postman](#documentación-de-postman)
- [Documentación de Swagger](#documentación-de-swagger)
- [Desafíos e Implementación](#desafíos-e-implementación)
- [Documentación en el Readme](#documentación-en-el-readme)

---

### Requisitos

Antes de utilizar esta API, asegúrate de cumplir con los siguientes requisitos:

- [Java JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- [Maven](https://maven.apache.org/download.cgi) instalado.
- MySQL configurado y accesible.
---

### Dependencias del Proyecto

- [spring-boot-starter-data-jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [spring-boot-starter-web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [springdoc-openapi-starter-webmvc-ui](https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui)
- [spring-boot-devtools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
- [mysql-connector-j](https://mvnrepository.com/artifact/mysql/mysql-connector-java)
- [spring-boot-starter-test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test)
- [jackson-databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
- [swagger-annotations](https://mvnrepository.com/artifact/io.swagger.core.v3/swagger-annotations)
---

### Modelo de Skin

La estructura de datos para representar las skins incluye los siguientes campos:

- `id`: Identificador único de la skin.
- `name`: Nombre de la skin.
- `type`: Tipo de skin.
- `price`: Precio de la skin.
- `color`: Color de la skin.
- `description`: Descripción de la skin.
---

### Configuración de la Base de Datos

Esta API se conecta a una base de datos para almacenar las skins adquiridas por los usuarios. Yo he legido MySQL para la configuración. Para configurar la base de datos, asegúrate de que la conexión esté definida en `application.properties`.

1- Asegurate de haber creado tu db schema previamente. 

2- En tu IDE, en application properties, cambia la url que accede a la base de datos y añade tu username y tu password.

![image](https://github.com/RobertoVetere/jump-2-digital-prueba/assets/42187726/ae212d4b-58bb-4d06-86bb-813774068d9a)

---

### Rutas de la API

La API ofrece las siguientes rutas:

- `GET /skins/avaible`: Devuelve una lista de todas las skins disponibles para comprar. **(Nota: el nombre "avaible" se mantiene según los requerimientos originales, aunque parece haber un error tipográfico en los requerimientos).**
- `POST /skins/buy`: Permite a los usuarios adquirir una skin y guardarla en la base de datos.
- `GET /skins/myskins`: Devuelve una lista de las skins compradas por el usuario.
- `PUT /skins/color`: Permite a los usuarios cambiar el color de una skin comprada.
- `DELETE /skins/delete/{id}`: Permite a los usuarios eliminar una skin comprada.
- `GET /skin/getskin/{id}`: Devuelve una determinada skin.
---

### Ejecución del Proyecto

Sigue estos pasos para ejecutar el proyecto:

1. Clona este repositorio en tu máquina local.

```bash
git clone https://github.com/tuusuario/tu-repo.git
cd tu-repo
mvn spring-boot:run

La aplicación se ejecutará en http://localhost:8080. Puedes acceder a la documentación de Swagger y probar la API o bien usar Postman tal y como indico a continuación.

```

---
### Documentación de Postman

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

---

### Documentación de Swagger

Puedes acceder a la documentación de la API y probar sus rutas utilizando Swagger. Para acceder 
a la documentación, ejecuta el proyecto y visita la siguiente URL en tu navegador:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/doc/swagger-ui/index.html)

Swagger proporciona una interfaz interactiva para explorar y probar todas las funcionalidades 
de la API.

---

### Desafíos e Implementación

### Definir el Modelo de Skin (Requisito 1):

El modelo de Skin se define en el paquete com.roberv.skin.models. Hemos enfrentado el desafío 
de diseñar una estructura de datos que contemple campos como id, nombre, tipo, precio, color y 
descripción., garantizando que esta estructura sea eficiente y escalable.

### Implementar una función para leer las skins disponibles desde un archivo (Requisito 2):

Uno de los desafíos clave fue cargar las skins disponibles desde un archivo JSON. Para 
resolverlo, hemos utilizado la biblioteca Jackson para mapear los datos del archivo a objetos 
Java. Esto permite a la aplicación acceder a las skins disponibles de manera eficiente.

### Configurar la Base de Datos (Requisito 3):

Conectar la aplicación a una base de datos (MySQL) planteó el desafío de establecer 
la configuración y la 
integración adecuadas. Hemos optado por utilizar el repositorio SkinRepository para 
interactuar con la base de datos, permitiendo así la persistencia y recuperación de datos 
relacionados con las skins.

### Creación de Excepciones Personalizadas:

Para manejar excepciones de manera efectiva, hemos implementado excepciones personalizadas, 
como **SkinNotFoundException**, **SkinPurchaseException** y **EmptyColorException**. 
Cada excepción se lanza en situaciones específicas, permitiendo una gestión de errores más 
precisa y significativa. Esta personalización de excepciones es especialmente útil para 
proporcionar mensajes de error claros y detallados a los usuarios y desarrolladores.

### Creación de las siguientes rutas de la API (Requisito 4):

La creación de rutas de la API en el controlador SkinController fue un paso fundamental en el 
cumplimiento de los requisitos. Cada ruta cumple con una función específica, como obtener skins 
disponibles, permitir a los usuarios comprar skins, listar las skins del usuario y más.

### Implementación del DTO con el Patrón Builder:

Uno de los aspectos clave de la implementación es el uso del patrón Builder en el SkinDTO. 
Esto resuelve el desafío de construir objetos complejos, ya que permite configurar un objeto 
DTO de manera más legible y mantenerlo inmutable. El patrón Builder se utiliza en la creación 
de objetos SkinDTO, proporcionando una forma clara y eficiente de construir instancias de DTO 
con varios campos. Esto contribuye a que la aplicación sea escalable y a la vez garantiza una 
estructura de datos eficiente en las respuestas API.


### Documentación en el Readme:

El archivo README del repositorio contiene documentación detallada que describe cómo se han 
cumplido los requisitos específicos de la prueba técnica. También proporciona una guía para 
ejecutar la aplicación localmente, lo que facilita la comprensión y el uso de la aplicación.

Desarrollado por Roberto Vetere
Contacto: [roberto.vetere@gmail.com](mailto:roberto.vetere@gmail.com) | Teléfono: +34 615-605-208

