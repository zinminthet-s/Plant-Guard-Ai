package com.zinminthet.plantguardai.services;

import com.zinminthet.plantguardai.dtos.requests.FarmerOrderPesticidesFromShopsRequestDto;
import com.zinminthet.plantguardai.entities.Order;
import com.zinminthet.plantguardai.entities.OrderLine;
import com.zinminthet.plantguardai.entities.Pesticide;
import com.zinminthet.plantguardai.exceptions.FarmerNotFound;
import com.zinminthet.plantguardai.exceptions.PesticideNotFound;
import com.zinminthet.plantguardai.exceptions.ShopNotFound;
import com.zinminthet.plantguardai.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final FarmerRepository farmerRepository;
    private final PesticideRepository pesticideRepository;
    private final ShopRepository shopRepository;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;


    public boolean makeOrder(FarmerOrderPesticidesFromShopsRequestDto requestDto, Long farmerId) throws PesticideNotFound, FarmerNotFound, ShopNotFound {



        var farmer = farmerRepository.findById(farmerId).orElseThrow(() -> new FarmerNotFound(String.format("Farmer with %d does not exist.", farmerId)));
        var locationToSend = requestDto.getAddress();


        var orders = requestDto.getOrders();
        for (var order : orders) {
            var shopId = order.getShopId();
            var shopEntity = shopRepository.findById(shopId).orElseThrow(
                    () -> new ShopNotFound(String.format("Shop with %d does not exists", shopId)));

                var pesticideDtos = order.getItems();
                List<Pesticide> pesticideEntities = new ArrayList<>();

            var orderLine = new OrderLine();
            var orderEntity = new Order();


            for(var pesticideDto: pesticideDtos) {
                var pesticideEntity = pesticideRepository.findById(pesticideDto.getPesticideId()).orElseThrow();
                orderLine.setCost(pesticideEntity.getPrice() * pesticideDto.getQuantity());
                orderLine.setQuantity(Math.toIntExact(pesticideDto.getQuantity()));
                pesticideEntities.add(pesticideEntity);
                }


            orderEntity.setTotalCost(orderLine.getCost());

                orderEntity.setAddress(locationToSend);
                orderEntity.setTotalCost(orderLine.getCost());

                farmer.addOrder(orderEntity, orderLine, shopEntity, pesticideEntities.toArray(new Pesticide[0]));

                farmerRepository.save(farmer);
                shopRepository.save(shopEntity);
                var saved = orderRepository.save(orderEntity);
                orderLineRepository.save(orderLine);


            }

        return true;


    }
    }


//    public Order makeOrder(OrderRequestDto orderRequestDto) throws PesticideNotFound, FarmerNotFound, ShopNotFound{
//
//        try {
//            var farmerId = orderRequestDto.getFarmerId();
//            var farmer = farmerRepository.findById(farmerId).orElseThrow(()-> new FarmerNotFound(String.format("Farmer with %d does not exist.", farmerId)));
//
//
//            var shopId = orderRequestDto.getShopId();
//            var shop = shopRepository.findById(shopId).orElseThrow(()-> new ShopNotFound(String.format("Shop with %d is not found.", shopId)));
//
//            var order = new Order();
//            var orderLine = new OrderLine();
//
//            var pesticidesDto = orderRequestDto.getOrder().getOrderLine().getPesticides();
//            var pesticideEntities = pesticidesDto.stream()
//                    .map(pesticideDto -> {
//                        var pesticideEntity =  pesticideRepository.findById(pesticideDto.getPesticideId()).orElseThrow(()->{
//                            throw new PesticideNotFound(String.format(" Pesticide ID: %d does not exist.", pesticideDto.getPesticideId()));
//                        });
//
//                        orderLine.setQuantity(orderLine.getQuantity() + pesticideDto.getQuantity());
//                        orderLine.setCost(orderLine.getCost() + (pesticideDto.getQuantity() * pesticideEntity.getPrice()));
//
//                        return pesticideEntity;
//                    })
//                    .toList();
//
//            order.setTotalCost(orderLine.getCost());
//
//
//            farmer.addOrder(order, orderLine, shop, pesticideEntities.toArray(new Pesticide[0]));
//
//            farmerRepository.save(farmer);
//            shopRepository.save(shop);
//            var saved = orderRepository.save(order);
//            orderLineRepository.save(orderLine);
//            return saved;
//
//        } catch (FarmerNotFound e) {
//            throw new RuntimeException(e);
//        } catch (ShopNotFound e) {
//            throw new RuntimeException(e);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }

