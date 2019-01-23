package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.entity.IntegratedService;
import cn.fanchencloud.airport.service.FlightInformationService;
import cn.fanchencloud.airport.service.IntegratedServiceService;
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
 * Time: 下午6:23
 * Description: 综合服务控制器
 *
 * @author chen
 */
@Controller
@RequestMapping(value = "/integratedService")
public class IntegratedServiceController {
    /**
     * 注入航班信息服务层
     */
    private FlightInformationService flightInformationService;

    /**
     * 注入综合信息服务
     */
    private IntegratedServiceService integratedServiceService;


    /**
     * 请求跳转到进行添加综合服务信息
     *
     * @param model 模型
     * @return 页面跳转
     */
    @GetMapping(value = "/add")
    public String addIntegratedService(Model model) {
        List<FlightInformation> flightInformationList = flightInformationService.queryDataWithinOneDay();
        model.addAttribute("flightInformationList", flightInformationList);
        return "integratedService";
    }


    /**
     * 请求添加一条综合服务信息记录
     *
     * @param flightInformationId 航班信息记录id
     * @param boardingTime        登机时间
     * @param readyTime           客齐时间
     * @param closeTime           关闭货仓时间
     * @param specialCase         特殊情况说明
     * @return 添加结果
     */
    @ResponseBody
    @PostMapping(value = "/add")
    public String addIntegratedService(int flightInformationId, String boardingTime, String readyTime, String closeTime, String specialCase) {
        // 封装数据对象
        IntegratedService integratedService = new IntegratedService();
        integratedService.setFlightInformationId(flightInformationId);
        integratedService.setBoardingTime(new Date(Long.parseLong(boardingTime)));
        integratedService.setReadyTime(new Date(Long.parseLong(readyTime)));
        integratedService.setCloseTime(new Date(Long.parseLong(closeTime)));
        integratedService.setSpecialCase(specialCase);
        // 将数据交给服务层处理
        boolean record = integratedServiceService.insertRecord(integratedService);
        if (record) {
            return "添加综合服务信息成功";
        } else {
            return "添加综合服务信息失败";
        }
    }

    @Autowired
    public void setFlightInformationService(FlightInformationService flightInformationService) {
        this.flightInformationService = flightInformationService;
    }

    @Autowired
    public void setIntegratedServiceService(IntegratedServiceService integratedServiceService) {
        this.integratedServiceService = integratedServiceService;
    }
}
