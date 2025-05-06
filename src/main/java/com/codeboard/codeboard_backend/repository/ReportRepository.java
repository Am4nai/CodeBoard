package com.codeboard.codeboard_backend.repository;

import com.codeboard.codeboard_backend.model.Report;
import com.codeboard.codeboard_backend.model.enums.ReportStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByStatus(ReportStatusEnum status); // Поиск отчётов по статусу
}
