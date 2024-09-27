
# **PRUEBA BACKEND ALTERNOVA**

Para esto queremos que crees un proyecto pequeño usando este framework y las siguientes pautas:



Una API de una plataforma de streaming documentada usando postman o swagger que exponga los siguientes endpoints:

- Obtener una película o seria aleatoria

- Obtener todas las películas o series ordenadas por (Nombre, tipo, género o puntaje)

- Filtros por nombre, tipo, genero

- Marcar una película o serie como vista

- Puntuar una película o serie

Una película o seria tendrá los siguientes atributos:

- Id

- Nombre

- Genero

- Tipo (serie o película)

- No de visualizaciones

- Puntaje (promedio de todas las puntuaciones (1 - 5)

El sistema de autenticación y la base de datos serán de libre elección



Nota: Un usuario solo puede puntuar y/o marcar como vista una película una sola vez. 

Entregable documentación en postman

## Descripción

Este proyecto es una API RESTful para una plataforma de streaming que permite gestionar películas y series. La API permite obtener una película o serie aleatoria, listar todas las películas o series, filtrarlas por nombre, género o tipo, marcarlas como vistas y puntuar cada título.


## Tecnologías

- **Java 17** o superior
- **Spring Boot** Spring CLI v3.3.4
- **PostgreSQL** como base de datos
- **Maven** para la gestión de dependencias
- **JWT (JSON Web Tokens)** para la autenticación
- **IntelliJ IDEA** como IDE principal
- **Postman** para pruebas de la API

## Login (Generación de JWT)

Este endpoint permite a los usuarios autenticarse y generar un JWT (JSON Web Token) para consumir los servicios de la API.

**URL:** `http://localhost:8082/auth/login`


Los usuarios se deben crear por base de datos, la contraseña que se se asocian a un usuario debe ser encriptada, en esta caso se uso Bcrypt para encriptar la contraseña al momento de ingresar un nuevo usuario.

### Cuerpo de la solicitud

{
   **"password"**: 1234

   **"username"**: "prueba@gmail.com"
}

### Descripción
El JWT generado tras un login exitoso se debe utilizar para autenticar y consumir otros servicios de la API. Asegúrate de incluir el token en el encabezado de cada solicitud:

`Authorization: Bearer <tu_jwt>`

### Consideraciones adicionales

- Los usuarios se deben crear manualmente desde la base de datos.

- Las contraseñas que se asocian a un usuario deben estar encriptadas. Para este proyecto, se utiliza Bcrypt para la encriptación de contraseñas.

-Asegúrate de encriptar las contraseñas al crear nuevos usuarios en la base de datos.


## Endpoints

### 1. Obtener película o serie aleatoria

**URL:** `/api/peliculas-series/aleatoria`

**Método:** `GET`

**Descripción:** Devuelve una película o serie aleatoria de la base de datos.

### 2. Listar películas o series ordenadas

**URL:** `/api/peliculas-series`

**Método:** `GET`

**Descripción:** Lista todas las películas o series ordenadas por un campo especificado (nombre, tipo, género o puntaje).

**Parámetros:**
- `ordenarPor` (opcional, por defecto: `nombre`)

### 3. Filtrar películas o series

**URL:** `/api/peliculas-series/filtro`

**Método:** `GET`

**Descripción:** Filtra las películas o series por nombre, tipo o género.

**Parámetros:**
- `nombre` (opcional)
- `tipo` (opcional)
- `genero` (opcional)

### 4. Marcar como vista

**URL:** `/api/peliculas-series/{id}/vista`

**Método:** `POST`

**Descripción:** Marca una película o serie como vista y aumenta el contador de visualizaciones.

### 5. Puntuar una película o serie

**URL:** `/api/peliculas-series/{id}/puntuar`

**Método:** `POST`

**Descripción:** Puntúa una película o serie con un valor entre 1 y 5.

**Parámetros:**
- `puntaje` (obligatorio)


## Dependencias Importantes

El proyecto usa las siguientes dependencias principales:

- Spring Boot Starter Web: para la creación de APIs RESTful.
- Spring Boot Starter Data JPA: para la interacción con bases de datos.
- PostgreSQL Driver: para la conexión con la base de datos PostgreSQL.
- JWT (JSON Web Token): para la autenticación y seguridad.
- Lombok: para generar automáticamente código repetitivo como getters, setters y constructores, reduciendo el código boilerplate.

## Configuración del Proyecto

### Requisitos Previos

- Tener instalado **Java 17** o superior.
- Tener una base de datos **PostgreSQL** configurada.

### Configuración de la Base de Datos

1. Crea una base de datos PostgreSQL llamada `alternova`.
2. Configura el archivo `application.properties` o `application.yml` en el proyecto para que apunte a tu base de datos.

```properties
server.port=8082
spring.datasource.url=jdbc:postgresql://localhost:5432/alternova
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
