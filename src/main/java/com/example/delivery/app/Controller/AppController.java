package com.example.delivery.app.Controller;


import com.example.delivery.app.Enum.Category;
import com.example.delivery.app.Model.CartItem;
import com.example.delivery.app.Model.Item;
import com.example.delivery.app.Service.CartService;
import com.example.delivery.app.Service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping()
@AllArgsConstructor
public class AppController {

    private final ItemService itemService;

    private final CartService cartService;

    @GetMapping("/*")
    public String defaultPath(){
        return "redirect:/home";
    }



    @GetMapping("/menu")
    public String menu(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", auth);
        return "menu";
    }


    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }


    @GetMapping("/category/{type}")
    public String category(Model model, @PathVariable String type) {
        List<Item> categoryItems = itemService.findAllByCategory(Category.valueOf(type));
        model.addAttribute("items", categoryItems);
        return "menu";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping("/cart")
    public String cart(Model model){
        List<CartItem> cartItems =
                cartService
                        .getCartItems();

        Long totalPrice = cartService.getTotalPrice();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }




}
