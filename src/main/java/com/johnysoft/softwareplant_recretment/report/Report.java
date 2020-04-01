package com.johnysoft.softwareplant_recretment.report;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;
}
