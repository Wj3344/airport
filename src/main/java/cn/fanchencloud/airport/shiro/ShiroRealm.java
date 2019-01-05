package cn.fanchencloud.airport.shiro;


import cn.fanchencloud.airport.entity.Admin;
import cn.fanchencloud.airport.mapper.AdminMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午6:08
 * Description:
 *
 * @author chen
 */
public class ShiroRealm extends AuthorizingRealm {

    private AdminMapper adminMapper;

    /**
     * 获取用户角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 验证用户的合法性
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户输入的用户名和密码
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        System.out.println("用户" + userName + "认证-----ShiroRealm.doGetAuthenticationInfo");

        //通过用户名到数据库查询用户信息
        Admin admin = adminMapper.queryAdmin(userName);

        if (admin == null) {
            throw new UnknownAccountException("用户名或者密码错误！");
        }
        if (!password.equals(admin.getPassword())) {
            throw new IncorrectCredentialsException("用户名或者密码错误！");
        }
        return new SimpleAuthenticationInfo(admin, password, getName());
    }

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }
}
