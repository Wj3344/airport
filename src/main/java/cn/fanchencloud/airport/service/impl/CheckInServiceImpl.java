package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.CheckIn;
import cn.fanchencloud.airport.mapper.CheckInMapper;
import cn.fanchencloud.airport.model.CheckInRecord;
import cn.fanchencloud.airport.service.CheckInService;
import cn.fanchencloud.airport.service.FlightInformationService;
import cn.fanchencloud.airport.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-22
 * Time: 下午1:06
 * Description:
 *
 * @author chen
 */
@Service
public class CheckInServiceImpl implements CheckInService {

    /**
     * 日志记录
     */
    private static Logger logger = LoggerFactory.getLogger(CheckInService.class);

    /**
     * 注入值机信息数据访问层
     */
    private CheckInMapper checkInMapper;

    /**
     * 注入航班信息服务
     */
    private FlightInformationService flightInformationService;

    @Override
    public boolean insertRecord(CheckIn checkIn) {
        int i = checkInMapper.addRecord(checkIn);
        return i != 0;
    }

    @Override
    public List<CheckInRecord> getCurrentRecords(int currentDays) {
        // 查询值机信息
        List<CheckIn> checkInList = checkInMapper.getCurrentRecords(currentDays);
        List<Integer> ids = new ArrayList<>(checkInList.size());
        for (CheckIn c : checkInList) {
            ids.add(c.getFlightInformationId());
        }
        Map<Integer, String> map = flightInformationService.queryFlightNumberWithId(ids);
        List<CheckInRecord> checkInRecordList = new ArrayList<>(checkInList.size());
        for (CheckIn c : checkInList) {
            CheckInRecord checkInRecord = new CheckInRecord();
            try {
                BeanUtils.fatherToChild(c, checkInRecord);
            } catch (Exception e) {
                logger.error("类型转换失败！值机信息实体转换值机信息记录");
                e.printStackTrace();
            }
            checkInRecord.setFlightNumber(map.get(c.getFlightInformationId()));
            checkInRecordList.add(checkInRecord);
        }
        return checkInRecordList;
    }

    @Override
    public CheckInRecord getCheckInRecordById(int id) {
        return null;
    }

    @Autowired
    public void setCheckInMapper(CheckInMapper checkInMapper) {
        this.checkInMapper = checkInMapper;
    }

    @Autowired
    public void setFlightInformationService(FlightInformationService flightInformationService) {
        this.flightInformationService = flightInformationService;
    }
}
