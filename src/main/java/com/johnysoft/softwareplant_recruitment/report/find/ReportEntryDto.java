package com.johnysoft.softwareplant_recruitment.report.find;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@JsonNaming(value = SnakeCaseStrategy.class)
class ReportEntryDto {
    long filmId;
    String filmName;
    long characterId;
    String characterName;
    long planetId;
    String planetName;
}
