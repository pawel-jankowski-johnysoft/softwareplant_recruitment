package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiSearchParams;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.SwapiDataProvider;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SingleSwapiRecord;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.defer;
import static reactor.core.publisher.Mono.just;

class SwapiDataMockResponse {

    private final SwapiDataProvider swapiDataProvider;

    SwapiDataMockResponse(SwapiDataProvider swapiDataProvider) {
        this.swapiDataProvider = swapiDataProvider;
    }

    static SingleSwapiRecord singleSwapiRecord(long filmId, long characterId, long planetId) {
        return new SingleSwapiRecord(filmId, "name_of_" + filmId,
                characterId, "name_of_" + characterId,
                planetId, "name_of_" + planetId);
    }

    void mockResponse(String characterPhrase, String planetName, List<SingleSwapiRecord> swapiRecords) {
        when(swapiDataProvider.getSwapiData(eq(SwapiSearchParams.builder().characterPhrase(characterPhrase)
                .planetName(planetName).build())))
                .thenReturn(defer(() -> just(swapiDataModel(swapiRecords))));
    }

    private SwapiDataModel swapiDataModel(List<SingleSwapiRecord> swapiRecords) {
        final SwapiDataModel swapiDataModel = new SwapiDataModel();
        swapiRecords.forEach(swapiDataModel::addRecord);
        return swapiDataModel;
    }
}
