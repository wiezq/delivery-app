package com.example.delivery.app.Model;

import com.example.delivery.app.Enum.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Entity
@Table(name = "app_user")
public class AppUser implements UserDetails {


    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cart cart;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DeliveryAddress deliveryAddress;

    public AppUser() {
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public AppUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AppUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AppUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AppUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AppUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AppUser setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public AppUser setRole(Role role) {
        this.role = role;
        return this;
    }

    public Cart getCart() {
        return cart;
    }

    public AppUser setCart() {
        Cart cart = new Cart(this);
        this.cart = cart;
        return this;
    }

    public AppUser setCart(Cart cart) {
        this.cart = cart;
        return this;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public AppUser setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }
}
