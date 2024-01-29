package com.example.hw_5;

import com.example.hw_5.models.Book;
import com.example.hw_5.models.Reader;
import com.example.hw_5.repositories.BookRepo;
import com.example.hw_5.repositories.ReaderRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class Hw5Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw5Application.class, args);
    }


}
