package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.entity.Freight;
import cn.fanchencloud.airport.mapper.FreightMapper;
import cn.fanchencloud.airport.model.FreightRecord;
import cn.fanchencloud.airport.service.FlightInformationService;
import cn.fanchencloud.airport.service.FreightService;
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
 * Date: 19-1-22
 * Time: 下午11:02
 * Description:
 *
 * @author chen
 */
@Service
public class FreightServiceImpl implements FreightService {

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(FreightService.class);

    /**
     * 注入货运信息数据持久层
     */
    private FreightMapper freightMapper;

    /**
     * 注入航班信息服务
     */
    private FlightInformationService flightInformationService;

    @Override
    public boolean addRecord(Freight freight) {
        int addRecord = freightMapper.addRecord(freight);
        return addRecord != 0;
    }

    @Override
    public boolean updateRecord(Freight freight) {
        return freightMapper.update(freight) != 0;
    }

    @Override
    public boolean deleteRecord(int id) {
        return false;
    }

    @Override
    public List<FreightRecord> getCurrentRecords(int currentDays) {
        List<Freight> freights = freightMapper.getCurrentRecord(currentDays);
        List<Integer> ids = freights.stream().map(Freight::getFlightInformationId).collect(Collectors.toList());
        Map<Integer, String> map = flightInformationService.queryFlightNumberWithId(ids);
        List<FreightRecord> freightRecordList = new ArrayList<>(freights.size());
        for (Freight freight : freights) {
            FreightRecord freightRecord = new FreightRecord();
            try {
                BeanUtils.fatherToChild(freight, freightRecord);
            } catch (Exception e) {
                logger.error("类型转换失败！FreightServiceImpl货运信息实体转换货运信息记录");
                e.printStackTrace();
            }
            freightRecord.setFlightNumber(map.get(freight.getFlightInformationId()));
            freightRecordList.add(freightRecord);
        }
        return freightRecordList;
    }

    @Override
    public FreightRecord getCheckInRecordById(int id) {
        Freight freight = freightMapper.getRecordById(id);
        FreightRecord freightRecord = new FreightRecord();
        try {
            BeanUtils.fatherToChild(freight, freightRecord);
        } catch (Exception e) {
            logger.error("类型转换出错，FreightServiceImpl.getCheckInRecordById");
            e.printStackTrace();
        }
        FlightInformation flightInformation = flightInformationService.queryRecordById(freight.getFlightInformationId());
        freightRecord.setFlightNumber(flightInformation.getFlightNumber());
        return freightRecord;
    }

    @Autowired
    public void setFlightInformationService(FlightInformationService flightInformationService) {
        this.flightInformationService = flightInformationService;
    }

    @Autowired
    public void setFreightMapper(FreightMapper freightMapper) {
        this.freightMapper = freightMapper;
    }
}
