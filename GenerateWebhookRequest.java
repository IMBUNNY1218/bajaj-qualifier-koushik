package com.example.bajaj.qualifier.dto;

public class GenerateWebhookRequest {
    private String name;
    private String regNo;
    private String email;

    // Constructors, Getters, and Setters
    public GenerateWebhookRequest(String name, String regNo, String email) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }

    public String getName() { return name; }
    public String getRegNo() { return regNo; }
    public String getEmail() { return email; }
}
