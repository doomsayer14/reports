package com.internship.reports.rest;

/**
 * Endpoints for <i>Patient Doctor Management</i>
 *
 * @see MicroserviceURLS
 * @see Endpoint
 */
public enum PdmEndpoints implements Endpoint{
    GET_PATIENT_BY_ID("/patient/%d");

    private String path;

    PdmEndpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
