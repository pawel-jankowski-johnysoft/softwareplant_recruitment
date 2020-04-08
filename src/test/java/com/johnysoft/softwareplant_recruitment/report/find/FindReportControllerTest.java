package com.johnysoft.softwareplant_recruitment.report.find;

import com.johnysoft.softwareplant_recruitment.AbstractDocumentationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.johnysoft.softwareplant_recruitment.common.ErrorResponseCode.REPORT_NOT_FOUND;
import static com.johnysoft.softwareplant_recruitment.report.find.FindReportController.FIND_BY_ID_URL;
import static com.johnysoft.softwareplant_recruitment.report.find.FindReportController.REPORT_URL;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;


class FindReportControllerTest extends AbstractDocumentationTest {
    protected static final long NOT_EXISTING_REPORT_ID = -1L;
    protected static final String GIVEN_PLANET_NAME = "planet name";
    protected static final long GIVEN_PLANET_ID = -1L;
    protected static final String GIVEN_FILM_NAME = "film name";
    protected static final long GIVEN_FILM_ID = -1L;
    protected static final String GIVEN_CHARACTER_NAME = "character name";
    protected static final long GIVEN_CHARACTER_ID = -1L;
    protected static final long GIVEN_REPORT_ID = -1L;
    protected static final String QUERY_CRITERIA_PLANET_NAME = "example planet name";
    protected static final String QUERY_CRITERIA_CHARACTER_PHRASE = "example character phrase";
    private static final String REPORT_ID_PARAMETER_NAME = "reportId";

    @MockBean
    private ReportFinder reportFinder;

    @Test
    public void notFoundReportReturnsErrorCode() {
        //given
        doThrow(ReportNotFoundException.class).when(reportFinder).findById(NOT_EXISTING_REPORT_ID);

        //expect
        given()
                .filter(document(documentName(), pathParameters(
                        parameterWithName(REPORT_ID_PARAMETER_NAME).description("report id to find")
                ), responseFields(
                        fieldWithPath(ERROR_CODE).description("Error code"),
                        fieldWithPath(MESSAGE).description("Message for exception")
                )))
                .get(REPORT_URL + FIND_BY_ID_URL, NOT_EXISTING_REPORT_ID)
                .then()
                .statusCode(NOT_FOUND.value())
                .body(ERROR_CODE, equalTo(REPORT_NOT_FOUND.getCode()));
    }

    @Test
    public void foundAllReports() {
        //given
        when(reportFinder.findAllReports())
                .thenReturn(singletonList(reportDto()));

        //expect
        given()
                .filter(document(documentName()))
                .get(REPORT_URL)
                .then()
                .statusCode(OK.value());
    }

    private ReportDto reportDto() {
        final ReportDto reportDto = new ReportDto();
        reportDto.setQueryCriteriaCharacterPhrase(QUERY_CRITERIA_CHARACTER_PHRASE);
        reportDto.setQueryCriteriaPlanetName(QUERY_CRITERIA_PLANET_NAME);
        reportDto.setReportId(GIVEN_REPORT_ID);
        reportDto.setResult(singletonList(reportEntryDto()));
        return reportDto;
    }

    private ReportEntryDto reportEntryDto() {
        final ReportEntryDto reportEntryDto = new ReportEntryDto();
        reportEntryDto.setCharacterId(GIVEN_CHARACTER_ID);
        reportEntryDto.setCharacterName(GIVEN_CHARACTER_NAME);
        reportEntryDto.setFilmId(GIVEN_FILM_ID);
        reportEntryDto.setFilmName(GIVEN_FILM_NAME);
        reportEntryDto.setPlanetId(GIVEN_PLANET_ID);
        reportEntryDto.setPlanetName(GIVEN_PLANET_NAME);
        return reportEntryDto;
    }
}
