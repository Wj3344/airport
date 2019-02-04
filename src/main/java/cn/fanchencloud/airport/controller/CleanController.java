package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.Baggage;
import cn.fanchencloud.airport.entity.Clean;
import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.model.CleanRecord;
import cn.fanchencloud.airport.model.JsonResponse;
import cn.fanchencloud.airport.service.BaggageService;
import cn.fanchencloud.airport.service.CleanService;
import cn.fanchencloud.airport.service.FlightInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * 记录时间限制
     */
    private int currentDay = 7;

    /**
     * 日志记录器
     */
    private static Logger logger = LoggerFactory.getLogger(CleanController.class);

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
     * @param clean 添加信息
     * @return 添加结果
     */
    @ResponseBody
    @PostMapping(value = "/add")
    public JsonResponse addClean(Clean clean) {
        // 将传输上来的信息存储到数据库中
        boolean insertRecord = cleanService.addRecord(clean);
        if (insertRecord) {
            return JsonResponse.ok();
        } else {
            return JsonResponse.errorMsg("添加失败");
        }
    }

    /**
     * 请求跳转到清洁列表页面
     *
     * @param model 模型
     * @return 页面跳转
     */
    @GetMapping(value = "/list")
    public String listClean(Model model) {
        // 查询记录
        List<CleanRecord> cleanRecordList = cleanService.getCurrentRecord(currentDay);
        model.addAttribute("cleanRecordList", cleanRecordList);
        return "cleanList";
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
