package com.example.demo.service;

import com.example.demo.model.dto.UserReportDTO;

import java.util.List;

public interface ReportService
{
    List<UserReportDTO> reportUsers();
}
