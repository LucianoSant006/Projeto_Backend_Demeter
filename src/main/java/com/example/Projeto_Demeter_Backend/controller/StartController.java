package com.example.Projeto_Demeter_Backend.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartController {
    @GetMapping("/teste")
public ModelAndView start(){
     ModelAndView  mv =  new ModelAndView("layout");
     return mv;
}

}
