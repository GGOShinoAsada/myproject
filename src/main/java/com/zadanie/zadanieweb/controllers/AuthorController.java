package com.zadanie.zadanieweb.controllers;

import com.zadanie.zadanieweb.model.Author;
import com.zadanie.zadanieweb.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService service;
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("authors",service.getAllLists());
        return "authors/index";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") int id, Model model){
        model.addAttribute("author", service.getAuthorById(id));
        return "authors/details";
    }
    @GetMapping("/create")
    public String create(Author author){
        return "authors/create";
    }
    @PostMapping("/create")
    public String createPost(Author author){
        service.addingAuthor(author);
        return "redirect:/authors/index";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("author", service.getAuthorById(id));
        return "authors/edit";
    }
    @PostMapping("/edit")
    public String editPost(Author author){
        service.updatingAuthor(author.getId(), author);
        return "redirect:/authors/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteauthor(@PathVariable("id") int id){
        service.removingAuthor(id);
        return "redirect:/authors/index";
    }
}
