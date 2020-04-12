package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.report.Report;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
class ReportGenerator {
    ReportCreator reportCreator;
    ReportPersister reportPersister;

    public void generateReport(@NonNull Long reportId, GenerateReportQueryCriteria criteria) {
        final Report report = reportCreator.create(reportId, criteria);
        reportPersister.saveReport(report);
    }

}
