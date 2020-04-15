package com.johnysoft.softwareplant_recruitment.report.generate.swapi;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SingleSwapiRecord;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "swapi.base_url=http://192.168.99.100:8081/api")
class SwapiDataProviderTest {

    private static final String GIVEN_PLANET_NAME = "Alderaan";
    private static final String GIVEN_CHARACTER_PHRASE = "Vader";

    @Autowired
    private SwapiDataProvider swapiDataProvider;

    @Test
    public void swapiRegressionTest() {
        //given
        final SwapiDataModel expectedResult = givenExpectedResult();

        //when
        final SwapiDataModel swapiDataModel = swapiDataProvider.getSwapiData(SwapiSearchParams.builder()
                .planetName(GIVEN_PLANET_NAME)
                .characterPhrase(GIVEN_CHARACTER_PHRASE)
                .build()).block();

        //then
        Assertions.assertThat(expectedResult.getRecords())
                .containsExactlyInAnyOrderElementsOf(swapiDataModel.getRecords());
    }

    private SwapiDataModel givenExpectedResult() {
        final SwapiDataModel model = new SwapiDataModel();
        model.addRecord(new SingleSwapiRecord(1L, "A New Hope", 4L, "Darth Vader", 2l, GIVEN_PLANET_NAME));
        model.addRecord(new SingleSwapiRecord(6L, "Revenge of the Sith", 4L, "Darth Vader", 2l, GIVEN_PLANET_NAME));
        return model;
    }
}

