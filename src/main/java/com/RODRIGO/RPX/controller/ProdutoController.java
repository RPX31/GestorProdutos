package com.RODRIGO.RPX.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.RODRIGO.RPX.entity.Categoria;
import com.RODRIGO.RPX.entity.Produto;
import com.RODRIGO.RPX.repository.CategoriaRepository;
import com.RODRIGO.RPX.repository.MarcaRepository;
import com.RODRIGO.RPX.repository.ProdutoRepository;
import com.RODRIGO.RPX.services.ProdutoService;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/")
public class ProdutoController {
    @Autowired
    private  ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private ProdutoService produtoService;
    @GetMapping("/inicio")
    public String listaProdutos(Model model) {
            model.addAttribute("produtos", produtoRepository.findAll());
            model.addAttribute("produto", new Produto()); 
            model.addAttribute("categorias", categoriaRepository.findAll());
            model.addAttribute("marcas", marcaRepository.findAll());
            model.addAttribute("categoria", new Categoria());  // <<< ESSA LINHA É ESSENCIAL

            return "produto/produto";
     }
     @GetMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable("id") Long id) {
        produtoService.deletar(id);
        return "redirect:/inicio";
    }
    @PostMapping("/inicios")
    public String salvarProduto(@Valid @ModelAttribute Produto produto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaRepository.findAll());
            model.addAttribute("marcas", marcaRepository.findAll());
            return "inicio";
        }
        produtoRepository.save(produto);
        return "redirect:/inicio";
    }

}
