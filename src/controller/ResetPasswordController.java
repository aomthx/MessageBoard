package controller;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SetPasswordController", urlPatterns = "/resetPassword")
public class ResetPasswordController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        UserService userService = new UserService();
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        if(userService.queryUserByEmail(email) == null) {
            PrintWriter out = resp.getWriter();
            out.write("邮箱未注册");
            return;
        }
        String sessionCode = String.valueOf(req.getSession().getAttribute("code"));
        if (!code.isEmpty() && code.equalsIgnoreCase(sessionCode)){
            int isSuccessful = userService.setPassword(email, password);
            if(isSuccessful > 0){
                PrintWriter out = resp.getWriter();
                out.write("success");
            }
        }else {
            PrintWriter out = resp.getWriter();
            out.write("验证码错误");
        }
    }
}
