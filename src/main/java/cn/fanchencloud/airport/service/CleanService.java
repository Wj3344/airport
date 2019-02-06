package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.Clean;
import cn.fanchencloud.airport.model.CleanRecord;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-22
 * Time: 下午9:02
 * Description: 清洁信息服务层
 *
 * @author chen
 */
public interface CleanService {
    /**
     * 添加一条清洁信息
     *
     * @param clean 清洁信息
     * @return 添加结果
     */
    boolean addRecord(Clean clean);

    /**
     * 查询最近的记录
     *
     * @param currentDay 时间限制
     * @return 查询结果
     */
    List<CleanRecord> getCurrentRecord(int currentDay);

    /**
     * 根据清洁信息记录id查询清洁信息记录
     *
     * @param id 记录id
     * @return 查询结果
     */
    CleanRecord getCleanRecordById(int id);

    /**
     * 修改一条清洁记录
     *
     * @param clean 清洁记录
     * @return 更新结果
     */
    boolean updateRecord(Clean clean);
}
