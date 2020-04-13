package com.johnysoft.softwareplant_recruitment.report.generate.swapi.external;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * make http calls to swapi service
 */
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
class SwapiRequestExecutor {
    private static final String CHARACTER_SEARCH_PARAMETER_NAME = "search";
    private static final String FORMAT = "format";
    private static final String JSON = "json";

    WebClient webClient;
    ObjectMapper objectMapper;

    <T> Mono<List<T>> executeRequest(String url, String queryParam, Class<T> classType) {
        final JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ModelResponse.class, classType);

        return webClient.get()
                .uri(it -> buildUri(it, url, queryParam))
                .accept(APPLICATION_JSON)
                .exchange()
                .flatMap(it -> it.body(BodyExtractors.toMono(new ParameterizedTypeReference<ModelResponse<T>>() {
                    @Override
                    public Type getType() {
                        return javaType;
                    }
                })))
                .map(ModelResponse::getResults);
    }

    private URI buildUri(UriBuilder uriBuilder, String url, String queryParam) {
        return uriBuilder.path(url)
                .queryParam(CHARACTER_SEARCH_PARAMETER_NAME, queryParam)
                .queryParam(FORMAT, JSON)
                .build();
    }

    @Data
    @FieldDefaults(level = PRIVATE)
    static class ModelResponse<T> {
        List<T> results;
    }
}
