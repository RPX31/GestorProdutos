package com.RODRIGO.RPX.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.RODRIGO.RPX.entity.Categoria;
import com.RODRIGO.RPX.services.CategoriaService;
import com.RODRIGO.RPX.services.ProdutoPageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final ProdutoPageService produtoPageService;

    @GetMapping
    public String listar(Model model) {

        carregarPagina(model);

        return "produto/produto";
    }

    @GetMapping("/buscar")
    public String buscarPorNome(
            @RequestParam("nome") String nome,
            Model model) {

        List<Categoria> categorias =
                categoriaService.buscarPorNome(nome);

        model.addAttribute(
                "categorias",
                categorias
        );

        model.addAttribute(
                "produtos",
                produtoPageService.listarProdutos()
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
                new Categoria()
        );

        model.addAttribute(
                "marca",
                produtoPageService.novaMarca()
        );

        return "produto/produto";
    }

    @GetMapping("/deletar/{id}")
    public String deletarCategoria(
            @PathVariable Long id) {

        categoriaService.deletar(id);

        return "redirect:/Gerenciador/de/produtos";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("categoria") Categoria categoria) {

        categoriaService.salvar(categoria);

        return "redirect:/Gerenciador/de/produtos";
    }

    private void carregarPagina(Model model) {

        model.addAttribute(
                "produtos",
                produtoPageService.listarProdutos()
        );

        model.addAttribute(
                "categorias",
                categoriaService.listarTodos()
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
                new Categoria()
        );

        model.addAttribute(
                "marca",
                produtoPageService.novaMarca()
        );
    }
}