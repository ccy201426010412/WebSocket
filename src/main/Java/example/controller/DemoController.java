package example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/home")
public class DemoController {

    @RequestMapping(method = RequestMethod.GET , value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST , value = "/login")
    public String login(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String username= request.getParameter("username");
        request.getSession().setAttribute("username", username);
        return "chat";
    }

}