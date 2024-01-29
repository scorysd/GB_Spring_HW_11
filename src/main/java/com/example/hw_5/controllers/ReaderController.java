package com.example.hw_5.controllers;

import com.example.hw_5.models.Reader;
import com.example.hw_5.services.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reader")

public class ReaderController {
    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/all")
    public List<Reader> getAllReaders(){
        return readerService.getAllReader();

    }
    @GetMapping("/{id}")
    public Optional<Reader> getReaderById(@PathVariable long id){
        return readerService.getReaderById(id);
    }
    @PostMapping("/new")
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {
        readerService.createReader(reader.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(reader);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Reader> deleteReader(@PathVariable long id, @RequestBody Reader reader){
        readerService.deleteReader(id);
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }
//    @GetMapping("/{id}/issue")
//    public List<Issue> readerIssue(@PathVariable long id){
//        return readerService.readerIssue(id);
//    }




}
