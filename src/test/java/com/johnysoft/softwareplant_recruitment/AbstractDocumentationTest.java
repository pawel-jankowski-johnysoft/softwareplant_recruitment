package com.johnysoft.softwareplant_recruitment;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.SwapiDataProvider;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;

import static com.johnysoft.softwareplant_recruitment.configuration.SecurityConfiguration.LOGIN_URL;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
public abstract class AbstractDocumentationTest {
    protected static final String ERROR_CODE = "code";
    protected static final String MESSAGE = "message";
    private static final String DEFAULT_DOCUMENT_PATH = "{class-name}/{method-name}";
    protected static final String USERNAME = "test";
    protected static final String PASSWORD = "test";
    private static final String USERNAME_PARAMETER = "username";
    private static final String PASSWORD_PARAMETER = "password";

    @LocalServerPort
    private int serverPort;

    private RequestSpecification spec;

    @MockBean
    protected SwapiDataProvider swapiDataProvider;

    @BeforeEach
    public void initRestAssure(RestDocumentationContextProvider restDocumentation) {
        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation).operationPreprocessors()
                        .withResponseDefaults(prettyPrint())
                )
                .setPort(serverPort)
                .build();
    }

    protected final String documentName() {
        return DEFAULT_DOCUMENT_PATH;
    }

    protected final RequestSpecification authenticatedGiven() {
        final String sessionId = sessionId();
        return given().sessionId(sessionId);
    }

    private final String sessionId() {
        return given().formParam(USERNAME_PARAMETER, USERNAME)
                .formParam(PASSWORD_PARAMETER, PASSWORD)
                .post(LOGIN_URL).sessionId();
    }

    protected final RequestSpecification given() {
        return RestAssured.given(spec);
    }
}
