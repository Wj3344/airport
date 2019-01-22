package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.CheckIn;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-22
 * Time: 下午1:04
 * Description:
 *
 * @author chen
 */
public interface CheckInService {
    /**
     * 添加一条值机信息到数据库中
     * @param checkIn 值机信息记录
     * @return 添加结果
     */
    boolean insertRecord(CheckIn checkIn);
}
