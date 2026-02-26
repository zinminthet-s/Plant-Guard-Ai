package com.zinminthet.plantguardai.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diseases")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private Long id;

    @Column(name = "disease_name")
    private String name;

    @Column(name = "caused_by")
    private String virus;

    @OneToOne(mappedBy = "disease")
    private Cure cure;

}
