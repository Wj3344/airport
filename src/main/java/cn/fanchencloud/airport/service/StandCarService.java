package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.StandCar;
import cn.fanchencloud.airport.model.StandCarRecord;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-23
 * Time: 下午10:04
 * Description: 站坪车辆服务
 *
 * @author chen
 */
public interface StandCarService {
    /**
     * 添加一条站坪信息记录
     *
     * @param standCar 站坪信息
     * @return 添加结果
     */
    boolean addRecord(StandCar standCar);

    /**
     * 查询最近的记录
     *
     * @param currentDay 时间限制
     * @return 查询结果
     */
    List<StandCarRecord> getCurrentRecord(int currentDay);
}
