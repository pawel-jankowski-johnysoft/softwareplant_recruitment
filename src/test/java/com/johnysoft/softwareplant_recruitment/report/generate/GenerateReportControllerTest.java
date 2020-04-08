package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.AbstractDocumentationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.johnysoft.softwareplant_recruitment.report.generate.GenerateReportController.REPORT_GENERATE_URL;
import static com.johnysoft.softwareplant_recruitment.report.generate.GenerateReportController.REPORT_URL;
import static io.restassured.http.ContentType.JSON;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class GenerateReportControllerTest extends AbstractDocumentationTest {

    private static final String GENERATE_REPORT_URL = REPORT_URL + REPORT_GENERATE_URL;

    private static final long GIVEN_REPORT_ID = -1;
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
        given()
                .filter(document(documentName()))
                .contentType(JSON)
                .body(QUERY_CRITERIA)
                .put(GENERATE_REPORT_URL, GIVEN_REPORT_ID)
                .then()
                .statusCode(NO_CONTENT.value());

        //and
        verify(reportGenerator).generateReport(eq(GIVEN_REPORT_ID), eq(QUERY_CRITERIA));
    }

    @Test
    public void cantGenerateReportWithInvalidCriteria() {
        given()
                .filter(document(documentName()))
                .contentType(JSON)
                .body(new GenerateReportQueryCriteria())
                .put(GENERATE_REPORT_URL, GIVEN_REPORT_ID)
                .then()
                .statusCode(BAD_REQUEST.value());
    }
}
