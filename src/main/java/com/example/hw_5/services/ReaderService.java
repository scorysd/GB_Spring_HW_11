package com.example.hw_5.services;

import com.example.hw_5.models.Reader;
import com.example.hw_5.models.Role;
import com.example.hw_5.repositories.ReaderRepo;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReaderService {
    private final ReaderRepo readerRepo;

    public ReaderService(ReaderRepo readerRepo) {
        this.readerRepo = readerRepo;
    }

    @PostConstruct
    public void generateReader() {
        for (int i = 1; i < 11; i++) {
            readerRepo.save(new Reader("Reader" + i, "reader" + i, "Reader # " + i, Role.USER));
        log.info("Create user " + i);
        }
        readerRepo.save(new Reader("admin","admin","Admin", Role.ADMIN));
        log.info("Create Admin");
    }

    public List<Reader> getAllReader() {
        return readerRepo.findAll();
    }

    public Optional<Reader> getReaderById(long id) {
        return readerRepo.findById(id);
    }

    public void createReader(String login, String pass, String name) {
        readerRepo.save(new Reader(login, pass, name, Role.USER));
    }

    public void deleteReader(long id) {
        readerRepo.deleteById(id);
    }

    public void setBookOnHands(long id) {
        Optional<Reader> exist = getReaderById(id);
        if (exist == null) {
            System.out.println("Do not exist reader!");
        } else {
            exist.get().setBookOnHands(exist.get().getBookOnHands() + 1);
        }

    }
}
