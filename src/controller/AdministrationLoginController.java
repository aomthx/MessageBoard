package controller;

import entity.Administration;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdministrationLoginController", urlPatterns = "/ad_login")
public class AdministrationLoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        Administration administration = new Administration();
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        String sessionCode = String.valueOf(req.getSession().getAttribute("codes"));

        if(!code.isEmpty() && sessionCode.equalsIgnoreCase(code)){
            boolean b = administration.login(user, password);
            if (b){
                resp.sendRedirect(req.getContextPath()+"/ad_messageboard.html");
            }else {
                resp.sendRedirect(req.getContextPath()+"/login.html");
            }
        }else {
            resp.sendRedirect(req.getContextPath()+"/login.html");
        }
    }
}
