package controller;
/**
 * The base class of all controller classes
 *
 * Once the method in controller classes has been invoked in the BaseFilter class
 *this class will handle the return value of the invoked method
 * and process the message that should be printed on the screen
 */

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public abstract class BaseController extends HttpServlet {
    protected static final String InvalidRequestUrl = "#/index.jsp?msg=Invalid Request";
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) {
        //Class get the method that will be invoked from Session attribute
        String inv_method = req.getSession().getAttribute("invMethod").toString();
        try {
            //invoke the method
            Method method = this.getClass().getMethod(inv_method, HttpServletRequest.class, HttpServletResponse.class);
            //get the result
            String redirAddr = method.invoke(this, req, res).toString();

            //if the uri has msg param(this is for server method redirect)
            if (req.getAttribute("msg") != null) {
                //only support when uri start with @
                req.setAttribute("msg", req.getAttribute("msg"));
            }
            if (redirAddr.startsWith("@")) {
                //server go to another method
                res.sendRedirect(redirAddr.substring(1));
            } else if (redirAddr.startsWith("#")) {
                //if the uri has msg param (this is for client page redirect)
                //only support when uri with no prefix
                req.setAttribute("msg", StringUtils.substringAfter(redirAddr, "msg="));
                req.getRequestDispatcher(StringUtils.substringBetween(redirAddr, "#", "?")).forward(req, res);
            } else if (redirAddr.startsWith("^")) {
                //direct out put the string
                res.getWriter().print(StringUtils.substring(redirAddr, 1));
            } else {
                //client go to another page
                if(req.getSession().getAttribute("msg")!=null){
                    //add message to page
                    req.setAttribute("msg",req.getSession().getAttribute("msg").toString());
                    req.getSession().removeAttribute("msg");
                }
                req.getRequestDispatcher(redirAddr).forward(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
