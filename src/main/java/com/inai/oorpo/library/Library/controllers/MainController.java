package com.inai.oorpo.library.Library.controllers;

import com.inai.oorpo.library.Library.models.Book;
import com.inai.oorpo.library.Library.models.User;
import com.inai.oorpo.library.Library.repository.BookRepository;
import com.inai.oorpo.library.Library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/")
    public String index(Model model, Principal principal) {
        User user;
        if (principal != null) {
            user = userRepository.findByUsername(principal.getName());
            model.addAttribute("user", user);
        }
        model.addAttribute("books", bookRepository.findAll());
        return "front/index";
    }

    @RequestMapping("/mylist")
    public String myList(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("books", user.getTakenBooks());
        return "front/book/mylist";
    }

    @RequestMapping("/mylist/add/{id}")
    public String addToMyList(@PathVariable("id") Long id, Model model, Principal principal, HttpServletRequest request) {
        User user = userRepository.findByUsername(principal.getName());
        Book book = bookRepository.getOne(id);
        user.getTakenBooks().add(book);
        userRepository.save(user);
        model.addAttribute("books", user.getTakenBooks());
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/mylist/delete/{id}")
    public String deleteFromMyList(@PathVariable("id") Long id, Model model, Principal principal, HttpServletRequest request) {
        User user = userRepository.findByUsername(principal.getName());
        Book book = bookRepository.getOne(id);
        user.getTakenBooks().remove(book);
        userRepository.save(user);
        model.addAttribute("books", user.getTakenBooks());
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/wishlist")
    public String wishList(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("books", user.getWishList());
        return "front/book/wishlist";
    }

    @RequestMapping("/wishlist/add/{id}")
    public String addToWishList(@PathVariable("id") Long id, Model model, Principal principal, HttpServletRequest request) {
        User user = userRepository.findByUsername(principal.getName());
        Book book = bookRepository.getOne(id);
        user.getWishList().add(book);
        userRepository.save(user);
        model.addAttribute("books", user.getWishList());
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/wishlist/delete/{id}")
    public String deleteFromWishList(@PathVariable("id") Long id, Model model, Principal principal, HttpServletRequest request) {
        User user = userRepository.findByUsername(principal.getName());
        Book book = bookRepository.getOne(id);
        user.getWishList().remove(book);
        userRepository.save(user);
        model.addAttribute("books", user.getWishList());
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }


}
