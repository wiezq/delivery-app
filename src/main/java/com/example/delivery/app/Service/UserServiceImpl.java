package com.example.delivery.app.Service;

import com.example.delivery.app.DTO.UserDTO;
import com.example.delivery.app.Exception.UselAlreadyExistsException;
import com.example.delivery.app.Model.AppUser;
import com.example.delivery.app.Repository.AppUserRepository;
import com.example.delivery.app.Repository.CartRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.delivery.app.Enum.Role.USER;

@Service
public class UserServiceImpl implements UserDetailsService {


    private final AppUserRepository userRepository;

    private final CartRepository cartRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(AppUserRepository userRepository, CartRepository cartRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private Boolean emailExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public AppUser save(UserDTO userDTO) throws UselAlreadyExistsException {
        if(emailExists(userDTO.getEmail())){
            throw new UselAlreadyExistsException("User with email: " + userDTO.getEmail() + " already exists");
        }

//        Cart cart = new Cart();

//        AppUser appUser = new AppUser(
//                userDTO.getFirstName(),
//                userDTO.getLastName(),
//                userDTO.getEmail(),
//                bCryptPasswordEncoder.encode(userDTO.getPassword()),
//                userDTO.getPhoneNumber(),
//                Role.USER,
//                cartRepository.save(cart)
//        );


//        cart.setAppUser(appUser);



        AppUser appUser = new AppUser();
        appUser
                .setFirstName(userDTO.getFirstName())
                .setLastName(userDTO.getLastName())
                .setEmail(userDTO.getEmail())
                .setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .setPhoneNumber(userDTO.getPhoneNumber())
                .setRole(USER)
                .setCart();
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
