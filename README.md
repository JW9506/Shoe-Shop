Currently, there are 4 microservices, each has its own build.gradle to declare its dependencies. `order-service` calls `product-service` for product details.

- order-service: 8081
- product-service: 8082
- api-gateway: 8090
- eureka-service: 8091
- auth-service: 8092

(Work In Progress)
![ERD Diagram](./erd-2.jpg)

Some API to ping:

```bash
Get /api/customer/id {
    request param {
        fields {
            orders # To display just the order id
            ordersPlus # To display full OrderDto
        }
    }
}
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

Post /api/order/addItem
    request body {
        Long orderId
        Long productId
        Long quantity
        BigDecimal totalPrice
    }
```

Similar result can be found at `<service>/v3/api-docs`

Key highlight:

- `Get /api/order/allproducts` fetches from the `product-service` module, using eureka for client discovery and HttpClient to make Http Get request for all products, show casing cross module communication.

View the project in minified view:

```bash
.
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
|   |           |-- application-prod.yml
|   |           `-- application.yml
|   |-- Dockerfile
|   `-- build.gradle
|-- auth-service
|   |-- src
|   |   `-- main
|   |       |-- java
|   |       |   `-- com
|   |       |       `-- shoeshop
|   |       |           |-- config
|   |       |           |   |-- AuthPartyProperties.java
|   |       |           |   `-- WebConfig.java
|   |       |           |-- controller
|   |       |           |   `-- AuthController.java
|   |       |           |-- dto
|   |       |           |   `-- AuthResponseDto.java
|   |       |           |-- response
|   |       |           |   |-- BaseResponse.java
|   |       |           |   |-- DataResponse.java
|   |       |           |   |-- FailureInfo.java
|   |       |           |   `-- SuccessInfo.java
|   |       |           `-- AuthApp.java
|   |       `-- resources
|   |           |-- application-prod.yml
|   |           `-- application.yml
|   |-- Dockerfile
|   `-- build.gradle
|-- eureka-service
|   |-- bin
|   |-- src
|   |   `-- main
|   |       |-- java
|   |       |   `-- com
|   |       |       `-- shoeshop
|   |       |           `-- EurekaApp.java
|   |       `-- resources
|   |           |-- application-prod.yml
|   |           `-- application.yml
|   |-- Dockerfile
|   `-- build.gradle
|-- frontend-client
|   `-- shoe-shop
|       |-- src
|       |   |-- app
|       |   |   |-- core
|       |   |   |   |-- network
|       |   |   |   |   |-- AuthInterceptor.ts
|       |   |   |   |   `-- Interceptor.ts
|       |   |   |   |-- service
|       |   |   |   |   |-- AuthService.ts
|       |   |   |   |   |-- LoadingService.ts
|       |   |   |   |   `-- TokenService.ts
|       |   |   |   `-- store
|       |   |   |       |-- cart.store.ts
|       |   |   |       |-- category.store.ts
|       |   |   |       `-- login.store.ts
|       |   |   |-- feature
|       |   |   |   |-- cart
|       |   |   |   |   `-- components
|       |   |   |   |       `-- cart
|       |   |   |   |           |-- cart.component.html
|       |   |   |   |           |-- cart.component.scss
|       |   |   |   |           |-- cart.component.spec.ts
|       |   |   |   |           `-- cart.component.ts
|       |   |   |   |-- category-list
|       |   |   |   |   |-- components
|       |   |   |   |   |   |-- category-item
|       |   |   |   |   |   |   |-- category-item.component.html
|       |   |   |   |   |   |   |-- category-item.component.scss
|       |   |   |   |   |   |   |-- category-item.component.spec.ts
|       |   |   |   |   |   |   `-- category-item.component.ts
|       |   |   |   |   |   |-- category-list
|       |   |   |   |   |   |   |-- category-list.component.html
|       |   |   |   |   |   |   |-- category-list.component.scss
|       |   |   |   |   |   |   |-- category-list.component.spec.ts
|       |   |   |   |   |   |   `-- category-list.component.ts
|       |   |   |   |   |   `-- interfaces
|       |   |   |   |   |       |-- CartProduct.ts
|       |   |   |   |   |       |-- CategoryNode.ts
|       |   |   |   |   |       `-- Product.ts
|       |   |   |   |   |-- services
|       |   |   |   |   |   |-- category.service.spec.ts
|       |   |   |   |   |   `-- category.service.ts
|       |   |   |   |   |-- category-list-routing.module.ts
|       |   |   |   |   `-- category-list.module.ts
|       |   |   |   `-- product-list
|       |   |   |       |-- components
|       |   |   |       |   |-- product-item
|       |   |   |       |   |   |-- product-item.component.html
|       |   |   |       |   |   |-- product-item.component.scss
|       |   |   |       |   |   |-- product-item.component.spec.ts
|       |   |   |       |   |   `-- product-item.component.ts
|       |   |   |       |   `-- product-list
|       |   |   |       |       |-- product-list.component.html
|       |   |   |       |       |-- product-list.component.scss
|       |   |   |       |       |-- product-list.component.spec.ts
|       |   |   |       |       `-- product-list.component.ts
|       |   |   |       |-- services
|       |   |   |       |   |-- product.service.spec.ts
|       |   |   |       |   `-- product.service.ts
|       |   |   |       |-- product-list-routing.module.ts
|       |   |   |       `-- product-list.module.ts
|       |   |   |-- pages
|       |   |   |   |-- cart
|       |   |   |   |   |-- cart.page.component.html
|       |   |   |   |   |-- cart.page.component.scss
|       |   |   |   |   |-- cart.page.component.spec.ts
|       |   |   |   |   `-- cart.page.component.ts
|       |   |   |   |-- home
|       |   |   |   |   |-- home.component.html
|       |   |   |   |   |-- home.component.scss
|       |   |   |   |   |-- home.component.spec.ts
|       |   |   |   |   `-- home.component.ts
|       |   |   |   `-- login
|       |   |   |       |-- login.component.html
|       |   |   |       |-- login.component.scss
|       |   |   |       |-- login.component.spec.ts
|       |   |   |       `-- login.component.ts
|       |   |   |-- shared
|       |   |   |   |-- components
|       |   |   |   |   |-- header
|       |   |   |   |   |   |-- header.component.html
|       |   |   |   |   |   |-- header.component.scss
|       |   |   |   |   |   |-- header.component.spec.ts
|       |   |   |   |   |   `-- header.component.ts
|       |   |   |   |   `-- loading
|       |   |   |   |       |-- loading.component.html
|       |   |   |   |       |-- loading.component.scss
|       |   |   |   |       |-- loading.component.spec.ts
|       |   |   |   |       `-- loading.component.ts
|       |   |   |   `-- interfaces.ts
|       |   |   |-- app-routing.module.ts
|       |   |   |-- app.component.html
|       |   |   |-- app.component.scss
|       |   |   |-- app.component.spec.ts
|       |   |   |-- app.component.ts
|       |   |   `-- app.module.ts
|       |   |-- assets
|       |   |-- environments
|       |   |   |-- environment.development.ts
|       |   |   |-- environment.production.ts
|       |   |   `-- environment.ts
|       |   |-- favicon.ico
|       |   |-- index.html
|       |   |-- main.ts
|       |   `-- styles.scss
|       |-- README.md
|       |-- angular.json
|       |-- notes.md
|       |-- package.json
|       |-- tsconfig.app.json
|       |-- tsconfig.json
|       |-- tsconfig.spec.json
|       `-- yarn.lock
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
|   |       |           |   |-- AppConfig.java
|   |       |           |   |-- CurrentEnvironment.java
|   |       |           |   |-- ProductServiceEndpointProperties.java
|   |       |           |   `-- WebConfig.java
|   |       |           |-- controller
|   |       |           |   |-- CustomerController.java
|   |       |           |   `-- OrderController.java
|   |       |           |-- dto
|   |       |           |   |-- CheckOutDto.java
|   |       |           |   |-- CustomerDto.java
|   |       |           |   |-- OrderCreateDto.java
|   |       |           |   |-- OrderDto.java
|   |       |           |   |-- OrderItemDto.java
|   |       |           |   `-- ProductItemDto.java
|   |       |           |-- entity
|   |       |           |   |-- Customer.java
|   |       |           |   |-- Order.java
|   |       |           |   `-- OrderItem.java
|   |       |           |-- enums
|   |       |           |   `-- PaymentStatus.java
|   |       |           |-- exceptions
|   |       |           |   |-- EntityNotFoundException.java
|   |       |           |   |-- ExceptionAdvice.java
|   |       |           |   `-- GlobalException.java
|   |       |           |-- repository
|   |       |           |   |-- CustomerRepository.java
|   |       |           |   |-- OrderItemRepository.java
|   |       |           |   `-- OrderRepository.java
|   |       |           |-- response
|   |       |           |   |-- APIResponseWrapperForJsonParsing.java
|   |       |           |   |-- BaseResponse.java
|   |       |           |   |-- DataResponse.java
|   |       |           |   |-- FailureInfo.java
|   |       |           |   `-- SuccessInfo.java
|   |       |           |-- service
|   |       |           |   |-- CustomerService.java
|   |       |           |   |-- OrderService.java
|   |       |           |   `-- ProductServiceClient.java
|   |       |           |-- util
|   |       |           |   `-- JwtVerifier.java
|   |       |           |-- AuditEntity.java
|   |       |           `-- OrderApp.java
|   |       `-- resources
|   |           |-- application-prod.yml
|   |           |-- application.yml
|   |           |-- product-service-api.json
|   |           `-- sql-init.sql
|   |-- Dockerfile
|   |-- build.gradle
|   `-- fetchApi.sh
|-- product-service
|   |-- src
|   |   `-- main
|   |       |-- java
|   |       |   `-- com
|   |       |       `-- shoeshop
|   |       |           |-- config
|   |       |           |   `-- WebConfig.java
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
|   |           |-- application-prod.yml
|   |           |-- application.yml
|   |           `-- sql-init.sql
|   |-- Dockerfile
|   `-- build.gradle
|-- README.md
|-- authentication-sequence.png
|-- build.gradle
|-- docker-compose.yml
|-- erd-1.jpg
|-- erd-2.jpg
|-- gradlew
|-- gradlew.bat
|-- settings.gradle
`-- this.code-workspace

99 directories, 188 files

```
![Screenshot 1](./screenshot_1.jpg)
![Screenshot 2](./screenshot_2.jpg)
![Screenshot 3](./screenshot_3.jpg)