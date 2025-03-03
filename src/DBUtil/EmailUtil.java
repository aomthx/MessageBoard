package DBUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {
    public static void sendEmail(String emailTo, String code) throws MessagingException {
        String emailFrom = "2914307247@qq.com";
        String appPassword = "doegmugwfshbdhff";

        Properties prop = new Properties();
        prop.setProperty("mail.debug", "true");
        prop.setProperty("mail.host", "smtp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.ssl.enable", "true");

        // 创建session，通过session得到transport对象
        Session session = Session.getInstance(prop);
        Transport ts = session.getTransport();
        // 连接邮件服务器
        ts.connect("smtp.qq.com",465, emailFrom, appPassword);

        // 创建邮件
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailFrom));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
        message.setSubject("验证码");
        message.setText("您的验证码：" + code);

        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }
}
