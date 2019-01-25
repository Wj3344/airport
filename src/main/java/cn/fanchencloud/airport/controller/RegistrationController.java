package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.PassengerTag;
import cn.fanchencloud.airport.entity.SpecialFlight;
import cn.fanchencloud.airport.model.Registration;
import cn.fanchencloud.airport.service.RegistrationService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午7:39
 * Description:
 *
 * @author chen
 */
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private RegistrationService registrationService;

    /**
     * 添加一条航班信息记录
     *
     * @param registration 航班信息
     * @return 添加结果
     */
    @ResponseBody
    @PostMapping(value = "/add")
    public String addRegistration(String registration) {
        logger.info("接收到的航班添加信息：" + registration);
        Registration parseObject = JSON.parseObject(registration, Registration.class);
        logger.info("转换成Java对象： " + parseObject.toString());
        int saveRegistration = registrationService.saveRegistration(parseObject);
        if (saveRegistration == 0) {
            // 保存记录失败
            return "添加记录失败！";
        } else {
            // 添加成功
            return registration;
        }
    }

    /**
     * 次日航班信息录入
     *
     * @return 次日航班信息录入
     */
    @GetMapping(value = "/add")
    public String registration(Model model) {
        // 获取所有的重点旅客标签
        List<PassengerTag> passengerTags = registrationService.findAllPassengerTag();
        // 获取所有的特殊航班标签
        List<SpecialFlight> specialFlightTags = registrationService.findAllSpecialFlightTags();
        model.addAttribute("passengerTags", passengerTags);
        model.addAttribute("specialFlightTags", specialFlightTags);
        return "registration";
    }

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
}
