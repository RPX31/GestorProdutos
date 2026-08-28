package com.RODRIGO.RPX.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.RODRIGO.RPX.entity.Marca;
import com.RODRIGO.RPX.services.MarcaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<Marca>> listar() {

        List<Marca> marcas = marcaService.listarTodos();

        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Marca>> buscarPorNome(
            @RequestParam String nome) {

        List<Marca> marcas = marcaService.buscarPorNome(nome);

        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarPorId(
            @PathVariable Long id) {

        Marca marca = marcaService.buscarPorId(id);

        return ResponseEntity.ok(marca);
    }

    @PostMapping
    public ResponseEntity<Marca> salvar(
            @Valid @RequestBody Marca marca) {

        Marca marcaSalva = marcaService.salvar(marca);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(marcaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Marca marca) {

        Marca marcaAtualizada =
                marcaService.atualizar(id, marca);

        return ResponseEntity.ok(marcaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMarca(
            @PathVariable Long id) {

        marcaService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}