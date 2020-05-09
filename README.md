# cvvs-mayo

## Ejecución

para la correcta ejecución del proyecto, se requiere una base de datos en ejecución con las 
siguientes características:

- **URL:** jdbc:hsqldb:hsql://localhost:9001
- **Driver:** org.hsqldb.jdbcDriver
- **Usuario:** SA
- **Contraseña:** 

Para resetear la base de datos se puede descomentar ```spring.jpa.hibernate.ddl-auto=create``` 
en el archivo ```application.properties``` del proyecto.

