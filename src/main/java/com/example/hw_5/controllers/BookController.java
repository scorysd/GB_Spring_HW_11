package com.example.hw_5.controllers;

import com.example.hw_5.models.Book;
import com.example.hw_5.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/all")
    public List<Book> getAllBook(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable long id){
        return bookService.getBookById(id);
    }
    @PostMapping("/new")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        bookService.createBook(book.getNameBook(), book.getAuthorBook());
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable long id, @RequestBody Book book){
         bookService.deleteBook(id);
         return ResponseEntity.status(HttpStatus.OK).body(book);
    }

}
