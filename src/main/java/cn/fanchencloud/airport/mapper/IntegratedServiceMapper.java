package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.IntegratedService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-23
 * Time: 下午6:14
 * Description: 综合信息服务数据持久化
 *
 * @author chen
 */
@Component
@Mapper
public interface IntegratedServiceMapper {

    /**
     * 将一条综合服务信息持久化到数据库
     *
     * @param integratedService 综合服务信息
     * @return 持久化结果
     */
    @Insert("insert into integratedService (flightInformationId, boardingTime, readyTime, closeTime, specialCase) VALUES" +
            " (#{flightInformationId}, #{boardingTime}, #{readyTime}, #{closeTime}, #{specialCase});")
    int addIntegratedService(IntegratedService integratedService);
}
