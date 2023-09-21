 
package com.example.Projeto_Demeter_Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.entity.Funcionarios;
import com.repository.FuncionarioRepository;

public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/administrativo/funcionario/cadastrar")
    public ModelAndView cadastrar(Funcionarios funcionarios){
        ModelAndView mv = new ModelAndView("administrativo/funcionario/cadastro");
        mv.addObject("funcionarios",funcionarios);
        return mv ;
    }
    @GetMapping("/administartivo/funcionarios/listar")
        public ModelAndView listar(){
            ModelAndView mv = new ModelAndView("/administartivo/funcionarios/listar");
            mv.addObject("listaFuncionarios",funcionarioRepository.findAll());
            return mv;
        }
        @PostMapping("/administrativo/funcionarios/salvar")
        public  ModelAndView salvar(@Validated Funcionarios funcionarios, BindingResult result) {
        if(result.hasErrors()){
            return cadastrar(funcionarios);
        }
        funcionarioRepository.saveAndFlush(funcionarios);
        return cadastrar(new Funcionarios());
    }


}
