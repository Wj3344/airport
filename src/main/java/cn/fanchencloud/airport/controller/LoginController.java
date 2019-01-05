package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.Admin;
import cn.fanchencloud.airport.entity.ResponseMessage;
import cn.fanchencloud.airport.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午7:15
 * Description:
 *
 * @author chen
 */
@Controller
public class LoginController {

    /**
     * 返回登录页面
     *
     * @return 登录页面
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseMessage login(String username, String password) {
        //对密码进行加密
        password = MD5Utils.encrypt(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResponseMessage.ok();
        } catch (UnknownAccountException | LockedAccountException | IncorrectCredentialsException e) {
            return ResponseMessage.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseMessage.error("认证失败！");
        }
    }

    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("admin", admin);
        return "index";
    }

}
