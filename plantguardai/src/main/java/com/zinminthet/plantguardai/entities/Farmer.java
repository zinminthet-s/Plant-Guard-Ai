package com.zinminthet.plantguardai.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "farmers")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Farmer {
    @Id
    @Column(name = "farmer_id")
    private Long id;

    private String name;

    private String address;

    @OneToOne
    @JoinColumn(name = "auth_id")
    private Auth auth;


    @OneToMany(mappedBy = "farmer", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();


    public void addOrder(Order order, OrderLine orderLine, Pesticide pesticide){
        orders.add(order);
        order.setFarmer(this);
        order.addOrderLine(orderLine, pesticide);
    }



    public void addOrder(Order order, OrderLine orderLine, Shop shop, Pesticide... pesticides){
        shop.getOrders().add(order);
        order.setShop(shop);
        orders.add(order);
        order.setFarmer(this);
        order.addOrderLine(orderLine, pesticides);
    }

    public void removeOrder(Order order, Shop shop){
        order.setFarmer(null);
        order.setShop(null);
        shop.getOrders().remove(order);
        order.setOrderLines(null);
        orders.remove(order);
    }

}