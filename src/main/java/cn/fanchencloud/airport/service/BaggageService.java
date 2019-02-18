package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.Baggage;
import cn.fanchencloud.airport.model.BaggageRecord;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-21
 * Time: 下午4:10
 * Description: 行查信息服务类
 *
 * @author chen
 */
public interface BaggageService {

    /**
     * 添加一条行查信息记录
     *
     * @param baggage 行查信息记录
     * @return 添加结果
     */
    boolean insertRecord(Baggage baggage);

    /**
     * 查询最近的行查记录信息
     *
     * @param currentDays 时间限制
     * @return 查询结果
     */
    List<BaggageRecord> getCurrentRecords(int currentDays);

    /**
     * 根据记录id查询记录信息
     *
     * @param id id
     * @return 查询信息
     */
    BaggageRecord getCheckInRecordById(int id);

    /**
     * 修改一条行查记录
     *
     * @param baggage 新的记录
     * @return 修改结果
     */
    boolean updateRecord(Baggage baggage);
}
