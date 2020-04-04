package com.johnysoft.softwareplant_recruitment.report.generate.swapi.model;

import lombok.Value;

@Value
public class SingleSwapiRecord {
    Long filmId;
    String filmName;
    Long characterId;
    String characterName;
    Long planetId;
    String planetName;
}
