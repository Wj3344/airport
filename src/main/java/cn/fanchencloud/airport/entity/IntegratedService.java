package cn.fanchencloud.airport.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午3:21
 * Description: 综合服务信息记录表
 *
 * @author chen
 */
public class IntegratedService implements Serializable {

    private static final long serialVersionUID = 2535084275034485331L;
    /**
     * 记录id
     */
    private int id;
    /**
     * 航班信息记录表的ID
     */
    private int flightInformationId;
    /**
     * 登机时间
     */
    private Date boardingTime;
    /**
     * 客齐时间
     */
    private Date readyTime;
    /**
     * 关闭客舱时间
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

    public IntegratedService() {
    }

    @Override
    public String toString() {
        return "IntegratedService{" +
                "id=" + id +
                ", flightInformationId=" + flightInformationId +
                ", boardingTime=" + boardingTime +
                ", readyTime=" + readyTime +
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

    public Date getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(Date boardingTime) {
        this.boardingTime = boardingTime;
    }

    public Date getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(Date readyTime) {
        this.readyTime = readyTime;
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
