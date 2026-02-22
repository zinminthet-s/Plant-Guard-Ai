package com.zinminthet.plantguardai.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;


    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    private String address;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderLine> orderLines = new ArrayList<>();

    @Column(name = "total_cost")
    private Double totalCost;


    public void addOrderLine(OrderLine orderLine, Pesticide pesticide){
        orderLines.add(orderLine);
        orderLine.addPesticide(pesticide);
        orderLine.setOrder(this);
    }

    public void addOrderLine(OrderLine orderLine, Pesticide... pesticides){
        orderLines.add(orderLine);
        orderLine.addPesticides(pesticides);
        orderLine.setOrder(this);
    }
}
