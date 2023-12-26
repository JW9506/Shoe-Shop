package com.shoeshop.controller;

import static com.shoeshop.response.SuccessInfo.SUCCESS;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoeshop.config.AuthPartyProperties;
import com.shoeshop.dto.AuthResponseDto;
import com.shoeshop.response.DataResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthPartyProperties authPartyProperties;

    @GetMapping("/login/google")
    public void googleLogin(HttpServletResponse response) throws IOException {
        String redirectUri = authPartyProperties.getRedirectUri();
        String clientId = authPartyProperties.getClientId();
        String scope = authPartyProperties.getScope().stream()
            .map(s -> "https://www.googleapis.com/auth/userinfo." + s)
            .collect(Collectors.joining(" "));
        
        String authorizationUrl = "https://accounts.google.com/o/oauth2/v2/auth" +
            "?client_id=" + clientId +
            "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8) +
            "&response_type=code" +
            "&scope=" + URLEncoder.encode(scope, StandardCharsets.UTF_8);

        log.info(" {}", authorizationUrl);
        response.sendRedirect(authorizationUrl);
    }

    @GetMapping("/login/google/callback")
    @ResponseBody
    public DataResponse<AuthResponseDto> googleCallback(@RequestParam String code) throws IOException, InterruptedException {
        // Exchange code for access token
        String clientId = authPartyProperties.getClientId();
        String clientSecret = authPartyProperties.getClientSecret();
        String redirectUri = authPartyProperties.getRedirectUri();

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
        ObjectMapper mapper = new ObjectMapper();
        log.info("{}", httpResponse.body());
        AuthResponseDto response = null;
        try {
            response = mapper.readValue(httpResponse.body(), AuthResponseDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new DataResponse<AuthResponseDto>(SUCCESS, response);
    }
}