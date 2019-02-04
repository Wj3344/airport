package cn.fanchencloud.airport.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-2-3
 * Time: 下午3:51
 * Description: 让配置在request请求时生效
 *
 * @author chen
 */
@Configuration
public class CustomDateEditorConfiguration {
    @Autowired
    public void setWebBindingInitializer(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        requestMappingHandlerAdapter.setWebBindingInitializer(new CustomDateWebBindingInitializer());
    }
}
