package com.RODRIGO.RPX.controller;
import com.RODRIGO.RPX.entity.Categoria;
import com.RODRIGO.RPX.entity.Marca;
import com.RODRIGO.RPX.entity.Produto;
import com.RODRIGO.RPX.repository.CategoriaRepository;
import com.RODRIGO.RPX.repository.MarcaRepository;
import com.RODRIGO.RPX.repository.ProdutoRepository;
import com.RODRIGO.RPX.services.MarcaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@AllArgsConstructor
@RequestMapping("/marcas")
public class MarcaController {

    private CategoriaRepository categoriaRepository;
    private ProdutoRepository produtoRepository;
    private MarcaRepository marcaRepository;
    private MarcaService marcaService;

    @GetMapping
    public String listar(Model model) {
            model.addAttribute("produtos", produtoRepository.findAll());
            model.addAttribute("categorias", categoriaRepository.findAll());
            model.addAttribute("marcas", marcaRepository.findAll());
            model.addAttribute("produto", new Produto()); 
            model.addAttribute("categoria", new Categoria());
            model.addAttribute("marca", new Marca()); 
        return "produto/produto";
    }
    @GetMapping("/buscar")
    public String buscarPorNome(@RequestParam("nome") String nome, Model model) {
        List<Marca> marcas = marcaRepository.findByNomeContainingIgnoreCase(nome);
        model.addAttribute("marcas", marcas);
        return "produto/produto";
    }
    @GetMapping("/marcas/deletar/{id}")
    public String deletarMarca(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try {
            marcaService.deletar(id);
            redirectAttributes.addFlashAttribute("mensagemMarca", "Marca deletada com sucesso.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erroMarca", e.getMessage());
        }
        return "redirect:/Gerenciador/de/produtos";
    }
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Marca marca , RedirectAttributes redirectAttributes)  {

    boolean existe = marcaRepository.existsByNomeIgnoreCase(marca.getNome());
    
        if (existe) {
            redirectAttributes.addFlashAttribute("erroMarca", "Já existe uma Marca com esse nome.");
            return "redirect:/Gerenciador/de/produtos";
        }

            marcaRepository.save(marca);
            return "redirect:/Gerenciador/de/produtos";
        }
}
