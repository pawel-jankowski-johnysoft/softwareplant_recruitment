package com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SingleSwapiRecord;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored.FilmsProvider.Film;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored.PlanetProvider.Planet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SwapiDataCollectorTest {

    private static final long FIRST_FILM_ID = 1L;
    private static final String FIRST_FILM_TITLE = "A New Hope";
    private static final long COMMON_CHARACTER_ID = 4L;
    private static final String COMMON_CHARACTER_NAME = "Darth Vader";
    private static final long COMMON_PLANET_ID = 2l;
    private static final String COMMON_PLANET_NAME = "Alderaan";
    private static final String SECOND_FILM_TITLE = "Revenge of the Sith";
    private static final long SECOND_FILM_ID = 6L;

    @Test
    void combinePlanetCharacterPairsWithFilmsIntoSwapiDataModel() {
        //given
        final SwapiDataModel expectedResult = expectedResult();
        //and
        SwapiDataCollector swapiDataCollector = new SwapiDataCollector();

        //when
        final SwapiDataModel generatedDataModel = swapiDataCollector.collect(films(), planets(), characters());

        //then
        assertThat(expectedResult.getRecords()).containsExactlyInAnyOrderElementsOf(generatedDataModel.getRecords());
    }


    private List<MovieCharacterProvider.MovieCharacter> characters() {
        return List.of(
                new MovieCharacterProvider.MovieCharacter(COMMON_CHARACTER_ID, COMMON_CHARACTER_NAME),
                new MovieCharacterProvider.MovieCharacter(-100L, "ANY_CHARACTER_NAME_1"),
                new MovieCharacterProvider.MovieCharacter(-101L, "ANY_CHARACTER_NAME_2")
        );
    }

    private List<Planet> planets() {
        return List.of(
                new Planet(COMMON_PLANET_ID, COMMON_PLANET_NAME),
                new Planet(-100L, "ANY_PLANET"),
                new Planet(-101L, "ANY_PLANET_2")
        );
    }


    private List<Film> films() {
        return List.of(
                new Film(FIRST_FILM_ID, FIRST_FILM_TITLE, List.of(COMMON_CHARACTER_ID, -1L, -2L), List.of(COMMON_PLANET_ID)),
                new Film(SECOND_FILM_ID, SECOND_FILM_TITLE, List.of(COMMON_CHARACTER_ID, -1L, -2L), List.of(COMMON_PLANET_ID)),
                new Film(-1L, "ANY_MOVIE_TITLE", List.of(COMMON_CHARACTER_ID, -1L, -2L), List.of()),
                new Film(-2L, "ANY_MOVIE_TITLE_2", List.of(-1L, -2L), List.of(COMMON_PLANET_ID)),
                new Film(-3L, "ANY_MOVIE_TITLE_3", List.of(-1L, -2L), List.of(-1L, -2L))
        );
    }

    private SwapiDataModel expectedResult() {
        final SwapiDataModel model = new SwapiDataModel();
        model.addRecord(new SingleSwapiRecord(FIRST_FILM_ID, FIRST_FILM_TITLE, COMMON_CHARACTER_ID, COMMON_CHARACTER_NAME, COMMON_PLANET_ID, COMMON_PLANET_NAME));
        model.addRecord(new SingleSwapiRecord(SECOND_FILM_ID, SECOND_FILM_TITLE, COMMON_CHARACTER_ID, COMMON_CHARACTER_NAME, COMMON_PLANET_ID, COMMON_PLANET_NAME));
        return model;
    }
}
