package com.cookbook.cookbookv3.CONTROLLER;


import com.cookbook.cookbookv3.MODEL.Recipe;
import com.cookbook.cookbookv3.MODEL.User;
import com.cookbook.cookbookv3.REPOSITORY.UserRepository;
import com.cookbook.cookbookv3.SERVICE.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping(value = "/profil/{id}")
    public User profil(@PathVariable String id){
        return this.userService.findById(id);
    }

    @CrossOrigin
    @PostMapping(value = "/add")
    public User addUser(@RequestBody User u){
        userService.addUser(u);
        return u;
    }

    @GetMapping(value = "/all")
    public List<User> allUser(){
        return userService.findAll();

    }

    @RequestMapping(value = "/delete/{usN}", method = RequestMethod.POST)
    public List<User> deleteUser(@PathVariable("usN") String un){
        userService.deleteUser(un);
        return null;
    }

    @RequestMapping(value = "/findOne/{usN}", method = RequestMethod.POST)
    public User findUser(@PathVariable("usN") String un){
        return this.userService.findByUserName(un);
    }

    @GetMapping(value = "/getId/{username}/{password}")
    public User loginUser(@PathVariable("username") String username, @PathVariable("password") String password){
        User user = this.userService.login(username,password);
        return user;

        }

    @PostMapping(value = "/addFavorite")
    public void addFavoriteRecipe(@RequestBody Recipe recipe,@RequestBody User user){
        this.userService.addFavoriteRecipe(user,recipe);
    }

}
