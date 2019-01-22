package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.Freight;
import cn.fanchencloud.airport.mapper.FreightMapper;
import cn.fanchencloud.airport.service.FreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 注入货运信息数据持久层
     */
    private FreightMapper freightMapper;

    @Override
    public boolean addRecord(Freight freight) {
        int addRecord = freightMapper.addRecord(freight);
        return addRecord != 0;
    }

    @Override
    public boolean updateRecord(Freight freight) {
        return false;
    }

    @Override
    public boolean deleteRecord(int id) {
        return false;
    }

    @Autowired
    public void setFreightMapper(FreightMapper freightMapper) {
        this.freightMapper = freightMapper;
    }
}
