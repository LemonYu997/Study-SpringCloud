package consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import consumer.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//相当于@ResponseBody+@Controller
@RestController
@RequestMapping("/consumer")
@Slf4j
//默认降级
@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    //注入eureka提供的服务发现工具类，这里用spring包下的
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    //编写降级逻辑，如果访问阻塞跳转执行queryByIdFallback方法
    //@HystrixCommand(fallbackMethod = "queryByIdFallback")
   @HystrixCommand  //使用默认降级方法
    public String queryById(@PathVariable Long id){
        //测试熔断器
        if (id == 1) {
            throw new RuntimeException("太忙了");
            //会跳转到默认降级方法
        }

        /*String url = "";

        //获取eureka中注册的user-service实例
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("user-service");
        //获取其中的一个实例（只有一个所以写0）
        ServiceInstance serviceInstance = serviceInstances.get(0);
        //重新获得url，拼接host和port，此时url就变成动态的了
        url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + id;*/

        //使用服务名称的方式获取服务
        //实际上没有这个地址，访问成功说明使用了负载均衡，拦截器加载了
        String url = "http://user-service/user/" + id;

        //由于@ResponseBody，返回的是一个User序列化后的字符串
        //restTemplate可以对json格式字符串进行反序列化
        return restTemplate.getForObject(url, String.class);
    }

    //如果上边方法访问失败，执行该方法
    //queryById与queryByIdFallback的返回类型要一致
    public String queryByIdFallback(Long id) {
        log.error("查询用户信息失败，id：{}", id);
        return "抱歉，网络繁忙！";
    }

    //默认降级方法
    public String defaultFallback() {
        return "默认提示：抱歉，网络繁忙！";
    }
}
