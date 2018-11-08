package temp;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

import javax.jws.WebService;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

@WebService(endpointInterface = "temp.serviceInter",serviceName = "serviceInter")
public class sendEmail implements serviceInter {

    private static MimeMessage message;
    public sendEmail() {
        final String ALIDM_SMTP_HOST = "smtpdm.aliyun.com";
        final String ALIDM_SMTP_PORT = "25";//或"80"
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // props.put("mail.smtp.socketFactory.port", "465");
        // props.put("mail.smtp.port", "465");
        // 发件人的账号，填写控制台配置的发信地址,比如xxx@xxx.com
        props.put("mail.user", "918570725@shawjan.club");
        // 访问SMTP服务时需要提供的密码(在控制台选择发信地址进行设置)
        props.put("mail.password", "LXJlxj20161004133");
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
//        mailSession.setDebug(true);
        //UUID uuid = UUID.randomUUID();
        //final String messageIDValue = "<" + uuid.toString() + ">";
        // 创建邮件消息
        message = new MimeMessage(mailSession);
    }
        @Override
        public boolean sendEmailDemo(List<String> url, String payload,String theme){
    try {
            // 设置发件人邮件地址和名称。填写控制台配置的发信地址,比如xxx@xxx.com。和上面的mail.user保持一致。名称用户可以自定义填写。
            InternetAddress from = new InternetAddress("918570725@shawjan.club", "刘晓剑");
            message.setFrom(from);
            //可选。设置回信地址

            // 设置收件人邮件地址，比如yyy@yyy.com
           // InternetAddress to = new InternetAddress("收件人邮件地址");
           // message.setRecipient(MimeMessage.RecipientType.TO, to);
            //如果同时发给多人，才将上面两行替换为如下（因为部分收信系统的一些限制，尽量每次投递给一个人；同时我们限制单次允许发送的人数是30人）：
            InternetAddress[] adds = new InternetAddress[url.size()];
            for(int i=0;i<url.size();i++) {
                adds[i] = new InternetAddress(url.get(i));
                System.out.println(url);
            }
            message.setRecipients(Message.RecipientType.TO, adds);
            // 设置邮件标题
            message.setSubject(theme);
            // 设置邮件的内容体
            message.setContent(payload, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        }
        catch (MessagingException |UnsupportedEncodingException e) {
            String err = e.getMessage();
            // 在这里处理message内容， 格式是固定的
            System.out.println(err);
        }
        return false;
    }
        @Override
        public boolean check(String email) {
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            return false;
        }
        String host = "";
        String hostName = email.split("@")[1];
        Record[] result = null;
        SMTPClient client = new SMTPClient();
        try {
            // 查找MX记录
            Lookup lookup = new Lookup(hostName, Type.MX);
            lookup.run();
            if (lookup.getResult() != Lookup.SUCCESSFUL) {
                return false;
            } else {
                result = lookup.getAnswers();
            }
            // 连接到邮箱服务器
            for (int i = 0; i < result.length; i++) {
                host = result[i].getAdditionalName().toString();
                client.connect(host);
                if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
                    client.disconnect();
                    continue;
                } else {
                    break;
                }
            }
            //以下2项自己填写快速的，有效的邮箱
            client.login("[图片]163.com");
            client.setSender("sxgkwei@163.com");
            client.addRecipient(email);
            if (250 == client.getReplyCode()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
            }
        }
        return false;
    }

}

