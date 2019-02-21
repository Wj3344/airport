package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.Identity;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 2019/2/21
 * Time: 15:50
 * Description: 账户等级服务
 *
 * @author chen
 */
public interface IdentityService {
    /**
     * 获取所有的身份等级列表
     *
     * @return 账户等级列表
     */
    List<Identity> getIdentityList();
}
