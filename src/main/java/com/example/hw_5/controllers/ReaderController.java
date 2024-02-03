package com.example.hw_5.controllers;

import com.example.hw_5.models.Reader;
import com.example.hw_5.services.ReaderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reader")
@Tag(name = "Readers")


public class ReaderController {
    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/all")
    @Operation(summary = "get all readers", description = "загружает всех читатаелей, хранящихся в системе")
    public List<Reader> getAllReaders() {
        return readerService.getAllReader();

    }

    @GetMapping("/{id}")
    @Operation(summary = "get reader by id", description = "загружает читатля по идентификатору")
    public Optional<Reader> getReaderById(@PathVariable long id) {
        return readerService.getReaderById(id);
    }

    @PostMapping("/new")
    @Operation(summary = "create new reader", description = "создает нового пользователя")
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {
        readerService.createReader(reader.getLogin(), reader.getPassword(), reader.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(reader);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete reader by id", description = "удаляет читателя по идентификатору")
    public ResponseEntity<Reader> deleteReader(@PathVariable long id, @RequestBody Reader reader) {
        readerService.deleteReader(id);
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }
//    @GetMapping("/{id}/issue")
//    public List<Issue> readerIssue(@PathVariable long id){
//        return readerService.readerIssue(id);
//    }


}
