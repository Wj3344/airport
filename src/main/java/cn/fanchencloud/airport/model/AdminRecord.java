package cn.fanchencloud.airport.model;

import cn.fanchencloud.airport.entity.Admin;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 2019/2/24
 * Time: 13:15
 * Description: 账号展示类
 *
 * @author chen
 */
public class AdminRecord extends Admin {

    /**
     * 账号等级描述
     */
    private String describe;

    @Override
    public String toString() {
        return super.toString() + " AdminRecord{" +
                "describe='" + describe + '\'' +
                '}';
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
