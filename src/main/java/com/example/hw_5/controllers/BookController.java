package com.example.hw_5.controllers;

import com.example.hw_5.models.Book;
import com.example.hw_5.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name = "Books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/all")
    @Operation(summary = "get all books", description = "загружает все книги, хранящиеся в системе")
    public List<Book> getAllBook(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get book by id", description = "загружает книгу по заданному монеру (id)")
    public ResponseEntity<Book> getBookById(@PathVariable long id, Book book){
        return bookService.getBookById(id).isPresent()
                ? ResponseEntity.status(HttpStatus.OK).body(book)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/new")
    @Operation(summary = "create new book", description = "создат новую книгу")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        bookService.createBook(book.getNameBook(), book.getAuthorBook());
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete book by id", description = "удаляет книгу по заданному номеру (id)")
    public ResponseEntity<Book> deleteBook(@PathVariable long id, @RequestBody Book book){
        return bookService.deleteBook(id).isPresent()
                 ? ResponseEntity.status(HttpStatus.OK).body(book)
                 : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
