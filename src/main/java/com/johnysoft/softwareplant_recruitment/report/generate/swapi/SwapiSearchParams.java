package com.johnysoft.softwareplant_recruitment.report.generate.swapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class SwapiSearchParams {
    @NonNull String characterPhrase;
    @NonNull String planetName;
}
