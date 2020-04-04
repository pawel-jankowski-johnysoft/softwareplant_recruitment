package com.johnysoft.softwareplant_recruitment.report;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static com.johnysoft.softwareplant_recruitment.report.ReportEntry.REPORT_FIELD;
import static javax.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Data
@FieldDefaults(level = PRIVATE)
public class Report {
    private static final String CHARACTER_PHRASE_COLUMN_NAME = "query_criteria_character_phrase";
    private static final String PLANET_NAME_COLUMN_NAME = "query_criteria_planet_name";

    @Id
    Long reportId;

    @Column(name = CHARACTER_PHRASE_COLUMN_NAME, nullable = false)
    String characterPhrase;

    @Column(name = PLANET_NAME_COLUMN_NAME, nullable = false)
    String planetName;

    @OneToMany(cascade = ALL, mappedBy = REPORT_FIELD)
    List<ReportEntry> reportEntries = new ArrayList<>();

    public void addReportEntry(ReportEntry reportEntry) {
        reportEntry.setReport(this);
        this.reportEntries.add(reportEntry);
    }
}
