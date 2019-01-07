package cn.fanchencloud.airport.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午3:19
 * Description:
 *
 * @author chen
 */
public class Baggage implements Serializable {
    private static final long serialVersionUID = 6080590092255846136L;
    /**
     * 记录id
     */
    private int id;
    /**
     * 航班信息记录表的ID
     */
    private int flightInformationId;
    /**
     * 行李车到位时间
     */
    private Date arrivedTime;
    /**
     * 传送带到位时间
     */
    private Date readyTime;
    /**
     * 特殊情况说明
     */
    private String specialCase;
    /**
     * 最后修改时间
     */
    private Date createTime;

    public Baggage() {
    }

    @Override
    public String toString() {
        return "Baggage{" +
                "id=" + id +
                ", flightInformationId=" + flightInformationId +
                ", arrivedTime=" + arrivedTime +
                ", readyTime=" + readyTime +
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

    public Date getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(Date arrivedTime) {
        this.arrivedTime = arrivedTime;
    }

    public Date getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(Date readyTime) {
        this.readyTime = readyTime;
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
