Currently, there are 4 microservices, each has its own build.gradle to declare its dependencies. `order-service` calls `product-service` for product details.

Some API to ping:

```bash
Post /api/product
    request body {
        String name
        String description
        Double price
        Long parentCategoryId
    }
Delete /api/product/{id}
Get /api/product/all

Get /api/categories
Get /api/categories/hierarchical
Get /api/categories/{categoryId}/products

Post /api/order
    request body {
        Long customerId
        String orderDetails
    }
Get /api/order/{id}
Get /api/order/allproducts

Get /api/cart/{id}
Post /api/cart/addItem
```

Key highlight:

- `Get /api/order/allproducts` fetches from the `product-service` module, using eureka for client discovery and HttpClient to make Http Get request for all products, show casing cross module communication.

View the project in minified view:

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
|   |       |           |   |-- CartController.java
|   |       |           |   |-- CustomerController.java
|   |       |           |   `-- OrderController.java
|   |       |           |-- dto
|   |       |           |   |-- CartCreateDto.java
|   |       |           |   |-- CartDto.java
|   |       |           |   |-- CartItemDto.java
|   |       |           |   |-- CustomerDto.java
|   |       |           |   |-- OrderCreateDto.java
|   |       |           |   `-- OrderDto.java
|   |       |           |-- entity
|   |       |           |   |-- Cart.java
|   |       |           |   |-- CartItem.java
|   |       |           |   |-- Customer.java
|   |       |           |   `-- Order.java
|   |       |           |-- enums
|   |       |           |   `-- PaymentStatus.java
|   |       |           |-- exceptions
|   |       |           |   |-- EntityNotFoundException.java
|   |       |           |   |-- ExceptionAdvice.java
|   |       |           |   `-- GlobalException.java
|   |       |           |-- repository
|   |       |           |   |-- CartItemRepository.java
|   |       |           |   |-- CartRepository.java
|   |       |           |   |-- CustomerRepository.java
|   |       |           |   `-- OrderRepository.java
|   |       |           |-- response
|   |       |           |   |-- APIResponseWrapper.java
|   |       |           |   |-- BaseResponse.java
|   |       |           |   |-- DataResponse.java
|   |       |           |   |-- FailureInfo.java
|   |       |           |   `-- SuccessInfo.java
|   |       |           |-- service
|   |       |           |   |-- CartService.java
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

52 directories, 82 files

```