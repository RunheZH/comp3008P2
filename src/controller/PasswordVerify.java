package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordVerify extends BaseController{
    public String octal(HttpServletRequest req, HttpServletResponse res){
        req.setAttribute("scheme",1);
        req.setAttribute("type",req.getParameter("type"));
        return "passwordverify.jsp";
    }
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
    public String binary(HttpServletRequest req, HttpServletResponse res){
        req.setAttribute("scheme",3);
        req.setAttribute("type",req.getParameter("type"));
        return "passwordverify.jsp";
    }
}
