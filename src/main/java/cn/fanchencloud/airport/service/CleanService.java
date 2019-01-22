package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.Clean;

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


}
