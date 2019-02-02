package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.CheckIn;
import cn.fanchencloud.airport.model.CheckInRecord;

import java.util.List;

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
     *
     * @param checkIn 值机信息记录
     * @return 添加结果
     */
    boolean insertRecord(CheckIn checkIn);

    /**
     * 获取最近currenrDays的值机信息记录
     *
     * @param currentDays 时间限制
     * @return 查询列表
     */
    List<CheckInRecord> getCurrentRecords(int currentDays);

    /**
     * 根据id查询值机信息记录
     *
     * @param id 值机信息id
     * @return 查询结果
     */
    CheckInRecord getCheckInRecordById(int id);
}
