package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.Identity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-6
 * Time: 下午3:05
 * Description:
 *
 * @author chen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserIdentityMapperTest {
    private UserIdentityMapper userIdentityMapper;

    @Test
    public void getIdentityByUsername() {
        List<Identity> fanchen0 = userIdentityMapper.findByUsername("fanchen0");
        for (Identity identity : fanchen0) {
            System.out.println(identity);
        }

    }


    @Autowired
    public void setUserIdentityMapper(UserIdentityMapper userIdentityMapper) {
        this.userIdentityMapper = userIdentityMapper;
    }
}