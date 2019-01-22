package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.Baggage;
import cn.fanchencloud.airport.mapper.BaggageMapper;
import cn.fanchencloud.airport.service.BaggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 注入行查信息数据访问层
     */
    private BaggageMapper baggageMapper;

    @Override
    public boolean insertRecord(Baggage baggage) {
        int i = baggageMapper.addRecord(baggage);
        return i != 0;
    }

    @Autowired
    public void setBaggageMapper(BaggageMapper baggageMapper) {
        this.baggageMapper = baggageMapper;
    }
}
