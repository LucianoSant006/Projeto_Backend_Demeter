package com.example.Projeto_Demeter_Backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entity.EntradaItens;
import com.entity.EntradaProduto;
import com.entity.Produto;
import com.repository.EntradaItensRepository;
import com.repository.EntradaProdutoRepository;
import com.repository.FuncionarioRepository;
import com.repository.ProdutoRepository;
import com.repository.ProdutosRepository;

@Controller
public class EntradaProdutoController {
	
	@Autowired
private EntradaProdutoRepository entradaProdutoRepositoryRepositorio;

    @Autowired
private EntradaItensRepository entradaItensRepository;

@Autowired
ProdutosRepository produtosRepository;

private List<EntradaItens> listaEntrada = new ArrayList<EntradaItens>();

    @Autowired
private FuncionarioRepository funcionarioRepository;
	
	@GetMapping("/administrativo/entrada/cadastrar")
	public ModelAndView cadastrar(EntradaProduto entrada, EntradaItens entradaItens) {
		ModelAndView mv =  new ModelAndView("administrativo/entrada/cadastro");
		mv.addObject("entrada",entrada);
        mv.addObject("listaEntradaItens", this.listaEntrada);
        mv.addObject("entadaItens",entradaItens);
        mv.addObject("listaFuncionarios", funcionarioRepository.findAll());
        mv.addObject("listaProdutos",produtosRepository.findAll());
		return mv;
	}
	
	//@GetMapping("/administrativo/estados/listar")
	//public ModelAndView listar() {
		//ModelAndView mv=new ModelAndView("administrativo/estados/lista");
		//mv.addObject("listaEstados", estadoRepositorio.findAll());
		//return mv;
	//}
	/* 
	@GetMapping("/administrativo/estados/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadoRepositorio.findById(id);
		return cadastrar(estado.get());
	}
	
	@GetMapping("/administrativo/estados/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadoRepositorio.findById(id);
		estadoRepositorio.delete(estado.get());
		return listar();
	}*/

	
	@PostMapping("/administrativo/entrada/salvar")
	public ModelAndView salvar(String acao,EntradaProduto entrada,  EntradaItens entradaItens) {


		if(acao.equals("itens")){
            this.listaEntrada.add(entradaItens);
        }else if(acao.equals("salvar")){
            entradaProdutoRepositoryRepositorio.saveAndFlush(entrada);
            for(EntradaItens it:listaEntrada){
                it.setEntrada(entrada);
                entradaItensRepository.saveAndFlush(it);
				Optional<Produto> prod = produtosRepository.findById(it.getProduto() .getId());
				Produto produto = prod.get();
				produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + it.getQuantidade());
				produto.setValorVenda(it.getValorVenda());
				produtosRepository.saveAndFlush(produto);
				this.listaEntrada = new ArrayList<>();
				
            }
			return cadastrar(new EntradaProduto(), new EntradaItens());

        }
        
		
		return cadastrar(entrada,new EntradaItens());
	}

}
