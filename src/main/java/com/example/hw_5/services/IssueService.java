package com.example.hw_5.services;

import com.example.hw_5.models.Issue;
import com.example.hw_5.models.IssueRequest;
import com.example.hw_5.models.IssuesBooks;
import com.example.hw_5.repositories.IssueRepo;
import com.example.hw_5.repositories.IssuesBookRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class IssueService {
    private final IssueRepo issueRepo;
    private final BookService bookService;
    private final ReaderService readerService;
    private final IssuesBookRepo issuesBookRepo;

    @Value("${application.max-allowed-book}")
    int maxBook;

    public IssueService(IssueRepo issueRepo, BookService bookService, ReaderService readerService, IssuesBookRepo issuesBookRepo) {
        this.issueRepo = issueRepo;
        this.bookService = bookService;
        this.readerService = readerService;
        this.issuesBookRepo = issuesBookRepo;
    }

    public Issue issue(IssueRequest request) {
        if (!issueRepo.findAll().contains(request.getBookId()))
            throw new RuntimeException("Книга " + request.getBookId() + " уже выдана");
        if (bookService.getBookById(request.getBookId()).isEmpty()) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerService.getReaderById(request.getReaderId()).isEmpty()) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        } else if (readerService.getReaderById(request.getReaderId()).get().getBookOnHands() > maxBook - 1) {
            throw new RuntimeException("Превышен лимит книг!");
        }

        Issue issue = new Issue(request.getBookId(), request.getReaderId(), LocalDateTime.now());
        issueRepo.save(issue);
        readerService.setBookOnHands((request.getReaderId()));
        return issue;
    }

    public Optional<Issue> getIssueById(long id) {
        return issueRepo.findById(id);
    }

    public List<IssuesBooks> getAllIssue() {
        List<IssuesBooks> res = new ArrayList<>();
        for (Issue it : issueRepo.findAll()) {
            issuesBookRepo.save(new IssuesBooks(bookService.getBookById(it.getBookId()).get().getNameBook(),
                    bookService.getBookById(it.getBookId()).get().getAuthorBook(),
                    readerService.getReaderById(it.getReaderId()).get().getName(),
                    issueRepo.findById(it.getId()).get().getIssueTime()));

        }
        return issuesBookRepo.findAll();
    }

    public List<IssuesBooks> getReaderIssue(long id) {
        List<IssuesBooks> res = new ArrayList<>();
        for (Issue it : issueRepo.findAllByReaderId(id)) {
            res.add(new IssuesBooks(bookService.getBookById(it.getBookId()).get().getNameBook(),
                    bookService.getBookById(it.getBookId()).get().getAuthorBook(),
                    readerService.getReaderById(it.getReaderId()).get().getName(),
                    issueRepo.findById(it.getId()).get().getIssueTime()));
        }
        return res;

    }

    @EventListener(ContextRefreshedEvent.class)
    public void dataExec() {
        log.info("max book = {}", maxBook);
    }
}
