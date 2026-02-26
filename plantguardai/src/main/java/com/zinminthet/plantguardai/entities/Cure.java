package com.zinminthet.plantguardai.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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


    @Column(columnDefinition = "text")
    private String prevention;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "disease_id")
    private Disease disease;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "pesticide_cure",
            joinColumns = @JoinColumn(name = "cure_id"),
            inverseJoinColumns = @JoinColumn(name = "pesticide_id")
    )
    private List<Pesticide> pesticides = new ArrayList<>();



}