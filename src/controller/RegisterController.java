package controller;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterController", urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        
        UserService userService = new UserService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        if(userService.queryUserByEmail(email) != null) {
            PrintWriter out = resp.getWriter();
            out.write("用户邮箱以被注册");
            return;
        }
        String sessionCode = String.valueOf(req.getSession().getAttribute("code"));
        if (!code.isEmpty() && code.equalsIgnoreCase(sessionCode)){
            int isSuccessful = userService.register(username, email, password);
            if(isSuccessful > 0){
                PrintWriter out = resp.getWriter();
                out.write("注册成功");
            }
        }else {
            PrintWriter out = resp.getWriter();
            out.write("验证码错误");
        }
    }
}
