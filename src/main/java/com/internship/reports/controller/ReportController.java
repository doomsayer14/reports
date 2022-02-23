package com.internship.reports.controller;

import com.internship.reports.entity.TreatmentModel;
import com.internship.reports.proxy.Proxy;
import com.internship.reports.service.ReportService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.io.File;
import java.util.List;

//@Api(value = "ReportController")
@RestController
@RequiredArgsConstructor
public class ReportController {

    private final Proxy proxy;

    @Autowired
    ReportService reportService;

//        @ApiOperation(value = "Expenses list for patient. Includes bills for appointment and medicines",
//            notes = "Returns Page of ")
    @SneakyThrows
    @GetMapping
    public ResponseEntity<HttpStatus> generateExpensesReport(
            @RequestParam String format, // pdf or html
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        String s = reportService.generateExpensesReport(format, pageable) + "/expenses_report." + format;
        if (new File(s).exists()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
