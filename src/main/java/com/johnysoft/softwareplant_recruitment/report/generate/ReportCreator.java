package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.report.Report;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class ReportCreator {
    SwapiDataToReportConverter converter;
    GenerateReportRepository generateReportRepository;

    ReportDSL withId(long reportId) {
        return new ReportDSL(reportId);
    }

    @RequiredArgsConstructor(access = PRIVATE)
    class ReportDSL {
        private final long id;
        private String characterPhrase;
        private String planetName;
        private List<?> reportRecords;

        ReportDSL queryCriteria(String characterPhrase, String planetName) {
            this.characterPhrase = characterPhrase;
            this.planetName = planetName;
            return this;
        }

        ReportDSL records(SwapiDataModel swapiDataModel) {
            reportRecords = converter.convert(swapiDataModel);
            return this;
        }

        void build() {
            final Report report = initializeReport();
            generateReportRepository.save(report);
        }

        private Report initializeReport() {
            Report report = new Report();

            report.setReportId(id);
            report.setCharacterPhrase(characterPhrase);
            report.setPlanetName(planetName);

            //prepare report records
            this.reportRecords.forEach(System.out::println);

            return report;
        }
    }
}
