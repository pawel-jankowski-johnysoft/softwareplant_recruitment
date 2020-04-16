package com.johnysoft.softwareplant_recruitment.report.generate.swapi.external;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiRequestExecutor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import reactor.core.publisher.Mono;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
class CharactersProvider {
    private static final String CHARACTERS_URL = "/people/";
    SwapiRequestExecutor requestExecutor;

    Mono<List<CharacterModel>> charactersMatched(String characterPartName) {
        return requestExecutor.executeRequest(CHARACTERS_URL, characterPartName, CharacterModel.class);
    }

    @Data
    @FieldDefaults(level = PRIVATE)
    static class CharacterModel {
        List<String> films;
        String name;
        String url;
    }
}
