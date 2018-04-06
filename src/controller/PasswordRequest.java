package controller;
/**
 * Password request page generator
 *
 * three method -> three schemes
 */

import bean.Password;
import bean.PwType;
import bean.Scheme;
import bean.User;
import util.Logger;
import util.PasswordConvertor;
import util.RandomNumberGenerator;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordRequest extends BaseController {

    /*
    Type define:
    1->ocatl
    2->image
    3->binary
     */

    /**
     * handle user request octal password
     * @param req
     * @param res
     * @return
     */
    public String octal(HttpServletRequest req, HttpServletResponse res) {
        User user  = (User) req.getSession().getAttribute("user");
        int step = Integer.parseInt(req.getSession().getAttribute("nextstep").toString());

        Password password = null;
        if(user.getPassword(PwType.TypeMapping.get(step))!=null){
            //not the first time on this password
            String target_scheme = Scheme.OCTAL;
            password = user.getPassword(PwType.TypeMapping.get(step));
            if(password == null){
                //error handler
                req.getSession().removeAttribute("user");
                return InvalidRequestUrl;
            }
            if(!Scheme.verifyScheme(target_scheme)){
                return InvalidRequestUrl;
            }
            password = PasswordConvertor.convertPasswordTo(password,target_scheme);
            user.addPassword(PwType.TypeMapping.get(step),password);
        }else{
            //first time create password
            String scheme = Scheme.OCTAL;
            password = new Password();
            password.setCurrentScheme(scheme);
            int password_int = RandomNumberGenerator.GenerateRandomNumber();//generate new password
            password.setPassword(password_int);
            password.setPassword_representative(Integer.toString(password_int));
            String step_str = req.getSession().getAttribute("nextstep").toString();
            int step_int = Integer.parseInt(step_str);
            String pwType = PwType.TypeMapping.get(step_int);
            user.addPassword(pwType,password);
        }
        //add user to session
        Logger.writeLog(user,"SETSCHEME",Scheme.OCTAL,PwType.TypeMapping.get(step));
        req.getSession().setAttribute("user",user);
        req.setAttribute("password",password.getPassword_representative());
        req.setAttribute("scheme",1);
        req.setAttribute("type",PwType.TypeMapping.get(step));
        return "/passwordgen.jsp";
    }

    /**
     * handle user image request
     * @param req
     * @param res
     * @return
     */
    public String image(HttpServletRequest req, HttpServletResponse res) {
        //todo
        User user = (User) req.getSession().getAttribute("user");
        int step = Integer.parseInt(req.getSession().getAttribute("nextstep").toString());
        Logger.writeLog(user,"SETSCHEME",Scheme.IMAGE,PwType.TypeMapping.get(step));
        Password password = user.getPassword(PwType.TypeMapping.get(step));
        //image next step
        //if the param does not exist -> step = 1
        //else step = what param is
        int image_current_step = (req.getParameter("imagens")==null?1:Integer.parseInt(req.getParameter("imagens")));
        int image_size = 0;//how many images in the page
        String row = "";//high light image row
        String col = "";//high light image col
        /**
         * step->1
         * Initial password scheme(change pwd scheme -> image)
         * Image size 8*8
         * Assign image row and col to request
         * set next step to request
         *
         * step->2&3
         * Assign image row and col to request
         * Image size: 8*8
         * set next step to 3
         *
         * step->4
         * Image size 4*2
         * Set next step to confirm
         *
         * step->5
         * goto next Type
         */
        if(image_current_step == 1){
            Logger.writeLog(user,"REMEMBER",Integer.toString(image_current_step),"");
            //password initial
            if(password==null){
                return InvalidRequestUrl;
            }
            password = PasswordConvertor.convertPasswordTo(password,Scheme.IMAGE);
            user.addPassword(PwType.TypeMapping.get(step),password);
            req.getSession().setAttribute("user",user);
            //
            String password_s = Integer.toString(password.getPassword());
            row = Character.toString(password_s.charAt(0));
            col = Character.toString(password_s.charAt(1));
            image_size = 64;
        }else if (image_current_step < 4){
            Logger.writeLog(user,"REMEMBER",Integer.toString(image_current_step),"");
            assert password != null;
            image_size = 64;
            String password_s = Integer.toString(password.getPassword());
            row = Character.toString(password_s.charAt((image_current_step-1)*2));
            col = Character.toString(password_s.charAt((image_current_step-1)*2+1));
        }else if (image_current_step == 4){
            Logger.writeLog(user,"REMEMBER",Integer.toString(image_current_step),"");
            assert  password !=null;
            String password_s = Integer.toString(password.getPassword());
            row = Integer.toString((password_s.charAt(6)-48)/5 +1);
            col = Integer.toString((password_s.charAt(6)-48)>4?(password_s.charAt(6)-48)-4:(password_s.charAt(6)-48));
            image_size = 8;
        }else if(image_current_step == 5){
            return "@flow_confirm";
        }
        req.setAttribute("imagens",++image_current_step);//set next step
        req.setAttribute("scheme",2);//set scheme
        req.setAttribute("row",row);//set row
        req.setAttribute("col",col);//set col
        req.setAttribute("image_size",image_size);//set size
        req.setAttribute("type",PwType.TypeMapping.get(step));
        return "/passwordgen.jsp";
    }

    /**
     * handle user binary request
     * @param req
     * @param res
     * @return
     */
    public String binary(HttpServletRequest req, HttpServletResponse res) {
        User user = (User) req.getSession().getAttribute("user");
        int step = Integer.parseInt(req.getSession().getAttribute("nextstep").toString());
        Logger.writeLog(user,"SETSCHEME",Scheme.BINARY,PwType.TypeMapping.get(step));
        Password password = user.getPassword(PwType.TypeMapping.get(step));
        password = PasswordConvertor.convertPasswordTo(password,Scheme.BINARY);
        user.addPassword(PwType.TypeMapping.get(step),password);
        req.setAttribute("password",password.getPassword_representative());
        req.setAttribute("scheme",3);
        req.setAttribute("type",PwType.TypeMapping.get(step));
        return "/passwordgen.jsp";
    }

}
