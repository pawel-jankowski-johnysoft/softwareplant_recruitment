package com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored;

import reactor.core.publisher.Mono;

import java.util.List;

class FilmsProvider {
    Mono<List<Film>> getAllFilms() {
        return Mono.empty();
    }

    static class Film {
    }
}
