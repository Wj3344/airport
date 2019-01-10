package cn.fanchencloud.airport.entity;

import java.io.Serializable;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-10
 * Time: 上午10:20
 * Description: 重点旅客标记与一趟航班的联系
 *
 * @author chen
 */
public class FlightInformationPassengerTag implements Serializable {

    private static final long serialVersionUID = -4111264518107370433L;

    private int id;
    /**
     * 航班记录id
     */
    private int flightId;
    /**
     * 标签id
     */
    private int tagId;

    @Override
    public String toString() {
        return "FlightInformationPassengerTag{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", tagId=" + tagId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
