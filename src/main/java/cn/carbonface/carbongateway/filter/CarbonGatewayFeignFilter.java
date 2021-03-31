package cn.carbonface.carbongateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;

@Component
public class CarbonGatewayFeignFilter implements GlobalFilter, Ordered {

    private static final String FEIGN_HEADER_NAME = "Carbon_Feign_Client";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        List<String> feignHeaders = headers.get(FEIGN_HEADER_NAME);
        if (feignHeaders == null || feignHeaders.isEmpty()){
            return chain.filter(exchange); // let it go
        }else{
            //and if this guy, faked the feign headers for some reason he/she is dangerous and got a big problem !
            // here may lies the strategy of handling those cases, maybe blacklist I suggest
            // *****
            HttpHeaders originalHeaders = exchange.getRequest().getHeaders();
//            HttpHeaders headers = new HttpHeaders();
//            headers.putAll(originalHeaders);
//            headers.remove(FEIGN_HEADER_NAME);
            Consumer<HttpHeaders> headersConsumer = httpHeaders -> {
                httpHeaders.remove(FEIGN_HEADER_NAME);
            };
            ServerHttpRequest request = exchange.getRequest().mutate().headers(headersConsumer).build();
            ServerWebExchange build = exchange.mutate().request(request).build();
            return chain.filter(build);
        }
    }

    @Override
    public int getOrder() {
        //拦截器优先级，越小位最先执行
        return 0;
    }
}