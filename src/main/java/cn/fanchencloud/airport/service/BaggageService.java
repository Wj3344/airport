package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.Baggage;

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
}
