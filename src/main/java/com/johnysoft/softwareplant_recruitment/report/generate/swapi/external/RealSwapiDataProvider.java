package com.johnysoft.softwareplant_recruitment.report.generate.swapi.external;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiDataProvider;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiSearchParams;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import org.springframework.stereotype.Component;

@Component
class RealSwapiDataProvider implements SwapiDataProvider {

    @Override
    public SwapiDataModel getReportData(SwapiSearchParams swapiSearchParams) {
        return null;
    }
}
