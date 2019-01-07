package cn.fanchencloud.airport.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午3:09
 * Description: 值机信息记录表
 *
 * @author chen
 */
public class CheckIn implements Serializable {

    private static final long serialVersionUID = 5732517616317988284L;

    /**
     * 记录id
     */
    private int id;
    /**
     * 航班信息记录表的ID
     */
    private int flightInformationId;
    /**
     * 实际人数
     */
    private int realNumber;
    /**
     * 实际行李件数
     */
    private int luggageNumber;
    /**
     * 特殊情况说明
     */
    private String specialCase;
    /**
     * 最后修改时间
     */
    private Date createTime;

    public CheckIn() {
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "id=" + id +
                ", flightInformationId=" + flightInformationId +
                ", realNumber=" + realNumber +
                ", luggageNumber=" + luggageNumber +
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

    public int getRealNumber() {
        return realNumber;
    }

    public void setRealNumber(int realNumber) {
        this.realNumber = realNumber;
    }

    public int getLuggageNumber() {
        return luggageNumber;
    }

    public void setLuggageNumber(int luggageNumber) {
        this.luggageNumber = luggageNumber;
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
