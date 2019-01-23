package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.IntegratedService;
import cn.fanchencloud.airport.mapper.IntegratedServiceMapper;
import cn.fanchencloud.airport.service.IntegratedServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 注入综合服务数据持久层
     */
    private IntegratedServiceMapper integratedServiceMapper;

    @Override
    public boolean insertRecord(IntegratedService integratedService) {
        return integratedServiceMapper.addIntegratedService(integratedService) != 0;
    }

    @Autowired
    public void setIntegratedServiceMapper(IntegratedServiceMapper integratedServiceMapper) {
        this.integratedServiceMapper = integratedServiceMapper;
    }
}