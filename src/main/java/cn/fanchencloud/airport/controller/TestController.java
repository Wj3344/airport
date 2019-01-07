package cn.fanchencloud.airport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-3
 * Time: 上午10:41
 * Description:
 *
 * @author chen
 */
@Controller
@RequestMapping("/test")
public class TestController {
    /**
     * 值机信息录入
     *
     * @return 值机信息录入
     */
    @RequestMapping("/checkIn")
    public String checkIn() {
        return "checkIn";
    }

    /**
     * 清洁
     *
     * @return 清洁
     */
    @RequestMapping("/clean")
    public String clean() {
        return "clean";
    }

    /**
     * 货运信息录入
     *
     * @return 货运信息录入
     */
    @RequestMapping("/freight")
    public String freight() {
        return "freight";
    }

    /**
     * 行查信息录入
     *
     * @return 行查信息录入
     */
    @RequestMapping("/baggage")
    public String baggage() {
        return "baggage";
    }

    /**
     * 综合服务信息录入
     *
     * @return 综合服务信息录入
     */
    @RequestMapping("/integratedService")
    public String integratedService() {
        return "integratedService";
    }

    /**
     * 站坪车辆信息录入
     *
     * @return 站坪车辆信息录入
     */
    @RequestMapping("/standCar")
    public String standCar() {
        return "standCar";
    }


    @ResponseBody
    @RequestMapping(value = "/getPassword")
    public String getPassword(String password) {
        return null;
    }

}
