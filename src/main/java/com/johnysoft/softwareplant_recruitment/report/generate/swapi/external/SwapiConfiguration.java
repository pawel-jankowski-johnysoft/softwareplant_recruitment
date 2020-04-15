package com.johnysoft.softwareplant_recruitment.report.generate.swapi.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiDataProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConditionalOnProperty(prefix = "swapi", value = "base_url")
class SwapiConfiguration {
    private static final String BASE_URL = "${swapi.base_url}";

    @Bean
    public SwapiDataProvider swapiDataProvider(@Value(BASE_URL) String swapiBaseUrl, ObjectMapper objectMapper) {
        final var requestExecutor = new SwapiRequestExecutor(swapiWebClient(swapiBaseUrl), objectMapper);

        return new OldSwapiDataProvider(new PlanetsProvider(requestExecutor),
                new CharactersProvider(requestExecutor),
                new MoviesProvider(requestExecutor));
    }

    private WebClient swapiWebClient(String swapiBaseUrl) {
        return WebClient.builder()
                .baseUrl(swapiBaseUrl)
                .build();
    }

}
