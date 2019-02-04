package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.Clean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-22
 * Time: 下午9:06
 * Description:
 *
 * @author chen
 */
@Component
@Mapper
public interface CleanMapper {

    /**
     * 添加一条清洁信息记录到数据库
     *
     * @param clean 清洁信息记录
     * @return 添加结果
     */
    @Insert("insert into clean(flightInformationId, readTime, usedTime, specialCase) values" +
            "(#{flightInformationId}, #{readTime}, #{usedTime}, #{specialCase});")
    int addRecord(Clean clean);

    /**
     * 查询最近的数据
     *
     * @param currentDay 时间限制
     * @return 查询结果
     */
    @Select("select * from clean where createTime >= DATE_SUB(NOW(),INTERVAL #{currentDay} DAY);")
    List<Clean> getCurrentRecord(int currentDay);
}