package com.cydeo.controller;

import com.cydeo.dto.UserDto;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<UserDto> users = userService.listUsersForCurrentUser();
        model.addAttribute("users", users);
        return "/user/user-list";
    }

    @GetMapping("/update/{userId}")
    public String editUser(@PathVariable Long userId, Model model) {
        UserDto userDto = userService.findByUserId(userId);
        model.addAttribute("user", userDto);
        return "user/user-update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserDto userDto) {
        userService.updateUser(userDto);
        return "redirect:/users/list";
    }

}
