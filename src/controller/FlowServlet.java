package controller;

import bean.Password;
import bean.PwType;
import bean.Scheme;
import bean.User;
import org.apache.commons.lang3.tuple.Pair;
import util.Logger;
import util.VerifyPassword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;
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
        Logger.writeLog((User) req.getSession().getAttribute("user"), "START", "", "");
        req.getSession().setAttribute("nextstep", 1);
        return "@flow_next";
    }

    /**
     * handle user confirm their password
     *
     * @param req
     * @param res
     * @return
     */
    public String confirm(HttpServletRequest req, HttpServletResponse res) {
        Logger.writeLog((User) req.getSession().getAttribute("user"), "CONFIRM", "", "");
        int step = Integer.parseInt(req.getSession().getAttribute("nextstep").toString());
        Integer fail_count = (Integer) req.getSession().getAttribute("fail_time");
        if(fail_count == null || fail_count == 3){
            req.getSession().removeAttribute("fail_time");
            req.getSession().setAttribute("nextstep",++step);
        }
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
        if (step == 7) {
            Logger.writeLog((User) req.getSession().getAttribute("user"), "FINISH", "", "");
            return "@flow_end";
        } else if (step > 3) {
            //initial
            String type = null;
            User user = (User) req.getSession().getAttribute("user");
            if (user.getRandom_password_list().size() == 0) {
                //randomize password
                Random random = new Random();
                int r_num;
                Password password = null;
                while (!user.pwdListIsEmpty()) {
                    r_num = random.nextInt(3) + 1;
                    password = user.getPassword(PwType.TypeMapping.get(r_num));
                    if (password == null) {
                        continue;
                    }
                    user.addToRandomList(Pair.of(PwType.TypeMapping.get(r_num), user.getPassword(PwType.TypeMapping.get(r_num))));
                    user.removePassword(PwType.TypeMapping.get(r_num));
                }

            }
            type = req.getParameter("type");
            if (type == null) {
                type = user.getRandom_password_list().get(step - 4).getLeft();
            }
            req.getSession().setAttribute("user", user);
            String scheme = (req.getParameter("scheme") == null) ? Scheme.OCTAL : req.getParameter("scheme");
            return "@verify_" + scheme.toLowerCase() + "?type=" + type;


        } else {
            //request
            Logger.writeLog((User) req.getSession().getAttribute("user"), "REQUESTPASS", PwType.TypeMapping.get(step), "");
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

        if (step > 3) {
            //verify
            returnul = "@flow_next?scheme=";
        } else {
            returnul = "@request_";
        }
        switch (target) {
            case Scheme.BINARY:
                //to binary
                returnul += Scheme.BINARY;
                break;
            case Scheme.IMAGE:
                returnul += Scheme.IMAGE;
                break;
            case Scheme.OCTAL:
                returnul += Scheme.OCTAL;
                break;
            default:
                return InvalidRequestUrl;
        }
        if (step > 3) {
            returnul += ("&type=" + type);
        } else {
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
        Logger.writeLog(user, "FINISH", "", "");
        return "/thankyou.jsp";
    }


    public String verify(HttpServletRequest req, HttpServletResponse res) {
        String user_password = req.getParameter("password");
        User user = (User) req.getSession().getAttribute("user");
        String password_type = req.getParameter("type");

        Integer step = Integer.parseInt(req.getSession().getAttribute("nextstep").toString());
        Password password = user.getRandom_password_list().get(step-4).getRight();
        if (VerifyPassword.verify(password, user_password)) {
            req.getSession().setAttribute("msg", "Yeahhhhh!!!! You have entered a correct password");
            req.getSession().removeAttribute("fail_time");
            Logger.writeLog(user, "RESULT", "SUCCESS", "");
        } else {
            Integer fail_count = (Integer) req.getSession().getAttribute("fail_time");
            if (fail_count == null) {
                //first time fail
                fail_count = 0;
            }
            fail_count++;
            req.getSession().setAttribute("fail_time", fail_count);
            req.getSession().setAttribute("msg", "Sorry. Your password is not correct");
            Logger.writeLog(user, "RESULT", "Fail", "");
        }
        return "@flow_confirm";
    }

    public String index(HttpServletRequest req, HttpServletResponse res) {
        return "/index.jsp";
    }


}
