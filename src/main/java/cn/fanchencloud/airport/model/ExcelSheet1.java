package cn.fanchencloud.airport.model;

import cn.fanchencloud.airport.entity.*;

import java.util.Date;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 2019/2/24
 * Time: 15:09
 * Description:
 *
 * @author chen
 */
public class ExcelSheet1 {
    /**
     * 日期
     */
    private Date date;

    /**
     * 航班号
     */
    private String flightNumber;

    /* 值机 start */
    /**
     * 人数
     */
    private Integer numberOfPeople;
    /**
     * 行李件数
     */
    private Integer numberOfLuggage;
    /**
     * 值机特殊情况
     */
    private String checkInSpecialCase;
    /* 值机 end */

    /* 清洁队 start */
    /**
     * 清洁队到位时间
     */
    private Date cleaningTeamInPlace;
    /**
     * 清洁用时
     */
    private Integer cleaningTime;
    /**
     * 清洁特殊情况说明
     */
    private String cleaningSpecialCaseDescription;
    /* 清洁队 end */

    /* 站坪车辆 start */
    /**
     * VIP车辆到位时间
     */
    private Date vipVehicleInPlace;
    /**
     * 推车到位时间
     */
    private Date cartInPlace;
    /**
     * 站坪特殊情况说明
     */
    private String stationFloorSpecialSituationDescription;
    /* 站坪车辆 end */

    /* 行查 start */
    /**
     * 行李车发时间
     */
    private Date baggageCarTime;
    /**
     * 传送带到位时间
     */
    private Date conveyorBeltInPlace;
    /**
     * 行查特殊情况说明
     */
    private String checkSpecialCaseDescription;
    /* 行查 end */

    /* 综服 start */
    /**
     * 登机时间
     */
    private Date boardingTime;
    /**
     * 客齐时间
     */
    private Date guestTime;
    /**
     * 关客舱时间
     */
    private Date offCabinTime;
    /**
     * 综服特殊情况说明
     */
    private String comprehensiveServiceDescription;
    /* 综服 end */

    /* 货运 start */
    /**
     * 关货舱时间
     */
    private Date closeCargoTime;
    /**
     * 货运特殊情况说明
     */
    private String specialCaseDescriptionOfFreight;
    /* 货运 end */

    /**
     * 设置航班信息
     *
     * @param flightInformation 航班信息
     */
    public void setFlightInformation(FlightInformation flightInformation) {
        if (flightInformation == null) {
            return;
        }
        this.date = flightInformation.getTime();
        this.flightNumber = flightInformation.getFlightNumber();
    }

    /**
     * 设置值机信息
     *
     * @param checkIn 值机信息
     */
    public void setCheckIn(CheckIn checkIn) {
        if (checkIn == null) {
            return;
        }
        this.numberOfPeople = checkIn.getRealNumber();
        this.numberOfLuggage = checkIn.getLuggageNumber();
        this.checkInSpecialCase = checkIn.getSpecialCase();
    }

    /**
     * 设置清洁信息
     *
     * @param clean 清洁信息
     */
    public void setClean(Clean clean) {
        if (clean == null) {
            return;
        }
        this.cleaningTeamInPlace = clean.getReadTime();
        this.cleaningTime = clean.getUsedTime();
        this.cleaningSpecialCaseDescription = clean.getSpecialCase();
    }

    /**
     * 设置站坪车辆信息
     *
     * @param standCar 站坪车辆
     */
    public void setStandCar(StandCar standCar) {
        if (standCar == null) {
            return;
        }
        this.vipVehicleInPlace = standCar.getVipTime();
        this.cartInPlace = standCar.getCartTime();
        this.stationFloorSpecialSituationDescription = standCar.getSpecialCase();
    }

    /**
     * 设置行查信息
     *
     * @param baggage 行查信息
     */
    public void setBaggage(Baggage baggage) {
        if (baggage == null) {
            return;
        }
        this.baggageCarTime = baggage.getArrivedTime();
        this.conveyorBeltInPlace = baggage.getReadyTime();
        this.checkSpecialCaseDescription = baggage.getSpecialCase();
    }

    /**
     * 设置综服信息
     *
     * @param integratedService 综服信息
     */
    public void setIntegratedService(IntegratedService integratedService) {
        if (integratedService == null) {
            return;
        }
        this.boardingTime = integratedService.getBoardingTime();
        this.guestTime = integratedService.getReadyTime();
        this.offCabinTime = integratedService.getCloseTime();
        this.comprehensiveServiceDescription = integratedService.getSpecialCase();
    }

    /**
     * 设置货运信息
     *
     * @param freight 货运信息
     */
    public void setFreight(Freight freight) {
        if (freight == null) {
            return;
        }
        this.closeCargoTime = freight.getCloseTime();
        this.specialCaseDescriptionOfFreight = freight.getSpecialCase();
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

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Integer getNumberOfLuggage() {
        return numberOfLuggage;
    }

    public void setNumberOfLuggage(Integer numberOfLuggage) {
        this.numberOfLuggage = numberOfLuggage;
    }

    public String getCheckInSpecialCase() {
        return checkInSpecialCase;
    }

    public void setCheckInSpecialCase(String checkInSpecialCase) {
        this.checkInSpecialCase = checkInSpecialCase;
    }

    public Date getCleaningTeamInPlace() {
        return cleaningTeamInPlace;
    }

    public void setCleaningTeamInPlace(Date cleaningTeamInPlace) {
        this.cleaningTeamInPlace = cleaningTeamInPlace;
    }

    public Integer getCleaningTime() {
        return cleaningTime;
    }

    public void setCleaningTime(Integer cleaningTime) {
        this.cleaningTime = cleaningTime;
    }

    public String getCleaningSpecialCaseDescription() {
        return cleaningSpecialCaseDescription;
    }

    public void setCleaningSpecialCaseDescription(String cleaningSpecialCaseDescription) {
        this.cleaningSpecialCaseDescription = cleaningSpecialCaseDescription;
    }

    public Date getVipVehicleInPlace() {
        return vipVehicleInPlace;
    }

    public void setVipVehicleInPlace(Date vipVehicleInPlace) {
        this.vipVehicleInPlace = vipVehicleInPlace;
    }

    public Date getCartInPlace() {
        return cartInPlace;
    }

    public void setCartInPlace(Date cartInPlace) {
        this.cartInPlace = cartInPlace;
    }

    public String getStationFloorSpecialSituationDescription() {
        return stationFloorSpecialSituationDescription;
    }

    public void setStationFloorSpecialSituationDescription(String stationFloorSpecialSituationDescription) {
        this.stationFloorSpecialSituationDescription = stationFloorSpecialSituationDescription;
    }

    public Date getBaggageCarTime() {
        return baggageCarTime;
    }

    public void setBaggageCarTime(Date baggageCarTime) {
        this.baggageCarTime = baggageCarTime;
    }

    public Date getConveyorBeltInPlace() {
        return conveyorBeltInPlace;
    }

    public void setConveyorBeltInPlace(Date conveyorBeltInPlace) {
        this.conveyorBeltInPlace = conveyorBeltInPlace;
    }

    public String getCheckSpecialCaseDescription() {
        return checkSpecialCaseDescription;
    }

    public void setCheckSpecialCaseDescription(String checkSpecialCaseDescription) {
        this.checkSpecialCaseDescription = checkSpecialCaseDescription;
    }

    public Date getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(Date boardingTime) {
        this.boardingTime = boardingTime;
    }

    public Date getGuestTime() {
        return guestTime;
    }

    public void setGuestTime(Date guestTime) {
        this.guestTime = guestTime;
    }

    public Date getOffCabinTime() {
        return offCabinTime;
    }

    public void setOffCabinTime(Date offCabinTime) {
        this.offCabinTime = offCabinTime;
    }

    public String getComprehensiveServiceDescription() {
        return comprehensiveServiceDescription;
    }

    public void setComprehensiveServiceDescription(String comprehensiveServiceDescription) {
        this.comprehensiveServiceDescription = comprehensiveServiceDescription;
    }

    public Date getCloseCargoTime() {
        return closeCargoTime;
    }

    public void setCloseCargoTime(Date closeCargoTime) {
        this.closeCargoTime = closeCargoTime;
    }

    public String getSpecialCaseDescriptionOfFreight() {
        return specialCaseDescriptionOfFreight;
    }

    public void setSpecialCaseDescriptionOfFreight(String specialCaseDescriptionOfFreight) {
        this.specialCaseDescriptionOfFreight = specialCaseDescriptionOfFreight;
    }
}
