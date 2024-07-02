package com.keatnis.LiterAlura;

import com.keatnis.LiterAlura.main.Main;
import com.keatnis.LiterAlura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
    @Autowired
    private LibroService libroService;


    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main(libroService);
        main.menu();
    }
}
