package filter;

import javax.servlet.*;
import java.io.IOException;

public class BaseFilter implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //todo

    }

    public void destroy() {

    }
}
