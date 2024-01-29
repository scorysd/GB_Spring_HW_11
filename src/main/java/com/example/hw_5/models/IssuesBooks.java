package com.example.hw_5.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "issuesBooks")
@Data
@NoArgsConstructor
public class IssuesBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bookName")
    private String bookName;
    @Column(name = "authorName")
    private String authorName;
    @Column(name = "readerName")
    private String readerName;
    @Column(name = "issueTime")

    private LocalDateTime issuesTime;

    public IssuesBooks(String bookName,String authorName, String readerName, LocalDateTime issuesTime) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.readerName = readerName;
        this.issuesTime = issuesTime;
    }

}


