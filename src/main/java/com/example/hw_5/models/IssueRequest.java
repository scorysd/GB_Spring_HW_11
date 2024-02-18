package com.example.hw_5.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Запрос на выдачу")

public class IssueRequest {
    @Schema(name = "Номер читателя")
    private long readerId;
    @Schema(name = "Номмер книги")
    private long bookId;
}
