package com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiDataProvider;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiSearchParams;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import reactor.core.publisher.Mono;

import static lombok.AccessLevel.PRIVATE;
import static reactor.core.publisher.Mono.zip;

@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class RefactoredSwapiDataProvider implements SwapiDataProvider {
    ToSwapiDataModelCombiner toSwapiDataModelCombiner;
    PlanetCharacterPairsProvider planetCharacterPairsProvider;
    FilmsProvider filmsProvider;

    @Override
    public Mono<SwapiDataModel> getSwapiData(SwapiSearchParams swapiSearchParams) {
        return zip(
                planetCharacterPairsProvider.getPairs(swapiSearchParams.getCharacterPhrase(), swapiSearchParams.getPlanetName()),
                filmsProvider.getAllFilms()
        ).map(it -> toSwapiDataModelCombiner.combine(it.getT1(), it.getT2()));
    }
}
