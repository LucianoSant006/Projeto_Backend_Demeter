 
package com.example.Projeto_Demeter_Backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.entity.Funcionario;
import com.repository.CidadeRepository;
import com.repository.FuncionarioRepository;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping("/administrativo/funcionarios/cadastrar")
    public ModelAndView cadastrar(Funcionario funcionario){
        ModelAndView mv = new ModelAndView("administrativo/funcionarios/cadastro");
        mv.addObject("funcionario",funcionario);
        mv.addObject("listaCidades",cidadeRepository.findAll());
        return mv ;
    }
    @GetMapping("/administrativo/funcionarios/listar")
        public ModelAndView listar(){
            ModelAndView mv = new ModelAndView("/administrativo/funcionarios/lista");
            mv.addObject("listaFuncionarios",funcionarioRepository.findAll());
            return mv;
        }

        @GetMapping("/admimistrativo/funcionarios/editar/{id}")//valor dessa variavel vai atrivuir na de baixo
        public ModelAndView editar(@PathVariable("id") Long id){
            Optional <Funcionario> funcionario = funcionarioRepository.findById(id);
            return cadastrar( funcionario.get());

        }
          @GetMapping("/admimistrativo/funcionarios/remover/{id}")//valor dessa variavel vai atrivuir na de baixo
        public ModelAndView remover(@PathVariable("id") Long id){
            Optional <Funcionario> funcionario = funcionarioRepository.findById(id);
            funcionarioRepository.delete(funcionario.get());
            return listar();

        }

        @PostMapping("/administrativo/funcionarios/salvar")
        public  ModelAndView salvar(@Validated Funcionario funcionario, BindingResult result) {
        if(result.hasErrors()){
            return cadastrar(funcionario);
        }
        funcionarioRepository.saveAndFlush(funcionario);
        return cadastrar(new Funcionario());
    }


}
