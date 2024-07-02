package com.keatnis.LiterAlura.model;

public enum Lenguaje {
    ESPANIOL("es"),
    INGLES("en"),
    FRANCES("fr"),
    PORTUGUES("pt");

    private String lenguaje;

    Lenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public static Lenguaje fromListString(String text) {
        for (Lenguaje len : Lenguaje.values()) {
            if (len.lenguaje.equalsIgnoreCase(text)) {
                return len;
            }
        }
        throw new IllegalArgumentException("Ningun Lenguaje fue encontrado: " + text);
    }
}
