package com.example.Projeto_Demeter_Backend.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.ItensCompra;
import com.entity.Produto;
import com.repository.ProdutoRepository;

@Controller
public class CarrinhoController {
        
    private List<ItensCompra> itensCompra = new ArrayList <ItensCompra>();

    @Autowired
    private ProdutoRepository produtoRepository;

   @GetMapping("/carrinho")
	@ResponseBody
    public ModelAndView chamarCarrinho() {
        ModelAndView mv = new ModelAndView("cliente/carrinho");
        mv.addObject("listaItens", itensCompra);
        return mv;

    }
    @GetMapping("/adicionarCarrinho/{id}")
	@ResponseBody
    public ModelAndView adicionarCarrinho(@PathVariable Long id
    ) {
        ModelAndView mv = new ModelAndView("cliente/carrinho");
        Optional <Produto> prod = produtoRepository.findById(id);
        Produto produto = prod.get();
        ItensCompra item = new ItensCompra();
        item.setProduto(produto);
        item.setValorUnitario(produto.getValorVenda());
        item.setQuantidade(item.getQuantidade()+1);
         item.setValorTotal(item.getQuantidade()*item.getValorUnitario());
        itensCompra.add(item);
        mv.addObject("listaItens", itensCompra);
        itensCompra.add(item);
        return mv;

    }

	
	

}
