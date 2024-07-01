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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LibroService {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConversorDatos conversor = new ConversorDatos();
    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/?";
    private LibroDTO libroEncontrado;
    private List<Libro> libros = new ArrayList<>();

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    public LibroService() {
    }

    private LibroDTO getLibroEncontrado() {
        System.out.println("Escriba el nombre del libro que desee buscar:");
        var titulo = scanner.nextLine();

        var json = consumoAPI.obtenerDatos(URL_BASE + "search=" + titulo.replace(" ", "+"));
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        Optional<LibroDTO> libroEnc = datos.resultado().stream()
                .filter(l -> l.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .findFirst();

        if (libroEnc.isPresent()) {
            System.out.println("Libro encontrado: " + libroEnc.get().titulo());
            libroEncontrado = libroEnc.get();

        } else {

            System.out.println("No se encontro un libro");

        }
        return libroEncontrado;
    }

    public Autor getAutorLibro() {
        //obtenemos el primer autor de la lista de autores
        Autor autors = new Autor(libroEncontrado.autores().get(0));
        System.out.println("autor: *" + autors);
        return autors;
    }

    public void buscarYGuardarDB() {

        libroEncontrado = getLibroEncontrado();
        Libro libro = new Libro(libroEncontrado);
        System.out.println("datoas del libro guradar: " + libroEncontrado);
        libros.add(libro);
        Autor autor = getAutorLibro();
        autor.setLibros(libros);
        autorRepository.save(autor);


    }

    public void mostrarLibrosRegistrados() {
        libroRepository.findAll()
                .stream().forEach(System.out::println);
    }

    public void mostarAutoresRegistrados() {
        autorRepository.findAll().stream()
                .map(a -> new AutorDTO(a.getNombre(), a.getAnioNacimiento(), a.getAnioFallecimiento()))
                .forEach(System.out::println);
    }

    public void listarAutorPorAnio() {
    }
}
