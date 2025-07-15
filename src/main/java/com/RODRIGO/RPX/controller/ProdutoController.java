package com.RODRIGO.RPX.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.RODRIGO.RPX.entity.Categoria;
import com.RODRIGO.RPX.entity.Marca;
import com.RODRIGO.RPX.entity.Produto;
import com.RODRIGO.RPX.repository.CategoriaRepository;
import com.RODRIGO.RPX.repository.MarcaRepository;
import com.RODRIGO.RPX.repository.ProdutoRepository;
import com.RODRIGO.RPX.services.ProdutoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class ProdutoController {

    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;
    private MarcaRepository marcaRepository;
    private ProdutoService produtoService;

    // Página principal com lista e formulários
    @GetMapping("/inicio")
    public String listaProdutos(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("marcas", marcaRepository.findAll());

        // Objetos vazios para os formulários
        model.addAttribute("produto", new Produto());
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("marca", new Marca());

        return "produto/produto";
    }

    // Cadastro de novo produto
    @PostMapping("/produtos/salvar")
    public String salvarProduto(@Valid @ModelAttribute Produto produto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("produtos", produtoRepository.findAll());
            model.addAttribute("categorias", categoriaRepository.findAll());
            model.addAttribute("marcas", marcaRepository.findAll());
            return "produto/produto";
        }

        produtoRepository.save(produto);
        return "redirect:/inicio";
    }

    // Deletar produto
    @GetMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable("id") Long id) {
        produtoService.deletar(id);
        return "redirect:/inicio";
    }

    // Editar produto (do modal)
    @PostMapping("/produtos/editar")
    public String editarProduto(@ModelAttribute Produto produto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("produtos", produtoRepository.findAll());
            model.addAttribute("categorias", categoriaRepository.findAll());
            model.addAttribute("marcas", marcaRepository.findAll());
            return "produto/produto";
        }

        produtoRepository.save(produto); // Como o ID já vem preenchido, ele faz update
        return "redirect:/inicio";
    }
}
