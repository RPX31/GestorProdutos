package com.RODRIGO.RPX.controller;

import com.RODRIGO.RPX.entity.Categoria;
import com.RODRIGO.RPX.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String listaCategorias(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("categoria", new Categoria());
        return "categoria/lista";
    }
    @PostMapping
    public String salvar(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
        if (result.hasErrors()) {
            return "/produto";
        }
        categoriaRepository.save(categoria);
        return "redirect:/inicio";
    }
}
