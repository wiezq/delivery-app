package com.example.delivery.app.Model;


import com.example.delivery.app.Enum.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;
    private Long price;


    private String imgURL;


    public Item(String name, String description, Category category, Long price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.imgURL = "/images/" + name + ".png";
    }


}
