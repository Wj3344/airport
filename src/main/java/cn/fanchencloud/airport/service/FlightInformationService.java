package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.FlightInformation;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-21
 * Time: 下午4:13
 * Description: 航班信息服务类
 *
 * @author chen
 */
public interface FlightInformationService {
    /**
     * 查询一日之内的航班记录
     *
     * @return 查询记录
     */
    List<FlightInformation> queryDataWithinOneDay();
}
