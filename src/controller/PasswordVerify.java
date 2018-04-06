package controller;
/**
 *Password verifycation page generator
 *
 * Three methods -> three schemes
 */

import bean.Scheme;
import bean.User;
import util.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordVerify extends BaseController{
    /**
     * handle user request octal password verify page
     * @param req
     * @param res
     * @return
     */
    public String octal(HttpServletRequest req, HttpServletResponse res){
        req.setAttribute("scheme",1);
        req.setAttribute("type",req.getParameter("type"));
        Logger.writeLog((User)req.getSession().getAttribute("user"),"VERIFY", Scheme.OCTAL,req.getParameter("type"));
        return "passwordverify.jsp";
    }

    /**
     * handle user request image password verify page
     * @param req
     * @param res
     * @return
     */
    public String image(HttpServletRequest req, HttpServletResponse res){
        //todo
        req.setAttribute("type",req.getParameter("type"));
        req.setAttribute("scheme",2);
        String imagestep_s = req.getParameter("imagens");
        String current_password;
        int imagens;
        int image_size = 0;
        String row;
        String col;
        Logger.writeLog((User)req.getSession().getAttribute("user"),"VERIFY", Scheme.IMAGE,req.getParameter("type"));
        if (imagestep_s == null){
            imagens = 1;
            current_password = "";
        }else{
            imagens = Integer.parseInt(imagestep_s);
            current_password = req.getParameter("password");

        }
        if(imagens < 4){
            image_size = 64;

        }else if (imagens == 4){
            image_size = 8;
        }else{
            return "@flow_verify?password="+current_password+"&type="+req.getParameter("type");
        }
        req.setAttribute("imagens",++imagens);
        req.setAttribute("image_size",image_size);
        req.setAttribute("current_password",current_password);

        return "passwordverify.jsp";
    }

    /**
     * handle user request binary password verify page
     * @param req
     * @param res
     * @return
     */
    public String binary(HttpServletRequest req, HttpServletResponse res){
        req.setAttribute("scheme",3);
        req.setAttribute("type",req.getParameter("type"));
        Logger.writeLog((User)req.getSession().getAttribute("user"),"VERIFY", Scheme.OCTAL,req.getParameter("type"));
        return "passwordverify.jsp";
    }
}
