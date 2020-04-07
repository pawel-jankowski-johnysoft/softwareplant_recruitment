package com.johnysoft.softwareplant_recruitment.report.find;

import com.johnysoft.softwareplant_recruitment.common.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.johnysoft.softwareplant_recruitment.common.ErrorResponseCode.REPORT_NOT_FOUND;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(FindReportController.REPORT_URL)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class FindReportController {
    static final String REPORT_URL = "/report";
    static final String FIND_BY_ID_URL = "/{reportId}";
    static final String REPORT_ID = "reportId";

    ReportFinder reportFinder;

    @GetMapping(FIND_BY_ID_URL)
    ReportDto findReportById(@PathVariable(REPORT_ID) Long reportId) {
        return reportFinder.findById(reportId);
    }

    @GetMapping
    List<ReportDto> findAllReports() {
        return reportFinder.findAllReports();
    }

    @ExceptionHandler(ReportNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ErrorResponse reportNotFound(ReportNotFoundException reportNotFoundException) {
        return new ErrorResponse(reportNotFoundException.getMessage(), REPORT_NOT_FOUND.getCode());
    }
}
