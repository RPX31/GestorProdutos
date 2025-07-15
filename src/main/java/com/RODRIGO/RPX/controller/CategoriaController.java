package com.RODRIGO.RPX.controller;
import com.RODRIGO.RPX.entity.Categoria;
import com.RODRIGO.RPX.entity.Marca;
import com.RODRIGO.RPX.entity.Produto;
import com.RODRIGO.RPX.repository.CategoriaRepository;
import com.RODRIGO.RPX.repository.MarcaRepository;
import com.RODRIGO.RPX.repository.ProdutoRepository;
import com.RODRIGO.RPX.services.CategoriaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@AllArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {
    
    private ProdutoRepository produtoRepository;
    private MarcaRepository marcaRepository;
    private CategoriaRepository categoriaRepository;
    private CategoriaService categoriaService;

    @GetMapping
    public String listaCategorias(Model model) {   
        model.addAttribute("produtos", produtoRepository.findAll());
            model.addAttribute("categorias", categoriaRepository.findAll());
            model.addAttribute("marcas", marcaRepository.findAll());
            model.addAttribute("produto", new Produto()); 
            model.addAttribute("categoria", new Categoria());
            model.addAttribute("marca", new Marca());
        return "produto/produto";}
    @GetMapping("/categorias/deletar/{id}")
    public String deletarCategoria(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.deletar(id);
            redirectAttributes.addFlashAttribute("mensagemCategoria", "Categoria deletada com sucesso.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erroCategoria", e.getMessage());
        }
        return "redirect:/Gerenciador/de/produtos";
    }
    @GetMapping("/buscar")
    public String buscarPorNome(@RequestParam("nome") String nome, Model model){
        List<Categoria> categoria = categoriaRepository.findByNomeContainingIgnoreCase(nome);
        model.addAttribute("categoria", categoria);
        return "produto/produto";}
    @PostMapping("/salvar")
   public String salvar(@Valid @ModelAttribute Categoria categoria, RedirectAttributes redirectAttributes) {
    boolean existe = categoriaRepository.existsByNomeIgnoreCase(categoria.getNome());
    
    if (existe) {
        redirectAttributes.addFlashAttribute("erroCategoria", "Já existe uma categoria com esse nome.");
        return "redirect:/Gerenciador/de/produtos";
    }
    categoriaRepository.save(categoria);
    return "redirect:/Gerenciador/de/produtos";
}
}