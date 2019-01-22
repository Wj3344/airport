package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.Baggage;
import cn.fanchencloud.airport.entity.Clean;
import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.service.BaggageService;
import cn.fanchencloud.airport.service.CleanService;
import cn.fanchencloud.airport.service.FlightInformationService;
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
 * Date: 19-1-21
 * Time: 上午11:12
 * Description: 清洁信息控制器
 *
 * @author chen
 */
@Controller
@RequestMapping(value = "/clean")
public class CleanController {

    /**
     * 注入航班信息服务层
     */
    private FlightInformationService flightInformationService;

    /**
     * 注入清洁信息服务
     */
    private CleanService cleanService;

    /**
     * 请求到清洁信息录入界面
     *
     * @param model 模型
     * @return 跳转地址
     */
    @GetMapping(value = "/add")
    public String addClean(Model model) {
        List<FlightInformation> flightInformationList = flightInformationService.queryDataWithinOneDay();
        model.addAttribute("flightInformationList", flightInformationList);
        return "clean";
    }

    /**
     * 处理请求添加一条清洁记录
     *
     * @param flightInformationId 航班id
     * @param readTime            到位时间
     * @param usedTime            清洁用时
     * @param specialCase         特殊情况说明
     * @return 添加结果返回
     */
    @ResponseBody
    @PostMapping(value = "/add")
    public String addClean(int flightInformationId, String readTime, int usedTime, String specialCase) {
        // 将数据封装成对象
        Clean clean = new Clean();
        clean.setFlightInformationId(flightInformationId);
        clean.setReadTime(new Date(Long.parseLong(readTime)));
        clean.setUsedTime(usedTime);
        clean.setSpecialCase(specialCase);
        // 将传输上来的信息存储到数据库中
        boolean insertRecord = cleanService.addRecord(clean);
        if (insertRecord) {
            return "OK";
        } else {
            return "ERROR";
        }
    }

    @Autowired
    public void setFlightInformationService(FlightInformationService flightInformationService) {
        this.flightInformationService = flightInformationService;
    }

    @Autowired
    public void setCleanService(CleanService cleanService) {
        this.cleanService = cleanService;
    }
}
