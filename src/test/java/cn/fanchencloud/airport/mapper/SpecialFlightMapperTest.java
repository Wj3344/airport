package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.PassengerTag;
import cn.fanchencloud.airport.entity.SpecialFlight;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午8:43
 * Description:
 *
 * @author chen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecialFlightMapperTest {

    private SpecialFlightMapper specialFlightMapper;

    /**
     * 测试查询
     */
    @Test
    public void testQuery() {
        List<SpecialFlight> all = specialFlightMapper.findAll();
        for (SpecialFlight specialFlight : all) {
            System.out.println(specialFlight);
        }

        SpecialFlight byId = specialFlightMapper.findById(8);
        System.out.println(byId);
    }

    /**
     * 测试添加
     */
    @Test
    public void testAdd() {
        int tag = specialFlightMapper.addSpecialFlight("测试标签1");
        System.out.println(tag);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        int i = specialFlightMapper.deleteSpecialFlightById(3);
        System.out.println(i);
        i = specialFlightMapper.deleteSpecialFlightByTag("测试标签777");
        System.out.println(i);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        SpecialFlight tag = new SpecialFlight();
        tag.setId(4);
        tag.setDescribe("测试标签777");
        int i = specialFlightMapper.updateSpecialFlight(tag);
        System.out.println(i);
    }

    @Autowired
    public void setSpecialFlightMapper(SpecialFlightMapper specialFlightMapper) {
        this.specialFlightMapper = specialFlightMapper;
    }
}