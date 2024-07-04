package com.keatnis.LiterAlura.model;

import com.keatnis.LiterAlura.dto.LibroDTO;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
//    @Enumerated(EnumType.STRING)
//    private Lenguaje lenguaje;

    private String lenguaje;
    private Integer numeroDescargas;

    @ManyToOne
    private Autor autor;


    public Libro() {
    }

    public Libro(LibroDTO d) {
        this.numeroDescargas = d.numeroDescargas();
        this.titulo = d.titulo();
        Optional<String> lenguaje = d.lenguajes().stream().findFirst();
        // this.lenguaje = Lenguaje.fromListString(lenguaje.get());
        lenguaje.ifPresent(s -> this.lenguaje = s);
        this.autor = new Autor(d.autores().get(0));

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

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + "\n" +
                "Autor:  " + autor.getNombre() + "\n" +
                "Idioma: " + lenguaje + "\n" +
                "Numero de decargas:" + numeroDescargas + "\n";
//        return "Libro{" +
//                "id=" + id +
//                ", titulo='" + titulo + '\'' +
//                ", lenguajes=" + lenguaje +
//                ", numeroDescargas=" + numeroDescargas +
//                '}';
    }


}
