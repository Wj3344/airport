package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.Clean;
import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.mapper.CleanMapper;
import cn.fanchencloud.airport.model.CleanRecord;
import cn.fanchencloud.airport.service.CleanService;
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
 * Date: 19-1-22
 * Time: 下午9:05
 * Description: 清洁信息服务层实现
 *
 * @author chen
 */
@Service
public class CleanServiceImpl implements CleanService {
    /**
     * 日志记录器
     */
    private static Logger logger = LoggerFactory.getLogger(CleanService.class);

    /**
     * 注入清洁信息数据访问层
     */
    private CleanMapper cleanMapper;

    /**
     * 航班信息服务层
     */
    private FlightInformationService flightInformationService;

    @Override
    public boolean addRecord(Clean clean) {
        int addRecord = cleanMapper.addRecord(clean);
        return addRecord != 0;
    }

    @Override
    public List<CleanRecord> getCurrentRecord(int currentDay) {
        List<Clean> cleans = cleanMapper.getCurrentRecord(currentDay);
        List<Integer> ids = cleans.stream().map(Clean::getFlightInformationId).collect(Collectors.toList());
        Map<Integer, String> map = flightInformationService.queryFlightNumberWithId(ids);
        List<CleanRecord> cleanRecordList = new ArrayList<>(cleans.size());
        for (Clean clean : cleans) {
            CleanRecord cleanRecord = new CleanRecord();
            try {
                BeanUtils.fatherToChild(clean, cleanRecord);
            } catch (Exception e) {
                logger.error("类型转换失败！getCurrentRecord");
                e.printStackTrace();
            }
            cleanRecord.setFlightNumber(map.get(clean.getFlightInformationId()));
            cleanRecordList.add(cleanRecord);
        }
        return cleanRecordList;
    }

    @Override
    public CleanRecord getCleanRecordById(int id) {
        Clean clean = cleanMapper.getRecordById(id);
        FlightInformation flightInformation = flightInformationService.queryRecordById(clean.getFlightInformationId());
        CleanRecord cleanRecord = new CleanRecord();
        try {
            BeanUtils.fatherToChild(clean, cleanRecord);
        } catch (Exception e) {
            logger.error("类型转换失败！方法：getCleanRecordById");
            e.printStackTrace();
        }
        cleanRecord.setFlightNumber(flightInformation.getFlightNumber());
        return cleanRecord;
    }

    @Override
    public boolean updateRecord(Clean clean) {
        return cleanMapper.updateRecord(clean) != 0;
    }

    @Autowired
    public void setFlightInformationService(FlightInformationService flightInformationService) {
        this.flightInformationService = flightInformationService;
    }

    @Autowired
    public void setCleanMapper(CleanMapper cleanMapper) {
        this.cleanMapper = cleanMapper;
    }
}
