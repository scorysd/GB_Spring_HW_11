package com.example.hw_5.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "issues")
@Schema(name = "Выдача")

public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Номер")
    private Long id;
    @Column(name = "bookId")
    @Schema(name = "Номер книги")
    private Long bookId;
    @Column(name = "readerId")
    @Schema(name = "Номер читателя")
    private Long readerId;
    @Column(name = "issueTime")
    @Schema(name = "Время выдачи")
    private LocalDateTime issueTime;

    public Issue(Long bookId, Long readerId, LocalDateTime issueTime){
        this.bookId = bookId;
        this.readerId = readerId;
        this.issueTime = issueTime;

    }
}
