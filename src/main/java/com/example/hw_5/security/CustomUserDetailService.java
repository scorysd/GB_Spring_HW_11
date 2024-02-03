package com.example.hw_5.security;

import com.example.hw_5.models.Reader;
import com.example.hw_5.repositories.ReaderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private ReaderRepo readerRepo;

    public CustomUserDetailService(ReaderRepo readerRepo) {
        this.readerRepo = readerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader reader = readerRepo.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(reader.getLogin(),reader.getPassword(), reader.getRoles());
    }
}
