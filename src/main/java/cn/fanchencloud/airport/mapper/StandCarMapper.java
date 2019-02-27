package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.StandCar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-23
 * Time: 下午10:07
 * Description: 站坪信息持久化层
 *
 * @author chen
 */
@Mapper
@Component
public interface StandCarMapper {
    /**
     * 请求将一条站坪信息持久化到数据库中
     *
     * @param standCar 站坪信息
     * @return 持久化结果
     */
    @Insert("insert into stand (flightInformationId, vipTime, cartTime, specialCase) values " +
            "(#{flightInformationId}, #{vipTime}, #{cartTime}, #{specialCase});")
    int addRecord(StandCar standCar);

    /**
     * 查询最近的数据
     *
     * @param currentDay 时间限制
     * @return 查询结果
     */
    @Select("select * from stand where createTime >= DATE_SUB(NOW(),INTERVAL #{currentDay} DAY);")
    List<StandCar> getCurrentRecords(int currentDay);

    /**
     * 根据记录id 查询记录信息
     *
     * @param id id
     * @return 查询结果
     */
    @Select("select * from stand where id = #{id}")
    StandCar getRecordById(int id);

    /**
     * 修改一条记录
     *
     * @param standCar 记录
     * @return 修改结果
     */
    @Update("update stand\n" +
            "set vipTime = #{vipTime},\n" +
            "    cartTime = #{cartTime},\n" +
            "    specialCase = #{specialCase}\n" +
            "where id = #{id};")
    int update(StandCar standCar);

    /**
     * 根据航班记录id查询值机信息记录
     *
     * @param ids 航班记录id列表
     * @return 查询结果
     */
    @Select({"<script> ",
            "select id, flightInformationId, vipTime, cartTime, specialCase, createTime from stand where flightInformationId in ",
            "<foreach collection='ids' item='item' index='index' open='(' close=')' separator=','>",
            "(#{item})",
            "</foreach>",
            "</script> "
    })
    @MapKey("flightInformationId")
    Map<Integer, StandCar> getRecordByIdList(@Param("ids") List<Integer> ids);
}
