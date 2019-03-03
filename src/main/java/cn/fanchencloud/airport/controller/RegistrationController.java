package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.entity.PassengerTag;
import cn.fanchencloud.airport.entity.SpecialFlight;
import cn.fanchencloud.airport.model.JsonResponse;
import cn.fanchencloud.airport.model.Registration;
import cn.fanchencloud.airport.service.RegistrationService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @RequiresPermissions("registration:add")
    @ResponseBody
    @PostMapping(value = "/add")
    public JsonResponse addRegistration(String registration) {
        logger.info("接收到的航班添加信息：" + registration);
        Registration parseObject = objectJsonToRegistration(registration);
        if (parseObject == null) {
            return JsonResponse.errorMsg("添加数据不能为空!");
        }
        logger.info("转换成Java对象： " + parseObject.toString());
        boolean updateRegistration = registrationService.saveRegistration(parseObject);
        if (updateRegistration) {
            // 添加成功
            return JsonResponse.ok();
        } else {
            // 保存记录失败
            return JsonResponse.errorMsg("添加记录失败");
        }
    }

    /**
     * 次日航班信息录入
     *
     * @return 次日航班信息录入
     */
    @RequiresPermissions("registration:add")
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
    @RequiresPermissions("registration:list")
    @GetMapping(value = "/list")
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
    @RequiresPermissions("registration:modify")
    @GetMapping(value = "/modify/{id}")
    public String getModifyPage(@PathVariable("id") int id, Model model) {
        // 查询该条信息
        Registration registration = registrationService.getRegistrationById(id);
        model.addAttribute("registration", registration);
        return "registrationModify";
    }

    @RequiresPermissions("registration:modify")
    @GetMapping(value = "/view/{id}")
    public String viewMessage(@PathVariable("id") int id, Model model) {
        // 查询该条信息
        Registration registration = registrationService.getRegistrationById(id);
        model.addAttribute("registration", registration);
        // 获取所有的重点旅客标签
        Map<Integer, PassengerTag> passengerTagMap = registrationService.findAllPassengerTagMap();
        List<PassengerTag> passengerTags = new ArrayList<>(registration.getPassengerTags().size());
        for (Integer number : registration.getPassengerTags()) {
            passengerTags.add(passengerTagMap.get(number));
        }
        // 获取所有的特殊航班标签
        Map<Integer, SpecialFlight> flightTagMap = registrationService.findAllSpecialFlightTagMap();
        List<SpecialFlight> specialFlightTags = new ArrayList<>(registration.getPassengerTags().size());
        for (Integer number : registration.getSpecialTags()) {
            specialFlightTags.add(flightTagMap.get(number));
        }
        model.addAttribute("passengerTags", passengerTags);
        model.addAttribute("specialFlightTags", specialFlightTags);
        return "registrationView";
    }

    /**
     * 请求提交处理一条修改的次日航班信息记录
     *
     * @param dataMessage 修改的次日航班信息记录
     * @return 处理结果
     */
    @RequiresPermissions("registration:modify")
    @PostMapping("/modify")
    @ResponseBody
    public JsonResponse modifyRegistration(String dataMessage) {
        logger.info("接收到的修改后的航班信息：" + dataMessage);
        Registration registrationModify = objectJsonToRegistration(dataMessage);
        if (registrationModify == null) {
            return JsonResponse.errorMsg("添加的数据不能为空");
        }
        // 将数据提交给服务层处理
        boolean modify = registrationService.updateRegistration(registrationModify);
        if (modify) {
            // 数据修改成功
            return JsonResponse.ok();
        } else {
            // 数据修改失败
            return JsonResponse.errorMsg("修改数据失败！");
        }
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

