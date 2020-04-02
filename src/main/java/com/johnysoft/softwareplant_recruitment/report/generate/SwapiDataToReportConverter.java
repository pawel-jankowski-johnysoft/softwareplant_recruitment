package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
class SwapiDataToReportConverter {

    public List<?> convert(SwapiDataModel swapiDataModel){
        return Collections.emptyList();
    }
}
