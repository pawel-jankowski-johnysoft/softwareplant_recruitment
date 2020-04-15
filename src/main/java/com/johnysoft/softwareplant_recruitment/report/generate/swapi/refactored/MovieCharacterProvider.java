package com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored;

import lombok.Value;
import reactor.core.publisher.Mono;

import java.util.List;

class MovieCharacterProvider {
    Mono<List<MovieCharacter>> characters(String characterPhrase) {
        return Mono.empty();
    }

    @Value
    static class MovieCharacter {
        long characterId;
        String name;
    }
}
