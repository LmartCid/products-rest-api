# Products REST API

REST API desarrollada con Java y Spring Boot para la gestión de productos y categorías.

## Funcionalidades

- Crear, consultar, actualizar y eliminar productos.
- Gestión de categorías.
- Filtrado de productos por categoría.
- Relación entre productos y categorías mediante JPA.
- Validación de los datos recibidos.
- Manejo global de excepciones.
- Uso de DTOs para separar las entidades de persistencia de los datos de entrada y salida.

## Tecnologías

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Jakarta Validation
- Maven

## Arquitectura

La aplicación está organizada en diferentes capas:

- Controller: gestión de las peticiones HTTP.
- Service: lógica de negocio y transformación de datos entre entidades y DTOs.
- Repository: acceso a la base de datos mediante Spring Data JPA.
- DTO: objetos utilizados para las peticiones y respuestas de la API.
- Exceptions: manejo centralizado de errores. 

## Modelo de datos

La API contiene dos entidades principales:

- Product
- Category

Una categoría puede contener varios productos y cada producto pertenece a una categoría.

## Autor

Luis Martínez