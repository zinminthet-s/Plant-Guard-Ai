package com.zinminthet.plantguardai.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cures")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cure_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    @ManyToOne
    @JoinColumn(name = "virus_id")
    private Virus virus;


    @ManyToOne
    @JoinColumn(name = "pesticide_id")
    private Pesticide pesticide;

    private String prevention;

}
