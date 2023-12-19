#!/bin/bash

product_service_url="http://localhost:8082"
curl -o src/main/resources/product-service-api.json $product_service_url/v3/api-docs
