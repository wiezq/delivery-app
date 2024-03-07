package com.example.delivery.app.Service;

import com.example.delivery.app.DTO.UserDTO;
import com.example.delivery.app.Enum.Role;
import com.example.delivery.app.Exception.UselAlreadyExistsException;
import com.example.delivery.app.Model.AppUser;
import com.example.delivery.app.Model.Cart;
import com.example.delivery.app.Repository.AppUserRepository;
import com.example.delivery.app.Repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {


    private final AppUserRepository userRepository;
    private final CartRepository cartRepository;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private Boolean emailExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public AppUser save(UserDTO userDTO) throws UselAlreadyExistsException {
        if(emailExists(userDTO.getEmail())){
            throw new UselAlreadyExistsException("User with email: " + userDTO.getEmail() + " already exists");
        }

        Cart cart = new Cart();

        AppUser appUser = new AppUser(
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                bCryptPasswordEncoder.encode(userDTO.getPassword()),
                userDTO.getPhoneNumber(),
                Role.USER,
                cartRepository.save(cart)
        );
        cart.setAppUser(appUser);
        return userRepository.save(appUser);
    }


    public AppUser findByEmail(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with email " + email + " not found")
                );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with email " + email + " not found")
                );
    }
}
