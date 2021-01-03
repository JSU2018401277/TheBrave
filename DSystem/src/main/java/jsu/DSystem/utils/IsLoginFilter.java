package jsu.DSystem.utils;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")//范围为全界面过滤

public class IsLoginFilter implements Filter {

    private String filterName;
    private String encoding = "GBK";
    private Log log = LogFactory.getLog(IsLoginFilter.class);

    public void destroy() {

    }

    /*
     * 注意传参不是httpservletRequest和httpservletResponse
     *
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("====>过滤器被使用");
        request.setCharacterEncoding(encoding);//设置编码格式
        HttpServletRequest request0 = (HttpServletRequest) request;
        HttpServletResponse response0 = (HttpServletResponse) response;
        HttpSession session = request0.getSession();//获取session方法
        String local = request0.getServletPath();//获取访问的地址


        if ((local.indexOf(".html")!=-1||local.indexOf(".jsp")!=-1)&&local.indexOf("login")==-1 && session.getAttribute("SESSION_USER") == null ) {
            response0.sendRedirect("login.html");
            return;
            //若访问地址不为登陆界面且从session获取userName如果为空则跳转至登陆界面
        }
        chain.doFilter(request, response);//继续进行下一个过滤器，如果没有则返回jsp等

    }
    public void init(FilterConfig config) throws ServletException {
        filterName = config.getFilterName();

        System.out.println("过滤器名称：" + filterName);
    }
}