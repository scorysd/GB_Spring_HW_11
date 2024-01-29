package com.example.hw_5.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NameBook")
    private String nameBook;
    @Column(name = "AuthorBook")
    private String authorBook;

    public Book(String nameBook, String authorBook){
        this.nameBook = nameBook;
        this.authorBook = authorBook;
    }
}
