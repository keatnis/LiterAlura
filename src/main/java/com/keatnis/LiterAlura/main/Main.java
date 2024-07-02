package com.keatnis.LiterAlura.main;

import com.keatnis.LiterAlura.service.LibroService;

import java.util.InputMismatchException;

import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);

    private LibroService appService;

    public Main(LibroService libroService) {
        this.appService = libroService;
    }

    public void menu() {

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
                System.out.println("Error: Introduza un numero.");
                scanner.next();
                continue;
            }
            switch (opcion) {
                case 1:
                    if (appService.getLibroEncontrado() != null) {
                        appService.buscarYGuardarDB();
                    } else {
                        continue;
                    }
                    break;
                case 2:
                    appService.mostrarLibrosRegistrados();
                    break;
                case 3:
                    appService.mostarAutoresRegistrados();
                    break;
                case 4:
                    appService.listarAutorPorAnio();
                    break;
                case 5:
                    appService.listarPorIdioma();
                    break;
                case 0:
                    System.out.println("Hasta la proxima, saliendo del programa ...");
                    break;
                default:
                    System.out.println("Seleccione una opcion valida.");
            }

        }

    }


}
