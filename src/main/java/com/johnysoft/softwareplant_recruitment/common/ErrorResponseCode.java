package com.johnysoft.softwareplant_recruitment.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorResponseCode {
    REPORT_NOT_FOUND(100),
    REPORT_GENERATING_INVALID_DATA(101),
    EXTERNAL_SERVICE_ERROR(102);
    @Getter
    private final int code;

}
