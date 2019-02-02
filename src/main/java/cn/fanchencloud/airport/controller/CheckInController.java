package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.Baggage;
import cn.fanchencloud.airport.entity.CheckIn;
import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.model.CheckInRecord;
import cn.fanchencloud.airport.model.JsonResponse;
import cn.fanchencloud.airport.service.BaggageService;
import cn.fanchencloud.airport.service.CheckInService;
import cn.fanchencloud.airport.service.FlightInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-21
 * Time: 上午11:12
 * Description: 行查信息控制器
 *
 * @author chen
 */
@Controller
@RequestMapping(value = "/checkIn")
public class CheckInController {

    /**
     * 最近的记录天数限制
     */
    private int currentDays = 7;

    /**
     * 注入航班信息服务层
     */
    private FlightInformationService flightInformationService;

    /**
     * 注入值机信息服务层
     */
    private CheckInService checkInService;


    /**
     * 请求到添加值机信息的页面
     *
     * @param model 模型
     * @return 页面跳转
     */
    @GetMapping(value = "/add")
    public String addBaggage(Model model) {
        List<FlightInformation> flightInformationList = flightInformationService.queryDataWithinOneDay();
        model.addAttribute("flightInformationList", flightInformationList);
        return "checkIn";
    }

    @ResponseBody
    @PostMapping(value = "/add")
    public JsonResponse addBaggage(CheckIn checkIn) {
        System.out.println(checkIn.toString());
        // 将传输上来的信息存储到数据库中
        boolean insertRecord = checkInService.insertRecord(checkIn);
        if (insertRecord) {
            return JsonResponse.ok();
        } else {
            return JsonResponse.errorMsg("添加记录失败");
        }
    }

    /**
     * 请求跳转到值机信息查询页面
     *
     * @param model 模型
     * @return 页面数据填充跳转
     */
    @GetMapping(value = "/search")
    public String searchPage(Model model) {
        // 获取最近的值机记录添加到模型中
        List<CheckInRecord> checkInRecordList = checkInService.getCurrentRecords(currentDays);
        model.addAttribute("checkInRecordList", checkInRecordList);
        return "checkInSearch";
    }

    @GetMapping(value = "/modify/{id}")
    public String modifyPage(Model model, @PathVariable("id") int id) {
        CheckInRecord checkInRecord = checkInService.getCheckInRecordById(id);
        return "checkInModify";
    }

    @Autowired
    public void setFlightInformationService(FlightInformationService flightInformationService) {
        this.flightInformationService = flightInformationService;
    }

    @Autowired
    public void setCheckInService(CheckInService checkInService) {
        this.checkInService = checkInService;
    }
}
