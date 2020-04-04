package com.johnysoft.softwareplant_recruitment.report.generate.swapi.external;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.CharactersProvider.CharacterModel;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.PlanetsProvider.PlanetModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toUnmodifiableList;
import static lombok.AccessLevel.PRIVATE;

/**
 * Prepare pairs where given planet and given character exists in same movie
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
class PlanetCharacterCombiner {
    List<PlanetModel> planets;
    List<CharacterModel> characters;

    List<PlanetCharacterCombination> combine() {
        return planets.stream()
                .flatMap(planet -> characters.stream().map(character -> new MatchedMovieCollector(planet, character)))
                .flatMap(MatchedMovieCollector::collect)
                .collect(toUnmodifiableList());
    }

    @RequiredArgsConstructor
    @FieldDefaults(level = PRIVATE, makeFinal = true)
    static class PlanetCharacterCombination {
        @Getter
        String planetName;
        String planetUrl;

        @Getter
        String characterName;
        String characterUrl;

        @Getter
        String movieUrl;

        long getPlanetId() {
            return extractId(planetUrl);
        }

        long getCharacterId() {
            return extractId(characterUrl);
        }

        long getMovieId() {
            return extractId(movieUrl);
        }

        private long extractId(String url) {
            final String[] urlParts = url.split("/");
            return parseLong(urlParts[urlParts.length - 1]);
        }
    }


    /**
     * Helper class used to used to generate a combination of planet, character and movies
     */
    @FieldDefaults(makeFinal = true, level = PRIVATE)
    private class MatchedMovieCollector {
        PlanetModel planetModel;
        CharacterModel characterModel;
        List<String> planetCharacterMovies;

        private MatchedMovieCollector(PlanetModel planetModel, CharacterModel characterModel) {
            this.planetModel = planetModel;
            this.characterModel = characterModel;
            this.planetCharacterMovies = initPlanetCharacterMovies(planetModel, characterModel);
        }

        private ArrayList<String> initPlanetCharacterMovies(PlanetModel planetModel, CharacterModel characterModel) {
            final ArrayList<String> planetCharacterMovies = new ArrayList<>(planetModel.getFilms());
            planetCharacterMovies.retainAll(characterModel.getFilms());
            return planetCharacterMovies;
        }

        private Stream<PlanetCharacterCombination> collect() {
            return this.planetCharacterMovies.stream()
                    .map(it -> new PlanetCharacterCombination(planetModel.getName(), planetModel.getUrl(),
                            characterModel.getName(), characterModel.getUrl(), it));
        }
    }
}
