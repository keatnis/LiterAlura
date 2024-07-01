package com.keatnis.LiterAlura.model;

import com.keatnis.LiterAlura.dto.DatosLibro;
import com.keatnis.LiterAlura.dto.LibroDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private List<String> lenguajes;
    private Integer numeroDescargas;

    @ManyToOne
   private Autor autor;

    public Libro() {
    }
    public Libro( LibroDTO d) {
        this.numeroDescargas = d.numeroDescargas();
        this.titulo = d.titulo();
        this.lenguajes = d.lenguajes();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {

        return autor;
    }

    public void setAutor(Autor autor) {

        this.autor = autor;
    }

    public List<String> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(List<String> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autor +
                ", lenguajes=" + lenguajes +
                ", numeroDescargas=" + numeroDescargas +
                '}';
    }


}
