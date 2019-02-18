package cn.fanchencloud.airport.model;

import cn.fanchencloud.airport.entity.Baggage;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 2019/2/17
 * Time: 0:28
 * Description:
 *
 * @author chen
 */
public class BaggageRecord extends Baggage {

    /**
     * 航班号码
     */
    private String flightNumber;

    @Override
    public String toString() {
        return super.toString() + " BaggageRecord{" +
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
