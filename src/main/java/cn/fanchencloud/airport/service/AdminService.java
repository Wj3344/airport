package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.Admin;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午4:20
 * Description:
 *
 * @author chen
 */
public interface AdminService {

    /**
     * 请求登录
     *
     * @param admin 用户
     * @return 登录后的信息
     */
    Admin login(Admin admin);

    /**
     * 添加一个管理员帐号
     *
     * @param admin 添加的管理员对象
     * @return 添加结果
     */
    int add(Admin admin);

    /**
     * 更新一个管理员信息(主要是密码）
     *
     * @param admin 更新的管理员信息
     * @return 更新结果
     */
    int update(Admin admin);

    /**
     * 删除一个管理员帐号
     *
     * @param username 学生学号
     * @return 删除结果
     */
    int deleteAdmin(String username);

    /**
     * 查询该用户是否存在
     *
     * @param admin 用户信息
     * @return 是否存在结果
     */
    boolean isExists(Admin admin);

    /**
     * 查询用户等级
     *
     * @param username 用户帐号
     * @return 用户等级
     */
    int queryIdentity(String username);
}
