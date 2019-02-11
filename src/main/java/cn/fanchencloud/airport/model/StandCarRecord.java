package cn.fanchencloud.airport.model;

import cn.fanchencloud.airport.entity.StandCar;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-2-11
 * Time: 下午4:03
 * Description: 站坪车辆信息集合
 *
 * @author chen
 */
public class StandCarRecord extends StandCar {
    /**
     * 航班号
     */
    private String flightNumber;

    @Override
    public String toString() {
        return super.toString() + "  StandCarRecord{" +
                "flightNumber='" + flightNumber + '\'' +
                '}';
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
}
