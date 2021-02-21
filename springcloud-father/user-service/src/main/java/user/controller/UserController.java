package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.pojo.User;
import user.service.UserService;


@RestController     //相当于@ResponseBody+@Controller，返回字符串
@RequestMapping("/user")
@RefreshScope       //刷新配置
public class UserController {
    @Autowired
    private UserService userService;

    //获取配置文件中的数据
    @Value("${test.name}")
    private String name;

    //根据访问路径中的id获取要查询的id
    @GetMapping("/{id}")
    public User queryById(@PathVariable Long id) {
        //由于@ResponseBody，所以返回的是一个json字符串

        //输出配置文件中的数据
        System.out.println("配置文件中的test.name为：" + name);

        return userService.queryById(id);
    }
}
