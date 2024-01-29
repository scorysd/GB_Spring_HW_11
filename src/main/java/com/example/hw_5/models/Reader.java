package com.example.hw_5.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "readers")
@NoArgsConstructor
@Data
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public int getBookOnHands() {
        return bookOnHands;
    }

    public void setBookOnHands(int bookOnHands) {
        this.bookOnHands = bookOnHands;
    }

    @Column(name = "bookOnHands")
    private int bookOnHands;

    public Reader(String name){
        this.name = name;
        this.bookOnHands = 0;
    }

}
