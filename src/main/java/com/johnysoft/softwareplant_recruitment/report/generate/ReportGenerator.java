package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.report.Report;
import com.johnysoft.softwareplant_recruitment.report.generate.ReportCreator.ReportDetails;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiSearchParams;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.SwapiDataProvider;
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
    ReportPersister reportPersister;

    public void generateReport(@NonNull Long reportId, GenerateReportQueryCriteria criteria) {
        final SwapiDataModel swapiData = getSwapiData(criteria);
        final Report report = reportCreator.create(ReportDetails.of(reportId, criteria.getQueryCriteriaCharacterPhrase(),
                criteria.getQueryCriteriaPlanetName(), swapiData.getRecords()));

        reportPersister.saveReport(report);
    }

    private SwapiDataModel getSwapiData(GenerateReportQueryCriteria criteria) {
        return swapiDataProvider.getSwapiData(SwapiSearchParams.builder()
                .characterPhrase(criteria.getQueryCriteriaCharacterPhrase())
                .planetName(criteria.getQueryCriteriaPlanetName()).build());
    }
}
