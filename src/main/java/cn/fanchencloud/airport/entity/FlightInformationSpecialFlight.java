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
public class FlightInformationSpecialFlight implements Serializable {

    private static final long serialVersionUID = -4111264518107370433L;

    private int id;
    /**
     * 航班记录id
     */
    private int flightId;
    /**
     * 标签id
     */
    private int specialFlight;

    @Override
    public String toString() {
        return "FlightInformationSpecialFlight{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", specialFlight=" + specialFlight +
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getSpecialFlight() {
        return specialFlight;
    }

    public void setSpecialFlight(int specialFlight) {
        this.specialFlight = specialFlight;
    }
}
