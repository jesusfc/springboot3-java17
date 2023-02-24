package com.jesusfc.springboot3java17.controller.mvc;


import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/form")
    public ModelAndView addUser(ModelAndView modelAndView) {
        Map<String, Object> modelObj = new HashMap<>();
        modelObj.put("user", new UserEntity());
        modelObj.put("titulo", "Añadir nuevo usuario");
        modelAndView.addAllObjects(modelObj);
        modelAndView.setViewName("add-user");
        return modelAndView;
    }

    @PostMapping("add-user-form")
    public String saveUser(UserEntity userEntity){
        userService.saveUser(userEntity);
        return "redirect:/mvc/user/list";
    }
}
