package com.internship.reports.controller;

import com.internship.reports.exception.ExceptionResponse;
import com.internship.reports.exception.ReportNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalTime;

/**
 * This class will handle all the exceptions, connected with medicines
 */

@ControllerAdvice(annotations = Controller.class)
public class ReportExceptionHandler {

    /**
     * When {@link com.internship.reports.exception.ReportNotCreatedException} occurs,
     * this method handles it.
     * @param e MedicineNotFoundException
     * @return {@link ResponseEntity} with {@link com.internship.reports.exception.ExceptionResponse}
     * and {@link HttpStatus} code BAD_REQUEST (404)
     */
    @ExceptionHandler(ReportNotCreatedException.class)
    public ResponseEntity<ExceptionResponse> handleMedicineNotFoundException(ReportNotCreatedException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage(), LocalTime.now(),HttpStatus.BAD_REQUEST );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}