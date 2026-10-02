package com.RODRIGO.RPX.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.RODRIGO.RPX.dto.request.CategoriaRequestDTO;
import com.RODRIGO.RPX.dto.response.CategoriaResponseDTO;
import com.RODRIGO.RPX.services.CategoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {

        List<CategoriaResponseDTO> categorias =
                categoriaService.listarTodos();

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CategoriaResponseDTO>> buscarPorNome(
            @RequestParam String nome) {

        List<CategoriaResponseDTO> categorias =
                categoriaService.buscarPorNome(nome);

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        CategoriaResponseDTO categoria =
                categoriaService.buscarPorId(id);

        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(
            @Valid @RequestBody CategoriaRequestDTO request) {

        CategoriaResponseDTO categoriaSalva =
                categoriaService.salvar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoriaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequestDTO request) {

        CategoriaResponseDTO categoriaAtualizada =
                categoriaService.atualizar(id, request);

        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(
            @PathVariable Long id) {

        categoriaService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}