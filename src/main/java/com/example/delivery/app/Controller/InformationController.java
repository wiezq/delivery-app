package com.example.delivery.app.Controller;


import com.example.delivery.app.DTO.InformationDTO;
import com.example.delivery.app.Service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
@AllArgsConstructor
public class InformationController {

    private UserServiceImpl userService;

    @ModelAttribute("information")
    public InformationDTO infoDTO(){return new InformationDTO();}


    @GetMapping("/information")
    public String showInformation(){
        return "information";
    }


    @PostMapping("/information")
    public String updateInformation(@ModelAttribute("information") InformationDTO informationDTO){
        System.out.println(informationDTO);
        userService.saveDeliveryAddress(informationDTO);

        return "redirect:/cart?success";
    }
}
