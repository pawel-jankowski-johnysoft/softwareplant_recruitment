package com.johnysoft.softwareplant_recruitment.report;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Data
@FieldDefaults(level = PRIVATE)
public class Report {
    private static final String CHARACTER_PHRASE_COLUMN_NAME = "query_criteria_character_phrase";
    private static final String PLANET_NAME_COLUMN_NAME = "query_criteria_planet_name";

    @Id
    @GeneratedValue(strategy = AUTO)
    Long reportId;

    @Column(name = CHARACTER_PHRASE_COLUMN_NAME, nullable = false)
    String characterPhrase;
    @Column(name = PLANET_NAME_COLUMN_NAME, nullable = false)
    String planetName;
}
