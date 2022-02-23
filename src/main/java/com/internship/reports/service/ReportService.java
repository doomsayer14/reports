package com.internship.reports.service;

import com.internship.reports.entity.TreatmentModel;
import com.internship.reports.proxy.Proxy;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final Proxy proxy;

    private final static String PATH = "/Users/vyesman/IdeaProjects/medicines/src/main/resources/reports";

    private final static String TMT = "";

    @SneakyThrows
    public String generateExpensesReport(String reportFormat, Pageable pageable) {
        Page<TreatmentModel> medicinePage = new PageImpl<>(proxy.treatments());
        //load file and compile
        File file = ResourceUtils.getFile("classpath:MedicineReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        //FontsОпределите используемый шрифт

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(medicinePage.getContent());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Viktor Yesman");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, PATH + "/reportAll.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, PATH + "/reportAll.pdf");
        }
        return PATH;
    }
}

