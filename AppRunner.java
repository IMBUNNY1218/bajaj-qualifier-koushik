package com.example.bajaj.qualifier.dto;

import com.example.bajaj.qualifier.dto.GenerateWebhookRequest;
import com.example.bajaj.qualifier.dto.GenerateWebhookResponse;
import com.example.bajaj.qualifier.dto.SubmitSolutionRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AppRunner implements CommandLineRunner {

    // Injecting values from application.properties
    @Value("${user.name}")
    private String name;

    @Value("${user.regno}")
    private String regNo;

    @Value("${user.email}")
    private String email;

    @Value("${api.url.generate-webhook}")
    private String generateWebhookUrl;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- Application Started ---");
        RestTemplate restTemplate = new RestTemplate();

        // Step 1 & 2: Generate Webhook and get response [cite: 8, 16]
        System.out.println("Step 1: Generating Webhook...");
        GenerateWebhookRequest request = new GenerateWebhookRequest(name, regNo, email);
        GenerateWebhookResponse response = restTemplate.postForObject(generateWebhookUrl, request, GenerateWebhookResponse.class);

        if (response == null || response.getWebhookURL() == null) {
            System.err.println("Failed to get webhook URL. Exiting.");
            return;
        }

        System.out.println("Webhook URL Received: " + response.getWebhookURL());
        System.out.println("Access Token Received.");

        // Step 3: Determine and solve the SQL problem [cite: 19]
        // The last two digits of "REG12347" are 47, which is an ODD number.
        // Therefore, you need to solve Question 1[cite: 20].
        
        // **!!! IMPORTANT !!!**
        // **Replace the string below with your actual SQL query for Question 1**
        String myFinalQuery = "SELECT column_name FROM table_name WHERE condition;";

        System.out.println("Step 2: SQL Problem Solved.");

        // Step 4: Submit your solution [cite: 24]
        System.out.println("Step 3: Submitting Final SQL Query...");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", response.getAccessToken()); // Use the received token [cite: 27]

        SubmitSolutionRequest solutionRequest = new SubmitSolutionRequest(myFinalQuery);
        HttpEntity<SubmitSolutionRequest> entity = new HttpEntity<>(solutionRequest, headers);

        // The webhook URL for submission is in the response from the first call [cite: 17, 25]
        String submitUrl = response.getWebhookURL(); 
        
        try {
            String result = restTemplate.postForObject(submitUrl, entity, String.class);
            System.out.println("Submission successful! Response: " + result);
        } catch (Exception e) {
            System.err.println("Submission failed: " + e.getMessage());
        }

        System.out.println("--- Process Finished ---");
    }
}

