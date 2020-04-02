package com.johnysoft.softwareplant_recruitment.report.generate;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.johnysoft.softwareplant_recruitment.report.generate.GenerateReportController.REPORT_URL;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(REPORT_URL)
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
class GenerateReportController {
    static final String REPORT_URL = "/report";
    static final String REPORT_GENERATE_URL = "/{reportId}";

    ReportGenerator reportGenerator;

    @PutMapping(REPORT_GENERATE_URL)
    @ResponseStatus(code = NO_CONTENT)
    public void generateReport(@PathVariable Long reportId,
                               @RequestBody @Validated GenerateReportQueryCriteria criteria) {
        reportGenerator.generateReport(reportId, criteria);
    }
}
