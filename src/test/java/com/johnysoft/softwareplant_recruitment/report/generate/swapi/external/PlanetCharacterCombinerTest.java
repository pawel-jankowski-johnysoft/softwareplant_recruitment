package com.johnysoft.softwareplant_recruitment.report.generate.swapi.external;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.CharactersProvider.CharacterModel;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.PlanetCharacterCombiner.PlanetCharacterCombination;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.PlanetsProvider.PlanetModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class PlanetCharacterCombinerTest {

    private static final long FIRST_PLANET_ID = 28;
    private static final String GIVEN_FIRST_PLANET_URL = "https://swapi.co/api/planets/" + FIRST_PLANET_ID + "/";
    private static final long FIRST_MOVIE_ID = 16;
    private static final String GIVEN_FIRST_COMMON_MOVIE = "https://swapi.co/api/films/" + FIRST_MOVIE_ID + "/";
    private static final long SECOND_PLANET_ID = 26;
    private static final String GIVEN_SECOND_PLANET_URL = "https://swapi.co/api/planets/" + SECOND_PLANET_ID + "/";
    private static final long FIRST_CHARACTER_ID = 5;
    private static final String GIVEN_FIRST_PEOPLE_URL = "https://swapi.co/api/people/" + FIRST_CHARACTER_ID + "/";
    private static final long SECOND_MOVIE_ID = 15;
    private static final String GIVEN_SECOND_COMMON_MOVIE = "https://swapi.co/api/films/" + SECOND_MOVIE_ID + "/";
    private static final long SECOND_CHARACTER_ID = 6;
    private static final String GIVEN_SECOND_PEOPLE_URL = "https://swapi.co/api/people/" + SECOND_CHARACTER_ID + "/";
    private static final String DOESNT_MATTER_CHARACTER_NAME_5 = "CharacterName 5";
    private static final String DOESNT_MATTER_MOVIE_5458 = "https://swapi.co/api/films/5458/";
    private static final String DOESNT_MATTER_MOVIE_123 = "https://swapi.co/api/films/123/";
    private static final String DOESNT_MATTER_PEOPLE_7 = "https://swapi.co/api/people/7/";
    private static final String DOESNT_MATTER_MOVIE_1239 = "https://swapi.co/api/films/1239/";
    private static final String DOESNT_MATTER_CHARACTER_NAME_7 = "CharacterName 7";
    private static final String DOESNT_MATTER_CHARACTER_NAME_6 = "CharacterName 6";
    private static final String DOESNT_MATTER_EXAMPLE_PLANET_NAME_6 = "examplePlanetName 6";
    private static final String DOESNT_MATTER_EXAMPLE_PLANET_NAME_8 = "examplePlanetName 8";

    @Test
    void extractingIdTest() {
        //given
        final var planetName = "planetName";
        final var givenPlanetId = 5L;
        final var planetUrl = "https://swapi.co/api/planets/" + givenPlanetId + "/";

        //and
        final var givenCharacterId = 36L;
        final var characterName = "character";
        final var characterUrl = "https://swapi.co/api/people/" + givenCharacterId + "/";

        final PlanetCharacterCombination planetCharacterCombination = new PlanetCharacterCombination(planetName, planetUrl,
                characterName, characterUrl, GIVEN_FIRST_COMMON_MOVIE);

        //expect
        assertThat(planetCharacterCombination.getCharacterId()).isEqualTo(givenCharacterId);
        assertThat(planetCharacterCombination.getPlanetId()).isEqualTo(givenPlanetId);
        assertThat(planetCharacterCombination.getMovieId()).isEqualTo(FIRST_MOVIE_ID);
    }

    @Test
    public void combineAllCharactersWithMovies() {
        //given
        List<PlanetModel> planets = planets();
        List<CharacterModel> characters = characters();

        final List<PlanetCharacterCombination> planetCharacterCombinations = new PlanetCharacterCombiner(planets, characters).combine();
        assertThat(planetCharacterCombinations).hasSize(3);
        assertThat(planetCharacterCombinations)
                .extracting(it -> tuple(it.getPlanetId(), it.getCharacterId(), it.getMovieId()))
                .containsExactlyInAnyOrder(
                        tuple(FIRST_PLANET_ID, FIRST_CHARACTER_ID, FIRST_MOVIE_ID),
                        tuple(SECOND_PLANET_ID, FIRST_CHARACTER_ID, FIRST_MOVIE_ID),
                        tuple(FIRST_PLANET_ID, SECOND_CHARACTER_ID, SECOND_MOVIE_ID)
                );
    }

    private List<PlanetModel> planets() {
        return of(
                planetModel(of(
                        GIVEN_SECOND_COMMON_MOVIE,
                        GIVEN_FIRST_COMMON_MOVIE
                ), DOESNT_MATTER_EXAMPLE_PLANET_NAME_8, GIVEN_FIRST_PLANET_URL),
                planetModel(of(
                        GIVEN_FIRST_COMMON_MOVIE
                ), DOESNT_MATTER_EXAMPLE_PLANET_NAME_6, GIVEN_SECOND_PLANET_URL)
        );
    }

    private PlanetModel planetModel(List<String> films, String planetName, String url) {
        final PlanetModel planetModel = new PlanetModel();
        planetModel.setFilms(films);
        planetModel.setName(planetName);
        planetModel.setUrl(url);
        return planetModel;
    }

    private List<CharacterModel> characters() {
        return of(
                characterModel(of(
                        GIVEN_FIRST_COMMON_MOVIE
                ), DOESNT_MATTER_CHARACTER_NAME_5, GIVEN_FIRST_PEOPLE_URL),
                characterModel(of(
                        DOESNT_MATTER_MOVIE_5458,
                        DOESNT_MATTER_MOVIE_123
                ), DOESNT_MATTER_CHARACTER_NAME_7, DOESNT_MATTER_PEOPLE_7),
                characterModel(of(
                        GIVEN_SECOND_COMMON_MOVIE,
                        DOESNT_MATTER_MOVIE_1239
                ), DOESNT_MATTER_CHARACTER_NAME_6, GIVEN_SECOND_PEOPLE_URL)
        );
    }

    private CharacterModel characterModel(List<String> films, String characterName, String url) {
        CharacterModel characterModel = new CharacterModel();
        characterModel.setFilms(films);
        characterModel.setName(characterName);
        characterModel.setUrl(url);
        return characterModel;
    }
}
