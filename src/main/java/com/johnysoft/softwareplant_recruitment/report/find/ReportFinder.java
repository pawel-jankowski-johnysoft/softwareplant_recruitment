package com.johnysoft.softwareplant_recruitment.report.find;

import com.johnysoft.softwareplant_recruitment.report.Report;
import com.johnysoft.softwareplant_recruitment.report.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;
import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ReportFinder {
    ReportRepository reportRepository;

    ReportDto findById(long reportId) {
        return reportRepository.findById(reportId)
                .map(ReportToReportDtoMapper.INSTANCE::toReportDto)
                .orElseThrow(() -> new ReportNotFoundException(reportId));
    }

    public List<ReportDto> findAllReports() {
        return reportRepository.findAll().stream()
                .map(ReportToReportDtoMapper.INSTANCE::toReportDto)
                .collect(toUnmodifiableList());
    }

    @Mapper
    interface ReportToReportDtoMapper {
        ReportToReportDtoMapper INSTANCE = Mappers.getMapper(ReportToReportDtoMapper.class);

        @Mappings({
                @Mapping(source = "characterPhrase", target = "queryCriteriaCharacterPhrase"),
                @Mapping(source = "planetName", target = "queryCriteriaPlanetName"),
                @Mapping(source = "reportEntries", target = "result"),
        })
        ReportDto toReportDto(Report report);
    }
}
