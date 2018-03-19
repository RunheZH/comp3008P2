package filter;

import bean.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseFilter implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //todo
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        if(req.getSession().getAttribute("user") == null){
            //first time open web
            User user = new User();
        }
        String contextpath = req.getContextPath();
        String uri = req.getRequestURI();
        uri = StringUtils.remove(uri,contextpath);
        if(uri.startsWith("/submit_")){
            //submit password
            String servlet = "pwverservlet";
            String method = StringUtils.substringAfterLast(uri,"_");
            req.getSession().setAttribute("invMethod",method);
            req.getRequestDispatcher("/"+servlet).forward(req,res);
            return;
        }
        if (uri.startsWith("/getpass_")){
            //getpassword
            String servlet ="pwreqservlet";
            String method = StringUtils.substringAfter(uri,"_");
            req.getSession().setAttribute("invMethod",method);
            req.getRequestDispatcher("/"+servlet).forward(req,res);
            return;
        }
        filterChain.doFilter(req,res);

    }

    public void destroy() {

    }
}
