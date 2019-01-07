package cn.fanchencloud.airport.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午3:16
 * Description:
 *
 * @author chen
 */
public class Freight implements Serializable {

    private static final long serialVersionUID = -1410284723669743761L;
    /**
     * 记录id
     */
    private int id;
    /**
     * 航班信息记录表的ID
     */
    private int flightInformationId;
    /**
     * 关闭货仓时间
     */
    private Date closeTime;
    /**
     * 特殊情况说明
     */
    private String specialCase;
    /**
     * 最后修改时间
     */
    private Date createTime;

    public Freight() {
    }

    @Override
    public String toString() {
        return "Freight{" +
                "id=" + id +
                ", flightInformationId=" + flightInformationId +
                ", closeTime=" + closeTime +
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

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
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
