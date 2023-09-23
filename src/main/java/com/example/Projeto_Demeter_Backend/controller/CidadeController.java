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

import com.entity.Cidade;
import com.repository.CidadeRepository;
import com.repository.EstadoRepository;

@Controller
public class CidadeController {
	
	@Autowired
private CidadeRepository cidadeRepository;

    @Autowired
private EstadoRepository estadoRepository;
	
	@GetMapping("/administrativo/cidades/cadastrar")
	public ModelAndView cadastrar(Cidade cidade) {
		ModelAndView mv =  new ModelAndView("administrativo/cidades/cadastro");
        mv.addObject("listaEstados", estadoRepository.findAll());
		mv.addObject("cidade",cidade);
		return mv;
	}
	
	@GetMapping("/administrativo/cidades/listar")
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/estados/lista");
		mv.addObject("listaCidades",cidadeRepository.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/cidades/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cidade> cidade =cidadeRepository.findById(id);
		return cadastrar(cidade.get());
	}
	
	@GetMapping("/administrativo/cidades/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		cidadeRepository.delete(cidade.get());
		return listar();
	}
	
	@PostMapping("/administrativo/cidades/salvar")
	public ModelAndView salvar(@Validated Cidade cidade, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastrar(cidade);
		}
		cidadeRepository.saveAndFlush(cidade);
		
		return cadastrar(new Cidade());
	}

}




