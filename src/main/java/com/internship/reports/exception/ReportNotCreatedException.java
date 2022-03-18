package com.internship.reports.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The ReportNotCreatedException can be thrown, when service didn't
 * generate report for some reasons.
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ReportNotCreatedException extends RuntimeException {

    public ReportNotCreatedException(String message) {
        super(message);
    }

}
