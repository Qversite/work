package com.example.demo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class IndexController {
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminPage(){return "admin";}
}
