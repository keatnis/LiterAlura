package com.keatnis.LiterAlura.main;

import com.keatnis.LiterAlura.dto.DatosLibro;
import com.keatnis.LiterAlura.dto.LibroDTO;
import com.keatnis.LiterAlura.model.Autor;
import com.keatnis.LiterAlura.model.Libro;
import com.keatnis.LiterAlura.repository.AutorRepository;
import com.keatnis.LiterAlura.repository.LibroRepository;
import com.keatnis.LiterAlura.service.ConsumoAPI;
import com.keatnis.LiterAlura.service.ConversorDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConversorDatos conversor = new ConversorDatos();
    private final String URL_BASE = "https://gutendex.com/books/?";
    //   private LibroDTO libroEncontrado;
    List<Libro> libros;

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    public Main(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;

    }

    public void menu() {

        String menu = """
                --------------------------------
                ***** BIENVENIDO/A A LITERALURA ***** 
                --------------------------------
                 
                1- Buscar libro por titulo
                2- Listar libros registrados
                3- Listar autores registrados
                4- Listar autores vivos en un determinado año
                5- Listar libros por idioma
                6- Top 10 de libros mas decargados
                7- Estadisticas de descargas
                8- Buscar autor por nombre en la base de datos
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
                scanner.nextLine();
                continue;
            }
            switch (opcion) {
                case 1:
                    buscarLibroAPI();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostarAutoresRegistrados();
                    break;
                case 4:
                    listarAutorPorAnio();
                    break;
                case 5:
                    listarPorIdioma();
                    break;
                case 6:
                    // appService.top10LibrosBaseDeDatos();
                    top10librosCosultaAPI();
                    break;
                case 7:
                    generarEstadisticas();
                    break;
                case 8:
                    buscarAutorPorNombre();
                    break;

                case 0:
                    System.out.println("Hasta la proxima, saliendo del programa ...");
                    break;
                default:
                    System.out.println("Seleccione una opcion valida.");
            }

        }

    }

    public void buscarLibroAPI() {
        System.out.println("Escriba el nombre del libro que desee buscar:");
        var titulo = scanner.nextLine();

        var json = consumoAPI.consultarAPI(URL_BASE + "search=" + titulo.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, DatosLibro.class);
        Optional<LibroDTO> libroEnc = datos.resultado().stream()
                .filter(l -> l.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .findFirst();
        if (libroEnc.isPresent()) {
            System.out.println("Libro encontrado: " + libroEnc.get().titulo());
            insertarLibro(libroEnc.get());

        } else {
            System.out.println("|* --- Mensaje: No se encontro un libro con este nombre, busque de nuevo");


        }

    }

    public void insertarLibro(LibroDTO libroEncontrado) {
        //verificamos si el libro no esta registrado
        Libro libro = new Libro(libroEncontrado);
        Optional<Libro> libroExiste = libroRepository.findByTituloContainingIgnoreCase(libroEncontrado.titulo());

        if (libroExiste.isPresent()) {
            System.out.println("|* --- Mensaje: El libro ya  esta registrado, no se puede registrar mas de una vez. ");

        } else {
            // verificamos si existe el autor en la base de datos
            Optional<Autor> autorExiste = autorRepository.findByNombreContainingIgnoreCase(libro.getAutor().getNombre());
            try {
                if (autorExiste.isPresent()) {
                    libro.setAutor(autorExiste.get());
                    libroRepository.save(libro);
                    System.out.println("|* --- Mensaje: Se registro el libro en la base de datos");
                } else {
                    autorRepository.save(libro.getAutor());
                    libroRepository.save(libro);
                    System.out.println(" |* --- Mensaje: Se regitro el libro y el autor ---*|");
                }
            } catch (DataIntegrityViolationException e) {
                System.out.println("** " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

    public void mostrarLibrosRegistrados() {
        libroRepository.findAll().forEach(System.out::println);
    }

    public void mostarAutoresRegistrados() {
        autorRepository.findAll()
                .forEach(System.out::println);
    }

    public void listarAutorPorAnio() {
        System.out.println("Ingrese a partir que año desea consultar para conocer los autores vivos en ese epoca: ");

        try {
            var anioAutores = scanner.nextInt();

            List<Autor> autorsVivos = autorRepository.listarAuroresVivos(anioAutores);
            if (!autorsVivos.isEmpty()) {
                autorsVivos.forEach(System.out::println);
            } else {
                System.out.println(" |* --- Mensaje: No se encontraron autores vivos en este año");
            }

        } catch (InputMismatchException e) {
            System.out.println("\tError: Ingrese correctamente el año (4 digitos)");
            scanner.nextLine();

        }
    }

    public void listarPorIdioma() {
        libros = new ArrayList<>();
        System.out.println("""
                **********************************
                Indique la opcion del idioma que desea buscar:
                    1. es - español
                    2. en - ingles
                    3. fr - frances
                    4. pt - portugues
                 """);

        int option = -1;


        while (option != 0) {
            try {
                option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        libros = libroRepository.findByLenguaje("es");
                        break;
                    case 2:
                        libros = libroRepository.findByLenguaje("en");
                        break;
                    case 3:
                        libros = libroRepository.findByLenguaje("fr");
                        break;
                    case 4:
                        libros = libroRepository.findByLenguaje("pt");
                        break;
                    default:
                        System.out.println("Opcion invalida");
                        break;
                }

                if (!libros.isEmpty()) {
                    System.out.println("Total de libros encontrados en el idioma: " + libros.get(0).getLenguaje() + "\n");
                    System.out.println((long) libros.size());
                    System.out.println("Pulse 1 para mostar los libros encontrados, pulse 0 para regresar al menu principal:");
                    var opcion = scanner.nextInt();
                    scanner.nextLine();
                    if (opcion == 1) {
                        libros.forEach(System.out::println);
                        break;
                    } else if (opcion==0) {
                        System.out.println("Regresando al menu principal...");
                        break;
                    }
                } else {
                    System.out.println("No se encontraron libros con el idioma");

                }
            } catch (InputMismatchException e) {
                System.out.println("\tError: Numero invalido. Vuleva a introducir el numero:");
                scanner.nextLine();
                continue;
            }
        }
    }
    // Funcionalidades extras

    public void generarEstadisticas() {
        List<Libro> libros = libroRepository.findAll();

        IntSummaryStatistics dst =
                libros.stream().collect(Collectors.summarizingInt(Libro::getNumeroDescargas));
        System.out.println("Estadisticas de los libros registrados");
        System.out.println("Libro mas descargado: " + dst.getMax());
        System.out.println("Libro menos descargado: " + dst.getMin());
        System.out.println("Total de libros contabilizados: " + dst.getCount());
    }

    public void top10librosCosultaAPI() {
        var json = consumoAPI.consultarAPI(URL_BASE);
        var datos = conversor.obtenerDatos(json, DatosLibro.class);

        System.out.println("Top 10 de Libros mas descargados de Project Gutenberg : \n");
        datos.resultado().stream()
                .sorted(Comparator.comparing(LibroDTO::numeroDescargas).reversed())
                .limit(10)
                .forEach(l -> System.out.println("Titulo: " + l.titulo() + "(" + l.lenguajes().get(0) + ") " +
                        "\tAutor: " + l.autores().get(0).nombre() + "\tTotal de descargas:" + l.numeroDescargas()));

    }

    public void top10LibrosBaseDeDatos() {
        System.out.println("Top 10 de Libros mas descargados registrados en la base de datos: \n");
        List<Libro> topLibros = libroRepository.findTop10ByOrderByNumeroDescargasDesc();
        topLibros.stream().sorted(Comparator.comparing(Libro::getNumeroDescargas).reversed())
                .limit(10).forEach(System.out::println);
    }

    public void buscarAutorPorNombre() {
        System.out.println("Ingrese el nombre del autor que desee buscar: ");
        var nombre = scanner.nextLine();


        List<Autor> autorBuscado = autorRepository.buscarAutorPorNombre(nombre);
        if (!autorBuscado.isEmpty()) {
            System.out.println("Autor/es encontrado/s en la base de datos: \n");
            autorBuscado.forEach(System.out::println);
            return;
        } else {
            System.out.println(" |* --- Mensaje: Mensaje:Autor no Encontrado, busque de nuevo. ** ");
        }
    }
}
