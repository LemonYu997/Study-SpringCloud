package user.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

//在编译阶段会根据注解自动生成对应的方法，data包含get/set/hashCode/equals/toString方法
@Data
@Table(name = "tb_user")        //根据表名自动引入
public class User {
    @Id
    //主键回填，数据库表中插入新数据时，根据插入的id回填到对象中
    @KeySql(useGeneratedKeys = true)
    private Long id;

    //符合驼峰规则可以不用加@Column属性注解，user_name-->userName可以直接映射
    @Column //这里加一个作示例，下边的属性不必再加
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
