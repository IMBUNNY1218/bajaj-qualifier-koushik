package com.example.bajaj.qualifier.dto;

public class GenerateWebhookResponse {
    private String webhookURL;
    private String accessToken;

    // Getters and Setters
    public String getWebhookURL() { return webhookURL; }
    public void setWebhookURL(String webhookURL) { this.webhookURL = webhookURL; }
    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
}
