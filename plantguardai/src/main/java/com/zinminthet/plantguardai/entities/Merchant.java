package com.zinminthet.plantguardai.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "merchants")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {
    @Id
    @Column(name = "merchant_id")
    private Long id;

    private String name;

    private String nrc;

    @OneToOne
    @JoinColumn(name = "auth_id")
    private Auth auth;

    @OneToMany(mappedBy = "merchant", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Shop> shops = new ArrayList<>();

    public void addShop(Shop shop){
        shops.add(shop);
        shop.setMerchant(this);
    }

    public void removeShop(Shop shop){
        shops.remove(shop);
        shop.setMerchant(null);
    }

    public void addPesticidesToShop(List<Shop> shops, List<Pesticide> pesticides){
        for(var shop: shops){
            var shopEntity = this.shops.get(this.shops.indexOf(shop));
            for (var pesticide: pesticides){
                shopEntity.addPesticides(pesticides);
            }
        }
    }


}
