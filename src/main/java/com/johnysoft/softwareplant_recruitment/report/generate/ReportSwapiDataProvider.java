package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiSearchParams;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.SwapiDataProvider;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
class ReportSwapiDataProvider {
    private static final String CANT_GET_DATA_FROM_SWAPI_ERROR_MESAGE = "can't get data from swapi service, planet name: %s, character name: %s  ";
    SwapiDataProvider swapiDataProvider;

    Mono<SwapiDataModel> getSwapiData(String planetName, String charcterName) {
        try {
            return swapiDataProvider.getSwapiData(SwapiSearchParams.builder()
                    .characterPhrase(charcterName)
                    .planetName(planetName).build());
        } catch (Exception e) {
            log.error(format(CANT_GET_DATA_FROM_SWAPI_ERROR_MESAGE, planetName, charcterName), e);
            throw new SwapiDataProvidingException(e);
        }
    }
}
