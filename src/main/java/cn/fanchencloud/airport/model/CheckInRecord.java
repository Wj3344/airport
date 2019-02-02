package cn.fanchencloud.airport.model;

import cn.fanchencloud.airport.entity.CheckIn;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-2-2
 * Time: 下午2:21
 * Description: 值机信息记录 （添加了航班号）
 *
 * @author chen
 */
public class CheckInRecord extends CheckIn {
    /**
     * 航班号码
     */
    private String flightNumber;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return s + " CheckInRecord{" +
                "flightNumber='" + flightNumber + '\'' +
                '}';
    }
}
