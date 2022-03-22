package com.internship.reports.controller;

import com.internship.reports.service.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReportControllerTest {

    @InjectMocks
    ReportController reportController;

    @Mock
    ReportService reportService;

    @Test
    public void testGenerateExpensesReport() {
        doNothing().when(reportService).generateExpensesReport("pdf", 1L, "USD");
        ResponseEntity<HttpStatus> responseEntity =
                reportController.generateExpensesReport(1L, "USD", "pdf");
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
