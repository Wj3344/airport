package cn.fanchencloud.airport.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-2-3
 * Time: 下午3:50
 * Description: 扩展web初始化的配置
 *
 * @author chen
 */
public class CustomDateWebBindingInitializer implements WebBindingInitializer {

    @Override
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor());
    }
}
