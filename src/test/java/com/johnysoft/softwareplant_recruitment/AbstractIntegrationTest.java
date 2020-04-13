package com.johnysoft.softwareplant_recruitment;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.SwapiDataProvider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public abstract class AbstractIntegrationTest {
    @MockBean
    protected SwapiDataProvider swapiDataProvider;
}
