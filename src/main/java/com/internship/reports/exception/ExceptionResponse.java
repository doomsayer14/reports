package com.internship.reports.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalTime;

/**
 * This class converts in JSON in case of exceptions.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private String message;
    private LocalTime time;
    private HttpStatus status;

    public ExceptionResponse(String message) {
        this.message = message;
    }
}
