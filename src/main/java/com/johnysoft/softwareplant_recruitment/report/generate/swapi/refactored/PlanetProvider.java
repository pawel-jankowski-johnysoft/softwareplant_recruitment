package com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored;

import lombok.Value;
import reactor.core.publisher.Mono;

import java.util.List;

class PlanetProvider {
    Mono<List<Planet>> planets(String planetName) {
        return Mono.empty();
    }

    @Value
    static class Planet {
        long planetId;
        String planetName;
    }
}
