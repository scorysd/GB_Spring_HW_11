package com.example.hw_5.repositories;

import com.example.hw_5.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Long> {
}
