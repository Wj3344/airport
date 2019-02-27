package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.FlightInformationPassengerTag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-10
 * Time: 上午10:22
 * Description: 重点旅客标记
 *
 * @author chen
 */
@Component
@Mapper
public interface FlightInformationPassengerTagMapper {

    /**
     * 添加一条记录
     *
     * @param flightId 航班记录id
     * @param tagId    标签id
     * @return 添加结果
     */
    @Insert("insert into `airport`.`flightInformation_passengerTag` (flightId, tagId) VALUES (#{flightId},#{tagId})")
    int addOne(int flightId, int tagId);

    /**
     * 添加多条记录
     *
     * @param flightId 航班记录Id
     * @param tagIds   标签id集合
     * @return 添加结果
     */
    @Insert({
            "<script>",
            "insert into `airport`.`flightInformation_passengerTag` (flightId, tagId) VALUES ",
            "<foreach collection='tagIds' item='item' index='index' separator=','>",
            "(#{flightId},#{item})",
            "</foreach>",
            "</script>"
    })
    int addMany(int flightId, List<Integer> tagIds);

    /**
     * 根据id查询记录
     *
     * @param id id
     * @return 记录
     */
    @Select("select id, flightId, tagId from flightInformation_passengerTag where id = #{id}")
    FlightInformationPassengerTag findById(int id);

    /**
     * 根据航班记录id查询标签列表
     *
     * @param id 航班记录id
     * @return 标签列表
     */
    @Select("select id, flightId, tagId from flightInformation_passengerTag where flightId = #{id}")
    List<FlightInformationPassengerTag> findByFlightId(int id);

    /**
     * 根据标签id查询有这个标签的航班id
     *
     * @param id 标签id
     * @return 航班列表
     */
    @Select("select id, flightId, tagId from flightInformation_passengerTag where flightId = #{id}")
    List<FlightInformationPassengerTag> findByTagId(int id);

    /**
     * 根据id删除记录
     *
     * @param id 记录id
     * @return 删除结果
     */
    @Delete("delete from flightInformation_passengerTag where id = #{id}")
    int deleteById(int id);

    /**
     * 根据航班id删除记录
     *
     * @param id 航班记录id
     * @return 删除结果
     */
    @Delete("delete from flightInformation_passengerTag where flightId = #{id}")
    int deleteByFlightId(int id);

}
