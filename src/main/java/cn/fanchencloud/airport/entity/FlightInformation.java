package cn.fanchencloud.airport.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午2:28
 * Description: 航班信息表
 *
 * @author chen
 */
public class FlightInformation implements Serializable {

    private static final long serialVersionUID = -1472642553477693913L;
    /**
     * 记录ID
     */
    private int id;
    /**
     * 航班号
     */
    private String flightNumber;
    /**
     * 机号
     */
    private String planeNumber;
    /**
     * 登机时间
     */
    private Date boardingTime;
    /**
     * 停机位
     */
    private String gatePosition;
    /**
     * 始发站
     */
    private String departureStation;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 特殊情况说明
     */
    private String special;
    /**
     * 记录添加时间
     */
    private Date time;

    public FlightInformation() {
        // 默认为创建时间
        this.time = new Date();
    }

    public FlightInformation(FlightInformation flightInformation) {
        this.flightNumber = flightInformation.getFlightNumber();
        this.planeNumber = flightInformation.getPlaneNumber();
        this.boardingTime = flightInformation.getBoardingTime();
        this.gatePosition = flightInformation.getGatePosition();
        this.departureStation = flightInformation.getDepartureStation();
        this.destination = flightInformation.getDestination();
        this.special = flightInformation.getSpecial();
    }

    @Override
    public String toString() {
        return "FlightInformation{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", planeNumber='" + planeNumber + '\'' +
                ", boardingTime=" + boardingTime +
                ", gatePosition='" + gatePosition + '\'' +
                ", departureStation='" + departureStation + '\'' +
                ", destination='" + destination + '\'' +
                ", special='" + special + '\'' +
                ", time=" + time +
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

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPlaneNumber() {
        return planeNumber;
    }

    public void setPlaneNumber(String planeNumber) {
        this.planeNumber = planeNumber;
    }

    public Date getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(Date boardingTime) {
        this.boardingTime = boardingTime;
    }

    public String getGatePosition() {
        return gatePosition;
    }

    public void setGatePosition(String gatePosition) {
        this.gatePosition = gatePosition;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
}
