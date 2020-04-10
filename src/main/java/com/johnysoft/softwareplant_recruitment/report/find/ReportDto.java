package com.johnysoft.softwareplant_recruitment.report.find;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@JsonNaming(value = SnakeCaseStrategy.class)
class ReportDto {
    long reportId;
    String queryCriteriaCharacterPhrase;
    String queryCriteriaPlanetName;
    List<ReportEntryDto> result;
}
