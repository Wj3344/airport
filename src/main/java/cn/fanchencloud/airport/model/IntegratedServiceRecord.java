package cn.fanchencloud.airport.model;

import cn.fanchencloud.airport.entity.IntegratedService;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-2-15
 * Time: 下午3:21
 * Description: 综合服务信息记录表
 *
 * @author chen
 */
public class IntegratedServiceRecord extends IntegratedService {
    /**
     * 航班号
     */
    private String flightNumber;

    @Override
    public String toString() {
        return super.toString() + " IntegratedServiceRecord{" +
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
