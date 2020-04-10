package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.common.InvalidField;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@Component
class GenerateReportCriteriaErrorsProcessor {
    void process(Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidGenerateCriteriaException(prepareErrors(errors));
        }
    }

    private List<InvalidField> prepareErrors(Errors errors) {
        return errors.getFieldErrors().stream()
                .map(it -> new InvalidField(it.getField(), it.getDefaultMessage()))
                .collect(toUnmodifiableList());
    }
}
