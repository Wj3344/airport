package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.StandCar;
import cn.fanchencloud.airport.mapper.StandCarMapper;
import cn.fanchencloud.airport.service.StandCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 注入站坪信息数据持久层
     */
    private StandCarMapper standCarMapper;

    @Override
    public boolean addRecord(StandCar standCar) {
        int i = standCarMapper.addRecord(standCar);
        return i != 0;
    }

    @Autowired
    public void setStandCarMapper(StandCarMapper standCarMapper) {
        this.standCarMapper = standCarMapper;
    }
}
