package com.inai.oorpo.library.Library.controllers;

import com.inai.oorpo.library.Library.models.User;
import com.inai.oorpo.library.Library.repository.RoleRepository;
import com.inai.oorpo.library.Library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/login")
    public String login(Model model) {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, BindingResult result, @RequestParam("password_confirm") String passwordConfirm, Model model) {
        try {
            if (result.hasErrors() || !passwordConfirm.equals(user.getPassword())) {
                model.addAttribute("errors", "Данные введены неправильно");
                model.addAttribute("user", user);
                return "auth/register";
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().add(roleRepository.findByName("USER"));
            user.setActive(true);
            userRepository.save(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errors", e.getMessage());
            model.addAttribute("user", user);
            return "auth/register";
        }
    }
}
