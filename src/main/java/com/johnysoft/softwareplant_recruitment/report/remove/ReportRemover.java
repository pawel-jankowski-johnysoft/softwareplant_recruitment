package com.johnysoft.softwareplant_recruitment.report.remove;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class ReportRemover {
    ReportRemoveRepository reportRemoveRepository;

    public void removeReport(@NonNull Long reportId) {
        reportRemoveRepository.deleteById(reportId);
    }

    public void removeAll() {
        reportRemoveRepository.deleteAll();
    }
}
