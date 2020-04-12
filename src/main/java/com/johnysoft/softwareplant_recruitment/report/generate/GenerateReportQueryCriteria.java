package com.johnysoft.softwareplant_recruitment.report.generate;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@JsonNaming(value = SnakeCaseStrategy.class)
class GenerateReportQueryCriteria {
    @NotBlank @Size(min = 3) String queryCriteriaCharacterPhrase;
    @NotBlank @Size(min = 3) String queryCriteriaPlanetName;
}
