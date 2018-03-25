package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordVerify extends BaseController{
    public String octal(HttpServletRequest req, HttpServletResponse res){
        //TODO
        req.setAttribute("scheme",1);
        req.setAttribute("PasswordType",req.getParameter("type"));
        return "passwordverify.jsp";
    }
    public String image(HttpServletRequest req, HttpServletResponse res){
        //todo
        req.setAttribute("scheme",2);
        String imagestep_s = req.getParameter("imagens");
        int imagens;
        int image_size = 0;
        if (imagestep_s == null){
            imagens = 1;
        }else{
            imagens = Integer.parseInt(imagestep_s);
        }
        if(imagens < 3){
            image_size = 64;

        }else if (imagens == 3){
            image_size = 8;
        }else{
            return "@flow_next";
        }




        return "passwordverify.jsp";
    }
    public String binary(HttpServletRequest req, HttpServletResponse res){
        //todo
        req.setAttribute("scheme",3);
        return null;
    }
}
