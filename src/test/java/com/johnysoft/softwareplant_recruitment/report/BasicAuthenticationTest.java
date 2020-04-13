package com.johnysoft.softwareplant_recruitment.report;

import com.johnysoft.softwareplant_recruitment.AbstractDocumentationTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@Import(BasicAuthenticationTest.ExampleController.class)
public class BasicAuthenticationTest extends AbstractDocumentationTest {

    private static final String TEST_CONTROLLER_URL = "/test";
    private static final String AUTHORIZATION_HEADER_NAME = "authorization";
    private static final String BASIC_AUTH_HEADER_DESCRIPTION = "Basic auth header";

    @Test
    public void basicAuthenticationAvailable() {
        given()
                .auth().preemptive().basic(USERNAME, PASSWORD)
                .filter(document(documentName(), requestHeaders(
                        headerWithName(AUTHORIZATION_HEADER_NAME).description(BASIC_AUTH_HEADER_DESCRIPTION)
                )))
                .get(TEST_CONTROLLER_URL)
                .then()
                .statusCode(NO_CONTENT.value());
    }

    @Test
    public void authenticationIsRequired() {
        given()
                .filter(document(documentName(), requestHeaders(
                        headerWithName(AUTHORIZATION_HEADER_NAME).optional().description(BASIC_AUTH_HEADER_DESCRIPTION)
                )))
                .get(TEST_CONTROLLER_URL)
                .then()
                .statusCode(UNAUTHORIZED.value());
    }

    @RestController
    @RequestMapping(TEST_CONTROLLER_URL)
    static class ExampleController {
        @GetMapping
        @ResponseStatus(NO_CONTENT)
        void ok() {
            System.out.println("test");
        }
    }


}
