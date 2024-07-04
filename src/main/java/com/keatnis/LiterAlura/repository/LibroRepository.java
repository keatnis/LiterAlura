package com.keatnis.LiterAlura.repository;

import com.keatnis.LiterAlura.model.Autor;
import com.keatnis.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainingIgnoreCase(String nombreLibro);

    List<Libro> findByLenguaje(String lenguaje);

    List<Libro> findTop10ByOrderByNumeroDescargasDesc();

}
