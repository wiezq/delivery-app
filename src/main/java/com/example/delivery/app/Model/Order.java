package com.example.delivery.app.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orderr")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate localDate;

    private Long totalPrice;


    @ManyToOne
    private AppUser appUser;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderItem> orderItemSet;
}
