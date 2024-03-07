package com.example.delivery.app.Controller;


import com.example.delivery.app.Exception.ItemNotFoundException;
import com.example.delivery.app.Service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/item")
@AllArgsConstructor
@Log4j2
public class ItemController {

    private final ItemService itemService;



    @GetMapping(value = "/add/{itemId}")
    public String addItem(@PathVariable String itemId) throws ItemNotFoundException {
        itemService.addItemToCart(Long.valueOf(itemId));
        return "redirect:/menu";
    }


    @GetMapping(value = "/plus/{itemId}")
    public String plusItem(@PathVariable String itemId) throws Exception {
        itemService.plusItemInCart(Long.valueOf(itemId));
        return "redirect:/cart";
    }

    @GetMapping(value = "/minus/{itemId}")
    public String minusItem(@PathVariable String itemId) throws Exception {
        itemService.minusItemFromCart(Long.valueOf(itemId));
        return "redirect:/cart";
    }
}
