package com.RODRIGO.RPX.infrastructure.restclient;

import com.RODRIGO.RPX.dto.request.ProdutoRequestDTO;
import com.RODRIGO.RPX.dto.response.ProdutoResponseDTO;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Component
public class ProdutoRestClient {

    private final RestClient restClient;

    public ProdutoRestClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }

    public List<ProdutoResponseDTO> listarTodos() {

        ProdutoResponseDTO[] produtos = restClient
                .get()
                .uri("/produtos")
                .retrieve()
                .body(ProdutoResponseDTO[].class);

        return produtos != null
                ? Arrays.asList(produtos)
                : List.of();
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO request) {

        return restClient
                .post()
                .uri("/produtos")
                .body(request)
                .retrieve()
                .body(ProdutoResponseDTO.class);
    }

    public ProdutoResponseDTO atualizar(
            Long id,
            ProdutoRequestDTO request) {

        return restClient
                .put()
                .uri("/produtos/{id}", id)
                .body(request)
                .retrieve()
                .body(ProdutoResponseDTO.class);
    }

    public void deletar(Long id) {

        restClient
                .delete()
                .uri("/produtos/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    public boolean existeProdutoPorCategoria(Long categoriaId) {

        return restClient
                .get()
                .uri(
                        "/produtos/categoria/{categoriaId}/existe",
                        categoriaId
                )
                .retrieve()
                .body(Boolean.class);
    }

    public boolean existeProdutoPorMarca(Long marcaId) {

        return restClient
                .get()
                .uri(
                        "/produtos/marca/{marcaId}/existe",
                        marcaId
                )
                .retrieve()
                .body(Boolean.class);
    }
}