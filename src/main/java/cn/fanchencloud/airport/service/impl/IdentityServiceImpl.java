package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.Identity;
import cn.fanchencloud.airport.mapper.IdentityMapper;
import cn.fanchencloud.airport.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 2019/2 /21
 * Time: 16:13
 * Description:
 *
 * @author chen
 */
@Service
public class IdentityServiceImpl implements IdentityService {

    /**
     * 注入账户等级持久层
     */
    private IdentityMapper identityMapper;

    @Override
    public List<Identity> getIdentityList() {
        List<Identity> allList = identityMapper.getAllList();
        allList.removeIf(identity -> identity.getId() == 0);
        return allList;
    }

    @Autowired
    public void setIdentityMapper(IdentityMapper identityMapper) {
        this.identityMapper = identityMapper;
    }
}
