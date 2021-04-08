package cn.carbonface.carbongateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CarbonGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarbonGatewayApplication.class, args);
    }

}
