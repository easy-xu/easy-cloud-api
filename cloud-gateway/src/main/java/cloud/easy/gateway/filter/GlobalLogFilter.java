package cloud.easy.gateway.filter;

import cloud.easy.utils.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * GlobalLogFilter
 *
 * @author xu honglin
 * @date 2021/11/25 21:20
 */
@Slf4j
@Component
public class GlobalLogFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Timer timer = new Timer();
        timer.start();
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    long used = timer.end();
                    ServerHttpRequest request = exchange.getRequest();
                    RequestPath path = request.getPath();
                    log.info("path={}, used={}", path, used);
                })
        );
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
