package cn.fanchencloud.airport.config.filter;

import cn.fanchencloud.airport.entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午5:25
 * Description:
 *
 * @author chen
 */
public class UserFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(UserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        logger.info("用户过滤器开始工作：");
        // 判断用户是否登录
//        Admin admin = (Admin) request.getSession().getAttribute("admin");
//        if (admin == null) {
//            // 用户未登录，重定向到登录界面
//            logger.info("用户未登录，重定向到登录界面");
//            response.sendRedirect("index");
//        }
    }

    @Override
    public void destroy() {

    }
}
