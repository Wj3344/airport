package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.CheckIn;
import cn.fanchencloud.airport.entity.Clean;
import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.entity.StandCar;
import cn.fanchencloud.airport.mapper.*;
import cn.fanchencloud.airport.service.FlightInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    /**
     * 注入清洁信息数据访问层
     */
    private CleanMapper cleanMapper;

    /**
     * 注入行查记录数据访问层
     */
    private BaggageMapper baggageMapper;

    /**
     * 注入货运信息数据持久层
     */
    private FreightMapper freightMapper;

    /**
     * 注入站坪信息持久化层
     */
    private StandCarMapper standCarMapper;

    @Override
    public List<FlightInformation> queryDataWithinOneDay(List<Integer> flightInformationIds) {
        List<FlightInformation> flightInformationList = flightInformationMapper.queryDataWithinOneDay();
        // 生成数据映射
        Map<Integer, FlightInformation> hashMap = flightInformationList.stream().collect(Collectors.toMap(FlightInformation::getId, flightInformation -> flightInformation));
        for (Integer id : flightInformationIds) {
            hashMap.remove(id);
        }
        List<FlightInformation> flightInformationArrayList = new ArrayList<>(hashMap.size());
        for (Map.Entry<Integer, FlightInformation> entry : hashMap.entrySet()) {
            flightInformationArrayList.add(entry.getValue());
        }
        return flightInformationArrayList;
    }

    @Override
    public List<FlightInformation> queryCleanDataWithinCurrentDaysNoMarked(int currentDay) {
        // 查询清洁信息列表
        List<Clean> currentRecord = cleanMapper.getCurrentRecord(currentDay);
        List<Integer> ids = currentRecord.stream().map(Clean::getFlightInformationId).collect(Collectors.toList());
        return queryDataWithinOneDay(ids);
    }

    @Override
    public List<FlightInformation> queryCheckInDataWithinCurrentDaysNoMarked(int currentDay) {
        // 查询值机信息记录列表
        List<CheckIn> currentRecords = checkInMapper.getCurrentRecords(currentDay);
        List<Integer> ids = currentRecords.stream().map(CheckIn::getFlightInformationId).collect(Collectors.toList());
        return queryDataWithinOneDay(ids);
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
    public FlightInformation queryRecordById(int id) {
        return flightInformationMapper.queryById(id);
    }

    @Override
    public List<FlightInformation> queryBaggageDataWithinCurrentDaysNoMarked(int currentDay) {
        return null;
    }

    @Override
    public List<FlightInformation> queryFreightDataWithinCurrentDaysNoMarked(int currentDay) {
        return null;
    }

    @Override
    public List<FlightInformation> queryIntegratedServiceDataWithinCurrentDaysNoMarked(int currentDay) {
        return null;
    }

    @Override
    public List<FlightInformation> queryStandCarDataWithinCurrentDaysNoMarked(int currentDay) {
        // 查询最近的站坪车辆信息集合
        List<StandCar> standCarList = standCarMapper.getCurrentRecords(currentDay);
        List<Integer> list = standCarList.stream().map(StandCar::getFlightInformationId).collect(Collectors.toList());
        return queryDataWithinOneDay(list);
    }

    @Autowired
    public void setFlightInformationMapper(FlightInformationMapper flightInformationMapper) {
        this.flightInformationMapper = flightInformationMapper;
    }

    @Autowired
    public void setCheckInMapper(CheckInMapper checkInMapper) {
        this.checkInMapper = checkInMapper;
    }

    @Autowired
    public void setCleanMapper(CleanMapper cleanMapper) {
        this.cleanMapper = cleanMapper;
    }

    @Autowired
    public void setBaggageMapper(BaggageMapper baggageMapper) {
        this.baggageMapper = baggageMapper;
    }

    @Autowired
    public void setFreightMapper(FreightMapper freightMapper) {
        this.freightMapper = freightMapper;
    }

    @Autowired
    public void setStandCarMapper(StandCarMapper standCarMapper) {
        this.standCarMapper = standCarMapper;
    }
}
