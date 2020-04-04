package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.report.Report;
import com.johnysoft.softwareplant_recruitment.report.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ReportPersister {
    ReportRepository reportRepository;

    @Transactional
    public void saveReport(Report report) {
        removeOldReport(report.getReportId());
        saveNewReport(report);
    }

    private void removeOldReport(Long reportId) {
        reportRepository.findById(reportId)
                .ifPresent(reportRepository::delete);
    }

    private void saveNewReport(Report report) {
        reportRepository.save(report);
    }
}
