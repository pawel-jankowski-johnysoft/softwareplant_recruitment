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
    SwapiDataCollector swapiDataCollector;
    FilmsProvider filmsProvider;
    PlanetProvider planetProvider;
    MovieCharacterProvider movieCharacterProvider;

    @Override
    public Mono<SwapiDataModel> getSwapiData(SwapiSearchParams swapiSearchParams) {
        return zip(
                filmsProvider.getAllFilms(),
                planetProvider.planets(swapiSearchParams.getPlanetName()),
                movieCharacterProvider.characters(swapiSearchParams.getCharacterPhrase())
        ).map(it -> swapiDataCollector.collect(it.getT1(), it.getT2(), it.getT3()));
    }

}
