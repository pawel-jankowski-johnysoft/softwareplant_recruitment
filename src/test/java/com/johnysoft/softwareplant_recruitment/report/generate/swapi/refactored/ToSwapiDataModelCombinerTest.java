package com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SingleSwapiRecord;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored.FilmsProvider.Film;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored.PlanetCharacterPairsProvider.CharacterPlanetPair;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class ToSwapiDataModelCombinerTest {

    private static final long FIRST_FILM_ID = 1L;
    private static final String FIRST_FILM_NAME = "A New Hope";
    private static final long COMMON_CHARACTER_ID = 4L;
    private static final String COMMON_CHARACTER_NAME = "Darth Vader";
    private static final long COMMON_PLANET_ID = 2l;
    private static final String COMMON_PLANET_NAME = "Alderaan";
    private static final String SECOND_FILM_NAME = "Revenge of the Sith";
    private static final long SECOND_FILM_ID = 6L;
    private ToSwapiDataModelCombiner toSwapiDataModelCombiner = new ToSwapiDataModelCombiner();

    @Test
    void combinePlanetCharacterPairsWithFilmsIntoSwapiDataModel() {
        //given
        final SwapiDataModel expectedResult = expectedResult();

        //when
        final SwapiDataModel generatedDataModel = toSwapiDataModelCombiner.combine(characterPlanetPairs(), films());

        //then
        Assertions.assertThat(expectedResult.getRecords()).containsExactlyInAnyOrderElementsOf(generatedDataModel.getRecords());
    }


    private List<CharacterPlanetPair> characterPlanetPairs() {
        return Collections.emptyList();
    }

    private List<Film> films() {
        return Collections.emptyList();
    }

    private SwapiDataModel expectedResult() {
        final SwapiDataModel model = new SwapiDataModel();
        model.addRecord(new SingleSwapiRecord(FIRST_FILM_ID, FIRST_FILM_NAME, COMMON_CHARACTER_ID, COMMON_CHARACTER_NAME, COMMON_PLANET_ID, COMMON_PLANET_NAME));
        model.addRecord(new SingleSwapiRecord(SECOND_FILM_ID, SECOND_FILM_NAME, COMMON_CHARACTER_ID, COMMON_PLANET_NAME, COMMON_PLANET_ID, COMMON_PLANET_NAME));
        return model;
    }
}
