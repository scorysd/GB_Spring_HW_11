package com.example.hw_5.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "issues")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bookId")
    private Long bookId;
    @Column(name = "readerId")
    private Long readerId;
    @Column(name = "issueTime")
    private LocalDateTime issueTime;

    public Issue(Long bookId, Long readerId, LocalDateTime issueTime){
        this.bookId = bookId;
        this.readerId = readerId;
        this.issueTime = issueTime;

    }
}
