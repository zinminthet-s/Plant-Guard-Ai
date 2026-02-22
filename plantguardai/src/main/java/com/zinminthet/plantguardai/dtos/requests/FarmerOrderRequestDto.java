package com.zinminthet.plantguardai.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmerOrderRequestDto {

    private Long pesticideId;
    private String name;
    private Double price;
    private Photo photo;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Photo {
        private String url;
    }

//    const cartItems: CartItem[] = [
//    {
//        pesticideId: 101,
//                name: "Organic Fertilizer",
//            price: 12.5,
//            photo: { url: "https://example.com/product.jpg" },
//        shopName: "Green Farm Supplies",
//                shopId: 5,
    //            uniqueId: "5-101",
//            quantity: 2
//    },

}
