package com.excelra.mvc.model;

import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;

public class UserJdbcTemp implements Serializable {

    private JdbcTemplate proteinJdbcConnection;
    private JdbcTemplate yearwiseJdbcConnection;
    private JdbcTemplate bibliographyJdbcConnection;
    private JdbcTemplate indicationJdbcConnection;
    private JdbcTemplate toxicityJdbcConnection;
    private JdbcTemplate taxonomyJdbcConnection;
    private JdbcTemplate endpointJdbcConnection;

    public JdbcTemplate getEndpointJdbcConnection() {
        return endpointJdbcConnection;
    }

    public void setEndpointJdbcConnection(JdbcTemplate endpointJdbcConnection) {
        this.endpointJdbcConnection = endpointJdbcConnection;
    }

    public JdbcTemplate getTaxonomyJdbcConnection() {
        return taxonomyJdbcConnection;
    }

    public void setTaxonomyJdbcConnection(JdbcTemplate taxonomyJdbcConnection) {
        this.taxonomyJdbcConnection = taxonomyJdbcConnection;
    }

    public JdbcTemplate getToxicityJdbcConnection() {
        return toxicityJdbcConnection;
    }

    public void setToxicityJdbcConnection(JdbcTemplate toxicityJdbcConnection) {
        this.toxicityJdbcConnection = toxicityJdbcConnection;
    }

    public JdbcTemplate getProteinJdbcConnection() {
        return proteinJdbcConnection;
    }

    public void setProteinJdbcConnection(JdbcTemplate proteinJdbcConnection) {
        this.proteinJdbcConnection = proteinJdbcConnection;
    }

    public JdbcTemplate getYearwiseJdbcConnection() {
        return yearwiseJdbcConnection;
    }

    public void setYearwiseJdbcConnection(JdbcTemplate yearwiseJdbcConnection) {
        this.yearwiseJdbcConnection = yearwiseJdbcConnection;
    }

    public JdbcTemplate getBibliographyJdbcConnection() {
        return bibliographyJdbcConnection;
    }

    public void setBibliographyJdbcConnection(JdbcTemplate bibliographyJdbcConnection) {
        this.bibliographyJdbcConnection = bibliographyJdbcConnection;
    }

    public JdbcTemplate getIndicationJdbcConnection() {
        return indicationJdbcConnection;
    }

    public void setIndicationJdbcConnection(JdbcTemplate indicationJdbcConnection) {
        this.indicationJdbcConnection = indicationJdbcConnection;
    }
}
