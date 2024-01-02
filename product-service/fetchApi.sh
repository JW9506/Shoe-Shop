#!/bin/bash

inventory_service_url="http://localhost:8083"
curl -o src/main/resources/inventory-service-api.json $inventory_service_url/v3/api-docs
