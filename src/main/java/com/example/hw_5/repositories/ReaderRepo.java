package com.example.hw_5.repositories;

import com.example.hw_5.models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ReaderRepo extends JpaRepository<Reader, Long> {
    Optional<Reader> findByLogin(String login);
}
