package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.Freight;
import cn.fanchencloud.airport.model.FreightRecord;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-22
 * Time: 下午10:56
 * Description: 货运信息服务
 *
 * @author chen
 */
public interface FreightService {

    /**
     * 添加一条货运信息
     *
     * @param freight 货运信息
     * @return 添加结果
     */
    boolean addRecord(Freight freight);


    /**
     * 更新一条货运信息
     *
     * @param freight 货运信息
     * @return 更新结果
     */
    boolean updateRecord(Freight freight);

    /**
     * 删除一条货运信息
     *
     * @param id 货运信息id
     * @return 添加结果
     */
    boolean deleteRecord(int id);

    /**
     * 查询最近的货运信息记录
     *
     * @param currentDays 时间限制
     * @return 查询结果
     */
    List<FreightRecord> getCurrentRecords(int currentDays);

    /**
     * 根据id 查询货运信息记录
     *
     * @param id 记录id
     * @return 查询结果
     */
    FreightRecord getCheckInRecordById(int id);
}
