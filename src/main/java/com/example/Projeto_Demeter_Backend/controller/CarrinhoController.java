package com.example.Projeto_Demeter_Backend.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarrinhoController {
        

   @GetMapping("/carrinho")
	@ResponseBody
    public ModelAndView chamarCarrinho() {
        ModelAndView mv = new ModelAndView("cliente/carrinho");
        return mv;

    }
	
	

}
