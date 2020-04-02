package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiDataProvider;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiSearchParams;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
class ReportGenerator {
    SwapiDataProvider swapiDataProvider;
    ReportCreator reportCreator;

    public void generateReport(@NonNull Long reportId, GenerateReportQueryCriteria criteria) {
        final SwapiDataModel swapiData = getSwapiData(criteria);
        buildReport(reportId, criteria, swapiData);
    }

    private SwapiDataModel getSwapiData(GenerateReportQueryCriteria criteria) {
        return swapiDataProvider.getReportData(SwapiSearchParams.builder()
                .characterPhrase(criteria.getQueryCriteriaCharacterPhrase())
                .planetName(criteria.getQueryCriteriaPlanetName()).build());
    }

    private void buildReport(Long reportId, GenerateReportQueryCriteria criteria, SwapiDataModel swapiData) {
        reportCreator.withId(reportId)
                .queryCriteria(criteria.getQueryCriteriaCharacterPhrase(), criteria.getQueryCriteriaPlanetName())
                .records(swapiData)
                .build();
    }
}
