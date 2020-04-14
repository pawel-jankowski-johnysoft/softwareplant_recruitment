package com.johnysoft.softwareplant_recruitment.report.remove;

import com.johnysoft.softwareplant_recruitment.AbstractDocumentationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.johnysoft.softwareplant_recruitment.report.remove.ReportRemoveController.DELETE_REPORT_BY_ID_URL;
import static com.johnysoft.softwareplant_recruitment.report.remove.ReportRemoveController.DELETE_REPORT_URL;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class ReportRemoveControllerTest extends AbstractDocumentationTest {
    private static final long GIVEN_REPORT_ID = -1;
    private static final String DELETE_SINGLE_REPORT_URL = DELETE_REPORT_URL + DELETE_REPORT_BY_ID_URL;
    private static final String REPORT_ID_PATH_PARAM_NAME = "reportId";

    @MockBean
    private ReportRemover reportRemover;

    @Test
    void successfullyRemoveSingleReport() {
        authenticatedGiven()
                .filter(document(documentName(), pathParameters(
                        parameterWithName(REPORT_ID_PATH_PARAM_NAME).description("report to remove"))))
                .delete(DELETE_SINGLE_REPORT_URL, GIVEN_REPORT_ID)
                .then().statusCode(NO_CONTENT.value());

        verify(reportRemover).removeReport(GIVEN_REPORT_ID);
    }

    @Test
    void successfullyRemoveAllReports() {
        authenticatedGiven()
                .filter(document(documentName()))
                .delete(DELETE_REPORT_URL)
                .then().statusCode(NO_CONTENT.value());

        verify(reportRemover).removeAll();
    }
}
