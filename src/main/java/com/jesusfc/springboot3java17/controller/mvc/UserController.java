package com.jesusfc.springboot3java17.controller.mvc;


import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.services.UserService;
import com.jesusfc.springboot3java17.services.WebClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/mvc/user")
public class UserController {

    private final UserService userService;
    private final WebClientService webClientService;

    @GetMapping({"/index", "/", "", "/home"})
    public String index(Model model) {
        model.addAttribute("titulo", "Página de pruebas Spring MVC ");
        return "index";
    }

    @GetMapping({"/list"})
    public String getUsersList(Model model) {
        model.addAttribute("titulo", "Página de pruebas Spring MVC - Listado de usuarios ");
        model.addAttribute("usuarios", userService.getUserList());

        // Cargar imágenes del API externa y agregar al modelo
        //List<String> imagesUrl = webClientService.getImagesUrl();
        //model.addAttribute("urlImages", imagesUrl);

        List<String> imagesUrl = List.of();
        model.addAttribute("urlImages", imagesUrl);

        return "listar";
    }

    @GetMapping("/form")
    public ModelAndView addUser(ModelAndView modelAndView) {
        Map<String, Object> modelObj = new HashMap<>();
        modelObj.put("cliente", new UserEntity());
        modelObj.put("titulo", "Añadir nuevo usuario");
        modelAndView.addAllObjects(modelObj);
        modelAndView.setViewName("add-user");
        return modelAndView;
    }

    @PostMapping("/form")
    public String saveUser(UserEntity userEntity){
        userEntity.setCreateAt(LocalDateTime.now());
        userService.saveUser(userEntity);
        return "redirect:/mvc/user/list";
    }
}
