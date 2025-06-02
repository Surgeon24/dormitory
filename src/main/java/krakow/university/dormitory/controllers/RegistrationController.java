package krakow.university.dormitory.controllers;

import krakow.university.dormitory.services.RegistrationDTO;
import krakow.university.dormitory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationDTO());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") RegistrationDTO dto, Model model) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            model.addAttribute("error", "Пароли не совпадают");
            return "register";
        }

        if (userService.existsByEmail(dto.getEmail())) {
            model.addAttribute("error", "Пользователь с таким email уже существует");
            return "register";
        }

        userService.registerNewUser(dto);
        return "redirect:/login";
    }
}

