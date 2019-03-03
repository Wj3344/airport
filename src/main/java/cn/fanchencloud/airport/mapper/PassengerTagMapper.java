package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.PassengerTag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午8:06
 * Description: 重点旅客标记映射
 *
 * @author chen
 */

@Mapper
@Component
public interface PassengerTagMapper {

    /**
     * 查询所有的重点旅客标签
     *
     * @return 表现列表
     */
    @Select("select `id`, `describe`from `passengerTag`")
    @Results(value = {
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "describe", column = "describe", javaType = String.class),
    })
    List<PassengerTag> findAll();

    /**
     * 根据航班记录id查询标签列表
     *
     * @param id 航班记录id
     * @return 标签记录id
     */
    @Select("select `id` from passengerTag where id in (select tagId from flightInformation_passengerTag where flightId = #{id});")
    List<Integer> findByFlightInformationId(int id);

    /**
     * 根据标签id查询标签信息
     *
     * @param id 标签id
     * @return 标签内容
     */
    @Select("select `id`, `describe`from `passengerTag` where id = #{id}")
    PassengerTag findById(int id);

    /**
     * 添加一个重点旅客标签
     *
     * @param tag 标签内容
     * @return 添加结果
     */
    @Insert("insert into `airport`.`passengerTag` (`describe`) values (#{tag})")
    int addPassengerTag(String tag);

    /**
     * 根据标签内容删除标签
     *
     * @param tag 标签内容
     * @return 删除结果
     */
    @Delete("delete from passengerTag where `describe` = #{tag}")
    int deletePassengerTagByTag(String tag);

    /**
     * 根据标签id删除标签
     *
     * @param id 标签id
     * @return 删除结果
     */
    @Delete("delete from passengerTag where `id` = #{id}")
    int deletePassengerTagById(int id);

    /**
     * 修改标签内容
     *
     * @param tag 标签
     * @return 修改结果
     */
    @Update("update passengerTag set `describe` = #{describe} where id = #{id}")
    int updatePassengerTag(PassengerTag tag);

    /**
     * 获取所有的特殊航班标记标签
     *
     * @return 所有的特殊航班标记标签列表
     */
    @Select("select `id`, `describe`from `passengerTag`")
    @MapKey("id")
    Map<Integer, PassengerTag> findAllMap();
}
