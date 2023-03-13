package com.example.delivery.app.Service;


import com.example.delivery.app.Model.*;
import com.example.delivery.app.Repository.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private CartService cartService;

    private CartItemRepository cartItemRepository;

    private UserServiceImpl userService;

    public void makeOrder(AppUser currentUser, Cart userCart) {
        //Create new order
        Order order = new Order();
        order.setTotalPrice(cartService.getTotalPrice());
        order.setAppUser(currentUser);
        order.setLocalDate(LocalDate.now());
        currentUser.getOrderSet().add(order);

        // Remove items from cart
        List<CartItem> cartItems = cartService.getCartItems();


        List<OrderItem> orderItems = new ArrayList<>();


        for(int i = 0; i < cartItems.size(); i++){
            CartItem cartItem = cartItems.get(i);
            orderItems.add(
                    new OrderItem(
                            cartItem.getItem(),
                            cartItem.getQuantity(),
                            order
                    )
            );
            cartItemRepository.deleteById(cartItem.getId());
        }

        userCart.setCartItems(new HashSet<>());


        order.setOrderItemSet(new HashSet<>(orderItems));


        userService.update(currentUser);
    }

    public List<Order> getAllOrders(){
        return userService
                .getCurrentUser()
                .getOrderSet()
                .stream()
                .sorted(
                        Comparator.comparing(
                                Order::getLocalDate
                        )
                )
                .collect(Collectors.toList());
    }
}
