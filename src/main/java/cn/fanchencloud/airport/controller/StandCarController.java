package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.entity.StandCar;
import cn.fanchencloud.airport.model.JsonResponse;
import cn.fanchencloud.airport.service.FlightInformationService;
import cn.fanchencloud.airport.service.StandCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-23
 * Time: 下午10:10
 * Description:
 *
 * @author chen
 */
@Controller
@RequestMapping(value = "/standCar")
public class StandCarController {

    /**
     * 注入航班信息服务层
     */
    private FlightInformationService flightInformationService;

    /**
     * 注入站坪信息服务
     */
    private StandCarService standCarService;

    /**
     * 请求跳转到添加站坪信息页面
     *
     * @param model 模型
     * @return 页面跳转
     */
    @GetMapping(value = "/add")
    public String addStandCar(Model model) {
        List<FlightInformation> flightInformationList = flightInformationService.queryStandCarDataWithinCurrentDaysNoMarked(7);
        model.addAttribute("flightInformationList", flightInformationList);
        return "standCar";
    }

    /**
     * 请求添加一条站坪信息
     *
     * @param standCar 站坪信息
     * @return 添加结果
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public JsonResponse addStandCar(StandCar standCar) {
        // 将数据交给服务层处理
        boolean b = standCarService.addRecord(standCar);
        if (b) {
            return JsonResponse.ok();
        } else {
            return JsonResponse.errorMsg("添加失败");
        }
    }

    @Autowired
    public void setFlightInformationService(FlightInformationService flightInformationService) {
        this.flightInformationService = flightInformationService;
    }

    @Autowired
    public void setStandCarService(StandCarService standCarService) {
        this.standCarService = standCarService;
    }
}
