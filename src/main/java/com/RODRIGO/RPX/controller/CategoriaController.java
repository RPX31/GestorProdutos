package com.RODRIGO.RPX.controller;
import com.RODRIGO.RPX.entity.Categoria;
import com.RODRIGO.RPX.repository.CategoriaRepository;
import com.RODRIGO.RPX.services.CategoriaService;
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
    @Autowired
    private CategoriaService categoriaService;
    @GetMapping
    public String listaCategorias(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("categoria", new Categoria());
        return "produto/produto";
    }
    @DeleteMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable("id") Long id){
        categoriaService.deletar(id);
        return "redirect:/inicio";
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
