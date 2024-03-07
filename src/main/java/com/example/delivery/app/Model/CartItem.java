package com.example.delivery.app.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue
    private Long id;


    public CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    @ManyToOne
    private Item item;

    @ManyToOne
    private Cart cart;

    private Integer quantity;

}
