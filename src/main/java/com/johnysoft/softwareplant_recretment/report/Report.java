package com.johnysoft.softwareplant_recretment.report;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
}
