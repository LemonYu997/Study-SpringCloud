package consumer.pojo;

import lombok.Data;

import java.util.Date;

//在编译阶段会根据注解自动生成对应的方法，data包含get/set/hashCode/equals/toString方法
//这里不需要和数据库交互
@Data
public class User {
    private Long id;
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private String note;
    private Date created;
    private Date updated;
}
