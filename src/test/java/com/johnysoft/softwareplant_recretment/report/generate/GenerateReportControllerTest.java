package com.johnysoft.softwareplant_recretment.report.generate;

import com.johnysoft.softwareplant_recretment.AbstractDocumentationTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static com.johnysoft.softwareplant_recretment.report.generate.GenerateReportController.REPORT_GENERATE_URL;
import static com.johnysoft.softwareplant_recretment.report.generate.GenerateReportController.REPORT_URL;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.NO_CONTENT;

class GenerateReportControllerTest extends AbstractDocumentationTest {

    private static final long GIVEN_REPORT_URL = -1;
    public static final String GENERATE_REPORT_PATH = REPORT_URL + REPORT_GENERATE_URL;

    @MockBean
    private ReportGenerator reportGenerator;

    @Test
    public void reportGeneratedSuccessfully() {
        //expect
        RestAssured.put(GENERATE_REPORT_PATH, GIVEN_REPORT_URL)
                .then()
                .statusCode(NO_CONTENT.value());

        //and
        verify(reportGenerator).generateReport(GIVEN_REPORT_URL);
    }

    @Test
    public void problemWithGeneratingReport() {
        //given
        doThrow(IllegalStateException.class).when(reportGenerator).generateReport(eq(GIVEN_REPORT_URL));

        RestAssured.put(GENERATE_REPORT_PATH, GIVEN_REPORT_URL)
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
