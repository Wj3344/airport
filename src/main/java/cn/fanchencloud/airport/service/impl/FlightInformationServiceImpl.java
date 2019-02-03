package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.CheckIn;
import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.mapper.CheckInMapper;
import cn.fanchencloud.airport.mapper.FlightInformationMapper;
import cn.fanchencloud.airport.service.FlightInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 注入值机信息数据映射层
     */
    private CheckInMapper checkInMapper;

    @Override
    public List<FlightInformation> queryDataWithinOneDay() {
        List<FlightInformation> flightInformationList = flightInformationMapper.queryDataWithinOneDay();
        // 生成数据映射
        HashMap<Integer, FlightInformation> hashMap = new HashMap<>(flightInformationList.size());
        for (FlightInformation f : flightInformationList) {
            hashMap.put(f.getId(), f);
        }
        // 查询两天内的值机信息记录
        List<CheckIn> checkInList = checkInMapper.getCheckInByTimeInTwoDays();
        for (CheckIn c : checkInList) {
            hashMap.remove(c.getFlightInformationId());
        }
        List<FlightInformation> flightInformations = new ArrayList<>(hashMap.size());
        for (Map.Entry<Integer, FlightInformation> entry : hashMap.entrySet()) {
            flightInformations.add(entry.getValue());
        }
        return flightInformations;
    }

    @Override
    public Map<Integer, String> queryFlightNumberWithId(List<Integer> ids) {
        Map<Integer, String> map = new HashMap<>(ids.size());
        for (Integer id : ids) {
            FlightInformation flightInformation = flightInformationMapper.queryById(id);
            map.put(id, flightInformation.getFlightNumber());
        }
        return map;
    }

    @Override
    public FlightInformation getRecordById(int id) {
        return flightInformationMapper.queryById(id);
    }

    @Autowired
    public void setFlightInformationMapper(FlightInformationMapper flightInformationMapper) {
        this.flightInformationMapper = flightInformationMapper;
    }

    @Autowired
    public void setCheckInMapper(CheckInMapper checkInMapper) {
        this.checkInMapper = checkInMapper;
    }
}
