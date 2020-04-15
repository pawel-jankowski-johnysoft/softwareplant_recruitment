package com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored;

import reactor.core.publisher.Mono;

import java.util.List;

class PlanetCharacterPairsProvider {
    Mono<List<CharacterPlanetPair>> getPairs(String characterPhrase, String planetName) {
        return Mono.empty();
    }

    static class CharacterPlanetPair {
    }
}
