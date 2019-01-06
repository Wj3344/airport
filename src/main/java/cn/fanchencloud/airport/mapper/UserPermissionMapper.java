package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-6
 * Time: 下午3:15
 * Description: 用户权限映射
 *
 * @author chen
 */
@Mapper
@Component
public interface UserPermissionMapper {

    @Select("select * " +
            "from `permission` " +
            "where `permission`.`id` in ( " +
            "  select `role_permission`.`permissionId`\n" +
            "  from `role_permission`\n" +
            "  where `role_permission`.`identity` in (\n" +
            "    select `admin`.`identity` from `admin` where `admin`.username = #{username}))")
    @Results(id = "permission" ,value = {
            @Result(column = "id" ,property = "id",javaType = Integer.class),
            @Result(property = "url", column = "url", javaType = String.class),
            @Result(property = "name", column = "name", javaType = String.class)
    })
    List<Permission> findByUserName(String userName);
}
