package cn.fanchencloud.airport.entity;

import java.io.Serializable;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午3:05
 * Description: 重点旅客标记表
 *
 * @author chen
 */
public class PassengerTag implements Serializable {

    private static final long serialVersionUID = 8024445359891876717L;

    /**
     * 旅客标记id
     */
    private int id;
    /**
     * 标记说明
     */
    private String describe;

    @Override
    public String toString() {
        return "PassengerTag{" +
                "id=" + id +
                ", describe='" + describe + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
