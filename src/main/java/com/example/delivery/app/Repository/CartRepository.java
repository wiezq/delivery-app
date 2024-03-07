package com.example.delivery.app.Repository;

import com.example.delivery.app.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
