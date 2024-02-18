package com.example.hw_5.services;

import com.example.hw_5.models.Book;
import com.example.hw_5.repositories.BookRepo;
import gb.hw.timerstarter.Timer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Timer
public class BookService {

    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }


    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Optional<Book> getBookById(long id) {
        return bookRepo.findById(id);
    }

    public Book createBook(String nameBook, String authorBook) {
        return bookRepo.save(new Book(nameBook, authorBook));
    }

    public Optional<Book> deleteBook(Long id) {
        Optional<Book> exist = getBookById(id);
        if (exist != null) {
            bookRepo.deleteById(id);
        } else {
            System.out.println("book doesnt exist");
        }
        return exist;
    }

            @PostConstruct
            @Timer
    public void generateBook() {
        bookRepo.save(new Book("Dead soul", "Gogol N.V."));
        bookRepo.save(new Book("Peace & War", "Tolstoy L.N."));
        bookRepo.save(new Book("Idiot", "Dostoevsky F.M."));
        bookRepo.save(new Book("America", "Kafka F."));
        bookRepo.save(new Book("Osnovy metafiziki", "Kant I."));
        bookRepo.save(new Book("Passions of the Soul", "Dekart R."));

    }

}
