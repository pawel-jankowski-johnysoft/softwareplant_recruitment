package com.johnysoft.softwareplant_recruitment.report.generate.swapi.v2;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

class IdFromURLExtractor {
    private static final String URL_PATH_SEPARATOR = "/";

    List<Long> extractIds(List<String> urls) {
        return urls.stream()
                .map(this::extractId)
                .collect(toUnmodifiableList());
    }

    Long extractId(String it) {
        final String[] requestParts = it.split(URL_PATH_SEPARATOR);
        return Long.valueOf(requestParts[requestParts.length - 1]);
    }
}
