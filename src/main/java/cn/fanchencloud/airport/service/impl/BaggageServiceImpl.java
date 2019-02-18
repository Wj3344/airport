package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.Baggage;
import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.mapper.BaggageMapper;
import cn.fanchencloud.airport.model.BaggageRecord;
import cn.fanchencloud.airport.service.BaggageService;
import cn.fanchencloud.airport.service.FlightInformationService;
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
 * Date: 19-1-21
 * Time: 下午4:12
 * Description:
 *
 * @author chen
 */
@Service
public class BaggageServiceImpl implements BaggageService {

    private static Logger logger = LoggerFactory.getLogger(BaggageService.class);

    /**
     * 注入行查信息数据访问层
     */
    private BaggageMapper baggageMapper;

    /**
     * 注入航班信息服务层
     */
    private FlightInformationService flightInformationService;

    @Override
    public boolean insertRecord(Baggage baggage) {
        int i = baggageMapper.addRecord(baggage);
        return i != 0;
    }

    @Override
    public List<BaggageRecord> getCurrentRecords(int currentDays) {
        List<Baggage> currentRecord = baggageMapper.getCurrentRecord(currentDays);
        List<Integer> ids = currentRecord.stream().map(Baggage::getFlightInformationId).collect(Collectors.toList());
        Map<Integer, String> map = flightInformationService.queryFlightNumberWithId(ids);
        List<BaggageRecord> baggageRecordList = new ArrayList<>(currentRecord.size());
        for (Baggage baggage : currentRecord) {
            BaggageRecord baggageRecord = new BaggageRecord();
            try {
                BeanUtils.fatherToChild(baggage, baggageRecord);
            } catch (Exception e) {
                logger.error("类型转换失败！行查信息实体转换行查信息记录");
                e.printStackTrace();
            }
            baggageRecord.setFlightNumber(map.get(baggage.getFlightInformationId()));
            baggageRecordList.add(baggageRecord);
        }
        return baggageRecordList;
    }

    @Override
    public BaggageRecord getCheckInRecordById(int id) {
        Baggage baggage = baggageMapper.getRecordById(id);
        BaggageRecord baggageRecord = new BaggageRecord();
        try {
            BeanUtils.fatherToChild(baggage, baggageRecord);
        } catch (Exception e) {
            logger.error("类型转换出错，getCheckInRecordById");
            e.printStackTrace();
        }
        FlightInformation flightInformation = flightInformationService.queryRecordById(baggage.getFlightInformationId());
        baggageRecord.setFlightNumber(flightInformation.getFlightNumber());
        return baggageRecord;
    }

    @Override
    public boolean updateRecord(Baggage baggage) {
        return baggageMapper.update(baggage) != 0;
    }

    @Autowired
    public void setBaggageMapper(BaggageMapper baggageMapper) {
        this.baggageMapper = baggageMapper;
    }

    @Autowired
    public void setFlightInformationService(FlightInformationService flightInformationService) {
        this.flightInformationService = flightInformationService;
    }
}
