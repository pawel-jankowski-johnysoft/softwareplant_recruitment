package com.johnysoft.softwareplant_recretment.report.generate;

import com.johnysoft.softwareplant_recretment.AbstractDocumentationTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.johnysoft.softwareplant_recretment.report.generate.GenerateReportController.REPORT_GENERATE_URL;
import static com.johnysoft.softwareplant_recretment.report.generate.GenerateReportController.REPORT_URL;
import static io.restassured.http.ContentType.JSON;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NO_CONTENT;

class GenerateReportControllerTest extends AbstractDocumentationTest {

    private static final String GENERATE_REPORT_PATH = REPORT_URL + REPORT_GENERATE_URL;

    private static final long GIVEN_REPORT_URL = -1;
    private static final String CHARACTER_PHRASE = "CHARACTER_PHRASE";
    private static final String PLANET_NAME = "PLANET_NAME";

    private static final GenerateReportQueryCriteria QUERY_CRITERIA = queryCriteria();

    @MockBean
    private ReportGenerator reportGenerator;

    private static GenerateReportQueryCriteria queryCriteria() {
        final GenerateReportQueryCriteria queryCriteria = new GenerateReportQueryCriteria();
        queryCriteria.setQueryCriteriaCharacterPhrase(CHARACTER_PHRASE);
        queryCriteria.setQueryCriteriaPlanetName(PLANET_NAME);
        return queryCriteria;
    }

    @Test
    public void reportGeneratedSuccessfully() {
        //expect
        RestAssured.given()
                .contentType(JSON)
                .body(QUERY_CRITERIA)
                .put(GENERATE_REPORT_PATH, GIVEN_REPORT_URL)
                .then()
                .statusCode(NO_CONTENT.value());

        //and
        verify(reportGenerator).generateReport(eq(GIVEN_REPORT_URL), eq(QUERY_CRITERIA));
    }

    @Test
    public void cantGenerateReportWithInvalidCriteria() {
        RestAssured.given()
                .contentType(JSON)
                .body(new GenerateReportQueryCriteria())
                .put(GENERATE_REPORT_PATH, GIVEN_REPORT_URL)
                .then()
                .statusCode(BAD_REQUEST.value());
    }

    @Test
    public void problemWithGeneratingReport() {
        //given
        doThrow(IllegalStateException.class).when(reportGenerator)
                .generateReport(eq(GIVEN_REPORT_URL), eq(QUERY_CRITERIA));

        RestAssured.given()
                .contentType(JSON)
                .body(QUERY_CRITERIA)
                .put(GENERATE_REPORT_PATH, GIVEN_REPORT_URL)
                .then()
                .statusCode(INTERNAL_SERVER_ERROR.value());
    }
}
