package com.example.Projeto_Demeter_Backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Cidade;
 @Controller
public class LoginController {
   


    @GetMapping("/login")
    public ModelAndView cadastrar(Cidade cidade){
        ModelAndView mv = new ModelAndView("/login");
        return mv;
    }
    
}


