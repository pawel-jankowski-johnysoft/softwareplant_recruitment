package com.johnysoft.softwareplant_recruitment;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
public abstract class AbstractDocumentationTest {
    protected static final String ERROR_CODE = "code";
    protected static final String MESSAGE = "message";
    private static final String DEFAULT_DOCUMENT_PATH = "{class-name}/{method-name}";

    @LocalServerPort
    private int serverPort;

    private RequestSpecification spec;

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

    protected final RequestSpecification given() {
        return RestAssured.given(spec);
    }
}
