package com.example.delivery.app.Model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Set;

@Entity
public class Cart {

    @Id
    @GeneratedValue
    private Long id;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AppUser appUser;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CartItem> cartItems = Set.of();


    public Cart(){};

    public Cart(AppUser appUser) {
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public Cart setId(Long id) {
        this.id = id;
        return this;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public Cart setAppUser(AppUser appUser) {
        this.appUser = appUser;
        return this;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public Cart setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
        return this;
    }


}
