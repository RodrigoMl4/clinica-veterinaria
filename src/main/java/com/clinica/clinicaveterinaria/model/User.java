package com.clinica.clinicaveterinaria.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String phone;

    @Column(unique = true, length = 50, nullable = false)
    private String ci;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;
}
