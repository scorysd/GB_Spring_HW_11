package com.example.hw_5.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Книга")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Номер")
    private Long id;
    @Column(name = "NameBook")
    @Schema(name = "Название")
    private String nameBook;
    @Column(name = "AuthorBook")
    @Schema(name = "Автор")
    private String authorBook;

    public Book(String nameBook, String authorBook) {
        this.nameBook = nameBook;
        this.authorBook = authorBook;
    }
}
