package wang.mh.base.network;


import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * 通过java Mail Api  发生邮件
 */
public class MailDemo {
    private static String from = "hellowmh@163.com";
    private static String to = "hellowmh@163.com";

    public static void main(String[] args) throws IOException, MessagingException {
        Properties properties = new Properties();
        Transport transport = null;
        try (InputStream in = Files.newInputStream(Paths.get("/Users/wmh/mine/IdeaProjects/mine/java-learning/src/main/resources/mail.properties"))){
            properties.load(in);
            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            message.setSubject("hello,world");
            message.setText("bye,bye,world");
            transport = session.getTransport();
            transport.connect(null, "");
            transport.sendMessage(message, message.getAllRecipients());
        }finally {
            transport.close();
        }

    }
}
