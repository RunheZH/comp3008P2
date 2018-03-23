package controller;

import bean.Password;
import bean.PwType;
import bean.Scheme;
import bean.User;

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
        if (step > 3) {
            //verify
            User user = (User) req.getSession().getAttribute("user");
            Random random = new Random();
            int r_num = -1;
            Password password = null;
            while (password == null) {
                //bad style
                r_num = random.nextInt(3);
                password = user.getPassword(PwType.TypeMapping.get(step - r_num));
            }
            user.removePassword(PwType.TypeMapping.get(step - r_num));
            String scheme = password.getCurrentScheme();
            return "@verify_" + scheme.toLowerCase();
        } else if (step == 7) {
            //log
            return "@flow_end";
        } else {
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
        String returnul = null;
        int step = Integer.parseInt(req.getSession().getAttribute("nextstep").toString());
        if(step >3){
            //verify
            returnul = "/verify_";
        }else{
            returnul = "/request_";
        }
        switch (target) {
            case Scheme.BINARY:
                //to binary
                returnul+="binary";
                break;
            case Scheme.IMAGE:
                returnul+="image";
                break;
            case Scheme.OCTAL:
                returnul+="octal";
                break;
            default:
                return InvalidRequestUrl;
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
        req.setAttribute("log", user.getLog());
        //todo
        return null;
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

}
