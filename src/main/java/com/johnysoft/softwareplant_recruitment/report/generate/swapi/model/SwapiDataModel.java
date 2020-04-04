package com.johnysoft.softwareplant_recruitment.report.generate.swapi.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class SwapiDataModel {
    private final List<SingleSwapiRecord> records = new ArrayList<>();

    public void addRecord(SingleSwapiRecord record) {
        this.records.add(record);
    }

    public List<SingleSwapiRecord> getRecords() {
        return unmodifiableList(records);
    }
}
