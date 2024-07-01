package com.keatnis.LiterAlura.model;

import com.keatnis.LiterAlura.dto.AutorDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer anioNacimiento;
    private Integer anioFallecimiento;
    private String nombre;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(AutorDTO autorDTO) {
        this.nombre = autorDTO.nombre();
        this.anioNacimiento = autorDTO.anioNacimiento();
        this.anioFallecimiento = autorDTO.anioFallecimiento();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public List<Libro> getLibros() {

        return libros;
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(l-> l.setAutor(this));
        this.libros = libros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ",nombre=" + nombre +
                ", anioNacimiento=" + anioNacimiento +
                ", anioFallecimiento=" + anioFallecimiento +
                '}';
    }
}
