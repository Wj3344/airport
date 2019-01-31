package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.model.JsonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午5:09
 * Description:
 *
 * @author chen
 */
@Controller
@RequestMapping(value = "/test")
public class AdminController {
    @GetMapping(value = "/Ok")
    @ResponseBody
    public JsonResponse testOk() {
        return JsonResponse.ok();
    }

    @GetMapping(value = "/errorException")
    @ResponseBody
    public JsonResponse testErrorException() {
        return JsonResponse.errorException("haha");
    }

    @GetMapping(value = "/errorMsg")
    @ResponseBody
    public JsonResponse errorMsg() {
        return JsonResponse.errorMsg("错误信息");
    }

    @GetMapping(value = "/build")
    @ResponseBody
    public JsonResponse build() {
        List<String> stringList = new ArrayList<>(5);
        stringList.add("测试数据1");
        stringList.add("测试数据2");
        stringList.add("测试数据3");
        stringList.add("测试数据4");
        stringList.add("测试数据5");
        return JsonResponse.build(200, "请求成功", stringList);
    }
}
