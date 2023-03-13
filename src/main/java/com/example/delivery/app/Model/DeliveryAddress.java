package com.example.delivery.app.Model;


import jakarta.persistence.*;

@Entity
public class DeliveryAddress {


    @Id
    @GeneratedValue
    private Long id;


    private String street;

    private Integer aptNumber;

    private Integer entrance;
    private Integer floor;

    private String comment;

    @OneToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

    public DeliveryAddress() {
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public Integer getAptNumber() {
        return aptNumber;
    }

    public Integer getEntrence() {
        return entrance;
    }

    public Integer getFloor() {
        return floor;
    }

    public String getComment() {
        return comment;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public DeliveryAddress setStreet(String street) {
        this.street = street;
        return this;
    }

    public DeliveryAddress setAptNumber(Integer aptNumber) {
        this.aptNumber = aptNumber;
        return this;
    }

    public DeliveryAddress setEntrence(Integer entrence) {
        this.entrance = entrence;
        return this;
    }

    public DeliveryAddress setFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public DeliveryAddress setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public DeliveryAddress setAppUser(AppUser appUser) {
        this.appUser = appUser;
        return this;
    }
}
