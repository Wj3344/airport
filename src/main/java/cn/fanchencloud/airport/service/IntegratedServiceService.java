package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.IntegratedService;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-23
 * Time: 下午6:03
 * Description: 综合服务服务
 *
 * @author chen
 */
public interface IntegratedServiceService {
    /**
     * 添加一条综合服务信息记录
     *
     * @param integratedService 综合服务信息记录
     * @return 添加结果
     */
    boolean insertRecord(IntegratedService integratedService);
}
