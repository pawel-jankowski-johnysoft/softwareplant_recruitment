package com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored.PlanetProvider.Planet;
import lombok.Value;
import reactor.core.publisher.Mono;

import java.util.List;

class FilmsProvider {
    Mono<List<Film>> getAllFilms() {
        return Mono.empty();
    }

    @Value
    static class Film {
        long filmId;
        String filmTitle;
        List<Long> characters;
        List<Long> planets;

        boolean characterPlays(MovieCharacterProvider.MovieCharacter movieCharacter) {
            return characters.contains(movieCharacter.getCharacterId());
        }

        boolean actionIsOnPlanet(Planet planet) {
            return planets.contains(planet.getPlanetId());
        }
    }
}
