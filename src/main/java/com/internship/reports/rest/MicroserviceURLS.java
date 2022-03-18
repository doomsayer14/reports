package com.internship.reports.rest;

/**
 * Domain urls for other microservices
 */
public enum MicroserviceURLS {
    /**
     * PDM - common beginning for url from <i>Patient Doctor Management</i>
     * TMT - common beginning for url from <i>Treatment Management Timeline</i>
     */
    PDM("https://pdm-service.herokuapp.com"),
    TMT("https://treatment-alex.herokuapp.com");

    private final String path;

    MicroserviceURLS(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
