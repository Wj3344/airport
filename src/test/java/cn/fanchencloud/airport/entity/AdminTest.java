package cn.fanchencloud.airport.entity;

import cn.fanchencloud.airport.service.AdminService;
import cn.fanchencloud.airport.utils.EncryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午4:18
 * Description:
 *
 * @author chen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest {

    private AdminService adminService;
    private EncryptUtil encryptUtil;

    @Test
    public void addAdmin() {
        String password = encryptUtil.encryptBASE64("123456");
        for (int i = 0; i < 13; i++) {
            Admin admin = new Admin("fanchen" + i, password, i);
            int add = adminService.add(admin);
            if (add > 0) {
                System.out.println("添加成功" + admin.toString());
            } else {
                System.out.println("添加失败");
            }

        }
    }


    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setEncryptUtil(EncryptUtil encryptUtil) {
        this.encryptUtil = encryptUtil;
    }
}
