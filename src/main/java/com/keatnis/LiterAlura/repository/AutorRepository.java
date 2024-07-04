package com.keatnis.LiterAlura.repository;

import com.keatnis.LiterAlura.model.Autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombreContainingIgnoreCase(String nombre);
    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :fecha AND a.anioFallecimiento >= :fecha")
    List<Autor> listarAuroresVivos(Integer fecha);
    @Query("SELECT a FROM Autor a WHERE a.nombre ILIKE %:nombreAutor%")
    List<Autor> buscarAutorPorNombre(String nombreAutor);

 // derived queris para encontrar

}
