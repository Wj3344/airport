package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.mapper.FlightInformationMapper;
import cn.fanchencloud.airport.service.FlightInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return flightInformationMapper.queryDataWithinOneDay();
    }

    @Autowired
    public void setFlightInformationMapper(FlightInformationMapper flightInformationMapper) {
        this.flightInformationMapper = flightInformationMapper;
    }
}
