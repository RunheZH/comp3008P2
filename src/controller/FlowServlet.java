package controller;

import bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlowServlet extends BaseController{
    public String start(HttpServletRequest req, HttpServletResponse res){
        //todo
        //initial user data
        req.getSession().setAttribute("step",1);
        return null;
    }
    public String next(HttpServletRequest req, HttpServletResponse res){
        Integer step = Integer.parseInt(req.getSession().getAttribute("step").toString());

        //todo
        if(step>3){
            //verify
        }else{
            //request
        }
        return null;
    }
    public String end(HttpServletRequest req, HttpServletResponse res){
        User user = (User)req.getSession().getAttribute("user");
        req.setAttribute("log",user.getLog());
        //todo
        return null;
    }
}
