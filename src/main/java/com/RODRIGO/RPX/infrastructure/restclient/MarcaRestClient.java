package com.RODRIGO.RPX.infrastructure.restclient;

import com.RODRIGO.RPX.dto.MarcaDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Component
public class MarcaRestClient {

    private final RestClient restClient;

    public MarcaRestClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8083")
                .build();
    }

    public List<MarcaDTO> listarTodos() {

        MarcaDTO[] marcas = restClient
                .get()
                .uri("/marcas")
                .retrieve()
                .body(MarcaDTO[].class);

        return marcas != null
                ? Arrays.asList(marcas)
                : List.of();
    }

    public List<MarcaDTO> buscarPorNome(String nome) {

        MarcaDTO[] marcas = restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/marcas/buscar")
                        .queryParam("nome", nome)
                        .build())
                .retrieve()
                .body(MarcaDTO[].class);

        return marcas != null
                ? Arrays.asList(marcas)
                : List.of();
    }

    public MarcaDTO buscarPorId(Long id) {

        return restClient
                .get()
                .uri("/marcas/{id}", id)
                .retrieve()
                .body(MarcaDTO.class);
    }

    public MarcaDTO salvar(MarcaDTO marca) {

        return restClient
                .post()
                .uri("/marcas")
                .body(marca)
                .retrieve()
                .body(MarcaDTO.class);
    }

    public MarcaDTO atualizar(Long id, MarcaDTO marca) {

        return restClient
                .put()
                .uri("/marcas/{id}", id)
                .body(marca)
                .retrieve()
                .body(MarcaDTO.class);
    }

    public void deletar(Long id) {

        restClient
                .delete()
                .uri("/marcas/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}