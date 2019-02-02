package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.FlightInformation;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据航班id查询航班信息记录
     *
     * @param ids 航班id集合
     * @return 查询结果
     */
    Map<Integer, String> queryFlightNumberWithId(List<Integer> ids);
}
