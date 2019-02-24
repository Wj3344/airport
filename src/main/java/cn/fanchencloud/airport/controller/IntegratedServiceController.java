package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.FlightInformation;
import cn.fanchencloud.airport.entity.IntegratedService;
import cn.fanchencloud.airport.model.IntegratedServiceRecord;
import cn.fanchencloud.airport.model.JsonResponse;
import cn.fanchencloud.airport.service.FlightInformationService;
import cn.fanchencloud.airport.service.IntegratedServiceService;
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
     * 日志记录器
     */
    private static Logger logger = LoggerFactory.getLogger(IntegratedServiceController.class);

    /**
     * 注入航班信息服务层
     */
    private FlightInformationService flightInformationService;

    /**
     * 注入综合信息服务
     */
    private IntegratedServiceService integratedServiceService;

    /**
     * 时间限制
     */
    private int currentDay = 7;

    /**
     * 请求跳转到进行添加综合服务信息
     *
     * @param model 模型
     * @return 页面跳转
     */
    @RequiresPermissions("integratedService:add")
    @GetMapping(value = "/add")
    public String addIntegratedService(Model model) {
        List<FlightInformation> flightInformationList = flightInformationService.queryIntegratedServiceDataWithinCurrentDaysNoMarked(currentDay);
        model.addAttribute("flightInformationList", flightInformationList);
        return "integratedService";
    }


    /**
     * 请求添加一条综合服务信息记录
     *
     * @param integratedService 综合服务信息
     * @return 添加结果
     */
    @RequiresPermissions("integratedService:add")
    @ResponseBody
    @PostMapping(value = "/add")
    public JsonResponse addIntegratedService(IntegratedService integratedService) {
        // 将数据交给服务层处理
        if (integratedServiceService.insertRecord(integratedService)) {
            return JsonResponse.ok();
        } else {
            return JsonResponse.errorMsg("添加数据失败！");
        }
    }

    /**
     * 请求到综合服务信息列表
     *
     * @param model 模型
     * @return 页面跳转
     */
    @RequiresPermissions("integratedService:list")
    @GetMapping(value = "/list")
    public String list(Model model) {
        List<IntegratedServiceRecord> integratedServiceRecordList = integratedServiceService.getCurrentRecord(currentDay);
        model.addAttribute("integratedServiceRecordList", integratedServiceRecordList);
        return "integratedServiceList";
    }

    /**
     * 请求到综合信息修改界面
     *
     * @param model 模型
     * @param id    记录id
     * @return 页面跳转
     */
    @RequiresPermissions("integratedService:modify")
    @GetMapping(value = "/modify/{id}")
    public String modify(Model model, @PathVariable("id") int id) {
        IntegratedServiceRecord integratedServiceRecord = integratedServiceService.getRecordById(id);
        model.addAttribute("integratedServiceRecord", integratedServiceRecord);
        return "integratedServiceModify";
    }

    /**
     * 请求修改一条综合服务信息
     *
     * @param integratedService 综合服务信息
     * @return 修改结果
     */
    @RequiresPermissions("integratedService:modify")
    @PostMapping(value = "/modify")
    @ResponseBody
    public JsonResponse modify(IntegratedService integratedService) {
        if (integratedServiceService.updateRecord(integratedService)) {
            return JsonResponse.ok();
        } else {
            return JsonResponse.errorMsg("更新数据失败！");
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
