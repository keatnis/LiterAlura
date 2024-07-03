# LiterAlura : Catalogo de Libros

## Descripcion del proyecto
Esta aplicacion es un catálogo de libros, realiza la solicitud de busqueda a la API https://gutendex.com.
Además, permite guardar la busqueda, filtrar y mostrar los libros y autores de interés.
Utiliza JPA y la implementacion de Hibernate para crear las tablas y atributos en la base de datos (PostgreSQL).

## Estado del Proyecto

  🚧 En construccion 🚧
 -  Falta agregar funcionalidades extra.
   
## ⭐ Caracteristicas y demostración del proyecto
El iniciar el proyecto se muestra el siguiente menú:

![image](https://github.com/keatnis/LiterAlura/assets/95552515/2699c5ea-0ee0-46d7-8f3a-13f77aae8d94)


A continuacion, se muestra como funciona cada opcion del menú.

* ` 1. Buscar libro por titulo: `

En esta opcion se introduce el titulo del libro que se desea buscar para que el programa realice la solicitud al API y 
guarde la informacion del primer resultado que encuentre en la base de datos (PostgreSQL).
Para esta opción se ocupó streams() y findFirst() para obtener el primer el resultado encontrado.

Al final notifica al usuario que se guardó la informacion del libro.
  
![image](https://github.com/keatnis/LiterAlura/assets/95552515/83b9f56f-dece-4761-8d63-2df142877cfd)

 🔴 Libro No encontrado. Al no encontrar un libro muestra un mensaje.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/ee693707-544d-4552-ba30-aa9681281d7c)

* `2. Listar libros registrados`

Esta opcion muestra todos los libros registrados en la base de datos.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/3a992408-47c8-43ac-ab95-79ba893aac51)

* `3. Listar autores registrados`

Esta opcion muestra los autores registrados en la base de datos, asi como los libros en los cuales son autores.
  
![image](https://github.com/keatnis/LiterAlura/assets/95552515/b5d27dc5-d663-4b5e-a67f-ca997c8603ab)

- `4. Listar autores vivos en un determinado año`

Es opcion muestra los autores vivos en el año que el usuario ingrese en la consola.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/31f07a60-e2fe-4090-bf22-56fd0330a88c)

 🔴  Autores no encontrados. Si no hay autores vivos en el año introducido, el programa muestra un mensaje.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/787f2777-ca1d-4f0f-a2be-60de34ace169)

* `5. Listar libros por idioma`

Esta opcion muestra el total de libros dependiendo del idioma que seleccione el usuario.
Al pulsar 1 se mostrara todos los libros encontrados con el idioma seleccionado.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/22cb97a7-5632-4b51-862f-76aae28c3f7d)

 🔴 Libros del idioma no encontrados.
Al NO encontrar libros con el idioma seleccionado en la base de datos, se mostrara el siguiente mensaje.

![image](https://github.com/keatnis/LiterAlura/assets/95552515/2fcce31a-b336-4d77-899d-0a69bf2bd601)



## ✅ Tecnologias utilizadas

- Spring Boot
- JPA
- Postgresql
- Intellij IDEA
- Java 17
- Jackson-databin
  
## Autores: 
🐈 Maria Torres 
  


  
