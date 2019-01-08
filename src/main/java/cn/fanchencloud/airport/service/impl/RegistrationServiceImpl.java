package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.PassengerTag;
import cn.fanchencloud.airport.entity.SpecialFlight;
import cn.fanchencloud.airport.mapper.PassengerTagMapper;
import cn.fanchencloud.airport.mapper.SpecialFlightMapper;
import cn.fanchencloud.airport.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private PassengerTagMapper passengerTagMapper;

    private SpecialFlightMapper specialFlightMapper;

    @Override
    public List<PassengerTag> findAllPassengerTag() {
        return passengerTagMapper.findAll();
    }

    @Override
    public List<SpecialFlight> findAllSpecialFlightTags() {
        return specialFlightMapper.findAll();
    }

    @Autowired
    public void setPassengerTagMapper(PassengerTagMapper passengerTagMapper) {
        this.passengerTagMapper = passengerTagMapper;
    }


    @Autowired
    public void setSpecialFlightMapper(SpecialFlightMapper specialFlightMapper) {
        this.specialFlightMapper = specialFlightMapper;
    }
}
