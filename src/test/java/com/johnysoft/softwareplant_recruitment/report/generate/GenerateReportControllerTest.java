package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.AbstractDocumentationTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

import static com.johnysoft.softwareplant_recruitment.common.ErrorResponseCode.EXTERNAL_SERVICE_ERROR;
import static com.johnysoft.softwareplant_recruitment.common.ErrorResponseCode.REPORT_GENERATING_INVALID_DATA;
import static com.johnysoft.softwareplant_recruitment.report.generate.GenerateReportController.REPORT_GENERATE_URL;
import static com.johnysoft.softwareplant_recruitment.report.generate.GenerateReportController.REPORT_URL;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class GenerateReportControllerTest extends AbstractDocumentationTest {

    private static final String GENERATE_REPORT_URL = REPORT_URL + REPORT_GENERATE_URL;

    private static final long GIVEN_REPORT_ID = -1;
    private static final String CHARACTER_PHRASE = "CHARACTER_PHRASE";
    private static final String PLANET_NAME = "PLANET_NAME";

    private static final GenerateReportQueryCriteria QUERY_CRITERIA = defaultQueryCriteria();

    @MockBean
    private ReportGenerator reportGenerator;

    private static GenerateReportQueryCriteria defaultQueryCriteria() {
        return queryCriteria(CHARACTER_PHRASE, PLANET_NAME);
    }

    private static GenerateReportQueryCriteria queryCriteria(String characterPhrase, String planetName) {
        final GenerateReportQueryCriteria queryCriteria = new GenerateReportQueryCriteria();
        queryCriteria.setQueryCriteriaCharacterPhrase(characterPhrase);
        queryCriteria.setQueryCriteriaPlanetName(planetName);
        return queryCriteria;
    }

    @Test
    void reportGeneratedSuccessfully() {
        Mockito.when(reportGenerator.generateReport(GIVEN_REPORT_ID, QUERY_CRITERIA))
                .thenReturn(Mono.empty());

        //expect
        authenticatedGiven()
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
    void cantGenerateReportWithEmptyCriteria() {
        authenticatedGiven()
                .filter(document(documentName()))
                .contentType(JSON)
                .body(new GenerateReportQueryCriteria())
                .put(GENERATE_REPORT_URL, GIVEN_REPORT_ID)
                .then()
                .body(ERROR_CODE, equalTo(REPORT_GENERATING_INVALID_DATA.getCode()))
                .statusCode(BAD_REQUEST.value());
    }

    @Test
    void cantGenerateReportWithInvalidCriteria() {
        final var tooShortCharacterPhrase = "to";
        final var tooShortPlanetName = "sh";
        authenticatedGiven()
                .filter(document(documentName()))
                .contentType(JSON)
                .body(queryCriteria(tooShortCharacterPhrase, tooShortPlanetName))
                .put(GENERATE_REPORT_URL, GIVEN_REPORT_ID)
                .then()
                .body(ERROR_CODE, equalTo(REPORT_GENERATING_INVALID_DATA.getCode()))
                .statusCode(BAD_REQUEST.value());
    }

    @Test
    void swapiCommunicationFailed() {
        //given
        doThrow(new SwapiDataProvidingException(new Exception()))
                .when(reportGenerator).generateReport(eq(GIVEN_REPORT_ID), eq(QUERY_CRITERIA));

        //expect
        authenticatedGiven()
                .filter(document(documentName()))
                .contentType(JSON)
                .body(QUERY_CRITERIA)
                .put(GENERATE_REPORT_URL, GIVEN_REPORT_ID)
                .then()
                .body(ERROR_CODE, equalTo(EXTERNAL_SERVICE_ERROR.getCode()))
                .statusCode(SERVICE_UNAVAILABLE.value());
    }
}
