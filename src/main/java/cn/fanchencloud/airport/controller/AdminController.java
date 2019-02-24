package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.Admin;
import cn.fanchencloud.airport.model.AdminRecord;
import cn.fanchencloud.airport.model.JsonResponse;
import cn.fanchencloud.airport.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(value = "/admin")
public class AdminController {

    /**
     * 注入账号管理服务
     */
    private AdminService adminService;

    /**
     * 列出用户列表
     *
     * @param model 模型
     * @return 页面跳转
     */
    @RequiresPermissions("admin:list")
    @GetMapping(value = "/list")
    public String listAdmin(Model model) {
        List<AdminRecord> adminRecordList = adminService.getAllList();
        model.addAttribute("adminRecordList", adminRecordList);
        return "adminList";
    }

    /**
     * 删除一个账号
     *
     * @param username 用户名
     * @return 删除结果
     */
    @RequiresPermissions("admin:delete")
    @GetMapping(value = "/delete/{username}")
    public JsonResponse deleteAdmin(@PathVariable("username") String username) {
        if (adminService.deleteAdmin(username)) {
            return JsonResponse.ok();
        } else {
            return JsonResponse.errorMsg("删除失败！");
        }
    }

    /**
     * 请求跳转到修改用户信息界面
     *
     * @param model    模型
     * @param username 用户名
     * @return 页面跳转
     */
    @RequiresPermissions("admin:update")
    @GetMapping("/modify/{username}")
    public String modifyAdmin(Model model, @PathVariable("username") String username) {
        model.addAttribute("username", username);
        return "adminModify";
    }

    @PostMapping(value = "/modify")
    public JsonResponse modifyAdmin(Admin admin) {
        Admin user = (Admin) SecurityUtils.getSubject().getPrincipal();
        if (user.getIdentity() != 0) {
            if (!user.getUsername().equals(admin.getUsername())) {
                return JsonResponse.errorMsg("你没有权限此账号！");
            }
        }
        if (adminService.update(admin)) {
            return JsonResponse.ok();
        } else {
            return JsonResponse.errorMsg("修改失败！");
        }
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
}
