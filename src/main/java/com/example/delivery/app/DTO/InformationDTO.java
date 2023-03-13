package com.example.delivery.app.DTO;


import lombok.Data;

@Data
public class InformationDTO {
    private String street;

    private Integer aptNumber;

    private Integer entrance;
    private Integer floor;

    private String comment;
}
