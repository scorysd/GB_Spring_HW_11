package com.example.hw_5.services;

import com.example.hw_5.JUnitSpringBootTest;
import com.example.hw_5.models.Reader;
import com.example.hw_5.models.Role;
import com.example.hw_5.repositories.ReaderRepo;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Objects;

class ReaderServiceTest extends JUnitSpringBootTest {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ReaderRepo readerRepo;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Data
    static class JUnitReaderResponse{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        long Id;
        String login;
        String password;
        String name;
        int bookOnHands;

        public JUnitReaderResponse(String login, String password, String name) {
            this.login = login;
            this.password = password;
            this.name = name;
            this.bookOnHands = 0;
        }
    }

    @Test
    void getAllReader() {
        readerRepo.saveAll(List.of(new Reader("first","first","first", Role.USER),
                new Reader("sec","sec","sec",Role.USER),
                new Reader("third","third","third",Role.USER)));

        List<Reader> excepted = readerRepo.findAll();

        List<JUnitReaderResponse> responses = webTestClient.get().uri("/reader/all")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitReaderResponse>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(excepted.size(), responses.size());
        for (JUnitReaderResponse resp : responses) {
            boolean found = excepted.stream()
                    .filter(it -> Objects.equals(it.getId(), resp.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), resp.getName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void getReaderByIdNotFound() {
        readerRepo.saveAll(List.of(new Reader("first","first","first", Role.USER),
                new Reader("sec","sec","sec",Role.USER),
                new Reader("third","third","third",Role.USER)));

        Long maxId = jdbcTemplate.queryForObject("select max(id) from readers", Long.class);
        webTestClient.get().uri("/reder/" + (maxId + 1)).exchange()
                .expectStatus()
                .isNotFound();

    }

    @Test
    void createReader() {
        JUnitReaderResponse reqest = new JUnitReaderResponse("foo", "foo", "foo");
        JUnitReaderResponse response = webTestClient.post()
                .uri("/reader/new")
                .bodyValue(reqest)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(JUnitReaderResponse.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());

    }

    @Test
    void deleteReader() {
        readerRepo.saveAll(List.of(new Reader("first","first","first", Role.USER),
                new Reader("sec","sec","sec",Role.USER),
                new Reader("third","third","third",Role.USER)));

        JUnitReaderResponse response = webTestClient
                .delete()
                .uri("/reader/1")
                .exchange()
                .expectBody(JUnitReaderResponse.class).returnResult().getResponseBody();

    }
}