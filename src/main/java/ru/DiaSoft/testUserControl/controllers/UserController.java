package ru.DiaSoft.testUserControl.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.DiaSoft.testUserControl.dao.UserDao;
import ru.DiaSoft.testUserControl.models.User;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }


    @GetMapping()
    public String users(Model model){

        model.addAttribute("userList", userDao.userList());

        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){


        model.addAttribute("user", userDao.getUser(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute("userNew") User userNew){
        return "users/new";
    }

    @PostMapping()
    public String createClient(@ModelAttribute("userNew") @Valid User userNew, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "users/new";
        }
        userDao.save(userNew);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editClient(Model model, @PathVariable("id") int id){
        model.addAttribute("userEdit", userDao.getUser(id));
        return "users/edit";
    }
    @PatchMapping("/{id}")
    public String updateClient(@ModelAttribute("userEdit") @Valid User userEdit, BindingResult bindingResult, @PathVariable("id") int id){

        if (bindingResult.hasErrors()){
            return "users/edit";
        }
        userDao.update(userEdit, id);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable("id") int id){

        userDao.delete(id);
        return "redirect:/users";
    }
}
