package com.example.Projeto_Demeter_Backend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.hibernate.query.PathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Estado;
import com.entity.Produto;
import com.repository.ProdutoRepository;

@Controller
public class ProdutoController {

   private static String caminhoImagens = "C:\\Users\\Administrador\\Documents\\imagens\\";

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
    @GetMapping("/administrativo/produtos/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		produtoRepository.delete(produto.get());
		return listar();
	}
   @GetMapping("/administrativo/produtos/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
//		System.out.println(imagem);
		File imagemArquivo = new File(caminhoImagens + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			System.out.println("No IF");
			return Files.readAllBytes(imagemArquivo.toPath());
		}
        return null;
	}


    @PostMapping("/administrativo/produtos/salvar")
 public ModelAndView salvar(@Validated Produto produto, BindingResult result, @RequestParam("file") MultipartFile arquivo){
            if(result.hasErrors()){
                return cadastrar(produto);
            }
             produtoRepository.saveAndFlush(produto);
            try {
                if(!arquivo.isEmpty()){
                    byte[] bytes = arquivo.getBytes();
                    Path caminho = Paths.get(caminhoImagens+String.valueOf(produto.getId())+arquivo.getOriginalFilename());
                    Files.write(caminho, bytes);

                    produto.setNomeImagem(String.valueOf(produto.getId())+arquivo.getOriginalFilename()
                    );
                }
            }catch(IOException e){
                e.printStackTrace();
            }

           
            return cadastrar(new Produto());
        }

    
}
