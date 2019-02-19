package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.Baggage;
import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.model.BaggageRecord;
import cn.fanchencloud.airport.model.JsonResponse;
import cn.fanchencloud.airport.service.BaggageService;
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
@RequestMapping(value = "/baggage")
public class BaggageController {

    /**
     * 时间限制
     */
    private int currentDays = 7;

    /**
     * 注入航班信息服务层
     */
    private FlightInformationService flightInformationService;

    /**
     * 注入行查信息服务层
     */
    private BaggageService baggageService;

    @GetMapping(value = "/add")
    public String addBaggage(Model model) {
        List<FlightInformation> flightInformationList = flightInformationService.queryBaggageDataWithinCurrentDaysNoMarked(currentDays);
        model.addAttribute("flightInformationList", flightInformationList);
        return "baggage";
    }

    @ResponseBody
    @PostMapping(value = "/add")
    public JsonResponse addBaggage(Baggage baggage) {
        // 将传输上来的信息存储到数据库中
        if (baggageService.insertRecord(baggage)) {
            return JsonResponse.ok();
        } else {
            return JsonResponse.errorMsg("添加记录失败！");
        }
    }

    /**
     * 请求到行查信息查询页面
     *
     * @param model 模型
     * @return 页面跳转
     */
    @GetMapping(value = "/list")
    public String getBaggageList(Model model) {
        List<BaggageRecord> baggageRecordList = baggageService.getCurrentRecords(currentDays);
        model.addAttribute("baggageRecordList", baggageRecordList);
        return "baggageList";
    }

    /**
     * 请求到修改行查信息页面
     *
     * @param model 模型
     * @param id    信息记录id
     * @return 页面跳转
     */
    @GetMapping(value = "/modify/{id}")
    public String modifyPage(Model model, @PathVariable("id") int id) {
        BaggageRecord baggageRecord = baggageService.getCheckInRecordById(id);
        model.addAttribute("baggageRecord", baggageRecord);
        return "baggageModify";
    }

    /**
     * 请求修改一条行查记录
     *
     * @param baggage 修改后的行查记录
     * @return 修改结果
     */
    @PostMapping(value = "/modify")
    @ResponseBody
    public JsonResponse modify(Baggage baggage) {
        if (baggageService.updateRecord(baggage)) {
            return JsonResponse.ok();
        } else {
            return JsonResponse.errorMsg("修改失败");
        }
    }

    @Autowired
    public void setFlightInformationService(FlightInformationService flightInformationService) {
        this.flightInformationService = flightInformationService;
    }

    @Autowired
    public void setBaggageService(BaggageService baggageService) {
        this.baggageService = baggageService;
    }
}
