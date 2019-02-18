package cn.fanchencloud.airport.model;

import cn.fanchencloud.airport.entity.Freight;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 2019/2/17
 * Time: 23:35
 * Description:
 *
 * @author chen
 */
public class FreightRecord extends Freight {
    /**
     * 航班号码
     */
    private String flightNumber;

    @Override
    public String toString() {
        return super.toString() + " FreightRecord{" +
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
