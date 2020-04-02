package com.johnysoft.softwareplant_recruitment.report.generate;

import com.johnysoft.softwareplant_recruitment.report.Report;
import org.springframework.data.repository.CrudRepository;

interface GenerateReportRepository extends CrudRepository<Report, Long> {
}
