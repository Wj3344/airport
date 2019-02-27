package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.Baggage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-21
 * Time: 下午1:06
 * Description: 行查记录数据访问层
 *
 * @author chen
 */
@Mapper
@Component
public interface BaggageMapper {

    /**
     * 添加一条行查记录
     *
     * @param baggage 行查记录
     * @return 添加结果
     */
    @Insert("insert into baggage(flightInformationId, arrivedTime, readyTime, specialCase) values (#{flightInformationId},#{arrivedTime},#{readyTime},#{specialCase});")
    int addRecord(Baggage baggage);


    /**
     * 删除一条行查记录
     *
     * @param id 新查记录id
     * @return 删除结果
     */
    @Delete("delete from baggage where id = #{id}")
    int deleteRecord(int id);

    /**
     * 查询最近的记录
     *
     * @param currentDay 时间限制
     * @return 查询列表
     */
    @Select("select * from baggage where  createTime >= DATE_SUB(NOW(),INTERVAL #{currentDay} DAY);")
    List<Baggage> getCurrentRecord(int currentDay);

    /**
     * 根据记录id查询记录详情
     *
     * @param id id
     * @return 详情
     */
    @Select("select * from baggage where id = #{id}")
    Baggage getRecordById(int id);

    /**
     * 更新一条行查记录
     *
     * @param baggage 行查记录
     * @return 更新结果
     */
    @Update("update baggage set arrivedTime = #{arrivedTime}, readyTime = #{readyTime}, specialCase = #{specialCase} where id = #{id}")
    int update(Baggage baggage);

    /**
     * 根据航班记录id查询值机信息记录
     *
     * @param ids 航班记录id列表
     * @return 查询结果
     */
    @Select({"<script> ",
            "select id, flightInformationId, arrivedTime, readyTime, specialCase, createTime from baggage where flightInformationId in ",
            "<foreach collection='ids' item='item' index='index' open='(' close=')' separator=','>",
            "(#{item})",
            "</foreach>",
            "</script> "
    })
    @MapKey("flightInformationId")
    Map<Integer, Baggage> getRecordByIdList(@Param("ids") List<Integer> ids);
}
