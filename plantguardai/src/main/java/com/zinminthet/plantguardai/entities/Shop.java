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
@Table(name = "shops")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    private String name;

    private String address;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "inventories",
            joinColumns = {@JoinColumn(name = "shop_id")},
            inverseJoinColumns = {@JoinColumn(name = "pesticide_id")}
    )
    private Set<Pesticide> pesticides = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;


    @OneToMany(mappedBy = "shop")
    private List<Order> orders = new ArrayList<>();

    public void addPesticides(List<Pesticide> pesticides){
        this.pesticides.addAll(pesticides);
    }

}
