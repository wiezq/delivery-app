package com.example.delivery.app.Model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Order {



    @Id
    @GeneratedValue
    private Long id;

    private LocalDate localDate;

    @OneToMany()
    private Set<CartItem> cartItem = Set.of();

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public Set<CartItem> getCartItem() {
        return cartItem;
    }

    public Order setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        return this;
    }

    public Order setCartItem(Set<CartItem> cartItem) {
        this.cartItem = cartItem;
        return this;
    }
}
