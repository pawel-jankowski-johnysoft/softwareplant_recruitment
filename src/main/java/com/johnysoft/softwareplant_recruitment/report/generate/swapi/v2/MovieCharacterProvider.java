package com.johnysoft.softwareplant_recruitment.report.generate.swapi.v2;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiRequestExecutor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import reactor.core.publisher.Mono;

import java.util.List;

import static java.util.function.Function.identity;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
class MovieCharacterProvider {
    private static final String CHARACTERS_URL = "/people/";
    SwapiRequestExecutor requestExecutor;
    IdFromURLExtractor idFromURLExtractor;

    Mono<List<MovieCharacter>> characters(String characterPhrase) {
        return requestExecutor.executeRequest(CHARACTERS_URL, characterPhrase, MovieCharacterResponse.class)
                .flatMapIterable(identity())
                .map(this::toMovieCharacter)
                .collectList();
    }

    private MovieCharacter toMovieCharacter(MovieCharacterResponse movieCharacterResponse) {
        return new MovieCharacter(idFromURLExtractor.extractId(movieCharacterResponse.url), movieCharacterResponse.name);
    }

    @Setter
    @FieldDefaults(level = PRIVATE)
    static class MovieCharacterResponse {
        String url;
        String name;
    }

    @Value
    static class MovieCharacter {
        long characterId;
        String name;
    }
}
