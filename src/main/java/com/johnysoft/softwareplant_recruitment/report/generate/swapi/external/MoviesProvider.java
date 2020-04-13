package com.johnysoft.softwareplant_recruitment.report.generate.swapi.external;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import reactor.core.publisher.Mono;

import java.util.List;

import static java.util.function.Function.identity;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class MoviesProvider {
    private static final String MOVIES_URL = "/films/";

    SwapiRequestExecutor requestExecutor;

    Mono<List<MovieModel>> moviesForIds(List<String> moviesUrls) {
        return requestExecutor.executeRequest(MOVIES_URL, null, MovieModel.class)
                .flatMapIterable(identity())
                .filter(movieModel -> moviesUrls.contains(movieModel.getUrl()))
                .collectList();
    }

    @Data
    @FieldDefaults(level = PRIVATE)
    static class MovieModel {
        String title;
        String url;
    }
}
