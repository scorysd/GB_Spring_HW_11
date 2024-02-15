package com.example.hw_5.controllers;

import com.example.hw_5.JUnitSpringBootTest;
import com.example.hw_5.models.Book;
import com.example.hw_5.repositories.BookRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

//@WebMvcTest
class BookControllerTest extends JUnitSpringBootTest {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Data
    static class JUnitBookResponse {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String name;
        private String author;

        public JUnitBookResponse(String nameBook, String authorBook) {
            this.name = nameBook;
            this.author = authorBook;
        }
    }

    @Test
    void getAllBook() {
        bookRepo.saveAll(List.of(new Book("first", "first"),
                new Book("second", "second")));

        List<Book> excepted = bookRepo.findAll();

        List<JUnitBookResponse> responses = webTestClient.get().uri("/book/all")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitBookResponse>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(excepted.size(), responses.size());
        for (JUnitBookResponse resp : responses) {
            boolean found = excepted.stream()
                    .filter(it -> Objects.equals(it.getId(), resp.getId()))
                    .filter(it -> Objects.equals(it.getNameBook(), resp.getName()))
                    .anyMatch(it -> Objects.equals(it.getAuthorBook(), resp.getAuthor()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    @Disabled
    void getBookByIdNotFound() {
        bookRepo.saveAll(List.of(new Book("first", "first"),
                new Book("second", "second")));

        Long maxId = jdbcTemplate.queryForObject("select max(id) from books", Long.class);

        webTestClient.get().uri("/book/" + (maxId + 1)).exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void createBook() {
        JUnitBookResponse reqest = new JUnitBookResponse("foo", "foo");
        JUnitBookResponse response = webTestClient.post()
                .uri("/book/new")
                .bodyValue(reqest)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(JUnitBookResponse.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());

    }

    @Test
    @Disabled
    void deleteBook() {
        bookRepo.saveAll(List.of(new Book("first", "first"),
                new Book("second", "second")));

        JUnitBookResponse response = webTestClient
                .delete()
                .uri("/book/1")
                .exchange()
                .expectBody(JUnitBookResponse.class).returnResult().getResponseBody();

    }
}