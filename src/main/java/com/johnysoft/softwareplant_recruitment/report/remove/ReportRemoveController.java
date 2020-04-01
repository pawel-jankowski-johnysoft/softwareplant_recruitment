package com.johnysoft.softwareplant_recruitment.report.remove;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(ReportRemoveController.DELETE_REPORT_URL)
@RequiredArgsConstructor
class ReportRemoveController {

    static final String DELETE_REPORT_BY_ID_URL = "/{reportId}";
    static final String DELETE_REPORT_URL = "/report";

    private final ReportRemover reportRemover;

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void removeAll() {
        reportRemover.removeAll();
    }

    @DeleteMapping(DELETE_REPORT_BY_ID_URL)
    @ResponseStatus(NO_CONTENT)
    public void removeSingleReport(@PathVariable Long reportId) {
        reportRemover.removeReport(reportId);
    }

}
