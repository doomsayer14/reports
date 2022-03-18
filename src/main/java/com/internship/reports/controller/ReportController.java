package com.internship.reports.controller;

import com.internship.reports.rest.Adapter;
import com.internship.reports.rest.MicroserviceURLS;
import com.internship.reports.rest.PdmEndpoints;
import com.internship.reports.rest.RestTemplateBean;
import com.internship.reports.entity.Patient;
import com.internship.reports.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReportController {

    @Autowired
    ReportService reportService;

    @Autowired
    RestTemplateBean restTemplateBean;

    /**
     * After {@link ReportService#generateExpensesReport(String, Long, String)} returns path,
     * controller verify whether file was created
     *
     * @param id       patient id
     * @param currency treatment's price currency
     * @param format   file extension. Can be "pdf" or "html"
     * @return 201 - if file was successfully created
     * 400 - if file was not created
     */
    @GetMapping(value = "/treatment")
    public ResponseEntity<HttpStatus> generateExpensesReport(
            @RequestParam Long id,
            @RequestParam String currency,
            @RequestParam String format) {
        reportService.generateExpensesReport(format, id, currency);
        return new ResponseEntity<>(HttpStatus.CREATED);    //exception not created
    }

//    @GetMapping(value = "/test")
//    public ResponseEntity<Patient> test() {
//        Adapter adapter = new Adapter(restTemplateBean.getRestTemplate());
//        ResponseEntity<Patient> patientResponseEntity =
//                adapter.getResponseEntity(MicroserviceURLS.PDM.getPath(), "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb2RlbG9qNTE3QHNpYmVycGF5LmNvbSIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJhY2NvdW50OnJlYWQifSx7ImF1dGhvcml0eSI6InBhdGllbnQ6Y3JlYXRlIn0seyJhdXRob3JpdHkiOiJwYXRpZW50OmRlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoiYWNjb3VudDpjcmVhdGUifSx7ImF1dGhvcml0eSI6ImRvY3RvcjpjcmVhdGUifSx7ImF1dGhvcml0eSI6ImRvY3RvcjpkZWxldGUifSx7ImF1dGhvcml0eSI6ImFjY291bnQ6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6dXBkYXRlIn0seyJhdXRob3JpdHkiOiJhY2NvdW50OnVwZGF0ZSJ9LHsiYXV0aG9yaXR5IjoicGF0aWVudDpyZWFkIn0seyJhdXRob3JpdHkiOiJkb2N0b3I6cmVhZCJ9XSwiaWF0IjoxNjQ3NDM5OTc2LCJleHAiOjE2NDg1ODc2MDB9.ml80Mj7uIU9kt5pU0qiu0hNspAxmtEE5WGnd8gO2NKJKfPDbnTdM1eQY7Z87KlmXKNpC5G16QhCmqw87j9epmw",
//                        PdmEndpoints.GET_PATIENT_BY_ID, null, Patient.class, 4);
//        Patient patient = patientResponseEntity.getBody();
//        return new ResponseEntity<>(patient, HttpStatus.OK);
//    }

}
