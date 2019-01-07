package cn.fanchencloud.airport.entity;

import java.io.Serializable;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午3:07
 * Description: 特殊团队标记标签
 *
 * @author chen
 */
public class SpecialFlight implements Serializable {

    private static final long serialVersionUID = -6563552960258174445L;
    /**
     * 特殊团队标记id
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
