package com.example.hw_5.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "readers")
@NoArgsConstructor
@Data
@Schema(name = "Читатель")

public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Номер")
    private Long id;

    @Column(name = "login")
    @Schema(name = "Логин")
    private String login;

    @Column(name = "password")
    @Schema(name = "Пароль")
    private String password;

    @Column(name = "name")
    @Schema(name = "Имя")
    private String name;

    @Column(name = "bookOnHands")
    @Schema(name = "Количество книг на руках", maximum = "задается в файле properties")
    private int bookOnHands;

//    @ElementCollection
//    @Column(name = "role")
    private ArrayList<Role> roles = new ArrayList<>();

    public int getBookOnHands() {
        return bookOnHands;
    }

    public void setBookOnHands(int bookOnHands) {
        this.bookOnHands = bookOnHands;
    }

    public Reader(String login, String password, String name, Role roles) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.bookOnHands = 0;
        this.roles.addAll(Arrays.asList(roles));
    }

}
