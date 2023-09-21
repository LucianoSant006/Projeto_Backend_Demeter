package com.example.Projeto_Demeter_Backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PricipalControle {

    @GetMapping("/adiministrativo")
    public String acessarPrincipal(){
        return "administrativo/home";
    }
    
}
