package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.Freight;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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
}
