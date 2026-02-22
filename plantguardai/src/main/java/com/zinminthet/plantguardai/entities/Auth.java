package com.zinminthet.plantguardai.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auth")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long id;

    private String email;
    private Boolean emailVerified;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
