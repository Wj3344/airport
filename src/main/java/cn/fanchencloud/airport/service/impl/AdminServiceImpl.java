package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.Admin;
import cn.fanchencloud.airport.entity.Identity;
import cn.fanchencloud.airport.mapper.AdminMapper;
import cn.fanchencloud.airport.mapper.IdentityMapper;
import cn.fanchencloud.airport.model.AdminRecord;
import cn.fanchencloud.airport.service.AdminService;
import cn.fanchencloud.airport.utils.BeanUtils;
import cn.fanchencloud.airport.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * 注入账号服务持久层
     */
    private AdminMapper adminMapper;

    /**
     * 注入账号等级服务持久层
     */
    private IdentityMapper identityMapper;

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
    public boolean update(Admin admin) {
        String password = MD5Utils.encrypt(admin.getUsername(), admin.getPassword());
        admin.setPassword(password);
        return adminMapper.update(admin) != 0;
    }

    @Override
    public boolean deleteAdmin(String username) {
        return adminMapper.deleteAdmin(username) != 0;
    }

    @Override
    public boolean isExists(Admin admin) {
        return adminMapper.isExists(admin);
    }

    @Override
    public int queryIdentity(String username) {
        return adminMapper.queryIdentity(username);
    }

    @Override
    public List<AdminRecord> getAllList() {
        List<Admin> adminList = adminMapper.queryAllAdmin();
        Map<Integer, Identity> identityMap = identityMapper.getAllIdentityMap();
        List<AdminRecord> adminRecordList = new ArrayList<>(adminList.size());
        for (Admin admin : adminList) {
            AdminRecord adminRecord = new AdminRecord();
            try {
                BeanUtils.fatherToChild(admin, adminRecord);
            } catch (Exception e) {
                e.printStackTrace();
            }
            adminRecord.setDescribe(identityMap.get(admin.getIdentity()).getDescribe());
            adminRecordList.add(adminRecord);
        }
        return adminRecordList;
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.queryAdmin(username);
    }

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Autowired
    public void setIdentityMapper(IdentityMapper identityMapper) {
        this.identityMapper = identityMapper;
    }
}
