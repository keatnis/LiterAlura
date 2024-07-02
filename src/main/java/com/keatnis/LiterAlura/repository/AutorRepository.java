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
 // derived queris para encontrar

}
