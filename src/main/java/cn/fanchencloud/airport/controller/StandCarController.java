package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.entity.StandCar;
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
        List<FlightInformation> flightInformationList = flightInformationService.queryDataWithinOneDay();
        model.addAttribute("flightInformationList", flightInformationList);
        return "standCar";
    }

    /**
     * 请求添加一条站坪信息
     *
     * @param flightInformationId 航班信息记录id
     * @param vipTime             vip车辆到位时间时间
     * @param cartTime            推车到位时间
     * @param specialCase         特殊情况说明
     * @return 添加结果
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public String addStandCar(int flightInformationId, String vipTime, String cartTime, String specialCase) {
        StandCar standCar = new StandCar();
        standCar.setFlightInformationId(flightInformationId);
        standCar.setVipTime(new Date(Long.parseLong(vipTime)));
        standCar.setCartTime(new Date(Long.parseLong(cartTime)));
        standCar.setSpecialCase(specialCase);
        // 将数据交给服务层处理
        boolean b = standCarService.addRecord(standCar);
        if (b) {
            return "添加成功";
        } else {
            return "添加失败";
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
