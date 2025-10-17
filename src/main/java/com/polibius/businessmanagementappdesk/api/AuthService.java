package com.polibius.businessmanagementappdesk.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polibius.businessmanagementappdesk.util.AppConfig;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthService {

    private static final String BASE_URL = AppConfig.getBackendURL();

    public static boolean loginOnline(String email, String password) {
        try {
            String jsonBody = String.format("""
                {
                    "email": "%s",
                    "password": "%s"
                }
            """, email, password);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/api/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response.body());

            if(response.statusCode() == 200 & jsonNode.get("success").asBoolean()) {
                System.out.println("Online login success!");
                return true;
            }
            System.out.println("Online login failed!" + jsonNode.get("message").asText());
            return false;
        } catch(Exception e) {
            System.out.println("Backend unreachable");
            return false;
        }
    }
}
