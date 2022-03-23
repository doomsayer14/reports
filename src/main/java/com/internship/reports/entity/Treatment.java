package com.internship.reports.entity;

import com.internship.reports.rest.MicroserviceURLS;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Model from <i>Treatment Management Timeline</i>
 *
 * @see MicroserviceURLS
 */
@Data
@NoArgsConstructor
public class Treatment {
    private String title;
    private String currency;
    private Double price;
    private LocalDate startDate;



}
