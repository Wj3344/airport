package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.entity.Freight;
import cn.fanchencloud.airport.model.FreightRecord;
import cn.fanchencloud.airport.model.JsonResponse;
import cn.fanchencloud.airport.service.FlightInformationService;
import cn.fanchencloud.airport.service.FreightService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(FreightController.class);

    /**
     * 时间限制
     */
    private int currentDays = 7;


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
    @RequiresPermissions("freight:add")
    @GetMapping(value = "/add")
    public String addFreight(Model model) {
        List<FlightInformation> flightInformationList = flightInformationService.queryFreightDataWithinCurrentDaysNoMarked(currentDays);
        model.addAttribute("flightInformationList", flightInformationList);
        return "freight";
    }

    /**
     * 请求添加货运信息
     *
     * @return 添加结果返回
     */
    @RequiresPermissions("freight:add")
    @ResponseBody
    @PostMapping(value = "/add")
    public JsonResponse addFreight(Freight freight) {
        // 将上传的数据持久化到数据库
        if (freightService.addRecord(freight)) {
            // 添加成功
            return JsonResponse.ok();
        } else {
            return JsonResponse.errorMsg("ERROR，添加货运信息失败");
        }
    }

    /**
     * 请求跳转到货运信息添加页面
     *
     * @param model 模型
     * @return 页面跳转
     */
    @RequiresPermissions("freight:list")
    @GetMapping("list")
    public String listFreight(Model model) {
        List<FreightRecord> freightRecordList = freightService.getCurrentRecords(currentDays);
        model.addAttribute("freightRecordList", freightRecordList);
        return "freightList";
    }

    /**
     * 请求获取一个修改的页面的记录信息
     *
     * @param model 模型
     * @param id    记录id
     * @return 查询结果并跳转页面
     */
    @RequiresPermissions("freight:modify")
    @GetMapping(value = "/modify/{id}")
    public String modify(Model model, @PathVariable("id") int id) {
        FreightRecord freightRecord = freightService.getCheckInRecordById(id);
        model.addAttribute("freightRecord", freightRecord);
        return "freightModify";
    }

    /**
     * 请求修改一条货运记录
     *
     * @param freight 货运信息
     * @return 修改结果
     */
    @RequiresPermissions("freight:modify")
    @PostMapping("/modify")
    @ResponseBody
    public JsonResponse modify(Freight freight) {
        if (freightService.updateRecord(freight)) {
            return JsonResponse.ok();
        } else {
            return JsonResponse.errorMsg("修改失败！");
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
