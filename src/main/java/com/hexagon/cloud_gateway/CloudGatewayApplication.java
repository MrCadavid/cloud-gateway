package com.hexagon.cloud_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(
        info = @Info(
                title = "API Gateway",
                version = "1.0",
                description = "Documentation API Gateway v1.0"
        )
)
public class CloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
    return builder
    .routes()
    .route(r -> r.path("/event-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://EVENTS"))
    .route(r -> r.path("/notification-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://NOTIFICATIONS"))
    .build();
 }
}

