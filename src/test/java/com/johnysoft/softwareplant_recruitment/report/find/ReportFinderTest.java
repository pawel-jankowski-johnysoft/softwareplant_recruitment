package com.johnysoft.softwareplant_recruitment.report.find;

import com.johnysoft.softwareplant_recruitment.report.Report;
import com.johnysoft.softwareplant_recruitment.report.ReportEntry;
import com.johnysoft.softwareplant_recruitment.report.ReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReportFinderTest {

    private static final String GIVEN_PLANET_NAME_PHRASE = "example planet name";
    private static final String GIVEN_CHARACTER_PHRASE = "example character phrase";
    private static final long GIVEN_REPORT_ID = 6;

    private static final String GIVEN_FILM_NAME = "GIVEN_FILM_NAME";
    private static final String GIVEN_CHARACTER_NAME = "GIVEN_CHARACTER_NAME";
    private static final String GIVEN_PLANET_NAME = "GIVEN_PLANET_NAME";
    private static final long GIVEN_PLANET_ID = -1;
    private static final long GIVEN_FILM_ID = -2;
    private static final long GIVEN_CHARACTER_ID = -3;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportFinder reportFinder;

    @Test
    @Transactional
    public void foundExistingReportById() {
        //given
        Report report = report();

        //when
        final ReportDto reportDto = reportFinder.findById(report.getReportId());

        //then
        assertThat(reportDto.getQueryCriteriaPlanetName()).isEqualTo(GIVEN_PLANET_NAME_PHRASE);
        assertThat(reportDto.getQueryCriteriaCharacterPhrase()).isEqualTo(GIVEN_CHARACTER_PHRASE);
        assertThat(reportDto.getReportId()).isEqualTo(GIVEN_REPORT_ID);
        assertThat(reportDto.getResult()).hasSize(1);
        assertThat(reportDto.getResult().get(0)).satisfies(reportEntryDto -> {
            assertThat(reportEntryDto.getCharacterId()).isEqualTo(GIVEN_CHARACTER_ID);
            assertThat(reportEntryDto.getCharacterName()).isEqualTo(GIVEN_CHARACTER_NAME);
            assertThat(reportEntryDto.getPlanetId()).isEqualTo(GIVEN_PLANET_ID);
            assertThat(reportEntryDto.getPlanetName()).isEqualTo(GIVEN_PLANET_NAME);
            assertThat(reportEntryDto.getFilmId()).isEqualTo(GIVEN_FILM_ID);
            assertThat(reportEntryDto.getFilmName()).isEqualTo(GIVEN_FILM_NAME);

        });
    }

    private Report report() {
        Report report = new Report();
        report.setPlanetName(GIVEN_PLANET_NAME_PHRASE);
        report.setCharacterPhrase(GIVEN_CHARACTER_PHRASE);
        report.setReportId(GIVEN_REPORT_ID);
        report.addReportEntry(reportEntry());

        return reportRepository.save(report);
    }

    private ReportEntry reportEntry() {
        final ReportEntry reportEntry = new ReportEntry();
        reportEntry.setCharacterId(GIVEN_CHARACTER_ID);
        reportEntry.setCharacterName(GIVEN_CHARACTER_NAME);
        reportEntry.setFilmId(GIVEN_FILM_ID);
        reportEntry.setFilmName(GIVEN_FILM_NAME);
        reportEntry.setPlanetName(GIVEN_PLANET_NAME);
        reportEntry.setPlanetId(GIVEN_PLANET_ID);
        return reportEntry;
    }

}
