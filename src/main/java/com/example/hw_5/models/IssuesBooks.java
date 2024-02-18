package com.example.hw_5.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "issuesBooks")
@Data
@NoArgsConstructor
@Schema(name = "Выданные книги")

public class IssuesBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Номер")
    private Long id;
    @Column(name = "bookName")
    @Schema(name = "Название книги")
    private String bookName;
    @Column(name = "authorName")
    @Schema(name = "Автор книги")
    private String authorName;
    @Column(name = "readerName")
    @Schema(name = "Имя читателя")
    private String readerName;
    @Column(name = "issueTime")
    @Schema(name = "время выдачи")
    private LocalDateTime issuesTime;

    public IssuesBooks(String bookName,String authorName, String readerName, LocalDateTime issuesTime) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.readerName = readerName;
        this.issuesTime = issuesTime;
    }

}


