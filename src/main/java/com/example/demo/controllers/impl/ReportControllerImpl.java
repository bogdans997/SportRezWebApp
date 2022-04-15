package com.example.demo.controllers.impl;

import com.example.demo.controllers.ReportController;
import com.example.demo.model.dto.UserReportDTO;
import com.example.demo.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReportControllerImpl implements ReportController
{
    @Autowired
    ReportService reportService;

    private static final Logger logger = LoggerFactory.getLogger(ReportControllerImpl.class);

    @Override
    public CollectionModel<EntityModel<UserReportDTO>> reportUsers() {
        logger.info("URL: ../report/user");
        logger.debug("Getting user report...");
        List<EntityModel<UserReportDTO>> models = new ArrayList<>();
        List<UserReportDTO> userReportDTOS = reportService.reportUsers();
        for (UserReportDTO userReportDTO:userReportDTOS) {
            models.add(EntityModel.of(userReportDTO));
        }

        return CollectionModel.of(models);
    }
}
