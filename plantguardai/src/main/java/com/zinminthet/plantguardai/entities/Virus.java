package com.zinminthet.plantguardai.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "viruses")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Virus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "virus_id")
    private Long id;

    @Column(name = "virus_name")
    private String name;

    @OneToMany(mappedBy = "virus")
    private List<Cure> cures = new ArrayList<>();

}
