package consumer.controller;

import consumer.client.UserClient;
import consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//相当于@ResponseBody+@Controller
@RestController
@RequestMapping("/cf")
public class ConsumerFeignController {

    @Autowired
    private UserClient userClient;

    //访问地址为：http://localhost:8080/cf/3
    @GetMapping("/{id}")
    public User queryById(@PathVariable Long id) {
        //经过feign客户端处理
        return userClient.queryById(id);
    }
}
