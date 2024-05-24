package com.jesusfc.springboot3java17.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

/**
 * Author Jesús Fdez. Caraballo
 * jesus.fdez.caraballo@gmail.com
 * Created on may - 2024
 */
@Service
public class WebClientServiceImpl implements WebClientService {
    @Override
    public List<String> getImagesUrl() {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8085/admin/test")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<List<Object>> single = webClient.get()
                .uri("/getOldImages")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .retrieve()
                .bodyToFlux(Object.class).collectList();

        List<Object> objects = Objects.requireNonNull(single.block());
        return objects.stream().map(Object::toString).toList();

    }
}
