package com.internship.reports.entity;

import com.internship.reports.rest.MicroserviceURLS;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model from <i>Patient Doctor Management</i>
 *
 * @see MicroserviceURLS
 */
@Data
@NoArgsConstructor
public class Patient {
    private String name;
}