package com.internship.reports.service;

import com.internship.reports.rest.*;
import com.internship.reports.entity.Patient;
import com.internship.reports.entity.Treatment;
import com.internship.reports.exception.ReportNotCreatedException;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Reports generates with {@link JasperReport}
 */
@Service
public class ReportService {

    @Autowired
    RestTemplateBean restTemplateBean;

    /**
     * absolute path to the directory, where class will generate reports
     */
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
        parameters.put("Patient", requestPatient().getName());

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
     * @param id       patient id
     * @param currency treatment's price currency
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Treatment> requestTreatment(Long id, String currency) {
        Adapter adapter = new Adapter(restTemplateBean.getRestTemplate());
        ResponseEntity<List> treatmentResponseEntity =
                adapter.getResponseEntity(MicroserviceURLS.TMT, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb2RlbG9qNTE3QHNpYmVycGF5LmNvbSIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJhY2NvdW50OnJlYWQifSx7ImF1dGhvcml0eSI6InBhdGllbnQ6Y3JlYXRlIn0seyJhdXRob3JpdHkiOiJwYXRpZW50OmRlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoiYWNjb3VudDpjcmVhdGUifSx7ImF1dGhvcml0eSI6ImRvY3RvcjpjcmVhdGUifSx7ImF1dGhvcml0eSI6ImRvY3RvcjpkZWxldGUifSx7ImF1dGhvcml0eSI6ImFjY291bnQ6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6dXBkYXRlIn0seyJhdXRob3JpdHkiOiJhY2NvdW50OnVwZGF0ZSJ9LHsiYXV0aG9yaXR5IjoicGF0aWVudDpyZWFkIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6cmVhZCJ9XSwiaWF0IjoxNjQ3NDM5OTc2LCJleHAiOjE2NDg1ODc2MDB9.ml80Mj7uIU9kt5pU0qiu0hNspAxmtEE5WGnd8gO2NKJKfPDbnTdM1eQY7Z87KlmXKNpC5G16QhCmqw87j9epmw",
                        TmtEndpoints.GET_TREATMENT_BY_PATIENT_ID, null, List.class, id, currency);

        List<Treatment> treatments = treatmentResponseEntity.getBody();

        return new ArrayList(treatments);
    }

    private Patient requestPatient() {
        Adapter adapter = new Adapter(restTemplateBean.getRestTemplate());

        ResponseEntity<Patient> patientResponseEntity =
                adapter.getResponseEntity(MicroserviceURLS.PDM, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb2RlbG9qNTE3QHNpYmVycGF5LmNvbSIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJhY2NvdW50OnJlYWQifSx7ImF1dGhvcml0eSI6InBhdGllbnQ6Y3JlYXRlIn0seyJhdXRob3JpdHkiOiJwYXRpZW50OmRlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoiYWNjb3VudDpjcmVhdGUifSx7ImF1dGhvcml0eSI6ImRvY3RvcjpjcmVhdGUifSx7ImF1dGhvcml0eSI6ImRvY3RvcjpkZWxldGUifSx7ImF1dGhvcml0eSI6ImFjY291bnQ6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6dXBkYXRlIn0seyJhdXRob3JpdHkiOiJhY2NvdW50OnVwZGF0ZSJ9LHsiYXV0aG9yaXR5IjoicGF0aWVudDpyZWFkIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6cmVhZCJ9XSwiaWF0IjoxNjQ3NDM5OTc2LCJleHAiOjE2NDg1ODc2MDB9.ml80Mj7uIU9kt5pU0qiu0hNspAxmtEE5WGnd8gO2NKJKfPDbnTdM1eQY7Z87KlmXKNpC5G16QhCmqw87j9epmw",
                        PdmEndpoints.GET_PATIENT_BY_ID, null, Patient.class, 4);
        Patient patient = patientResponseEntity.getBody();

        return patient;
    }
}

