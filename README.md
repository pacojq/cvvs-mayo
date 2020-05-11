# cvvs-mayo

## Ejecución

Para la correcta ejecución del proyecto, se requiere una base de datos en ejecución con las 
siguientes características:

- **URL:** jdbc:hsqldb:hsql://localhost:9001
- **Driver:** org.hsqldb.jdbcDriver
- **Usuario:** SA
- **Contraseña:** 

Para resetear la base de datos se puede descomentar ```spring.jpa.hibernate.ddl-auto=create``` 
en el archivo ```application.properties``` del proyecto.

Una base de datos puede encontrarse en la carpeta ```res``` de este mismo repositorio. Para ejecutarla,
se debe descomprimir el archivo y ejecutar ```hsqldb\bin\RunServer.bat```.

## Tests

De la misma manera, para realizar los tests se deben actualizar las siguientes rutas en
la clase ```com.uniovi.tests.util.TestUtils```:

```
    public final static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    public final static String Geckdriver024 = "D:\\Desktop\\CVVS-MAYO\\geckodriver024win64.exe";
```

Para ejecutar las pruebas con Selenium, Mozilla Firefox debe estar ejecutandose en el ordenador 
antes de empezar las pruebas.
