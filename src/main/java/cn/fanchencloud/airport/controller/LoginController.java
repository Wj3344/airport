package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.Admin;
import cn.fanchencloud.airport.entity.ErrorMessage;
import cn.fanchencloud.airport.entity.Identity;
import cn.fanchencloud.airport.model.JsonResponse;
import cn.fanchencloud.airport.service.AdminService;
import cn.fanchencloud.airport.service.IdentityService;
import cn.fanchencloud.airport.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
     * 注入账号等级服务
     */
    private IdentityService identityService;

    /**
     * 注入用户信息服务
     */
    private AdminService adminService;

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
    public String login(String username, String password) {
        //对密码进行加密
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            // 跳转到首页
            return "redirect:/index";
        } catch (AuthenticationException e) {
            return "login";
        }
    }

    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @RequestMapping("/index_back")
    public String indexBack() {
        return "index_back";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("admin", admin);
        return "index";
    }

    /**
     * 请求跳转到添加账号信息的界面
     *
     * @param model 模型
     * @return 页面跳转
     */
    @RequiresPermissions("admin:add")
    @GetMapping(value = "/signUp")
    public String signUp(Model model) {
        List<Identity> identityList = identityService.getIdentityList();
        model.addAttribute("identityList", identityList);
        return "signUp";
    }

    /**
     * 请求添加一个账号
     *
     * @param admin 账号信息
     * @return 添加结果
     */
    @PostMapping(value = "/signUp")
    @RequiresPermissions("admin:add")
    @ResponseBody
    public JsonResponse signUp(Admin admin) {
        if (admin == null) {
            return JsonResponse.errorMsg("注册信息不能为空！");
        }
        if (adminService.isExists(admin)) {
            return JsonResponse.errorMsg("该用户名已被注册！");
        }
        String password = MD5Utils.encrypt(admin.getUsername(), admin.getPassword());
        admin.setPassword(password);
        if (adminService.add(admin) != 0) {
            return JsonResponse.ok();
        } else {
            return JsonResponse.errorMsg("添加失败");
        }
    }

    @RequestMapping(value = "/errorPage")
    public String errorPage(Model model) {
        System.out.println("进入error控制器");
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTitle("暂无权限");
        errorMessage.setHead("您没有权限进行该操作");
        errorMessage.setMessage("<a href=\"/index\">返回</a>");
        model.addAttribute("errorMessage", errorMessage);
        return "errorPage";
    }

    @Autowired
    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
}
