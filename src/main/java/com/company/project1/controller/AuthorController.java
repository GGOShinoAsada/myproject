package com.company.project1.controller;

import com.company.project1.model.Author;
import com.company.project1.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;

@Controller
@RequestMapping("authors/")
public class AuthorController {

    public AuthorService service;
    @Autowired
    public  void setService(AuthorService service){
        this.service = service;
    }
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute(service.getAllAuthors());
        int uu = 0;
        return "/authors/index";
    }
    @GetMapping("/create")
    public String create(){
        return "authors/create/";
    }
    @PostMapping("/create")
    public String addingNewAuthor(@RequestParam("country") String country, @RequestParam("name") String name, @RequestParam("age") String age)
    {
        if (name!=null && age != null && country != null)
        {
            int tmp = Integer.parseInt(age);
            Author author = new Author(name, country, tmp);
            service.addingAuthor(author);
            return "redirect: authors/";
        }
        else {
            return "authors/create";
        }
    }
    @GetMapping("/author/details/{id}")
    public  String details(@RequestPart("id") int id, Model model) {
        Author author = service.getAuthorById(id);
        model.addAttribute("author", author);
        return "authors/index";
    }
    @GetMapping("/edit")
    public String update(@RequestPart("id") int id, Model model){
        if (id>0){
            Author author = service.getAuthorById(id);
            model.addAttribute("author", author);
        }
        return "uautors/edit";
    }
    @PostMapping("/edit/{id}")
    public String updatePost( @RequestPart("id") int id,@RequestParam("name") String name, @RequestParam("country") String country, @RequestParam("age") String age){
        if (name!=null && country!=null && age!=null){
            Author data = service.getAuthorById(id);
            if (data!=null){
                data.setName(name);
                int tmp = Integer.parseInt(age);
                data.setAge(tmp);
                data.setCountry(country);
            }
            //service.updatingAuthor(new Author(name, country, tmp));
            return "redirect: /authors";
        }
        else {
            return "authors/edit";
        }
    }
    @GetMapping("/edit/{id}")
    public String updateGet(@RequestPart("id") int id, Model model){
        Author author = service.getAuthorById(id);
        model.addAttribute("author",author);
        return "authors/edit";
    }
    @GetMapping("authors/delete/{id}")
    public String delete(@RequestPart("id") int id){
        Author author = service.getAuthorById(id);
        if (author!=null){
            service.removingAuthor(id);
        }
        return "redirect: authors/index";
    }
}
