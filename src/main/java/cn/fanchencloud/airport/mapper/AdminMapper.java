package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午3:59
 * Description:
 *
 * @author chen
 */
@Mapper
@Component
public interface AdminMapper {
    /**
     * 添加一个管理员帐号
     *
     * @param admin 添加的管理员对象
     * @return 添加结果
     */
    @Insert("insert into admin(username, password, identity) VALUES (#{username} , #{password},#{identity})")
    int add(Admin admin);

    /**
     * 更新一个管理员信息(主要是密码）
     *
     * @param admin 更新的管理员信息
     * @return 更新结果
     */
    @Update("update admin set password = #{password}  where username = #{username}")
    int update(Admin admin);

    /**
     * 删除一个管理员帐号
     *
     * @param username 学生学号
     * @return 删除结果
     */
    @Delete("delete from admin where username = #{username}")
    int deleteAdmin(String username);

    /**
     * 查询该用户是否存在
     *
     * @param admin 用户信息
     * @return 是否存在结果
     */
    boolean isExists(Admin admin);

    /**
     * 查询用户等级
     *
     * @param username 用户帐号
     * @return 用户等级
     */
    @Select("select identity from admin where username =#{username}")
    int queryIdentity(String username);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户详细信息
     */
    @Select("select * from admin where username = #{username} limit 1")
    @Results(id = "admin", value = {
            @Result(property = "username", column = "username", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class),
            @Result(property = "identity", column = "identity", javaType = Integer.class)
    })
    Admin queryAdmin(String username);

    /**
     * 查询所有的管理员账号
     *
     * @return 账号列表
     */
    @Select("select `username`, `password`,`identity` from admin where `identity` != 0")
    List<Admin> queryAllAdmin();
}
