package com.example.delivery.app.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    private Item item;

    @ManyToOne
    private Cart cart;

    private Integer quantity;

    public CartItem(){};

    public CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }

}
