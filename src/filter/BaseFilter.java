package filter;

import bean.User;
import org.apache.commons.lang3.StringUtils;
import util.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseFilter implements Filter{
    public void init(FilterConfig filterConfig) {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        if(req.getSession().getAttribute("user") == null){
            //first time open web
            User user = new User();
            user.setUserid(User.uid++);
            req.getSession().setAttribute("user",user);
            Logger.writeLog(user,"Login",req.getRemoteUser(),"");

        }
        String contextpath = req.getContextPath();
        String uri = req.getRequestURI();
        uri = StringUtils.remove(uri,contextpath);
        if(uri.contains("_")){
            //locate request handler
            String servlet = StringUtils.substringBefore(uri,"_");
            servlet+="Servlet";
            String method = StringUtils.substringAfter(uri,"_");
            req.getSession().setAttribute("invMethod",method);
            req.getRequestDispatcher(servlet).forward(req,res);
            return;
        }
        filterChain.doFilter(req,res);
    }

    public void destroy() {

    }
}
