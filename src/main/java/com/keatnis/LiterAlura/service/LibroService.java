package com.keatnis.LiterAlura.service;

import com.keatnis.LiterAlura.dto.AutorDTO;
import com.keatnis.LiterAlura.dto.DatosLibro;
import com.keatnis.LiterAlura.dto.LibroDTO;
import com.keatnis.LiterAlura.model.Autor;
import com.keatnis.LiterAlura.model.Libro;
import com.keatnis.LiterAlura.repository.AutorRepository;
import com.keatnis.LiterAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LibroService {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConversorDatos conversor = new ConversorDatos();
    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/?";
    private LibroDTO libroEncontrado;
    List<Libro> libros;

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    public LibroService() {
    }

    public LibroDTO getLibroEncontrado() {
        System.out.println("Escriba el nombre del libro que desee buscar:");
        var titulo = scanner.nextLine();

        var json = consumoAPI.obtenerDatos(URL_BASE + "search=" + titulo.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, DatosLibro.class);
        Optional<LibroDTO> libroEnc = datos.resultado().stream()
                .filter(l -> l.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .findFirst();

        if (libroEnc.isPresent()) {
            System.out.println("Libro encontrado: " + libroEnc.get().titulo());
            return libroEncontrado = libroEnc.get();

        } else {
            System.out.println("No se encontro un libro con este nombre, busque de nuevo");
            return null;

        }

    }

    public void buscarYGuardarDB() {
        //verificamos si el libro no esta registrado

        Optional<Libro> libroExiste = libroRepository.findByTituloContainingIgnoreCase(libroEncontrado.titulo());

        if (libroExiste.isPresent()) {
            System.out.println("** El libro ya se registro con el nombre : " + libroExiste.get().getTitulo());

        } else {
            Libro libro = new Libro(libroEncontrado);
            Autor autor = verificarLibroTieneAutor(libroEncontrado);
            // verificamos si existe el autor en la base de datos
            Optional<Autor> autorExiste = autorRepository.findByNombreContainingIgnoreCase(autor.getNombre());
            if (autorExiste.isPresent()) {
                libro.setAutor(autorExiste.get());
                libroRepository.save(libro);
                System.out.println("Se registro el libro en la base de datos");
            } else {
                libros = new ArrayList<>();
                libros.add(libro);
                autor.setLibros(libros);
                autorRepository.save(autor);
                System.out.println("Se regitro el libro y el autor");
            }
        }

    }

    private Autor verificarLibroTieneAutor(LibroDTO libroEncontrado) {
        //verificamos si el libro tiene un autor, en caso de que no se registra como desconocido
        Autor autor;
        if (libroEncontrado.autores().isEmpty()) {
            autor = new Autor(new AutorDTO("Desconocido", null, null));
        } else {
            autor = new Autor(libroEncontrado.autores().get(0));
        }

        return autor;
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
                System.out.println("*** No se encontraron autores vivos en este año");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese correctamente el año (4 digitos)");
        }
    }

    public void listarPorIdioma() {
        libros = new ArrayList<>();
        System.out.println("""
                1. es - español
                2. en - ingles
                3. fr - frances
                4. pt - portugues
                Indique la opcion que desea buscar:""");

        int option = -1;
        while (option != 0) {

            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Introduza un numero.");
                scanner.next();
                continue;
            }
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
                System.out.println("Total de libros encontrados del idioma: " + libros.get(0).getLenguaje());
                System.out.println((long) libros.size());
                System.out.println("Pulse 1 para mostar los libros encontrados o pulse numero tecla para salir:");


                try {
                    var opcion = scanner.nextInt();
                    if (opcion == 1) {
                        libros.forEach(System.out::println);
                        libros.clear();

                    }
                } catch (InputMismatchException e) {
                    System.out.println("Regresando al menu principal");
                    break;
                }

            } else {
                System.out.println("No se encontraron libros con el idioma");
            }

        }
    }


}
