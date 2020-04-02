package com.johnysoft.softwareplant_recruitment.report.remove;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.johnysoft.softwareplant_recruitment.report.remove.ReportRemoveController.DELETE_REPORT_URL;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(DELETE_REPORT_URL)
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
class ReportRemoveController {

    static final String DELETE_REPORT_BY_ID_URL = "/{reportId}";
    static final String DELETE_REPORT_URL = "/report";

    ReportRemover reportRemover;

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
