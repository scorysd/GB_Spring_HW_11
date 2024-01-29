package com.example.hw_5.repositories;

import com.example.hw_5.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepo extends JpaRepository<Issue, Long> {

    List<Issue> findAllByReaderId(long id);
}
