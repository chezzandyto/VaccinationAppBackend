# VaccineApplication Inventory

Esta es la implementación de la Aplicación de Inventario de Vacunas de Empleados




## Author

- [@chezzandyto](https://www.github.com/chezzandyto)



## Instalación

Requiere:
- Base de Datos Postgres, crear DB con nombre "vaccines_kruger": ver (schema.sql y data.sql para crear las tablas)
  Editar el archivo **application.properties** con las credenciales de la base de datos de su PC

```
spring.datasource.url=jdbc:postgresql://localhost:5432/vaccines_kruger
spring.datasource.username=<usernamePSQL>
spring.datasource.password=<passwordPSQL>
```
- Java 11
- SpringBoot 2.7.3
- Maven

Se recomienda Lanzar la aplicación desde IntelliJ Idea.

## Postman
- Se incluye un archivo Postman Collection para mayor facilidad de probar la API (VaccineApp.postman_collection.json)
- Considerar que se debe iniciar sesión al comenzar para generar los Tokens Necesarios