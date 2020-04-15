package com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored.FilmsProvider.Film;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored.PlanetCharacterPairsProvider.CharacterPlanetPair;

import java.util.List;

class ToSwapiDataModelCombiner {
    SwapiDataModel combine(List<CharacterPlanetPair> characterPlanetPairs, List<Film> films) {
        return new SwapiDataModel();
    }
}
