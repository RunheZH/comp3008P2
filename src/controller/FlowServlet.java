package controller;

import bean.Password;
import bean.PwType;
import bean.Scheme;
import bean.User;
import util.VerifyPassword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

public class FlowServlet extends BaseController {
    /**
     * Handle user click start button which is on the first page
     *
     * @param req
     * @param res
     * @return
     */
    public String start(HttpServletRequest req, HttpServletResponse res) {
        //initial user data
        req.getSession().setAttribute("nextstep", 1);
        return "@request_octal";
    }

    /**
     * handle user confirm their password
     *
     * @param req
     * @param res
     * @return
     */
    public String confirm(HttpServletRequest req, HttpServletResponse res) {
        int step = Integer.parseInt(req.getSession().getAttribute("nextstep").toString());
        req.getSession().setAttribute("nextstep", ++step);
        return "@flow_next";
    }

    /**
     * Main control method
     *
     * @param req
     * @param res
     * @return
     */
    public String next(HttpServletRequest req, HttpServletResponse res) {
        Integer step = Integer.parseInt(req.getSession().getAttribute("nextstep").toString());
        //todo
        if(step == 7) {
            return "@flow_end";
        }else if (step > 3) {
            //verify
            User user = (User) req.getSession().getAttribute("user");
            String type = req.getParameter("type");
            if (type == null){
                Random random = new Random();
                int r_num = -1;
                Password password = null;
                while (password == null) {
                    //bad style
                    r_num = random.nextInt(3)+1;
                    password = user.getPassword(PwType.TypeMapping.get(r_num));
                }
                type = PwType.TypeMapping.get(r_num);
            }

            req.getSession().setAttribute("user",user);

            String scheme = (req.getParameter("scheme")==null)?Scheme.OCTAL:req.getParameter("scheme");
            return "@verify_" + scheme.toLowerCase()+"?type="+type;
        }else {
            //request
            return "@request_octal";
        }
    }

    /**
     * Handle user scheme change request
     *
     * @param req
     * @param res
     * @return pwdreq or pwdverify
     */
    public String changescheme(HttpServletRequest req, HttpServletResponse res) {
        String target = req.getParameter("target");
        String type = req.getParameter("type");
        String returnul = null;
        int step = Integer.parseInt(req.getSession().getAttribute("nextstep").toString());

        if(step >3){
            //verify
            returnul = "@flow_next?scheme=";
        }else{
            returnul = "@request_";
        }
        switch (target) {
            case Scheme.BINARY:
                //to binary
                returnul+=Scheme.BINARY;
                break;
            case Scheme.IMAGE:
                returnul+=Scheme.IMAGE;
                break;
            case Scheme.OCTAL:
                returnul+=Scheme.OCTAL;
                break;
            default:
                return InvalidRequestUrl;
        }
        if(step>3){
            returnul+=("&type="+type);
        }else{
            returnul = returnul.toLowerCase();
        }
        return returnul;
    }

    /**
     * Handle user finish their "memory test"
     *
     * @param req
     * @param res
     * @return
     */
    public String end(HttpServletRequest req, HttpServletResponse res) {
        User user = (User) req.getSession().getAttribute("user");
        req.getSession().removeAttribute("user");
        req.getSession().removeAttribute("msg");
        req.setAttribute("log", user.getLog());
        //todo
        return "/";
    }

    /**
     * Re-generate a password for user
     *
     * @param req
     * @param res
     * @return
     */
    public String reset(HttpServletRequest req, HttpServletResponse res) {
        //todo
        return null;
    }
    public String verify(HttpServletRequest req,HttpServletResponse res){
        String user_password = req.getParameter("password");
        User user = (User) req.getSession().getAttribute("user");
        String password_type = req.getParameter("type");
        Password password = user.getPassword(password_type);
        user.removePassword(password_type);
        if (VerifyPassword.verify(password,user_password)){
            req.getSession().setAttribute("msg","Correct");
            return "@flow_confirm";
        }else{
            req.getSession().setAttribute("msg","wrong");
            return "@flow_confirm";
        }
    }

}
