package com.johnysoft.softwareplant_recruitment.report.generate.swapi.refactored;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiDataProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnProperty(prefix = "swapi", value = "base_url")
class RefactoredSwapiDataConfiguration {

    private static final String SWAPI_BASE_URL = "${swapi.base_url}";

    @Primary
    @Bean
    public SwapiDataProvider swapiDataProvider(@Value(SWAPI_BASE_URL) String swapiBaseUrl) {
        return new RefactoredSwapiDataProvider(null, null, null);
    }
}
