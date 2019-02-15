package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.entity.IntegratedService;
import cn.fanchencloud.airport.mapper.IntegratedServiceMapper;
import cn.fanchencloud.airport.model.IntegratedServiceRecord;
import cn.fanchencloud.airport.service.FlightInformationService;
import cn.fanchencloud.airport.service.IntegratedServiceService;
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
 * Time: 下午6:10
 * Description: 综合服务服务层实现
 *
 * @author chen
 */
@Service
public class IntegratedServiceServiceImpl implements IntegratedServiceService {

    /**
     * 日志记录
     */
    private static Logger logger = LoggerFactory.getLogger(IntegratedServiceService.class);

    /**
     * 注入综合服务数据持久层
     */
    private IntegratedServiceMapper integratedServiceMapper;

    /**
     * 注入航班信息服务
     */
    private FlightInformationService flightInformationService;

    @Override
    public boolean insertRecord(IntegratedService integratedService) {
        return integratedServiceMapper.addIntegratedService(integratedService) != 0;
    }

    @Override
    public List<IntegratedServiceRecord> getCurrentRecord(int currentDay) {
        List<IntegratedService> integratedServiceList = integratedServiceMapper.getCurrentRecord(currentDay);
        List<Integer> ids = integratedServiceList.stream().map(IntegratedService::getFlightInformationId).collect(Collectors.toList());
        Map<Integer, String> map = flightInformationService.queryFlightNumberWithId(ids);
        List<IntegratedServiceRecord> integratedServiceRecords = new ArrayList<>(integratedServiceList.size());
        for (IntegratedService integratedService : integratedServiceList) {
            IntegratedServiceRecord integratedServiceRecord = new IntegratedServiceRecord();
            try {
                BeanUtils.fatherToChild(integratedService, integratedServiceRecord);
            } catch (Exception e) {
                logger.error("类型转换失败！IntegratedServiceService.getCurrentRecord");
                e.printStackTrace();
            }
            integratedServiceRecord.setFlightNumber(map.get(integratedService.getFlightInformationId()));
            integratedServiceRecords.add(integratedServiceRecord);
        }
        return integratedServiceRecords;
    }

    @Override
    public IntegratedServiceRecord getRecordById(int id) {
        IntegratedService integratedService = integratedServiceMapper.queryById(id);
        FlightInformation flightInformation = flightInformationService.queryRecordById(integratedService.getFlightInformationId());
        IntegratedServiceRecord integratedServiceRecord = new IntegratedServiceRecord();
        try {
            BeanUtils.fatherToChild(integratedService, integratedServiceRecord);
        } catch (Exception e) {
            logger.error("类型转换失败！IntegratedServiceService.getRecordById");
            e.printStackTrace();
        }
        integratedServiceRecord.setFlightNumber(flightInformation.getFlightNumber());
        return integratedServiceRecord;
    }

    @Override
    public boolean updateRecord(IntegratedService integratedService) {
        return integratedServiceMapper.update(integratedService) != 0;
    }

    @Autowired
    public void setFlightInformationService(FlightInformationService flightInformationService) {
        this.flightInformationService = flightInformationService;
    }

    @Autowired
    public void setIntegratedServiceMapper(IntegratedServiceMapper integratedServiceMapper) {
        this.integratedServiceMapper = integratedServiceMapper;
    }
}