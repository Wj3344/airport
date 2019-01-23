package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.Baggage;
import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.service.BaggageService;
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
 * Description: 行查信息控制器
 *
 * @author chen
 */
@Controller
@RequestMapping(value = "/baggage")
public class BaggageController {

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
        List<FlightInformation> flightInformationList = flightInformationService.queryDataWithinOneDay();
        model.addAttribute("flightInformationList", flightInformationList);
        return "baggage";
    }

    @ResponseBody
    @PostMapping(value = "/add")
    public String addBaggage(int flightInformationId, String arrivedTime, String readyTime, String specialCase) {
        Baggage baggage = new Baggage();
        baggage.setFlightInformationId(flightInformationId);
        baggage.setArrivedTime(new Date(Long.parseLong(arrivedTime)));
        baggage.setReadyTime(new Date(Long.parseLong(readyTime)));
        baggage.setSpecialCase(specialCase);
        // 将传输上来的信息存储到数据库中
        boolean insertRecord = baggageService.insertRecord(baggage);
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
    public void setBaggageService(BaggageService baggageService) {
        this.baggageService = baggageService;
    }
}
