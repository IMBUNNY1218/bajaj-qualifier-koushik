package com.example.bajaj.qualifier.dto;

public class SubmitSolutionRequest {
    private String finalQuery;

    // Constructor, Getter, and Setter
    public SubmitSolutionRequest(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    public String getFinalQuery() { return finalQuery; }
    public void setFinalQuery(String finalQuery) { this.finalQuery = finalQuery; }
}
