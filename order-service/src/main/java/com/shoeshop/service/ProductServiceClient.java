package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.BAD_THIRD_PARTY_ENDPOINT;
import static com.shoeshop.response.FailureInfo.INTERNAL_SERVER_ERROR;
import static com.shoeshop.response.FailureInfo.PARSING_ERROR;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.shoeshop.config.CurrentEnvironment;
import com.shoeshop.config.ProductServiceEndpointProperties;
import com.shoeshop.exceptions.GlobalException;
import com.shoeshop.order.model.ProductDto;
import com.shoeshop.response.APIResponseWrapperForJsonParsing;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceClient {

    private final EurekaClient eurekaClient;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ProductServiceEndpointProperties productServiceProperties;
    private final ObjectMapper objectMapper;
    private final CurrentEnvironment env;

    private String productServiceUrl() {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("product-service", false);
        return instance.getHomePageUrl();
    }

    public List<ProductDto> getAllProducts() {
        URI baseUri;
        try {
            baseUri = new URI(productServiceUrl());
        } catch (Exception e) {
            throw new GlobalException(INTERNAL_SERVER_ERROR, e);
        }
        URI fullUri = baseUri.resolve(productServiceProperties.getAllProducts());
        String url = fullUri.toString();
        log.info("Product Service Url: {}", url);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            List<ProductDto> productDtos = null;
            if (response.statusCode() == HttpStatus.OK.value()) {
                productDtos = convertJsonToListOfProductDto(response.body());
            } else {
                // Handle non-successful response or throw an exception
                log.info("Product Service returned non-successful response: {}, Url tried to request: {}",
                        response.statusCode(), url);
                productDtos = Collections.emptyList();
            }

            return productDtos;
        } catch (Exception e) {
            throw new GlobalException(BAD_THIRD_PARTY_ENDPOINT, e);
        }
    }

    private <T> T convertJsonToListOfProductDto(String jsonResponse) {
        try {
            APIResponseWrapperForJsonParsing<T> wrapper = objectMapper.readValue(jsonResponse,
                    new TypeReference<APIResponseWrapperForJsonParsing<T>>() {});
            return wrapper.getData();
        } catch (JsonProcessingException e) {
            throw new GlobalException(PARSING_ERROR, e);
        }
    }
}


