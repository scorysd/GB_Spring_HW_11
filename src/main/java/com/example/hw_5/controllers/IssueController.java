package com.example.hw_5.controllers;

import com.example.hw_5.models.Issue;
import com.example.hw_5.models.IssueRequest;
import com.example.hw_5.models.IssuesBooks;
import com.example.hw_5.services.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    private IssueService service;

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final Issue issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }
    @GetMapping("/{id}")
    public Optional<Issue> getIssueById(@PathVariable long id){
        return service.getIssueById(id);
    }
    @GetMapping("/all")
    public List<IssuesBooks> getAllIssue() {
        return service.getAllIssue();
    }
}
