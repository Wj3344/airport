package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
public class TestController {
    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(15041225, "小明", 18));
        list.add(new Student(15041225, "小明", 18));
        list.add(new Student(15041225, "小明", 18));
        model.addAttribute("studentList", list);
        model.addAttribute("message", "哈哈");
        return "index";
    }
}
