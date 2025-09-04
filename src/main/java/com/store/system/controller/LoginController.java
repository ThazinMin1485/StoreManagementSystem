package com.store.system.controller;

import com.store.system.code.generator.VerificationCodeGenerator;
import com.store.system.dto.LoginDTO;
import com.store.system.dto.UserDTO;
import com.store.system.dto.UserOtpDTO;
import com.store.system.entity.User;
import com.store.system.entity.UserOtp;
import com.store.system.repository.UserRepository;
import com.store.system.service.EmailService;
import com.store.system.service.UserOtpService;
import com.store.system.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class LoginController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserOtpService otpService;

    @Autowired
    private EmailService emailService;

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
    public String signUp(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "user/register";
        }
        if(service.getUserByEmail(userDTO.getEmail()) != null) {
            model.addAttribute("user", userDTO);
            model.addAttribute("errorMessage", "User already exists");
            return "user/register";
        }
        registerUser(userDTO);
        session.setAttribute("email", userDTO.getEmail());
        return "redirect:/verifyCode";
    }

    @GetMapping("/verifyCode")
    public String goToVerification(@SessionAttribute(value = "email", required = false) String email, Model model) {
        UserOtpDTO dto = new UserOtpDTO();
        if (email == null || email.isEmpty()) {
            return "redirect:/signUp";
        }
        model.addAttribute("email", email);
        model.addAttribute("userOtp", new UserOtpDTO());
        return "user/verificationCode";
    }

    @PostMapping("/verifyCode")
    public String verifyCode(@Valid @ModelAttribute("userOtp") UserOtpDTO dto,@SessionAttribute("email") String email, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("userOtp", dto);
            return "user/verificationCode";
        }
        Boolean verify = otpService.verifyCode(email, dto.getOtp(), LocalDateTime.now());
        if(verify) {
            User user = service.getUserByEmail(email);
            user.setEnabled(true);
            service.saveUser(user);
           attributes.addFlashAttribute("successMessage", "Successfully Verified");
            return "redirect:/login";
        } else {
            model.addAttribute("userOtp", dto);
            model.addAttribute("errorMessage", "Verification code is wrong or expired");
            return "user/verificationCode";
        }
    }

    public void registerUser(UserDTO dto) {
        String code = VerificationCodeGenerator.generateVerificationCode();
        User user = new User(dto);
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setUserType("user");
        user.setEnabled(false);
        User savedUser = repository.save(user);
        UserOtp otp = new UserOtp();
        otp.setEmail(dto.getEmail());
        otp.setOtp(code);
        otp.setExpiredTime(LocalDateTime.now().plusMinutes(15));
        otp.setUser(savedUser);
        otpService.saveOtp(otp);
        emailService.sendEmailVerification(dto.getEmail(), code);
    }
}
