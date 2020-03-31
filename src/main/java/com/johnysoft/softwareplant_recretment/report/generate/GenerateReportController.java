package com.johnysoft.softwareplant_recretment.report.generate;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.johnysoft.softwareplant_recretment.report.generate.GenerateReportController.REPORT_URL;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(REPORT_URL)
@RequiredArgsConstructor
class GenerateReportController {
    static final String REPORT_URL = "/report";
    static final String REPORT_GENERATE_URL = "/{reportId}";

    private final ReportGenerator reportGenerator;

    @PutMapping(REPORT_GENERATE_URL)
    @ResponseStatus(code = NO_CONTENT)
    public void generateReport(@PathVariable Long reportId) {
        reportGenerator.generateReport(reportId);
    }
}
