package com.johnysoft.softwareplant_recruitment.report.generate.swapi;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;

public interface SwapiDataProvider {
    SwapiDataModel getReportData(SwapiSearchParams swapiSearchParams);
}
