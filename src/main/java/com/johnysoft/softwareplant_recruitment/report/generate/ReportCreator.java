package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.report.Report;
import com.johnysoft.softwareplant_recruitment.report.ReportEntry;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SingleSwapiRecord;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.johnysoft.softwareplant_recruitment.report.generate.ReportCreator.ReportCreatingMapper.INSTANCE;
import static lombok.AccessLevel.PRIVATE;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
class ReportCreator {
    ReportSwapiDataProvider reportSwapiDataProvider;

    Mono<Report> create(Long reportId, GenerateReportQueryCriteria criteria) {
        return reportSwapiDataProvider.getSwapiData(criteria.getQueryCriteriaPlanetName(),
                criteria.getQueryCriteriaCharacterPhrase())
                .map(data -> buildReport(reportId, criteria, data));
    }

    private Report buildReport(Long reportId, GenerateReportQueryCriteria criteria, SwapiDataModel data) {
        Report report = INSTANCE.convert(reportId, criteria);
        INSTANCE.convert(data.getRecords()).forEach(report::addReportEntry);
        return report;
    }

    @Mapper(unmappedTargetPolicy = IGNORE)
    interface ReportCreatingMapper {
        ReportCreatingMapper INSTANCE = Mappers.getMapper(ReportCreatingMapper.class);

        @Mapping(ignore = true, target = "reportEntryId")
        ReportEntry convert(SingleSwapiRecord record);

        List<ReportEntry> convert(List<SingleSwapiRecord> records);

        @Mappings({
                @Mapping(source = "reportDetails.queryCriteriaCharacterPhrase", target = "characterPhrase"),
                @Mapping(source = "reportDetails.queryCriteriaPlanetName", target = "planetName"),
        })
        Report convert(Long reportId, GenerateReportQueryCriteria reportDetails);
    }
}
