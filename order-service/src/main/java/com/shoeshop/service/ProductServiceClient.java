package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.BAD_THIRD_PARTY_ENDPOINT;
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
import com.shoeshop.config.ProductServiceProperties;
import com.shoeshop.exceptions.GlobalException;
import com.shoeshop.order.model.ProductDto;
import com.shoeshop.response.APIResponseWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceClient {

    private final EurekaClient eurekaClient;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ProductServiceProperties productServiceProperties;
    private final ObjectMapper objectMapper;
    private final CurrentEnvironment env;

    private String productServiceUrl() {
        if (env.isDev()) {
            return productServiceProperties.getUrl();
        }
        // TODO: secure flag should be dynamic based on env.isDev()
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("product-service", false);
        return instance.getHomePageUrl();
    }

    public List<ProductDto> getAllProducts() {
        String url = productServiceUrl();
        log.info("Product Service Url: {}", url);
        url += productServiceProperties.getAllProducts();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            List<ProductDto> productDtos = null;
            if (response.statusCode() == HttpStatus.OK.value()) {
                productDtos = convertJsonToListOfProductDto(response.body());
            } else {
                // Handle non-successful response or throw an exception
                productDtos = Collections.emptyList();
            }

            return productDtos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(BAD_THIRD_PARTY_ENDPOINT);
        }
    }

    private <T> T convertJsonToListOfProductDto(String jsonResponse) {
        try {
            APIResponseWrapper<T> wrapper = objectMapper.readValue(jsonResponse,
                    new TypeReference<APIResponseWrapper<T>>() {});
            return wrapper.getData();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new GlobalException(PARSING_ERROR);
        }
    }
}


