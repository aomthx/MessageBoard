package controller;

import entity.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        String sessionCode = String.valueOf(req.getSession().getAttribute("codes"));

        UserService userService =new UserService();
        User user = new User();

        if(!code.isEmpty() && sessionCode.equalsIgnoreCase(code)){
            user = userService.login(email, password);
            if (user!=null){
                if(user.getStatus() == 0){
                    HttpSession session = req.getSession();
                    session.setAttribute("User",user);
                    resp.getWriter().write("success");
                }else{
                    resp.getWriter().write("账号已被封禁");
                }
            }else {
                resp.getWriter().write("账号或密码错误");
            }
        }else {
            PrintWriter out = resp.getWriter();
            out.write("验证码错误");
        }
    }
}

