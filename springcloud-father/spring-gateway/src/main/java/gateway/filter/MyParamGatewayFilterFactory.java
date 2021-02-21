package gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MyParamGatewayFilterFactory extends AbstractGatewayFilterFactory<MyParamGatewayFilterFactory.Config> {

    static final String PARAM_NAME = "param";

    //构造函数
    public MyParamGatewayFilterFactory() {
        super(Config.class);
    }

    //实现父类方法
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARAM_NAME);
    }

    //实现方法
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            //http://127.0.0.1:10010/api/user/3?name=test
            //config.param ==> name
            //获取请求参数中param对应参数名的参数值
            ServerHttpRequest request = exchange.getRequest();
            //如果含有该参数值，则输出
            if (request.getQueryParams().containsKey(config.param)) {
                List<String> list = request.getQueryParams().get(config.param);
                for (String value : list) {
                    System.out.println("局部过滤器：" + config.param + " = " + value);
                }
            }
            return chain.filter(exchange);
        };
    }

    //配置类
    public static class Config{
        //对应在配置过滤器时指定的参数名，MyParam=name
        private String param;

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }
    }
}
