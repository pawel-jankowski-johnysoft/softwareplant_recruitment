package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.report.ReportRepository;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiDataProvider;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SingleSwapiRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.util.List;

import static com.johnysoft.softwareplant_recruitment.report.generate.SwapiDataMockResponse.singleSwapiRecord;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@SpringBootTest
class ReportGeneratorTest {

    public static final long GIVEN_REPORT_ID = 6;

    private static final String GIVEN_PLANET_NAME = "planetName";

    private static final String GIVEN_CHARACTER_PHRASE = "characterPhrase";

    @Autowired
    private ReportGenerator reportGenerator;

    @Autowired
    private ReportRepository reportRepository;

    @MockBean
    private SwapiDataProvider swapiDataProvider;

    @Test
    @Transactional
    void generateSuccessfullyNewReport() {
        //given
        final int filmId = 1;
        final int characterId = 1;
        final int planetId = 1;

        //and
        final List<SingleSwapiRecord> swapiRecords = singletonList(singleSwapiRecord(filmId, characterId, planetId));

        //and
        new SwapiDataMockResponse(swapiDataProvider)
                .mockResponse(GIVEN_CHARACTER_PHRASE, GIVEN_PLANET_NAME, swapiRecords);

        //when
        reportGenerator.generateReport(GIVEN_REPORT_ID, criteria());

        //then
        new ReportAssertions(reportRepository.getOne(GIVEN_REPORT_ID))
                .assertBaseParams(GIVEN_REPORT_ID, GIVEN_CHARACTER_PHRASE, GIVEN_PLANET_NAME)
                .assertRecords(swapiRecords);
    }

    @Test
    @Transactional
    void recreatingReportHasNewEntries() {
        //given
        generateFirstReport();

        //and
        mockSecondSwapiResponse();

        //when
        reportGenerator.generateReport(GIVEN_REPORT_ID, criteria());

        //then
        new ReportAssertions(reportRepository.getOne(GIVEN_REPORT_ID))
                .assertBaseParams(GIVEN_REPORT_ID, GIVEN_CHARACTER_PHRASE, GIVEN_PLANET_NAME)
                .hasEntriesCount(2);
    }

    private void generateFirstReport() {
        final int filmId = 1;
        final int characterId = 1;
        final int planetId = 1;

        //and
        final List<SingleSwapiRecord> swapiRecords = singletonList(singleSwapiRecord(filmId, characterId, planetId));

        //and
        new SwapiDataMockResponse(swapiDataProvider)
                .mockResponse(GIVEN_CHARACTER_PHRASE, GIVEN_PLANET_NAME, swapiRecords);

        //when
        reportGenerator.generateReport(GIVEN_REPORT_ID, criteria());
    }

    private void mockSecondSwapiResponse() {
        new SwapiDataMockResponse(swapiDataProvider)
                .mockResponse(GIVEN_CHARACTER_PHRASE, GIVEN_PLANET_NAME, asList(
                        singleSwapiRecord(2, 2, 2),
                        singleSwapiRecord(3, 3, 3)
                ));
    }

    private GenerateReportQueryCriteria criteria() {
        final var criteria = new GenerateReportQueryCriteria();
        criteria.setQueryCriteriaCharacterPhrase(GIVEN_CHARACTER_PHRASE);
        criteria.setQueryCriteriaPlanetName(GIVEN_PLANET_NAME);
        return criteria;
    }
}
