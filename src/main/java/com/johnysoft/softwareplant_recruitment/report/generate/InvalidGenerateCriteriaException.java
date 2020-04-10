package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.common.InvalidField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;

@RequiredArgsConstructor
@Getter
class InvalidGenerateCriteriaException extends RuntimeException {
    protected static final String INVALID_FIELD_MESSAGE_TEMPLATE = "Invalid field %s - reason: %s";
    private final List<InvalidField> invalidFields;

    String generateMessage() {
        return invalidFields.stream()
                .map(it -> format(INVALID_FIELD_MESSAGE_TEMPLATE, it.getFieldName(), it.getMessage()))
                .collect(Collectors.joining(lineSeparator()));
    }
}
