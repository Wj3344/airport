package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.Identity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-6
 * Time: 下午2:56
 * Description: 用户账户等级映射
 *
 * @author chen
 */
@Mapper
@Component
public interface UserIdentityMapper {

    /**
     * 查询该用户的所有角色
     *
     * @param username 用户名
     * @return 用户角色列表
     */
    @Select("select `identity`.`id`,airport.`identity`.`name`,`identity`.`describe` from identity where id = (select identity from admin where username = #{username})")
    @Results(id = "identity", value = {
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "describe", column = "describe", javaType = String.class)
    })
    List<Identity> findByUsername(String username);

}
