package com.johnysoft.softwareplant_recruitment.report.find;

class ReportNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Report with id %d does not exists";

    ReportNotFoundException(long id) {
        super(String.format(MESSAGE, id));
    }
}
