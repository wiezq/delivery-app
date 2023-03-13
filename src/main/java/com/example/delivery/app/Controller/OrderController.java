package com.example.delivery.app.Controller;


import com.example.delivery.app.Model.*;
import com.example.delivery.app.Repository.CartItemRepository;
import com.example.delivery.app.Service.CartService;
import com.example.delivery.app.Service.OrderService;
import com.example.delivery.app.Service.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private UserServiceImpl userService;

    private CartItemRepository cartItemRepository;

    private CartService cartService;

    private OrderService orderService;

    @GetMapping("/make")
    public String makeOrder(Model model) {
        AppUser currentUser = userService.getCurrentUser();
        Cart userCart = currentUser.getCart();

        if (userCart.getCartItems().size() == 0) {
            model.addAttribute("error", "Корзина пуста");
            return "redirect:/cart?error";
        }

        if (Objects.isNull(currentUser.getDeliveryAddress())){
            return "redirect:/information";
        }


        orderService.makeOrder(currentUser, userCart);


        return "redirect:/orders";

    }


    @GetMapping("/all")
    public String getAllOrders(Model model){
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }


}
