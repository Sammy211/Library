package com.inai.oorpo.library.Library.controllers.admin;

import com.inai.oorpo.library.Library.models.User;
import com.inai.oorpo.library.Library.repository.RoleRepository;
import com.inai.oorpo.library.Library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/user/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/user/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute User user, BindingResult result, Model model) {
        System.out.println(user);
        try {
            if (result.hasErrors()) {
                System.out.println(result.getAllErrors());
                model.addAttribute("user", user);
                model.addAttribute("errors", "Данные введены неправильно");
                return "admin/user/create";
            }
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "redirect:/admin/user";
        } catch (Exception e) {
            model.addAttribute("errors", e.getMessage());
            model.addAttribute("user", user);
            return "admin/user/create";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userRepository.getOne(id));
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/user/edit";
    }


    @PostMapping("/edit")
    public String update(@ModelAttribute("user") User user, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("errors", "Данные введены неправильно");
                model.addAttribute("user", user);
                return "admin/user/edit";
            }
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "redirect:/admin/user";
        } catch (Exception e) {
            model.addAttribute("errors", e.getMessage());
            model.addAttribute("user", user);
            return "admin/user/edit";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        userRepository.delete(userRepository.getOne(id));
        return "redirect:/admin/user";
    }
}
