package com.johnysoft.softwareplant_recruitment.report.find;

import com.johnysoft.softwareplant_recruitment.AbstractDocumentationTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.johnysoft.softwareplant_recruitment.common.ErrorResponseCode.REPORT_NOT_FOUND;
import static com.johnysoft.softwareplant_recruitment.report.find.FindReportController.FIND_BY_ID_URL;
import static com.johnysoft.softwareplant_recruitment.report.find.FindReportController.REPORT_URL;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.HttpStatus.NOT_FOUND;


class FindReportControllerTest extends AbstractDocumentationTest {
    protected static final long NOT_EXISTING_REPORT_ID = -1L;

    @MockBean
    private ReportFinder reportFinder;

    @Test
    public void notFoundReportReturnsErrorCode() {
        //given
        doThrow(ReportNotFoundException.class).when(reportFinder).findById(NOT_EXISTING_REPORT_ID);

        //expect
        RestAssured.given()
                .get(REPORT_URL + FIND_BY_ID_URL, NOT_EXISTING_REPORT_ID)
                .then()
                .statusCode(NOT_FOUND.value())
                .body(ERROR_CODE, equalTo(REPORT_NOT_FOUND.getCode()));
    }
}
