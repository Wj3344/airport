package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.Baggage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

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

}