# LiterAlura : Catalogo de Libros

## Descripcion del proyecto
Esta aplicacion es un catálogo de libros, realiza la solicitud de busqueda a la API https://gutendex.com.
Además, permite guardar la busqueda, filtrar y mostrar los libros y autores de interés.
Utiliza JPA y la implementacion de Hibernate para crear las tablas y atributos en la base de datos (PostgreSQL).
  
## ⭐ Caracteristicas y demostración del proyecto
Al iniciar el proyecto se muestra el siguiente menú en la consola:

![image](https://github.com/keatnis/LiterAlura/assets/95552515/85644f0a-4841-4b7c-9d58-5c23925b171a)


A continuacion, se muestra como funciona cada opcion del menú.

* ` 1. Buscar libro por titulo: `

En esta opción se introduce el titulo del libro que se desea buscar para que el programa realice la solicitud al API y 
guarde la informacion del primer resultado que encuentre en la base de datos (PostgreSQL).
Para esta opción se ocupó streams() y findFirst() para obtener el primer el resultado encontrado.

Al final notifica al usuario que se guardó la informacion del libro.
  
![image](https://github.com/keatnis/LiterAlura/assets/95552515/bcc75016-04a1-4a7d-aff3-df959529ed19)


 🔴 Libro No encontrado. Al no encontrar un libro muestra un mensaje.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/c3db1096-69ab-4433-8eca-f0541086c63f)


* `2. Listar libros registrados`

Esta opción muestra la información de todos los libros registrados en la base de datos.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/e9286ca3-8ea9-4991-9ff8-9d289dd9302a)


* `3. Listar autores registrados`

Esta opción muestra los autores registrados en la base de datos, asi como los libros en los cuales son autores.
  
![image](https://github.com/keatnis/LiterAlura/assets/95552515/8a878f04-44df-4359-968a-a2690c25c12f)

- `4. Listar autores vivos en un determinado año`

Es opción muestra los autores vivos en el año que el usuario ingrese en la consola.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/1c14877b-4717-4802-b0aa-d7a7d5ff46b0)


 🔴  Autores no encontrados. Si no hay autores vivos en el año introducido, el programa muestra un mensaje.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/37309d18-7f2f-4aeb-a162-f1876313e6ff)


* `5. Listar libros por idioma`

Esta opción muestra el total de libros dependiendo del idioma que seleccione el usuario.
Al pulsar 1 se mostrara todos los libros encontrados con el idioma seleccionado.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/a4d44916-f624-42b9-82c1-ad830bc233bf)


 🔴 Libros del idioma no encontrados.
Al NO encontrar libros con el idioma seleccionado en la base de datos, se mostrara el siguiente mensaje.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/9348f654-6ad9-46b4-a387-e24f9d8dd36f)

## Funciones extras

`6. Top 10 libros mas descargados`

Esta opción muestra los 10 libros mas descargados de Project Gutenberg usando el API de Gutendex.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/c501d0e3-352d-4cfc-ad0e-65d616075d5d)

`7. Estadisticas de descargas `

Esta opción muestra las estadisticas de los libros registrados en la base de datos de acuerdo al numero descargas usando IntSummaryStatistics. 

![image](https://github.com/keatnis/LiterAlura/assets/95552515/4c4f0e94-4b5b-4ed4-8539-5b20f9e34259)

`8. Buscar autor por nombre en la base de datos`

Esta opción busca en el registro de la base de datos el nombre del autor ingresado por el usuario en la consola, en caso de encontrar una coidcidencia el programa muestra los datos del autor.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/f232eb9f-fe87-40e0-a2b0-2cbed2cfbc3b)

Se introduze el 0 para salir de la aplicación.
## ✅ Tecnologias utilizadas

- Spring Boot
- JPA
- Postgresql
- Intellij IDEA
- Java 17
- Jackson-databin
  
## Autores: 
🐈 Maria Torres 
  


  
