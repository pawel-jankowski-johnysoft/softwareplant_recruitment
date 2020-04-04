package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.report.Report;
import com.johnysoft.softwareplant_recruitment.report.ReportEntry;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SingleSwapiRecord;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.johnysoft.softwareplant_recruitment.report.generate.ReportCreator.ReportCreatingMapper.INSTANCE;
import static lombok.AccessLevel.PRIVATE;

@Component
class ReportCreator {

    public Report create(ReportDetails reportDetails) {
        Report report = INSTANCE.convert(reportDetails);
        INSTANCE.convert(reportDetails.reportRecords).forEach(report::addReportEntry);
        return report;
    }

    @Mapper
    interface ReportCreatingMapper {
        ReportCreatingMapper INSTANCE = Mappers.getMapper(ReportCreatingMapper.class);

        @Mapping(ignore = true, target = "reportEntryId")
        ReportEntry convert(SingleSwapiRecord record);

        List<ReportEntry> convert(List<SingleSwapiRecord> records);

        @Mapping(source = "id", target = "reportId")
        Report convert(ReportDetails record);
    }

    @Data(staticConstructor = "of")
    @FieldDefaults(makeFinal = true, level = PRIVATE)
    static class ReportDetails {
        long id;
        String characterPhrase;
        String planetName;
        List<SingleSwapiRecord> reportRecords;
    }
}
