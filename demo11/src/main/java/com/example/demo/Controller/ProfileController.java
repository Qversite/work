package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, @AuthenticationPrincipal User user
    ) {
        model.addAttribute("user",userService.findUser(user.getUsername()));
        return "profile";

    }
}


