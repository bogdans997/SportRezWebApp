package com.example.demo.service.impl;

import com.example.demo.model.dto.UserReportDTO;
import com.example.demo.repositories.ReportRepository;
import com.example.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService
{
    @Autowired
    ReportRepository reportRepository;

    @Override
    public List<UserReportDTO> reportUsers() {
        return reportRepository.reportUsers();
    }
}
