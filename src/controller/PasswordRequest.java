package controller;

import bean.Password;
import bean.PwType;
import bean.Scheme;
import bean.User;
import util.RandomNumberGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordRequest extends BaseController {
    public String octal(HttpServletRequest req, HttpServletResponse res) {
        Scheme scheme = Scheme.CreateOctalScheme();
        Password password = new Password();
        password.setCurrentScheme(scheme);
        int password_int = RandomNumberGenerator.GenerateRandomNumber();
        password.setPassword(password_int);
        String target = req.getParameter("purpose");//for xxxx
        PwType pwType = new PwType();
        if(pwType.setType(target)==-1){
            return "#/index.jsp?msg=Invalid Password Type";
        }
        User user  = (User) req.getSession().getAttribute("user");
        user.addPassword(pwType,password);
        req.getSession().setAttribute("user",user);
        req.setAttribute("password",password_int);
        /**
         * TODO!!!
         * Create Flow Servlet
         * Add next method
         */
        return "/passwordgen.jsp";
    }

    public String image(HttpServletRequest req, HttpServletResponse res) {
        //todo
        return null;
    }

    public String binary(HttpServletRequest req, HttpServletResponse res) {
        //todo
        return null;
    }
}
