package com.johnysoft.softwareplant_recruitment.report.generate.swapi.external;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiSearchParams;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import org.springframework.beans.factory.annotation.Autowired;

//@SpringBootTest
class SwapiDataProviderTest {
    @Autowired
    private SwapiDataProvider swapiDataProvider;

    //    @Test
    public void realSwapiTestGetData() {
        final SwapiDataModel reportData = swapiDataProvider.getSwapiData(SwapiSearchParams.builder()
                .planetName("raan")
                .characterPhrase("Skywalker")
                .build()).block();
        System.out.println(reportData);
    }
}
