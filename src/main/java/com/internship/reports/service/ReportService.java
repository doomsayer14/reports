package com.internship.reports.service;

import com.internship.reports.entity.TreatmentModel;
import com.internship.reports.proxy.Proxy;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final Proxy proxy;

    private final static String PATH = "/Users/vyesman/IdeaProjects/reports/src/main/resources/reports";

    @SneakyThrows
    public String generateExpensesReport(String reportFormat, Pageable pageable) {
        List<TreatmentModel> treatments = proxy.treatments();
        int num = 1;
        for (TreatmentModel treatment : treatments) {
            treatment.setNum(num);
            num++;
        }
        Page<TreatmentModel> medicinePage = new PageImpl<>(treatments);
        //load file and compile
        File file = ResourceUtils.getFile("classpath:expensesReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(medicinePage.getContent());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Viktor Yesman");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, PATH + "/expensesReport.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, PATH + "/expensesReport.pdf");
        }
        return PATH;
    }
}

