package com.example.delivery.app.Service;


import com.example.delivery.app.Enum.Category;
import com.example.delivery.app.Exception.ItemNotFoundException;
import com.example.delivery.app.Model.AppUser;
import com.example.delivery.app.Model.Cart;
import com.example.delivery.app.Model.CartItem;
import com.example.delivery.app.Model.Item;
import com.example.delivery.app.Repository.CartItemRepository;
import com.example.delivery.app.Repository.CartRepository;
import com.example.delivery.app.Repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final UserServiceImpl userService;

    public List<Item> findAll() {
        return itemRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Item::getCategory))
                .toList();
    }

    public Item findByName(String name) throws ChangeSetPersister.NotFoundException {
        return itemRepository.findByName(name).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public List<Item> findAllByCategory(Category category) {
        return itemRepository.findAllByCategory(category);
    }

    public Item findById(Long id) throws ItemNotFoundException {
        return itemRepository
                .findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item with id: " + id + " not found"));
    }

    public void addItemToCart(Long itemId) throws ItemNotFoundException {
        AppUser appUser = userService.findByEmail(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );

        Item item = findById(itemId);

        Cart cart = appUser.getCart();

        cart
                .getCartItems()
                .stream()
                .filter(cartItem -> cartItem.getItem().equals(item))
                .findAny()
                .ifPresentOrElse(cartItem -> {
                            cartItem.setQuantity(cartItem.getQuantity() + 1);
                        },
                        () -> {
                            CartItem cartItem = new CartItem(item);
                            cartItem.setCart(cart);
                            cart.getCartItems().add(cartItem);
                        }
                );

        cartRepository.save(cart);

    }

    public void minusItemFromCart(Long cartItemId) throws Exception {
        CartItem cartItem = getCartItemById(cartItemId);

        if (cartItem.getQuantity() == 1) {
            cartItem.getCart().getCartItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItemRepository.save(cartItem);
        }


    }

    private CartItem getCartItemById(Long cartItemId) throws Exception {
        CartItem cartItem =
                cartItemRepository
                        .findById(cartItemId)
                        .orElseThrow(
                                () -> new Exception("CartItem with id: " + cartItemId + " not found"));
        return cartItem;
    }


    public void plusItemInCart(Long cartItemId) throws Exception {
        CartItem cartItem = getCartItemById(cartItemId);

        cartItem.setQuantity(cartItem.getQuantity() + 1);

        cartItemRepository.save(cartItem);


    }
}
