package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.entity.StandCar;
import cn.fanchencloud.airport.mapper.StandCarMapper;
import cn.fanchencloud.airport.model.StandCarRecord;
import cn.fanchencloud.airport.service.CleanService;
import cn.fanchencloud.airport.service.FlightInformationService;
import cn.fanchencloud.airport.service.StandCarService;
import cn.fanchencloud.airport.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-23
 * Time: 下午10:06
 * Description:
 *
 * @author chen
 */
@Service
public class StandCarServiceImpl implements StandCarService {

    /**
     * 日志记录器
     */
    private static Logger logger = LoggerFactory.getLogger(StandCarService.class);


    /**
     * 注入站坪信息数据持久层
     */
    private StandCarMapper standCarMapper;

    /**
     * 航班信息服务层
     */
    private FlightInformationService flightInformationService;


    @Override
    public boolean addRecord(StandCar standCar) {
        int i = standCarMapper.addRecord(standCar);
        return i != 0;
    }

    @Override
    public List<StandCarRecord> getCurrentRecord(int currentDay) {
        List<StandCar> currentRecords = standCarMapper.getCurrentRecords(currentDay);
        List<Integer> integerList = currentRecords.stream().map(StandCar::getFlightInformationId).collect(Collectors.toList());
        Map<Integer, String> map = flightInformationService.queryFlightNumberWithId(integerList);
        List<StandCarRecord> standCarRecordList = new ArrayList<>(currentRecords.size());
        for (StandCar standCar : currentRecords) {
            StandCarRecord standCarRecord = new StandCarRecord();
            try {
                BeanUtils.fatherToChild(standCar, standCarRecord);
            } catch (Exception e) {
                logger.error("类型转换失败！StandCarService.getCurrentRecord");
                e.printStackTrace();
            }
            standCarRecord.setFlightNumber(map.get(standCar.getFlightInformationId()));
            standCarRecordList.add(standCarRecord);
        }
        return standCarRecordList;
    }

    @Override
    public StandCarRecord getRecordById(int id) {
        StandCar standCar = standCarMapper.getRecordById(id);
        StandCarRecord standCarRecord = new StandCarRecord();
        try {
            BeanUtils.fatherToChild(standCar, standCarRecord);
        } catch (Exception e) {
            logger.error("类型转换失败！StandCarService.getRecordById");
            e.printStackTrace();
        }
        FlightInformation flightInformation = flightInformationService.queryRecordById(standCar.getFlightInformationId());
        standCarRecord.setFlightNumber(flightInformation.getFlightNumber());
        return standCarRecord;
    }

    @Override
    public boolean modifyRecord(StandCar standCar) {
        return standCarMapper.update(standCar) != 0;
    }

    @Autowired
    public void setStandCarMapper(StandCarMapper standCarMapper) {
        this.standCarMapper = standCarMapper;
    }

    @Autowired
    public void setFlightInformationService(FlightInformationService flightInformationService) {
        this.flightInformationService = flightInformationService;
    }
}
