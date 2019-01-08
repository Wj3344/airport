package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.PassengerTag;
import cn.fanchencloud.airport.entity.SpecialFlight;
import cn.fanchencloud.airport.model.Registration;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午8:00
 * Description: 航班信息服务
 *
 * @author chen
 */
public interface RegistrationService {

    /**
     * 获取所有的重点旅客标记标签
     *
     * @return 重点旅客标记标签列表
     */
    List<PassengerTag> findAllPassengerTag();

    /**
     * 获取所有的特殊航班标记标签
     *
     * @return 所有的特殊航班标记标签列表
     */
    List<SpecialFlight> findAllSpecialFlightTags();

    /**
     * 保存上传的航班信息记录
     *
     * @param registration 航班信息记录
     * @return 保存结果
     */
    int saveRegistration(Registration registration);

}
