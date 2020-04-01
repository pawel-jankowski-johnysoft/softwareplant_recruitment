package com.johnysoft.softwareplant_recruitment.report.generate;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(value = SnakeCaseStrategy.class)
class GenerateReportQueryCriteria {
    @NotBlank String queryCriteriaCharacterPhrase;
    @NotBlank String queryCriteriaPlanetName;
}
