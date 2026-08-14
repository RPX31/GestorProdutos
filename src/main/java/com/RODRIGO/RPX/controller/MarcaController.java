package com.RODRIGO.RPX.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.RODRIGO.RPX.entity.Marca;
import com.RODRIGO.RPX.services.MarcaService;
import com.RODRIGO.RPX.services.ProdutoPageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService marcaService;
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

        model.addAttribute(
                "marcas",
                marcaService.buscarPorNome(nome)
        );

        model.addAttribute(
                "produtos",
                produtoPageService.listarProdutos()
        );

        model.addAttribute(
                "categorias",
                produtoPageService.listarCategorias()
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
                new Marca()
        );

        return "produto/produto";
    }

    @GetMapping("/deletar/{id}")
    public String deletarMarca(
            @PathVariable Long id) {

        marcaService.deletar(id);

        return "redirect:/Gerenciador/de/produtos";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("marca") Marca marca) {

        marcaService.salvar(marca);

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
                marcaService.listarTodos()
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
                new Marca()
        );
    }
}