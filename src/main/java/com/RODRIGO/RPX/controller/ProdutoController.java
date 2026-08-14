package com.RODRIGO.RPX.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.RODRIGO.RPX.entity.Produto;
import com.RODRIGO.RPX.services.ProdutoPageService;
import com.RODRIGO.RPX.services.ProdutoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoPageService produtoPageService;

    @GetMapping("/Gerenciador/de/produtos")
    public String listaProdutos(Model model) {

        carregarPagina(model);

        return "produto/produto";
    }

    @PostMapping("/produtos/salvar")
    public String salvarProduto(
            @Valid @ModelAttribute("produto") Produto produto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            carregarPagina(model);

            return "produto/produto";
        }

        produtoService.salvar(produto);

        return "redirect:/Gerenciador/de/produtos";
    }

    @GetMapping("/deletar/{id}")
    public String deletarProduto(
            @PathVariable Long id) {

        produtoService.deletar(id);

        return "redirect:/Gerenciador/de/produtos";
    }

    @PostMapping("/produtos/editar")
    public String editarProduto(
            @Valid @ModelAttribute("produto") Produto produto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            carregarPagina(model);

            return "produto/produto";
        }

        produtoService.atualizar(
                produto.getId(),
                produto
        );

        return "redirect:/Gerenciador/de/produtos";
    }

    @GetMapping("/")
    public String redirecionarParaInicio() {

        return "redirect:/Gerenciador/de/produtos";
    }

    private void carregarPagina(Model model) {

        model.addAttribute(
                "produtos",
                produtoPageService.listarProdutos()
        );

        model.addAttribute(
                "categorias",
                produtoPageService.listarCategorias()
        );

        model.addAttribute(
                "marcas",
                produtoPageService.listarMarcas()
        );

        model.addAttribute(
                "produto",
                produtoPageService.novoProduto()
        );

        model.addAttribute(
                "categoria",
                produtoPageService.novaCategoria()
        );

        model.addAttribute(
                "marca",
                produtoPageService.novaMarca()
        );
    }
}