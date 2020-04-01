package com.johnysoft.softwareplant_recruitment.report.remove;

import com.johnysoft.softwareplant_recruitment.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

interface ReportRemoveRepository extends JpaRepository<Report, Long> {
}
