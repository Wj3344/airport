package cn.fanchencloud.airport.model;

import cn.fanchencloud.airport.entity.FlightInformation;

import java.util.Date;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 2019/2/24
 * Time: 15:28
 * Description:
 *
 * @author chen
 */
public class ExcelSheet2 {
    /**
     * 日期
     */
    private Date date;
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
     * 重点旅客
     */
    private String keyPassenger;

    /**
     * 特殊航班
     */
    private String specialFlight;

    public void setFlightInformation(FlightInformation flightInformation) {
        if (flightInformation == null) {
            return;
        }
        this.date = flightInformation.getTime();
        this.flightNumber = flightInformation.getFlightNumber();
        this.planeNumber = flightInformation.getPlaneNumber();
        this.boardingTime = flightInformation.getBoardingTime();
        this.gatePosition = flightInformation.getGatePosition();
        this.departureStation = flightInformation.getDepartureStation();
        this.destination = flightInformation.getDestination();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getKeyPassenger() {
        return keyPassenger;
    }

    public void setKeyPassenger(String keyPassenger) {
        this.keyPassenger = keyPassenger;
    }

    public String getSpecialFlight() {
        return specialFlight;
    }

    public void setSpecialFlight(String specialFlight) {
        this.specialFlight = specialFlight;
    }
}
