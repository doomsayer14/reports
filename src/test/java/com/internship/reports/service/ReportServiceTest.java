package com.internship.reports.service;

import com.internship.reports.proxy.Proxy;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {

    @InjectMocks
    ReportService reportService;

    @Mock
    Proxy proxy;

    public String testGenerateExpensesReport(String reportFormat, Pageable pageable) {
        return "";
    }

}
