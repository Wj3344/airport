package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.SpecialFlight;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午8:06
 * Description: 特殊航班标签映射
 *
 * @author chen
 */

@Mapper
@Component
public interface SpecialFlightMapper {

    /**
     * 查询所有的特殊航班标签
     *
     * @return 标签列表
     */
    @Select("select `id`, `describe`from `specialFlight`")
    @Results(value = {
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "describe", column = "describe", javaType = String.class),
    })
    List<SpecialFlight> findAll();

    /**
     * 根据标签id查询标签信息
     *
     * @param id 标签id
     * @return 标签内容
     */
    @Select("select `id`, `describe`from `specialFlight` where id = #{id}")
    SpecialFlight findById(int id);

    /**
     * 添加一个重点旅客标签
     *
     * @param tag 标签内容
     * @return 添加结果
     */
    @Insert("insert into `airport`.`specialFlight` (`describe`) values (#{tag})")
    int addSpecialFlight(String tag);

    /**
     * 根据标签内容删除标签
     *
     * @param tag 标签内容
     * @return 删除结果
     */
    @Delete("delete from specialFlight where `describe` = #{tag}")
    int deleteSpecialFlightByTag(String tag);

    /**
     * 根据标签id删除标签
     *
     * @param id 标签id
     * @return 删除结果
     */
    @Delete("delete from specialFlight where `id` = #{id}")
    int deleteSpecialFlightById(int id);

    /**
     * 修改标签内容
     *
     * @param specialFlight 标签
     * @return 修改结果
     */
    @Update("update specialFlight set `describe` = #{describe} where id = #{id}")
    int updateSpecialFlight(SpecialFlight specialFlight);

}
