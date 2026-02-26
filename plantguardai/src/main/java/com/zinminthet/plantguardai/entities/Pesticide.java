package com.zinminthet.plantguardai.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pesticides")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pesticide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pesticide_id")
    private Long id;

    @Column(name = "pesticide_name", columnDefinition = "VARCHAR(2048) DEFAULT NULL, FULLTEXT KEY searchNameQuery (pesticide_name) ")
    private String name;

    @Column(columnDefinition = "text")
    private String info;

    private Double price;

    private String weight;

    private String ingredients;

    private String imagePath;


    @ManyToMany(mappedBy = "pesticides")
    private List<OrderLine> orderLines = new ArrayList<>();


    @ManyToMany(mappedBy = "pesticides")
    private Set<Shop> shops = new HashSet<>();

}
