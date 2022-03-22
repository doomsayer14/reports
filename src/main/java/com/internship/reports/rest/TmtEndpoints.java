package com.internship.reports.rest;

/**
 * Endpoints for <i>Treatment Management Timeline</i>
 *
 * @see MicroserviceURLS
 * @see Endpoint
 */
public enum TmtEndpoints implements Endpoint {
    GET_TREATMENT_BY_PATIENT_ID("/api/v1/treatments/%d/%s");

    private String path;

    TmtEndpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

