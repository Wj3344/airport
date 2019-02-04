package cn.fanchencloud.airport.model;

import cn.fanchencloud.airport.entity.Clean;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-2-3
 * Time: 下午11:02
 * Description: 清洁信息记录
 *
 * @author chen
 */
public class CleanRecord extends Clean {
    /**
     * 航班号
     */
    private String flightNumber;

    @Override
    public String toString() {
        return super.toString() + " CleanRecord{" +
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
