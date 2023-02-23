package com.jesusfc.springboot3java17.controller.mvc;


import com.jesusfc.springboot3java17.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/mvc/user")
public class UserController {

    private final UserService userService;

    @GetMapping({"/index", "/", "", "/home"})
    public String index(Model model) {
        model.addAttribute("titulo", "Página de pruebas Spring MVC ");
        return "index";
    }

    @GetMapping({"/list"})
    public String getUsersList(Model model) {
        model.addAttribute("titulo", "Página de pruebas Spring MVC - Listado de usuarios ");
        model.addAttribute("usuarios", userService.getUserList());
        return "listar";
    }

}
