package cn.fanchencloud.airport.model;

import cn.fanchencloud.airport.entity.FlightInformation;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午5:37
 * Description:
 *
 * @author chen
 */
public class Registration extends FlightInformation {

    private static final long serialVersionUID = 2440764776096395948L;
    /**
     * 重点旅客标记
     */
    private List<Integer> passengerTags;

    /**
     * 特殊航班标记
     */
    private List<Integer> specialTags;

    @Override
    public String toString() {
        return "Registration{" +
                "passengerTags=" + passengerTags +
                ", specialTags=" + specialTags +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Integer> getPassengerTags() {
        return passengerTags;
    }

    public void setPassengerTags(List<Integer> passengerTags) {
        this.passengerTags = passengerTags;
    }

    public List<Integer> getSpecialTags() {
        return specialTags;
    }

    public void setSpecialTags(List<Integer> specialTags) {
        this.specialTags = specialTags;
    }
}
