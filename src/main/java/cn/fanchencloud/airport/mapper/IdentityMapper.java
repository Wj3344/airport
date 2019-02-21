package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.Identity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 2019/2/21
 * Time: 1:29
 * Description: 用户账户啊数据操作持久化层
 *
 * @author chen
 */
@Mapper
@Component
public interface IdentityMapper {

    /**
     * 获取所有的账户等级
     *
     * @return 查询结果
     */
    @Select("select id,describe, name from identity;")
    List<Identity> getAllList();
}
