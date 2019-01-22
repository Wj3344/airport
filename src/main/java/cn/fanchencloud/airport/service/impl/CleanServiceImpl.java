package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.Clean;
import cn.fanchencloud.airport.mapper.CleanMapper;
import cn.fanchencloud.airport.service.CleanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-22
 * Time: 下午9:05
 * Description:
 *
 * @author chen
 */
@Service
public class CleanServiceImpl implements CleanService {

    /**
     * 注入清洁信息数据访问层
     */
    private CleanMapper cleanMapper;

    @Override
    public boolean addRecord(Clean clean) {
        int addRecord = cleanMapper.addRecord(clean);
        return addRecord != 0;
    }

    @Autowired
    public void setCleanMapper(CleanMapper cleanMapper) {
        this.cleanMapper = cleanMapper;
    }
}
