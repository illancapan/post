# API de Gestión de Publicaciones (Posts)

Esta es una API RESTful para gestionar publicaciones (posts), implementada con Spring Boot, conectada a una base de datos H2, y que expone operaciones básicas de CRUD: GET, GET by ID, POST, PUT, y DELETE. El proyecto incluye pruebas unitarias, cobertura de código con JaCoCo, y utiliza MapStruct y Lombok para simplificar el código.

## Funcionalidades

- `GET /posts`: Recupera todas las publicaciones.
- `GET /posts/{id}`: Recupera una publicación por su ID.
- `POST /posts`: Crea una nueva publicación.
- `PUT /posts/{id}`: Actualiza una publicación existente.
- `DELETE /posts/{id}`: Elimina una publicación.

## Tecnologías

- Spring Boot: Para el desarrollo del backend.
- H2 Database: Base de datos en memoria para almacenamiento rápido y pruebas.
- JUnit 5: Para pruebas unitarias.
- Mockito: Para realizar simulaciones de servicios en las pruebas.
- JaCoCo: Para la cobertura de pruebas.
- Lombok: Para reducir el código repetitivo.
- MapStruct: Para el mapeo de objetos DTO a entidades.

## Requisitos

Antes de comenzar, asegúrate de tener lo siguiente:

- Java 17+ instalado.
- Gradle instalado (o utiliza el wrapper de Gradle proporcionado en el proyecto).
- Postman o cualquier otra herramienta para realizar pruebas de APIs.

## Instalación

1. Clona el repositorio:

   Bash

   ```
   git clone https://github.com/illancapan/post
   cd post
   ```

2. Compila el proyecto:

   Usa Gradle para construir el proyecto y resolver las dependencias:

   Bash

   ```
   ./gradlew build
   ```

3. Ejecuta la aplicación:

   Para iniciar el servidor de la API, ejecuta el siguiente comando:

   Bash

   ```
   ./gradlew bootRun
   ```

   La API estará disponible en `http://localhost:8080`.

## Endpoints de la API

1. **GET /posts**

   Recupera todas las publicaciones.

   Respuesta exitosa (200 OK):

   JSON

   ```
   [
     {
       "id": 1,
       "title": "Post Title 1",
       "content": "Content of the first post.",
       "author": "Author 1"
     },
     {
       "id": 2,
       "title": "Post Title 2",
       "content": "Content of the second post.",
       "author": "Author 2"
     }
   ]
   ```

2. **GET /posts/{id}**

   Recupera una publicación por su ID.

   Respuesta exitosa (200 OK):

   JSON

   ```
   {
     "id": 1,
     "title": "Post Title 1",
     "content": "Content of the first post.",
     "author": "Author 1"
   }
   ```

3. **POST /posts**

   Crea una nueva publicación.

   Solicitud:

   JSON

   ```
   {
     "title": "New Post Title",
     "content": "This is a new post content",
     "author": "New Author"
   }
   ```

   Respuesta exitosa (201 Created):

   JSON

   ```
   {
     "id": 3,
     "title": "New Post Title",
     "content": "This is a new post content",
     "author": "New Author"
   }
   ```

4. **PUT /posts/{id}**

   Actualiza una publicación existente.

   Solicitud:

   JSON

   ```
   {
     "title": "Updated Post Title",
     "content": "Updated content of the post",
     "author": "Updated Author"
   }
   ```

   Respuesta exitosa (200 OK):

   JSON

   ```
   {
     "id": 1,
     "title": "Updated Post Title",
     "content": "Updated content of the post",
     "author": "Updated Author"
   }
   ```

5. **DELETE /posts/{id}**

   Elimina una publicación por su ID.

   Respuesta exitosa (204 No Content): No devuelve ningún cuerpo de respuesta.

## Pruebas Unitarias

Las pruebas unitarias están implementadas utilizando JUnit 5 y Mockito para simular la capa de servicio. Puedes ejecutarlas con el siguiente comando:

Bash

```
./gradlew test
```

Las pruebas verifican las funcionalidades de la API, y JaCoCo se utiliza para generar un reporte de cobertura de pruebas.

## Generación de Reporte de Cobertura con JaCoCo

El reporte de cobertura de código generado por JaCoCo se encuentra en la siguiente ruta:

```
build/reports/jacoco/test/html/index.html
```

Abre este archivo en tu navegador para ver la cobertura de pruebas.

## Configuración de JaCoCo

El proyecto está configurado para generar un reporte de cobertura y verificar que se cumpla un mínimo de 80% de cobertura. Si las pruebas no cumplen con este criterio, el proceso de construcción fallará.
