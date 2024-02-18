package com.example.hw_5.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

//@Entity
//@Table(name = "role")
//@Data
//@Schema(name = "Роли")
//@NoArgsConstructor
public enum Role implements GrantedAuthority {
    @JsonProperty("user")
    USER,
    @JsonProperty("admin")
    ADMIN;
//
//    @Id
//    @Schema(name = "идентификатор")
//    private Long id;

//    @Schema(name = "Идентификатор читателя")
//    private Long readerId;
//
//    @Column(name = "nameRole")
//    private String nameRole;

    @Override
    public String getAuthority() {
        return null;
    }
}
