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
    @Id
    @GeneratedValue(strategy = AUTO)
    Long reportId;

    @Column(name = "query_criteria_character_phrase", nullable = false)
    String characterPhrase;
    @Column(name = "query_criteria_planet_name", nullable = false)
    String planetName;
}
