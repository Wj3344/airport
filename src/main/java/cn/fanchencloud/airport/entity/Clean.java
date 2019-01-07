package cn.fanchencloud.airport.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午3:13
 * Description: 清洁队信息记录表
 *
 * @author chen
 */
public class Clean implements Serializable {

    private static final long serialVersionUID = 5519823519796106494L;

    /**
     * 记录id
     */
    private int id;
    /**
     * 航班信息记录表的ID
     */
    private int flightInformationId;
    /**
     * 到位时间
     */
    private Date readTime;
    /**
     * 清洁用时
     */
    private int usedTime;
    /**
     * 特殊情况说明
     */
    private String specialCase;
    /**
     * 最后修改时间
     */
    private Date createTime;

    public Clean() {
    }

    @Override
    public String toString() {
        return "Clean{" +
                "id=" + id +
                ", flightInformationId=" + flightInformationId +
                ", readTime=" + readTime +
                ", usedTime=" + usedTime +
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

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public int getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(int usedTime) {
        this.usedTime = usedTime;
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
