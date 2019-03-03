package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.entity.PassengerTag;
import cn.fanchencloud.airport.entity.SpecialFlight;
import cn.fanchencloud.airport.exception.MyAddException;
import cn.fanchencloud.airport.mapper.*;
import cn.fanchencloud.airport.model.Registration;
import cn.fanchencloud.airport.service.RegistrationService;
import cn.fanchencloud.airport.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午8:05
 * Description:
 *
 * @author chen
 */
@Service(value = "registrationService")
public class RegistrationServiceImpl implements RegistrationService {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    /**
     * 重点旅客标记映射
     */
    private PassengerTagMapper passengerTagMapper;

    /**
     * 特殊航班映射
     */
    private SpecialFlightMapper specialFlightMapper;

    /**
     * 航班信息映射
     */
    private FlightInformationMapper flightInformationMapper;

    /**
     * 航班与重点旅客主键的关联
     */
    private FlightInformationPassengerTagMapper flightInformationPassengerTagMapper;

    /**
     * 航班记录与特殊航班之间的关联
     */
    private FlightInformationSpecialFlightMapper flightInformationSpecialFlightMapper;

    @Override
    public List<PassengerTag> findAllPassengerTag() {
        return passengerTagMapper.findAll();
    }

    @Override
    public Map<Integer, PassengerTag> findAllPassengerTagMap() {
        return passengerTagMapper.findAllMap();
    }

    @Override
    public List<SpecialFlight> findAllSpecialFlightTags() {
        return specialFlightMapper.findAll();
    }

    @Override
    public Map<Integer, SpecialFlight> findAllSpecialFlightTagMap() {
        return specialFlightMapper.findAllMap();
    }

    /**
     * 加入事务控制
     *
     * @param registration 航班信息记录
     * @return 1——添加成功
     */
    @Override
    @Transactional(rollbackFor = MyAddException.class)
    public boolean saveRegistration(Registration registration) {
        // 添加航班记录
        FlightInformation flightInformation = new FlightInformation(registration);
        int i = flightInformationMapper.addOne(flightInformation);
        if (i == 0) {
            // 添加航班记录失败，抛出异常
            throw new MyAddException("添加航班信息失败：" + registration.toString());
        }
        // 查询添加的记录
        int flightId = flightInformationMapper.getLastId();
        // 添加两个标签的记录
        logger.info("添加航班记录的特殊旅客标记 " + registration.getPassengerTags().toString());
        i = flightInformationPassengerTagMapper.addMany(flightId, registration.getPassengerTags());
        if (i == 0) {
            // 添加航班记录的特殊旅客标记失败，抛出异常
            throw new MyAddException("添加航班记录的特殊旅客标记失败！");
        }
        logger.info("添加航班记录的特殊航班标记" + registration.getSpecialTags().toString());
        i = flightInformationSpecialFlightMapper.addMany(flightId, registration.getSpecialTags());
        if (i == 0) {
            // 添加航班记录的特殊航班标记失败，抛出异常
            throw new MyAddException("添加航班记录的特殊航班标记失败！");
        }
        return true;
    }

    @Override
    public List<FlightInformation> getAllRegistration() {
        return flightInformationMapper.getAll();
    }

    @Override
    public Registration getRegistrationById(int id) {
        // 通过id查询航班信息
        FlightInformation flightInformation = flightInformationMapper.queryById(id);
        // 通过航班记录id查询特殊航班标签列表
        List<Integer> specialFlightList = specialFlightMapper.findByFlightInformationId(id);
        List<Integer> passengerTagList = passengerTagMapper.findByFlightInformationId(id);
        Registration registration = new Registration();
        try {
            BeanUtils.fatherToChild(flightInformation, registration);
        } catch (Exception e) {
            logger.debug("类型转换失败！" + e.getMessage());
            e.printStackTrace();
        }
        registration.setSpecialTags(specialFlightList);
        registration.setPassengerTags(passengerTagList);
        return registration;
    }

    @Override
    public boolean updateRegistration(Registration registrationModify) {
        // 修改航班记录
       return flightInformationMapper.updateRecord(registrationModify) != 0;

    }

    @Autowired
    public void setPassengerTagMapper(PassengerTagMapper passengerTagMapper) {
        this.passengerTagMapper = passengerTagMapper;
    }


    @Autowired
    public void setSpecialFlightMapper(SpecialFlightMapper specialFlightMapper) {
        this.specialFlightMapper = specialFlightMapper;
    }

    @Autowired
    public void setFlightInformationPassengerTagMapper(FlightInformationPassengerTagMapper flightInformationPassengerTagMapper) {
        this.flightInformationPassengerTagMapper = flightInformationPassengerTagMapper;
    }

    @Autowired
    public void setFlightInformationSpecialFlightMapper(FlightInformationSpecialFlightMapper flightInformationSpecialFlightMapper) {
        this.flightInformationSpecialFlightMapper = flightInformationSpecialFlightMapper;
    }

    @Autowired
    public void setFlightInformationMapper(FlightInformationMapper flightInformationMapper) {
        this.flightInformationMapper = flightInformationMapper;
    }
}
