package com.keatnis.LiterAlura.main;

import com.keatnis.LiterAlura.service.LibroService;

import java.util.InputMismatchException;

import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);

    private LibroService service;

    public Main(LibroService libroService) {
        this.service = libroService;
    }

    public void menu() {
       /*
        * no se puede registar dos veces el mismo libro
        Salida de opcion 1:
        ---- Libro ---
        Titulo: Pride and Prejuice
        Autor: Austen, Jane
        Idioma: en
        Numero de decargas:
         */
        String menu = """
                --------------------------------
                ***** CHALLENGE LITERATURA ***** 
                --------------------------------
                 
                1- Buscar libro por titulo
                2- Listar libros registrados
                3- Listar autores registrados
                4- Listar autores vivos en un determinado año
                5- Listar libros por idioma
                0. salir            
                Introduzca el numero de opcion:                 
                """;

        int opcion = -1;
        while (opcion != 0) {

            System.out.println(menu);
            //validamos que el usuario introduzca un numero
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("Error: Introduza un numero.");
                scanner.next();
                continue;
            }
            switch (opcion) {
                case 1:
                    service.buscarYGuardarDB();
                    break;
                case 2:
                    service.mostrarLibrosRegistrados();
                    break;
                case 3:
                    service.mostarAutoresRegistrados();
                    break;
                case 4:
                    service.listarAutorPorAnio();
                    break;
                case 0:
                    System.out.println("Saliendo del program ...");
                    break;
                default:
                    System.out.println("Seleccione una opcion valida.");


            }

        }

    }


}
