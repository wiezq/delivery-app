package com.example.delivery.app;

import com.example.delivery.app.Enum.Category;
import com.example.delivery.app.Model.Item;
import com.example.delivery.app.Repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@AllArgsConstructor
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private ItemRepository itemRepository;


    @Override
    public void run(String... args) throws Exception {
        setItems();
    }

    public void setItems() {
        if(itemRepository.findAll().isEmpty()) {
            List<Item> items = List.of(
                    new Item(Category.SALAD.name(), "1", Category.SALAD, 101L),
                    new Item(Category.SOUP.name(), "2", Category.SOUP, 102L),
                    new Item(Category.SUSHI.name(), "3", Category.SUSHI, 103L),
                    new Item(Category.PORK.name(), "4", Category.PORK, 104L),
                    new Item(Category.BEEF.name(), "5", Category.BEEF, 105L),
                    new Item(Category.CHICKEN.name(), "6", Category.CHICKEN, 106L),
                    new Item(Category.KOREAN_NOODLES.name(), "7", Category.KOREAN_NOODLES, 107L),
                    new Item(Category.DISHES_WITH_RICE.name(), "8", Category.DISHES_WITH_RICE, 108L),
                    new Item(Category.SEA_FOOD.name(), "9", Category.SEA_FOOD, 109L),
                    new Item(Category.DESSERT.name(), "10", Category.DESSERT, 110L),
                    new Item(Category.DRINKS.name(), "11", Category.DRINKS, 111L)
            );
            itemRepository.saveAll(items);
        }

    }
}
