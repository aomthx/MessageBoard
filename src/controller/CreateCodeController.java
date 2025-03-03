package controller;

import cn.dsna.util.images.ValidateCode;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CreateCodeController" , urlPatterns = "/createCode")
public class CreateCodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ValidateCode validateCode = new ValidateCode(200,30,4,20);
        //拿到输入的字符串
        String codes = validateCode.getCode();
        //把验证码存到session中
        HttpSession session = req.getSession();
        session.setAttribute("codes",codes);
        validateCode.write(resp.getOutputStream());
    }
}
