package com.zinminthet.plantguardai.entities;



import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "orderlines")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderline_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(
            name = "orderline_pesticide",
            joinColumns = @JoinColumn(name = "orderline_id"),
            inverseJoinColumns = @JoinColumn(name = "pesticide_id")
    )
    private List<Pesticide> pesticides = new ArrayList<>();

    private Integer quantity = 0;
    private Double cost = 0.0;

    public void addPesticide(Pesticide pesticide) {
        pesticides.add(pesticide);
    }

    public void removePesticide(Pesticide pesticide){
        pesticides.remove(pesticide);
    }

    public long addPesticides(Pesticide... pesticides) {
        this.pesticides.addAll(Arrays.asList(pesticides));
        return pesticides.length;
    }

}