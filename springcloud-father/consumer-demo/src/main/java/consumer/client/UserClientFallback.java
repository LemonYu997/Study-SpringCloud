package consumer.client;

import consumer.pojo.User;
import org.springframework.stereotype.Component;

//服务降级处理类
@Component
public class UserClientFallback implements UserClient {
    @Override
    public User queryById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("用户异常");
        return user;
    }
}
