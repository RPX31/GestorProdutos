package com.RODRIGO.RPX.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.RODRIGO.RPX.entity.Categoria;
import com.RODRIGO.RPX.services.CategoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {

        List<Categoria> categorias = categoriaService.listarTodos();

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Categoria>> buscarPorNome(
            @RequestParam String nome) {

        List<Categoria> categorias =
                categoriaService.buscarPorNome(nome);

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(
            @PathVariable Long id) {

        Categoria categoria = categoriaService.buscarPorId(id);

        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> salvar(
            @Valid @RequestBody Categoria categoria) {

        Categoria categoriaSalva =
                categoriaService.salvar(categoria);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoriaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Categoria categoria) {

        Categoria categoriaAtualizada =
                categoriaService.atualizar(id, categoria);

        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(
            @PathVariable Long id) {

        categoriaService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}