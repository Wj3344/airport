package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.CheckIn;
import cn.fanchencloud.airport.mapper.CheckInMapper;
import cn.fanchencloud.airport.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 注入值机信息数据访问层
     */
    private CheckInMapper checkInMapper;

    @Override
    public boolean insertRecord(CheckIn checkIn) {
        int i = checkInMapper.addRecord(checkIn);
        return i != 0;
    }

    @Autowired
    public void setCheckInMapper(CheckInMapper checkInMapper) {
        this.checkInMapper = checkInMapper;
    }
}
