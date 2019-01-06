package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.Permission;
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
 * Time: 下午4:11
 * Description:
 *
 * @author chen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPermissionMapperTest {

    private UserPermissionMapper userPermissionMapper;

    @Test
    public void testQuery() {
        List<Permission> fanchen0 = userPermissionMapper.findByUserName("fanchen0");
        for (Permission p :
                fanchen0) {
            System.out.println(p);
        }
    }

    @Autowired
    public void setUserIdentityMapper(UserPermissionMapper userPermissionMapper) {
        this.userPermissionMapper = userPermissionMapper;
    }
}