package com.snaplink.api_gateway.filters;

import com.snaplink.api_gateway.service.JwtService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter {

    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        String token = null;

        if (path.equals("/auth/login")) {
            return chain.filter(exchange);
        } else if (path.equals("/auth/refresh")) {
            HttpCookie cookie = exchange.getRequest().getCookies().getFirst("refresh-token");
            if (cookie == null) return returnUnauthorized(exchange);
            token = cookie.getValue();
        } else {
            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                return returnUnauthorized(exchange);
            }
            token = authHeader.substring(7);
        }

        try {
            long userId = jwtService.getUserId(token);
            ServerHttpRequest mutatedRequest = exchange.getRequest()
                    .mutate()
                    .header("x-User-Id", String.valueOf(userId))
                    .build();
            return chain.filter(exchange.mutate().request(mutatedRequest).build());

        } catch (Exception ex){
            return returnUnauthorized(exchange);
        }

    }

    private Mono<Void> returnUnauthorized(ServerWebExchange exchange){
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
