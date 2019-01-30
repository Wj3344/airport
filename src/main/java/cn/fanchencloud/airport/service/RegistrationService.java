package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.FlightInformation;
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
    boolean saveRegistration(Registration registration);

    /**
     * 获取所以的次日航班信息数据
     *
     * @return 数据列表
     */
    List<FlightInformation> getAllRegistration();

    /**
     * 获取次日航班登记信息
     *
     * @param id 航班记录id
     * @return 航班登记信息
     */
    Registration getRegistrationById(int id);

    /**
     * 将一条次日航班修改记录进行处理
     *
     * @param registrationModify 次日航班修改后的数据
     * @return 处理结果
     */
    boolean updateRegistration(Registration registrationModify);
}
