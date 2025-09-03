package com.store.system.controller;

import com.store.system.dto.LoginDTO;
import com.store.system.dto.UserDTO;
import com.store.system.entity.User;
import com.store.system.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/login")
    public String userLogin(Model model) {
        model.addAttribute("login", new LoginDTO());
        return "user/login";
    }

    @GetMapping("/logout")
    public String LogOut(Model model) {
        model.addAttribute("login", new LoginDTO());
        return "redirect:/login";
    }

    @GetMapping("/signUp")
    public String goToSignUp(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user/register";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "user/register";
        }
        return "user/verificationCode";
    }
}
