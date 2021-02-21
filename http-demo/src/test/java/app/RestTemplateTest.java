package app;

import app.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

//springboot测试类必带注解
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test() {
        //要先保证地址可以访问到
        String url = "http://localhost/user/3";
        //restTemplate可以对json格式字符串进行反序列化
        User user = restTemplate.getForObject(url, User.class);
        System.out.println(user);
        /*
         * 打开springboot项目的启动类，使地址可以访问到
         * 输出：
         * User(id=3, userName=wangwu, password=123456, name=王五, age=22, sex=2, birthday=Sat Jan 01 08:00:00 CST 1994,
         * note=在学php, created=Sat Jan 02 06:16:32 CST 2021, updated=Thu Feb 11 06:16:38 CST 2021)
         * */
    }
}