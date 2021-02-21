package user.mapper;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import user.pojo.User;

//使用通用Mapper，启动类上加入了@mapperScan注解，所以这里不需要添加@Mapper注解
@Repository     //解决其他类中自动注入时无法识别的问题
public interface UserMapper extends Mapper<User> {
}
