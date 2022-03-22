package com.internship.reports.rest;

/**
 * Marker interface for enums which contains
 * endpoints from other microservices
 */
public interface Endpoint {
    /**
     * @return full path (endpoint)
     */
    String getPath();
}
