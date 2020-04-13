package com.johnysoft.softwareplant_recruitment.report.remove;

import com.johnysoft.softwareplant_recruitment.AbstractDocumentationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.johnysoft.softwareplant_recruitment.report.remove.ReportRemoveController.DELETE_REPORT_BY_ID_URL;
import static com.johnysoft.softwareplant_recruitment.report.remove.ReportRemoveController.DELETE_REPORT_URL;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NO_CONTENT;

class ReportRemoveControllerTest extends AbstractDocumentationTest {
    private static final long GIVEN_REPORT_ID = -1;
    private static final String DELETE_SINGLE_REPORT_URL = DELETE_REPORT_URL + DELETE_REPORT_BY_ID_URL;

    @MockBean
    private ReportRemover reportRemover;

    @Test
    void successfullyRemoveSingleReport() {
        authenticatedGiven()
                .delete(DELETE_SINGLE_REPORT_URL, GIVEN_REPORT_ID)
                .then().statusCode(NO_CONTENT.value());

        verify(reportRemover).removeReport(GIVEN_REPORT_ID);
    }

    @Test
    void cantRemoveSingleReport() {
        doThrow(IllegalStateException.class).when(reportRemover).removeReport(GIVEN_REPORT_ID);

        authenticatedGiven()
                .delete(DELETE_SINGLE_REPORT_URL, GIVEN_REPORT_ID)
                .then().statusCode(INTERNAL_SERVER_ERROR.value());
    }

}
