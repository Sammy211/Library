package com.inai.oorpo.library.Library.controllers.admin;

import com.inai.oorpo.library.Library.models.Book;
import com.inai.oorpo.library.Library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;


    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "admin/book/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("book", new Book());
        return "/admin/book/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@Valid Book book, BindingResult bindingResult, Model model) {
        System.out.println(book);
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("book", book);
                model.addAttribute("errors", "Данные введены неправильно");
                return "admin/book/create";
            }
            bookRepository.save(book);
            return "redirect:/admin/book";
        } catch (Exception e) {
            model.addAttribute("errors", e.getMessage());
            model.addAttribute("book", book);
            return "admin/book/create";
        }
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookRepository.getOne(id));
        return "admin/book/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute("book") Book book, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("book", book);
                model.addAttribute("errors", "Данные введены неправильно");
                return "admin/book/edit";
            }
            bookRepository.save(book);
            return "redirect:/admin/book";
        } catch (Exception e) {
            model.addAttribute("errors", e.getMessage());
            model.addAttribute("book", book);
            return "admin/book/edit";
        }
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        bookRepository.delete(bookRepository.getOne(id));
        return "redirect:/admin/book";
    }

}
