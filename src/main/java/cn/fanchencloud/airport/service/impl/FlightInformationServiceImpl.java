package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.mapper.FlightInformationMapper;
import cn.fanchencloud.airport.service.FlightInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-21
 * Time: 下午4:14
 * Description:
 *
 * @author chen
 */
@Service(value = "flightInformationService")
public class FlightInformationServiceImpl implements FlightInformationService {

    /**
     * 注入航班信息数据访问层
     */
    private FlightInformationMapper flightInformationMapper;

    @Override
    public List<FlightInformation> queryDataWithinOneDay() {
        List<FlightInformation> flightInformationList = flightInformationMapper.queryDataWithinOneDay();
        // 生成数组
        List<Integer> flightInformationIds = new ArrayList<>(flightInformationList.size());
        for (FlightInformation f: flightInformationList){
            flightInformationIds.add(f.getId());
        }
        // 查询包含这些数字的记录

        return flightInformationList;
    }

    @Autowired
    public void setFlightInformationMapper(FlightInformationMapper flightInformationMapper) {
        this.flightInformationMapper = flightInformationMapper;
    }
}
