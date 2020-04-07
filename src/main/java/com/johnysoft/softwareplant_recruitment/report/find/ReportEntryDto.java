package com.johnysoft.softwareplant_recruitment.report.find;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
class ReportEntryDto {
    long filmId;
    String filmName;
    long characterId;
    String characterName;
    long planetId;
    String planetName;
}
