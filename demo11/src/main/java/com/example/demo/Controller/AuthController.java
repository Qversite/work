package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import com.example.demo.UsersDt.UsersDt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Некорректное имя пользователя или пароль");
        }
        if (logout != null) {
            model.addAttribute("message", "Вы успешно вышли из системы");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registerForm(Model model) {
        model.addAttribute("usersDt", new UsersDt());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("usersDt") UsersDt usersDt, BindingResult result, Model model) {
        if (userService.findByEmail(usersDt.getEmail()) != null) {
            result.rejectValue("email", "error.user", "Этот адрес электронной почты уже используется");
        }
        if (result.hasErrors()) {
            return "registration";
        }
        userService.registration(usersDt);
        return "redirect:/login";
    }
}
