package com.example.demo.controllers;

import com.example.demo.model.dto.UserReportDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/report")
public interface ReportController
{
    @GetMapping("/user")
    CollectionModel<EntityModel<UserReportDTO>> reportUsers();
}
