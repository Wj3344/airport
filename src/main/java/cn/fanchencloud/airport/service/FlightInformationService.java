package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.FlightInformation;

import java.util.List;
import java.util.Map;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-21
 * Time: 下午4:13
 * Description: 航班信息服务类
 *
 * @author chen
 */
public interface FlightInformationService {
    /**
     * 查询一日之内的航班记录
     *
     * @param flightInformationIds 航班列表id集合
     * @return 查询记录
     */
    List<FlightInformation> queryDataWithinOneDay(List<Integer> flightInformationIds);

    /**
     * 根据航班id查询航班信息记录
     *
     * @param ids 航班id集合
     * @return 查询结果
     */
    Map<Integer, String> queryFlightNumberWithId(List<Integer> ids);

    /**
     * 根据记录id查询记录详情
     *
     * @param id 记录id
     * @return 查询结果
     */
    FlightInformation queryRecordById(int id);

    /**
     * 查询最近 currentDay 时间内的未被记录的清洁航班
     *
     * @param currentDay 时间限制
     * @return 查询航班列表
     */
    List<FlightInformation> queryCleanDataWithinCurrentDaysNoMarked(int currentDay);

    /**
     * 查询最近 currentDay 时间内的未被记录的值机航班
     *
     * @param currentDay 时间限制
     * @return 查询航班列表
     */
    List<FlightInformation> queryCheckInDataWithinCurrentDaysNoMarked(int currentDay);

    /**
     * 查询最近 currentDay 时间内的未被记录的行查航班
     *
     * @param currentDay 时间限制
     * @return 查询航班列表
     */
    List<FlightInformation> queryBaggageDataWithinCurrentDaysNoMarked(int currentDay);

    /**
     * 查询最近 currentDay 时间内的未被记录的货运航班
     *
     * @param currentDay 时间限制
     * @return 查询航班列表
     */
    List<FlightInformation> queryFreightDataWithinCurrentDaysNoMarked(int currentDay);

    /**
     * 查询最近 currentDay 时间内的未被记录的综合服务航班
     *
     * @param currentDay 时间限制
     * @return 查询航班列表
     */
    List<FlightInformation> queryIntegratedServiceDataWithinCurrentDaysNoMarked(int currentDay);

    /**
     * 查询最近 currentDay 时间内的未被记录的站坪车辆航班
     *
     * @param currentDay 时间限制
     * @return 查询航班列表
     */
    List<FlightInformation> queryStandCarDataWithinCurrentDaysNoMarked(int currentDay);
}
