package com.internship.reports.controller;

import com.internship.reports.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Class with declared endpoints to generate expenses report for patient
 *
 * @see ReportService
 */
@RestController
@RequestMapping(value = "/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    /**
     * After {@link ReportService#generateExpensesReport(String, Long, String)} returns path,
     * controller verify whether file was created
     *
     * @param id       patient id
     * @param currency treatment's price currency
     * @param format   file extension. Can be "pdf" or "html"
     * @return 201 - if file was successfully created
     *         400 - if file was not created
     */
    @GetMapping(value = "/treatment")
    public ResponseEntity<HttpStatus> generateExpensesReport(
            @RequestParam Long id,
            @RequestParam String currency,
            @RequestParam String format) {
        reportService.generateExpensesReport(format, id, currency);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
