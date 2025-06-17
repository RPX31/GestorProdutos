package com.RODRIGO.RPX.controller;
import com.RODRIGO.RPX.entity.Marca;
import com.RODRIGO.RPX.repository.MarcaRepository;
import com.RODRIGO.RPX.services.MarcaService;
import jakarta.validation.Valid;
import java.util.List;
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
    @Autowired
    private MarcaService marcaService;
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("marcas", marcaRepository.findAll());
        return "produto/produto";
    }
    @GetMapping("/buscar")
    public String buscarPorNome(@RequestParam("nome") String nome, Model model) {
        List<Marca> marcas = marcaRepository.findByNomeContainingIgnoreCase(nome);
        model.addAttribute("marcas", marcas);
        return "produto/produto";
    }
    @DeleteMapping("/deletar/{id}")
    public String deletarMarca(@PathVariable("id") Long id){
        marcaService.deletar(id);
        return "redirect:/inicio";
    }
    @PostMapping
    public String salvar(@Valid @ModelAttribute Marca marca, BindingResult result) {
        if (result.hasErrors()) {
        return "produto";
        }
        marcaRepository.save(marca);
        return "redirect:/inicio";
    }
}
