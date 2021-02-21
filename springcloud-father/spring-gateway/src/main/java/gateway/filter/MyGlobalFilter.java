package gateway.filter;

import com.netflix.ribbon.proxy.annotation.Http;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    //过滤器实现方法
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("---------全局过滤器MyGlobalFilter----------");
        //获取访问地址中的token
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        //判断token是否存在或是否为空
        if (StringUtils.isBlank(token)) {
            //设置响应状态码为未授权
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //token存在，放行
        return chain.filter(exchange);
    }

    //过滤器执行顺序
    @Override
    public int getOrder() {
        //值越小越先执行（如果有多个过滤器的话）
        return 1;
    }
}
