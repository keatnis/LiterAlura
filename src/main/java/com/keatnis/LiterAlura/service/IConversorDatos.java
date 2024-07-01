package com.keatnis.LiterAlura.service;

public interface IConversorDatos {
    <T> T obtenerDatos(String json, Class<T> tClass);
}
