package cn.carbonface.carbongateway;

//import cn.carbonface.carbongateway.security.config.JWTConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
//@EnableConfigurationProperties({JWTConfig.class})
@ComponentScan("cn.carbonface")
//@EnableFeignClients
public class CarbonGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarbonGatewayApplication.class, args);
    }

}
