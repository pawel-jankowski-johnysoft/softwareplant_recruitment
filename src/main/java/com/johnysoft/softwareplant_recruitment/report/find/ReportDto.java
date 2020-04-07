package com.johnysoft.softwareplant_recruitment.report.find;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
class ReportDto {
    long reportId;
    String queryCriteriaCharacterPhrase;
    String queryCriteriaPlanetName;
    List<ReportEntryDto> result;
}
