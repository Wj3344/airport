package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TestController {

    private EncryptUtil encryptUtil;

    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/getPassword")
    public String getPassword(String password) {
        return encryptUtil.encryptBASE64(password);
    }

    @Autowired
    public void setEncryptUtil(EncryptUtil encryptUtil) {
        this.encryptUtil = encryptUtil;
    }
}
