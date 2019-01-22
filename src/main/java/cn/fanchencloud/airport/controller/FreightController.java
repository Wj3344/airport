package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.entity.Freight;
import cn.fanchencloud.airport.service.FlightInformationService;
import cn.fanchencloud.airport.service.FreightService;
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
 * Date: 19-1-22
 * Time: 下午9:28
 * Description: 货运信息控制器
 *
 * @author chen
 */
@Controller
@RequestMapping(value = "/freight")
public class FreightController {

    /**
     * 注入航班信息服务
     */
    private FlightInformationService flightInformationService;

    /**
     * 注入货运信息服务
     */
    private FreightService freightService;

    /**
     * 请求跳转到货运信息服务添加页面
     *
     * @param model 模型
     * @return 页面跳转
     */
    @GetMapping(value = "/add")
    public String addFreight(Model model) {
        List<FlightInformation> flightInformationList = flightInformationService.queryDataWithinOneDay();
        model.addAttribute("flightInformationList", flightInformationList);
        return "freight";
    }

    /**
     * 请求添加货运信息
     *
     * @return 添加结果返回
     */
    @ResponseBody
    @PostMapping(value = "/add")
    public String addFreight(int flightInformationId, String closeTime, String specialCase) {
        Freight freight = new Freight();
        freight.setFlightInformationId(flightInformationId);
        freight.setCloseTime(new Date(Long.parseLong(closeTime)));
        freight.setSpecialCase(specialCase);
        // 将上传的数据持久化到数据库
        boolean result = freightService.addRecord(freight);
        if (result) {
            // 添加成功
            return "OK，添加货运信息成功";
        } else {
            return "ERROR，添加货运信息失败";
        }
    }

    @Autowired
    public void setFlightInformationService(FlightInformationService flightInformationService) {
        this.flightInformationService = flightInformationService;
    }

    @Autowired
    public void setFreightService(FreightService freightService) {
        this.freightService = freightService;
    }
}
