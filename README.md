Currently, there are 4 microservices, each has its own build.gradle to declare its dependencies. `order-service` calls `product-service` for product details.

Some API to ping:

```bash
Post "/api/product"
    request body {
        String name
        String description
        Double price
        Long parentCategoryId
    }
Delete "/api/product/{id}"
Get "/api/product/all"

Get "/api/categories"
Get "/api/categories/hierarchical"
Get "/api/categories/{categoryId}/products"
```

```bash
|-- api-gateway
|   |-- src
|   |   `-- main
|   |       |-- java
|   |       |   `-- com
|   |       |       `-- shoeshop
|   |       |           |-- domain
|   |       |           |   `-- member
|   |       |           |       `-- controller
|   |       |           |           `-- MemberController.java
|   |       |           |-- exception
|   |       |           |   `-- ExceptionAdvice.java
|   |       |           |-- filter
|   |       |           |   `-- LoggingFilter.java
|   |       |           |-- response
|   |       |           |   |-- BaseResponse.java
|   |       |           |   |-- FailureInfo.java
|   |       |           |   `-- SuccessInfo.java
|   |       |           |-- ApiGatewayApp.java
|   |       |           `-- Result.java
|   |       `-- resources
|   |           `-- application.yml
|   `-- build.gradle
|-- eureka-service
|   |-- src
|   |   `-- main
|   |       |-- java
|   |       |   `-- com
|   |       |       `-- shoeshop
|   |       |           `-- EurekaApp.java
|   |       `-- resources
|   |           `-- application.yml
|   `-- build.gradle
|-- gradle
|   `-- wrapper
|       |-- gradle-wrapper.jar
|       `-- gradle-wrapper.properties
|-- order-service
|   |-- src
|   |   `-- main
|   |       |-- java
|   |       |   `-- com
|   |       |       `-- shoeshop
|   |       |           |-- config
|   |       |           |   |-- CurrentEnvironment.java
|   |       |           |   `-- ProductServiceProperties.java
|   |       |           |-- controller
|   |       |           |   |-- CustomerController.java
|   |       |           |   `-- OrderController.java
|   |       |           |-- dto
|   |       |           |   |-- CustomerDto.java
|   |       |           |   |-- OrderCreationDto.java
|   |       |           |   `-- OrderDto.java
|   |       |           |-- entity
|   |       |           |   |-- Customer.java
|   |       |           |   `-- Order.java
|   |       |           |-- enums
|   |       |           |   `-- PaymentStatus.java
|   |       |           |-- exceptions
|   |       |           |   |-- EntityNotFoundException.java
|   |       |           |   |-- ExceptionAdvice.java
|   |       |           |   `-- GlobalException.java
|   |       |           |-- repository
|   |       |           |   |-- CustomerRepository.java
|   |       |           |   `-- OrderRepository.java
|   |       |           |-- response
|   |       |           |   |-- APIResponseWrapper.java
|   |       |           |   |-- BaseResponse.java
|   |       |           |   |-- DataResponse.java
|   |       |           |   |-- FailureInfo.java
|   |       |           |   `-- SuccessInfo.java
|   |       |           |-- service
|   |       |           |   |-- CustomerService.java
|   |       |           |   |-- OrderService.java
|   |       |           |   `-- ProductServiceClient.java
|   |       |           |-- AuditEntity.java
|   |       |           `-- OrderApp.java
|   |       `-- resources
|   |           |-- application.yml
|   |           |-- product-service-api.json
|   |           `-- sql-init.sql
|   |-- build.gradle
|   `-- fetchApi.sh
|-- product-service
|   |-- src
|   |   `-- main
|   |       |-- java
|   |       |   `-- com
|   |       |       `-- shoeshop
|   |       |           |-- controller
|   |       |           |   |-- CategoryController.java
|   |       |           |   `-- ProductController.java
|   |       |           |-- dto
|   |       |           |   |-- CategoryDto.java
|   |       |           |   |-- CategoryNode.java
|   |       |           |   |-- ProductCreateDto.java
|   |       |           |   `-- ProductDto.java
|   |       |           |-- entity
|   |       |           |   |-- Category.java
|   |       |           |   `-- Product.java
|   |       |           |-- exceptions
|   |       |           |   |-- EntityNotFoundException.java
|   |       |           |   `-- ExceptionAdvice.java
|   |       |           |-- repository
|   |       |           |   |-- CategoryRepository.java
|   |       |           |   `-- ProductRepository.java
|   |       |           |-- response
|   |       |           |   |-- BaseResponse.java
|   |       |           |   |-- DataResponse.java
|   |       |           |   |-- FailureInfo.java
|   |       |           |   `-- SuccessInfo.java
|   |       |           |-- service
|   |       |           |   |-- CategoryService.java
|   |       |           |   `-- ProductService.java
|   |       |           `-- ProductApp.java
|   |       `-- resources
|   |           |-- application.yml
|   |           `-- sql-init.sql
|   `-- build.gradle
|-- README.md
|-- build.gradle
|-- gradlew
|-- gradlew.bat
|-- settings.gradle
`-- this.code-workspace

52 directories, 73 files

```