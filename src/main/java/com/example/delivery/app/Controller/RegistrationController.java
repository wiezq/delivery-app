package com.example.delivery.app.Controller;

import com.example.delivery.app.DTO.UserDTO;
import com.example.delivery.app.Exception.UselAlreadyExistsException;
import com.example.delivery.app.Service.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@AllArgsConstructor
@Log4j2
public class RegistrationController {
    private final UserServiceImpl userService;


    @ModelAttribute("user")
    public UserDTO userdto() {
        return new UserDTO();
    }


    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")
                                      UserDTO userDTO) {

        log.info(userDTO);
        try {
            userService.save(userDTO);
        } catch (UselAlreadyExistsException e) {
            return "redirect:/registration?error";
        }
        return "redirect:/registration?success";
    }
}
