package com.johnysoft.softwareplant_recruitment.report.generate.swapi;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import reactor.core.publisher.Mono;

public interface SwapiDataProvider {
    Mono<SwapiDataModel> getSwapiData(SwapiSearchParams swapiSearchParams);
}
