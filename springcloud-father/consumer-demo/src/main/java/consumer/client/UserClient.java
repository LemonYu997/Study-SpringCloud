package consumer.client;

import consumer.config.FeignConfig;
import consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//声明当前类是一个Feign客户端，指定服务名为user-service
@FeignClient(value = "user-service", fallback = UserClientFallback.class, configuration = FeignConfig.class)
@Repository     //解决注入失败的问题
public interface UserClient {

    //服务必须存在才能拼接
    //自动拼接一个路径: http://user-service/user/3
    @GetMapping("/user/{id}")
    User queryById(@PathVariable Long id);
}
