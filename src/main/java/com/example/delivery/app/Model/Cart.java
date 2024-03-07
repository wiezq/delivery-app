package com.example.delivery.app.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue
    private Long id;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AppUser appUser;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CartItem> cartItems = Set.of();








}
