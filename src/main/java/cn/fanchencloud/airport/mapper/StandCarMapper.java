package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.StandCar;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-23
 * Time: 下午10:07
 * Description:
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
}
