package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.Admin;
import cn.fanchencloud.airport.mapper.AdminMapper;
import cn.fanchencloud.airport.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午4:22
 * Description:
 *
 * @author chen
 */
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;

    @Override
    public Admin login(Admin admin) {
        boolean flag = this.isExists(admin);
        if (flag) {
            int identity = this.queryIdentity(admin.getUsername());
            admin.setIdentity(identity);
            return admin;
        }
        return null;
    }

    @Override
    public int add(Admin admin) {
        return adminMapper.add(admin);
    }

    @Override
    public int update(Admin admin) {
        return adminMapper.update(admin);
    }

    @Override
    public int deleteAdmin(String username) {
        return adminMapper.deleteAdmin(username);
    }

    @Override
    public boolean isExists(Admin admin) {
        return adminMapper.isExists(admin);
    }

    @Override
    public int queryIdentity(String username) {
        return adminMapper.queryIdentity(username);
    }

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }
}
