package com.RODRIGO.RPX.infrastructure.restclient;

import java.util.Arrays;
import java.util.List;

import com.RODRIGO.RPX.Dto.ProdutoDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;



@Component
public class ProdutoRestClient {

    private final RestClient restClient;

    public ProdutoRestClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }

    public List<ProdutoDTO> listarTodos() {

        ProdutoDTO[] produtos = restClient
                .get()
                .uri("/produtos")
                .retrieve()
                .body(ProdutoDTO[].class);

        return produtos != null
                ? Arrays.asList(produtos)
                : List.of();
    }

    public ProdutoDTO salvar(ProdutoDTO produto) {

        return restClient
                .post()
                .uri("/produtos")
                .body(produto)
                .retrieve()
                .body(ProdutoDTO.class);
    }

    public ProdutoDTO atualizar(Long id, ProdutoDTO produto) {

        return restClient
                .put()
                .uri("/produtos/{id}", id)
                .body(produto)
                .retrieve()
                .body(ProdutoDTO.class);
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
                .uri("/produtos/categoria/{categoriaId}/existe", categoriaId)
                .retrieve()
                .body(Boolean.class);
    }
    public boolean existeProdutoPorMarca(Long marcaId) {

        return restClient
                .get()
                .uri("/produtos/marca/{marcaId}/existe", marcaId)
                .retrieve()
                .body(Boolean.class);
    }
}