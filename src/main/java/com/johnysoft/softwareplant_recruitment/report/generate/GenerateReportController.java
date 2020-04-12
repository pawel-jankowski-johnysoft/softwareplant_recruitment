package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.common.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.johnysoft.softwareplant_recruitment.common.ErrorResponseCode.EXTERNAL_SERVICE_ERROR;
import static com.johnysoft.softwareplant_recruitment.common.ErrorResponseCode.REPORT_GENERATING_INVALID_DATA;
import static com.johnysoft.softwareplant_recruitment.report.generate.GenerateReportController.REPORT_URL;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@RestController
@RequestMapping(REPORT_URL)
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
class GenerateReportController {
    static final String REPORT_URL = "/report";
    static final String REPORT_GENERATE_URL = "/{reportId}";

    ReportGenerator reportGenerator;
    GenerateReportCriteriaErrorsProcessor generateReportCriteriaErrorsProcessor;

    @PutMapping(REPORT_GENERATE_URL)
    @ResponseStatus(code = NO_CONTENT)
    public void generateReport(@PathVariable Long reportId,
                               @RequestBody @Validated GenerateReportQueryCriteria criteria, Errors errors) {
        generateReportCriteriaErrorsProcessor.process(errors);
        reportGenerator.generateReport(reportId, criteria);
    }

    @ExceptionHandler({InvalidGenerateCriteriaException.class})
    @ResponseStatus(BAD_REQUEST)
    ErrorResponse invalidGenerateReportCriteriaHandle(InvalidGenerateCriteriaException e) {
        return new ErrorResponse(e.generateMessage(), REPORT_GENERATING_INVALID_DATA.getCode());
    }

    @ExceptionHandler({SwapiDataProvidingException.class})
    @ResponseStatus(SERVICE_UNAVAILABLE)
    ErrorResponse swapiDataProvidingExceptionHandle(SwapiDataProvidingException e) {
        return new ErrorResponse(e.getCause().getMessage(), EXTERNAL_SERVICE_ERROR.getCode());
    }
}
