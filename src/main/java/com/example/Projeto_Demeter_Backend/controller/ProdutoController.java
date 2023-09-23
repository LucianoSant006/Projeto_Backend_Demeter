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

import com.entity.Estado;
import com.entity.Produto;
import com.repository.ProdutoRepository;

@Controller
public class ProdutoController {

   // private static String caminhoImagens = "Administrador\D//ocuments\imagens\";

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/administrativo/produtos/cadastrar")
    public ModelAndView cadastrar(Produto produto){
        ModelAndView mv = new ModelAndView("administrativo/produtos/cadastro");
        mv.addObject("produto",produto);
        return mv;
    }

@GetMapping("/administrativo/produtos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return cadastrar(produto.get());
	}

    @GetMapping("/administrativo/produtos/listar")
    public ModelAndView listar(){
        ModelAndView mv=new ModelAndView("administartivo/produtos/lista");
        mv.addObject("listaProdutos", produtoRepository.findAll());
        return mv;
    }
    @PostMapping("/adiministrativo/produtos/salvar")
 public ModelAndView salvar(@Validated Produto produto, BindingResult result){
            if(result.hasErrors()){
                return cadastrar(produto);
            }
            produtoRepository.saveAndFlush(produto);
            return cadastrar(new Produto());
        }

    
}
