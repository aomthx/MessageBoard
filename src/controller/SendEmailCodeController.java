package controller;

import DBUtil.EmailUtil;
import com.mysql.cj.xdevapi.JsonString;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "SendEmailCodeController", urlPatterns = "/sendEmailCode")
public class SendEmailCodeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String email = req.getParameter("email");
        String code = String.valueOf(new Random().nextInt(900000) + 100000);
        HttpSession session = req.getSession();
        session.setAttribute("code",code);
        try {
            EmailUtil.sendEmail(email, code);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
