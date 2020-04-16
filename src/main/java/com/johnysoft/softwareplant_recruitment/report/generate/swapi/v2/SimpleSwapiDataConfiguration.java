package com.johnysoft.softwareplant_recruitment.report.generate.swapi.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiDataProvider;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiRequestExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConditionalOnProperty(prefix = "swapi", value = "base_url")
class SimpleSwapiDataConfiguration {

    private static final String SWAPI_BASE_URL = "${swapi.base_url}";

    @Bean
    public SwapiDataProvider swapiDataProvider(@Value(SWAPI_BASE_URL) String swapiBaseUrl, ObjectMapper objectMapper) {
        final var swapiRequestExecutor = new SwapiRequestExecutor(swapiWebClient(swapiBaseUrl), objectMapper);
        final IdFromURLExtractor idFromURLExtractor = new IdFromURLExtractor();
        return new SimpleSwapiDataProvider(new SwapiDataCollector(),
                new FilmsProvider(swapiRequestExecutor, idFromURLExtractor),
                new PlanetProvider(swapiRequestExecutor, idFromURLExtractor),
                new MovieCharacterProvider(swapiRequestExecutor, idFromURLExtractor));
    }

    private WebClient swapiWebClient(String swapiBaseUrl) {
        return WebClient.builder()
                .baseUrl(swapiBaseUrl)
                .build();
    }
}
