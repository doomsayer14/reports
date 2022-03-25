package com.internship.reports.service;

import com.internship.reports.rest.*;
import com.internship.reports.entity.Patient;
import com.internship.reports.entity.Treatment;
import com.internship.reports.exception.ReportNotCreatedException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class generates expenses report for user in the <u>resources</u> directory.
 * Reports generated with {@link JasperReport}
 */

@Service
@RequiredArgsConstructor
public class ReportService {

    private final Adapter adapter;

    private final HeaderService headerService;

    /** absolute path to the directory, where class will generate reports */
    private final static String PATH = System.getProperty("user.dir") + "/src/main/resources";

    /**
     * literally generates "pdf" or "html" report. Contain JasperReport API.
     *
     * @param reportFormat file extension. Can be "pdf" or "html"
     * @param id           patient id
     * @param currency     treatment's price currency
     */
    @SneakyThrows
    public void generateExpensesReport(String reportFormat, Long id, String currency) {

        List<Treatment> list = requestTreatment(id, currency);

        //load file and compile
        File file = ResourceUtils.getFile("classpath:expensesReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Patient", requestPatient(id).getName());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, PATH + "/expensesReport.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, PATH + "/expensesReport.pdf");
        }

        String s = PATH + "/expensesReport." + reportFormat;
        if (!(new File(s).exists())) {
            throw new ReportNotCreatedException("report was not created");
        }
    }

    /**
     * To generate report, method {@link ReportService#generateExpensesReport(String, Long, String)}
     * needs name of {@link Patient} and his {@link Treatment}.
     * Method wraps them in one List and returns
     *
     * Connects with <i>Treatment Management Timeline</i> microservice
     * and gets List of treatments
     *
     * @param id       patient id
     * @param currency treatment's price currency
     * @return ArrayList of patients' treatments
     */
    @SuppressWarnings("unchecked")
    private List<Treatment> requestTreatment(Long id, String currency) {
        ResponseEntity<List> treatmentResponseEntity =
                adapter.getResponseEntity(MicroserviceURLS.TMT, headerService.getToken(),
                        TmtEndpoints.GET_TREATMENT_BY_PATIENT_ID, null, List.class, id, currency);
        List<Treatment> treatments = treatmentResponseEntity.getBody();

        return new ArrayList(treatments);
    }

    /**
     * Connects with <i>Patient Doctor Management</i> microservice
     * and gets Patient instance
     *
     * @return Patient instance
     */
    private Patient requestPatient(Long id) {
        ResponseEntity<Patient> patientResponseEntity =
                adapter.getResponseEntity(MicroserviceURLS.PDM, headerService.getToken(),
                        PdmEndpoints.GET_PATIENT_BY_ID, null, Patient.class, 4);
        return patientResponseEntity.getBody();
    }
}

