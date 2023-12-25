package com.shoeshop.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/login/google")
    public void googleLogin(HttpServletResponse response) throws IOException {
        String redirectUri = "http://localhost:8080/login/google/callback";
        String clientId = "YOUR_CLIENT_ID";
        String scope = "https://www.googleapis.com/auth/userinfo.email";
        
        String authorizationUrl = "https://accounts.google.com/o/oauth2/v2/auth" +
            "?client_id=" + clientId +
            "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8) +
            "&response_type=code" +
            "&scope=" + URLEncoder.encode(scope, StandardCharsets.UTF_8);

        response.sendRedirect(authorizationUrl);
    }

    @GetMapping("/login/google/callback")
    public void googleCallback(@RequestParam String code, HttpServletResponse response) throws IOException, InterruptedException {
        // Exchange code for access token
        String clientId = "YOUR_CLIENT_ID";
        String clientSecret = "YOUR_CLIENT_SECRET";
        String redirectUri = "http://localhost:8080/login/google/callback";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://oauth2.googleapis.com/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "code=" + code +
                        "&client_id=" + clientId +
                        "&client_secret=" + clientSecret +
                        "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8) +
                        "&grant_type=authorization_code"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Extract access token from httpResponse and handle user session
    }
}