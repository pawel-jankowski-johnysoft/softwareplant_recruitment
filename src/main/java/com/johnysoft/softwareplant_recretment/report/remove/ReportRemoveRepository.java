package com.johnysoft.softwareplant_recretment.report.remove;

import com.johnysoft.softwareplant_recretment.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

interface ReportRemoveRepository extends JpaRepository<Report, Long> {
}
