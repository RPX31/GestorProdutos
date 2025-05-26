package com.RODRIGO.RPX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.RODRIGO.RPX.entity.Produto;
import com.RODRIGO.RPX.repository.CategoriaRepository;
import com.RODRIGO.RPX.repository.MarcaRepository;
import com.RODRIGO.RPX.repository.ProdutoRepository;

import jakarta.validation.Valid;

@Controller
public class ProdutoController {

    @Autowired
    private  ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping("/produto")
    public String listaProdutos(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "produto";
    }


@PostMapping("/formulario")
public String salvarProduto(@Valid @ModelAttribute Produto produto, BindingResult result, Model model) {
    if (result.hasErrors()) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("marcas", marcaRepository.findAll());
        return "formulario";
    }

    produtoRepository.save(produto);
    return "redirect:/sucesso";
}


        // Mostra o formulário de cadastro
    @GetMapping("/formulario")
public String mostrarFormulario(Model model) {
    model.addAttribute("produto", new Produto());
    model.addAttribute("categorias", categoriaRepository.findAll());
    model.addAttribute("marcas", marcaRepository.findAll());
    return "formulario";
}

     @GetMapping("/sucesso")
    public String sucesso() {
        return "sucesso"; // sucesso.html, se quiser criar uma página de sucesso
    }
}
