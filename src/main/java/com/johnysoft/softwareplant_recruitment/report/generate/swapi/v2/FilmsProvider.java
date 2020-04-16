package com.johnysoft.softwareplant_recruitment.report.generate.swapi.v2;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiRequestExecutor;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.v2.PlanetProvider.Planet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@Slf4j
class FilmsProvider {
    private static final String MOVIES_URL = "/films/";

    SwapiRequestExecutor requestExecutor;
    IdFromURLExtractor idFromURLExtractor;

    Mono<List<Film>> getAllFilms() {
        return requestExecutor.executeRequest(MOVIES_URL, null, FilmResponse.class)
                .flatMapIterable(Function.identity())
                .map(this::toFilm)
                .collectList();
    }

    private Film toFilm(FilmResponse filmResponse) {
        return new Film(idFromURLExtractor.extractId(filmResponse.url), filmResponse.title,
                idFromURLExtractor.extractIds(filmResponse.characters),
                idFromURLExtractor.extractIds(filmResponse.planets));
    }

    @RequiredArgsConstructor(access = PACKAGE)
    @FieldDefaults(level = PRIVATE, makeFinal = true)
    @Getter
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

    @Setter
    @FieldDefaults(level = PRIVATE)
    static class FilmResponse {
        String url;
        String title;
        List<String> characters;
        List<String> planets;
    }

}
