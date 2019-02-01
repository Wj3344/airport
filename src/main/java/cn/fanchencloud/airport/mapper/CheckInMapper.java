package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.CheckIn;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-22
 * Time: 下午1:07
 * Description:
 *
 * @author chen
 */
@Component
@Mapper
public interface CheckInMapper {
    /**
     * 添加一条值机记录到数据库中
     *
     * @param checkIn 值机信息
     * @return 添加记录
     */
    @Insert("insert into checkIn (flightInformationId,realNumber,luggageNumber,specialCase) " +
            "values (#{flightInformationId},#{realNumber},#{luggageNumber},#{specialCase})")
    int addRecord(CheckIn checkIn);

    /**
     * 删除一条值机信息记录
     *
     * @param id 记录id
     * @return 删除结果
     */
    @Delete("delete from checkIn where id = #{id}")
    int deleteRecord(int id);

    /**
     * 更新一条记录
     *
     * @param checkIn 值机信息记录
     * @return 更新记录结果
     */
    @Update("update checkIn set flightInformationId = #{flightInformationId},realNumber = #{realNumber}," +
            "luggageNumber  = #{luggageNumber},specialCase = #{specialCase} where id = #{id}")
    int updateRecord(CheckIn checkIn);

    /**
     * 查询两天内的数据记录
     *
     * @return 查询结果
     */
    @Select("select id, flightInformationId, realNumber, luggageNumber, specialCase, createTime from checkIn where createTime >= DATE_SUB(NOW(),INTERVAL 2 DAY);")
    List<CheckIn> getCheckInByTimeInTwoDays();

}
