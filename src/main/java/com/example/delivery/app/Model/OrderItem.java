package com.example.delivery.app.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    private Item item;

    @ManyToOne
    private Order order;

    private Integer quantity;

    public OrderItem(Item item, Integer quantity, Order order) {
        this.item = item;
        this.quantity = quantity;
        this.order = order;
    }

}
