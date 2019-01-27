package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.FlightInformation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-10
 * Time: 上午9:54
 * Description:
 *
 * @author chen
 */
@Mapper
@Component
public interface FlightInformationMapper {
    /**
     * 添加一条航班记录
     *
     * @param flightInformation 航班记录信息
     * @return 添加结果
     */
    @Insert("insert into `airport`.`flightInformation` "
            + "(flightNumber, planeNumber, boardingTime, gatePosition, departureStation, destination, special) "
            + "VALUES (#{flightNumber},#{planeNumber},#{boardingTime},"
            + "#{gatePosition},#{departureStation},#{destination},#{special})")
    int addOne(FlightInformation flightInformation);

    /**
     * 根据航班编号和机号查询航班记录表中的Id
     *
     * @param flightNumber 航班编号
     * @param planeNumber  飞机编号
     * @return 查询结果
     */
    @Select("select id from flightInformation where planeNumber = #{planeNumber} and flightNumber = #{flightNumber} order by time limit 1;")
    List<Integer> findIdByFlightNumberAndPlaneNumber(String flightNumber, String planeNumber);

    /**
     * 获取最近一次提交的自增长id
     *
     * @return id
     */
    @Select("SELECT LAST_INSERT_ID();")
    int getLastId();

    /**
     * 查询一日之内的航班记录
     *
     * @return 查询记录
     */
    @Select("select id, flightNumber, planeNumber, boardingTime, gatePosition, departureStation, destination, time, special\n" +
            "from flightInformation\n" +
            "where to_days(now()) - to_days(time) <= 2;")
    List<FlightInformation> queryDataWithinOneDay();

    /**
     * 获取所有的次日航班信息记录
     *
     * @return 记录列表
     */
    @Select("select id, flightNumber, planeNumber, boardingTime, gatePosition, departureStation, destination, time, special\n" +
            "from flightInformation order by time desc")
    List<FlightInformation> getAll();

    /**
     * 通过记录id查询记录信息
     *
     * @param id 记录id'
     * @return 记录信息
     */
    @Select("select id, flightNumber, planeNumber, boardingTime, gatePosition, departureStation, destination, time, special\n" +
            "from flightInformation where id = #{id}")
    FlightInformation queryById(int id);

    /**
     * 更新一条航班信息记录
     *
     * @param flightInformation 航班信息
     * @return 更新影响的记录条数
     */
    @Update("update flightInformation set flightNumber = #{flightNumber}, planeNumber = #{planeNumber}, boardingTime = #{boardingTime}, " +
            "gatePosition = #{gatePosition}, departureStation = #{departureStation}, destination = #{destination}, time = #{time}, " +
            "special = #{special} where id = #{id}")
    int updateRecord(FlightInformation flightInformation);
}
