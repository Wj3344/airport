package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.PassengerTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-7
 * Time: 下午8:22
 * Description:
 *
 * @author chen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PassengerTagMapperTest {
    private PassengerTagMapper passengerTagMapper;

    /**
     * 测试查询
     */
    @Test
    public void testQuery() {
        List<PassengerTag> all = passengerTagMapper.findAll();
        for (PassengerTag passengerTag : all) {
            System.out.println(passengerTag);
        }

        PassengerTag byId = passengerTagMapper.findById(8);
        System.out.println(byId);
    }

    /**
     * 测试添加
     */
    @Test
    public void testAdd() {
        int tag = passengerTagMapper.addPassengerTag("测试标签2");
        System.out.println(tag);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        int i = passengerTagMapper.deletePassengerTagById(11);
        System.out.println(i);
        i = passengerTagMapper.deletePassengerTagByTag("测试标签1");
        System.out.println(i);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        PassengerTag tag = new PassengerTag();
        tag.setId(10);
        tag.setDescribe("测试标签1");
        int i = passengerTagMapper.updatePassengerTag(tag);
        System.out.println(i);
    }

    @Autowired
    public void setPassengerTagMapper(PassengerTagMapper passengerTagMapper) {
        this.passengerTagMapper = passengerTagMapper;
    }
}