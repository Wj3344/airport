package cn.fanchencloud.airport.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午3:26
 * Description:
 *
 * @author chen
 */
public class StandCar implements Serializable {

    private static final long serialVersionUID = -6544185977530374104L;
    /**
     * 记录id
     */
    private int id;

    /**
     * 航班信息记录表的ID
     */
    private int flightInformationId;

    /**
     * vip车辆到位时间时间
     */
    private Date vipTime;

    /**
     * 推车到位时间
     */
    private Date cartTime;

    /**
     * 特殊情况说明
     */
    private String specialCase;

    /**
     * 最后修改时间
     */
    private Date createTime;

    public StandCar() {
    }

    @Override
    public String toString() {
        return "Stand{" +
                "id=" + id +
                ", flightInformationId=" + flightInformationId +
                ", vipTime=" + vipTime +
                ", cartTime=" + cartTime +
                ", specialCase='" + specialCase + '\'' +
                ", createTime=" + createTime +
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

    public int getFlightInformationId() {
        return flightInformationId;
    }

    public void setFlightInformationId(int flightInformationId) {
        this.flightInformationId = flightInformationId;
    }

    public Date getVipTime() {
        return vipTime;
    }

    public void setVipTime(Date vipTime) {
        this.vipTime = vipTime;
    }

    public Date getCartTime() {
        return cartTime;
    }

    public void setCartTime(Date cartTime) {
        this.cartTime = cartTime;
    }

    public String getSpecialCase() {
        return specialCase;
    }

    public void setSpecialCase(String specialCase) {
        this.specialCase = specialCase;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
