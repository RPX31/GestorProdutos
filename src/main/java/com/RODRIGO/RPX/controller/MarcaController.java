package com.RODRIGO.RPX.controller;

import com.RODRIGO.RPX.entity.Marca;
import com.RODRIGO.RPX.repository.MarcaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("marcas", marcaRepository.findAll());
        return "marca/lista";
    }

    @GetMapping("/nova")
    public String novaMarca(Model model) {
        model.addAttribute("marca", new Marca());
        return "marca/formulario";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute Marca marca, BindingResult result) {
        if (result.hasErrors()) {
            return "marca/formulario";
        }
        marcaRepository.save(marca);
        return "redirect:/marcas";
    }
}
