package com.johnysoft.softwareplant_recruitment.report.generate;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
class ReportGenerator {
    ReportCreator reportCreator;
    ReportPersister reportPersister;

    public Mono<Void> generateReport(@NonNull Long reportId, GenerateReportQueryCriteria criteria) {
        return reportCreator.create(reportId, criteria)
                .doOnNext(reportPersister::saveReport).then();
    }

}
