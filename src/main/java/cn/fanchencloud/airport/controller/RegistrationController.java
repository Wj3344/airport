package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.entity.PassengerTag;
import cn.fanchencloud.airport.entity.SpecialFlight;
import cn.fanchencloud.airport.model.Registration;
import cn.fanchencloud.airport.model.ResponseWrapper;
import cn.fanchencloud.airport.service.RegistrationService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    public ResponseWrapper addRegistration(String registration) {
        logger.info("接收到的航班添加信息：" + registration);
        Registration parseObject = objectJsonToRegistration(registration);
        if (parseObject == null) {
            return ResponseWrapper.markCustom(false, 400, "添加数据不能为空！", null);
        }
        logger.info("转换成Java对象： " + parseObject.toString());
        boolean updateRegistration = registrationService.updateRegistration(parseObject);
        if (updateRegistration) {
            // 添加成功
            return ResponseWrapper.markCustom(true, 200, "修改记录成功", null);
        } else {
            // 保存记录失败
            return ResponseWrapper.markCustom(false, 500, "修改记录失败", null);
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

    /**
     * 请求到查询页面
     *
     * @return 页面返回
     */
    @GetMapping(value = "/search")
    public String getSearchPage(Model model) {
        // 查询次日航班信息
        List<FlightInformation> flightInformationList = registrationService.getAllRegistration();
        model.addAttribute("flightInformationList", flightInformationList);
        return "registrationSearch";
    }

    /**
     * 请求到修改次日航班的页面
     *
     * @param id    航班记录id
     * @param model 模型
     * @return 页面
     */
    @GetMapping(value = "/modify/{id}")
    public String getModifyPage(@PathVariable("id") int id, Model model) {
        // 查询该条信息
        Registration registration = registrationService.getRegistrationById(id);
        model.addAttribute("registration", registration);
        // 获取所有的重点旅客标签
        List<PassengerTag> passengerTags = registrationService.findAllPassengerTag();
        // 获取所有的特殊航班标签
        List<SpecialFlight> specialFlightTags = registrationService.findAllSpecialFlightTags();
        model.addAttribute("passengerTags", passengerTags);
        model.addAttribute("specialFlightTags", specialFlightTags);
        return "registrationModify";
    }

    /**
     * 请求提交处理一条修改的次日航班信息记录
     *
     * @param dataMessage 修改的次日航班信息记录
     * @return 处理结果
     */
    @PostMapping("/modify")
    public ResponseWrapper modifyRegistration(String dataMessage) {
        System.out.println(dataMessage);
        logger.info("接收到的修改后的航班信息：" + dataMessage);
        Registration registrationModify = objectJsonToRegistration(dataMessage);
        if (registrationModify == null) {
            return ResponseWrapper.markCustom(false, 400, "添加数据不能为空！", null);
        }
        // 将数据提交给服务层处理
        boolean modify = registrationService.updateRegistration(registrationModify);
        return null;
    }

    private Registration objectJsonToRegistration(String jsonObject) {
        if (StringUtils.isNotBlank(jsonObject)) {
            // 将数据转换成对象
            return JSON.parseObject(jsonObject, Registration.class);
        } else {
            return null;
        }
    }

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
}

