package com.example.hw_5.repositories;

import com.example.hw_5.models.IssuesBooks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuesBookRepo extends JpaRepository<IssuesBooks, Long> {
}
