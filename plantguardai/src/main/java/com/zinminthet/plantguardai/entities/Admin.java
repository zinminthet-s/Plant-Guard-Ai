package com.zinminthet.plantguardai.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admins")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @Column(name = "admin_id")
    private Long id;

    @Column(name = "admin_name")
    private String name;


    @OneToOne
    @JoinColumn(name = "auth_id")
    private Auth auth;

}
