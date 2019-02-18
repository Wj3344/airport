package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.Freight;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-22
 * Time: 下午11:47
 * Description: 货运信息数据持久层
 *
 * @author chen
 */
@Mapper
@Component
public interface FreightMapper {
    /**
     * 将一条新的货运信息持久化到数据库
     *
     * @param freight 货运信息
     * @return 添加结果
     */
    @Insert("insert into freight(flightInformationId, closeTime, specialCase) " +
            "values (#{flightInformationId}, #{closeTime}, #{specialCase});")
    int addRecord(Freight freight);

    /**
     * 查询最近的记录
     *
     * @param currentDay 时间限制
     * @return 查询结果
     */
    @Select("select * from freight where  createTime >= DATE_SUB(NOW(),INTERVAL #{currentDay} DAY);")
    List<Freight> getCurrentRecord(int currentDay);

    /**
     * 根据id查询记录
     *
     * @param id id
     * @return 查询结果
     */
    @Select("select * from freight where id = #{id}")
    Freight getRecordById(int id);

    /**
     * 更新一条记录
     *
     * @param freight 新的记录
     * @return 更新结果
     */
    @Update("update freight set `closeTime` = #{closeTime},`specialCase` = #{specialCase} where id = #{id}")
    int update(Freight freight);
}
