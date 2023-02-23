package com.example.delivery.app.Service;

import com.example.delivery.app.Model.AppUser;
import com.example.delivery.app.Model.CartItem;
import com.example.delivery.app.Model.Item;
import com.example.delivery.app.Repository.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {

    private final UserServiceImpl userService;

    private final CartItemRepository cartItemRepository;

    public List<CartItem> getCartItems() {
        AppUser appUser = userService.findByEmail(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );
        return appUser
                .getCart()
                .getCartItems()
                .stream()
                .sorted(
                        Comparator.comparing(
                                CartItem::getItem, Comparator.comparing(Item::getName)))
                .collect(Collectors.toList());
    }

    public Long getTotalPrice() {
        AppUser appUser = userService.findByEmail(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );

        Long totalPrice = 0L;

        Set<CartItem> cartItems = appUser.getCart().getCartItems();

        for(CartItem cartItem : cartItems) {
            totalPrice += cartItem.getItem().getPrice() * cartItem.getQuantity();
        }

        return totalPrice;
    }
}
