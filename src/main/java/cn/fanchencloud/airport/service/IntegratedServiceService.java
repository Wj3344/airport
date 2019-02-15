package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.IntegratedService;
import cn.fanchencloud.airport.model.IntegratedServiceRecord;

import java.util.List;

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

    /**
     * 查询最近的综合服务的记录
     *
     * @param currentDay 时间限制
     * @return 查询结果
     */
    List<IntegratedServiceRecord> getCurrentRecord(int currentDay);

    /**
     * 通过id查询综合服务信息
     *
     * @param id 记录id
     * @return 查询结果
     */
    IntegratedServiceRecord getRecordById(int id);

    /**
     * 更新一条记录到数据库
     *
     * @param integratedService 新的记录
     * @return 更新结果
     */
    boolean updateRecord(IntegratedService integratedService);
}
