package cn.fanchencloud.airport.entity;

import java.io.Serializable;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午3:54
 * Description: 用户帐号表
 *
 * @author chen
 */
public class Admin implements Serializable {

    private static final long serialVersionUID = -5557991673697530680L;
    /**
     * 用户名（用户账号）
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户账户等级
     */
    private int identity;

    public Admin() {
    }

    public Admin(String username, String password, int identity) {
        this.username = username;
        this.password = password;
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", identity=" + identity +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }
}
