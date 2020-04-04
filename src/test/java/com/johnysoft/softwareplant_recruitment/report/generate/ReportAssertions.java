package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.report.Report;
import com.johnysoft.softwareplant_recruitment.report.ReportEntry;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SingleSwapiRecord;
import org.assertj.core.api.AbstractAssert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReportAssertions extends AbstractAssert<ReportAssertions, Report> {

    ReportAssertions(Report report) {
        super(report, ReportAssertions.class);
    }

    ReportAssertions assertBaseParams(long givenReportId, String givenCharacterPhrase, String givenPlanetName) {
        assertThat(actual.getCharacterPhrase()).isEqualTo(givenCharacterPhrase);
        assertThat(actual.getPlanetName()).isEqualTo(givenPlanetName);
        assertThat(actual.getReportId()).isEqualTo(givenReportId);
        return this;
    }

    public void assertRecords(List<SingleSwapiRecord> swapiRecords) {
        final ReportEntry reportEntry = actual.getReportEntries().get(0);
        final SingleSwapiRecord singleSwapiRecord = swapiRecords.get(0);
        assertThat(reportEntry.getPlanetId()).isEqualTo(singleSwapiRecord.getPlanetId());
        assertThat(reportEntry.getFilmId()).isEqualTo(singleSwapiRecord.getFilmId());
        assertThat(reportEntry.getCharacterId()).isEqualTo(singleSwapiRecord.getCharacterId());
        assertThat(reportEntry.getCharacterName()).isEqualTo(singleSwapiRecord.getCharacterName());
        assertThat(reportEntry.getPlanetName()).isEqualTo(singleSwapiRecord.getPlanetName());
        assertThat(reportEntry.getFilmName()).isEqualTo(singleSwapiRecord.getFilmName());
    }

    public void hasEntriesCount(int entriesCount) {
        assertThat(actual.getReportEntries()).hasSize(entriesCount);
    }
}
