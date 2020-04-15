package com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiDataProvider;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiSearchParams;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import reactor.core.publisher.Mono;

class RefactoredSwapiDataProvider implements SwapiDataProvider {
    @Override
    public Mono<SwapiDataModel> getSwapiData(SwapiSearchParams swapiSearchParams) {
        return Mono.empty();
    }
}
