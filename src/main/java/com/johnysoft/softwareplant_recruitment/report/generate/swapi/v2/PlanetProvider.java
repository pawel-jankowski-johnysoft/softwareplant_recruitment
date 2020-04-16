package com.johnysoft.softwareplant_recruitment.report.generate.swapi.v2;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiRequestExecutor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
class PlanetProvider {
    private static final String PLANETS_URL = "/planets/";
    SwapiRequestExecutor requestExecutor;
    IdFromURLExtractor idFromURLExtractor;

    Mono<List<Planet>> planets(String planetName) {
        return requestExecutor.executeRequest(PLANETS_URL, planetName, PlanetResponse.class)
                .flatMapIterable(Function.identity())
                .map(this::toPlanet)
                .collectList();
    }

    private Planet toPlanet(PlanetResponse planetResponse) {
        return new Planet(idFromURLExtractor.extractId(planetResponse.url), planetResponse.name);
    }

    @Setter
    @FieldDefaults(level = PRIVATE)
    static class PlanetResponse {
        String url;
        String name;
    }

    @Value
    static class Planet {
        long planetId;
        String planetName;
    }
}
