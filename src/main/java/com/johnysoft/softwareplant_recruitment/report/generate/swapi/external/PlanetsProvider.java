package com.johnysoft.softwareplant_recruitment.report.generate.swapi.external;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiRequestExecutor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import reactor.core.publisher.Mono;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
class PlanetsProvider {

    private static final String PLANETS_URL = "/planets/";

    SwapiRequestExecutor requestExecutor;

    Mono<List<PlanetModel>> planetsMatched(String planetNamePart) {
        return requestExecutor.executeRequest(PLANETS_URL, planetNamePart, PlanetModel.class);
    }

    @Data
    @FieldDefaults(level = PRIVATE)
    static class PlanetModel {
        List<String> films;
        String name;
        String url;
    }
}
