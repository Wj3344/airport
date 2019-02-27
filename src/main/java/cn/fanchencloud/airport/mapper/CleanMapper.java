package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.Clean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-22
 * Time: 下午9:06
 * Description:
 *
 * @author chen
 */
@Component
@Mapper
public interface CleanMapper {

    /**
     * 添加一条清洁信息记录到数据库
     *
     * @param clean 清洁信息记录
     * @return 添加结果
     */
    @Insert("insert into clean(flightInformationId, readTime, usedTime, specialCase) values" +
            "(#{flightInformationId}, #{readTime}, #{usedTime}, #{specialCase});")
    int addRecord(Clean clean);

    /**
     * 查询最近的数据
     *
     * @param currentDay 时间限制
     * @return 查询结果
     */
    @Select("select * from clean where createTime >= DATE_SUB(NOW(),INTERVAL #{currentDay} DAY);")
    List<Clean> getCurrentRecord(int currentDay);

    /**
     * 通过记录id'查询记录
     *
     * @param id 记录id
     * @return 查询结果
     */
    @Select("select * from clean where id = #{id}")
    Clean getRecordById(int id);

    /**
     * 更新一条记录
     *
     * @param clean 清洁信息记录
     * @return 更新结果
     */
    @Update("update clean set readTime = #{readTime}, usedTime = #{usedTime}, specialCase = #{specialCase} where id = #{id}")
    int updateRecord(Clean clean);

    /**
     * 根据航班记录id查询值机信息记录
     *
     * @param ids 航班记录id列表
     * @return 查询结果
     */
    @Select({"<script> ",
            "select id, flightInformationId, readTime, usedTime, specialCase, createTime from clean where flightInformationId in ",
            "<foreach collection='ids' item='item' index='index' open='(' close=')' separator=','>",
            "(#{item})",
            "</foreach>",
            "</script> "
    })
    @MapKey("flightInformationId")
    Map<Integer, Clean> getRecordByIdList(@Param("ids") List<Integer> ids);
}