package com.johnysoft.softwareplant_recruitment.report;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Data
@FieldDefaults(level = PRIVATE)
public class ReportEntry {

    static final String FOREIGN_KEY_REPORT_ID = "report_id";
    static final String REPORT_FIELD = "report";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long reportEntryId;

    Long filmId;
    String filmName;

    Long characterId;
    String characterName;

    Long planetId;
    String planetName;

    @ManyToOne
    @JoinColumn(name = FOREIGN_KEY_REPORT_ID)
    Report report;
}
