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
        final String ALIDM_SMTP_PORT = "25";//��"80"
        // ���÷����ʼ��Ļ�������
        final Properties props = new Properties();
        // ��ʾSMTP�����ʼ�����Ҫ���������֤
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
        // ���ʹ��ssl����ȥ��ʹ��25�˿ڵ����ã�������������,
        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // props.put("mail.smtp.socketFactory.port", "465");
        // props.put("mail.smtp.port", "465");
        // �����˵��˺ţ���д����̨���õķ��ŵ�ַ,����xxx@xxx.com
        props.put("mail.user", "918570725@shawjan.club");
        // ����SMTP����ʱ��Ҫ�ṩ������(�ڿ���̨ѡ���ŵ�ַ��������)
        props.put("mail.password", "LXJlxj20161004133");
        // ������Ȩ��Ϣ�����ڽ���SMTP���������֤
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // �û���������
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // ʹ�û������Ժ���Ȩ��Ϣ�������ʼ��Ự
        Session mailSession = Session.getInstance(props, authenticator);
//        mailSession.setDebug(true);
        //UUID uuid = UUID.randomUUID();
        //final String messageIDValue = "<" + uuid.toString() + ">";
        // �����ʼ���Ϣ
        message = new MimeMessage(mailSession);
    }
        @Override
        public boolean sendEmailDemo(List<String> url, String payload,String theme){
    try {
            // ���÷������ʼ���ַ�����ơ���д����̨���õķ��ŵ�ַ,����xxx@xxx.com���������mail.user����һ�¡������û������Զ�����д��
            InternetAddress from = new InternetAddress("918570725@shawjan.club", "������");
            message.setFrom(from);
            //��ѡ�����û��ŵ�ַ

            // �����ռ����ʼ���ַ������yyy@yyy.com
           // InternetAddress to = new InternetAddress("�ռ����ʼ���ַ");
           // message.setRecipient(MimeMessage.RecipientType.TO, to);
            //���ͬʱ�������ˣ��Ž����������滻Ϊ���£���Ϊ��������ϵͳ��һЩ���ƣ�����ÿ��Ͷ�ݸ�һ���ˣ�ͬʱ�������Ƶ��������͵�������30�ˣ���
            InternetAddress[] adds = new InternetAddress[url.size()];
            for(int i=0;i<url.size();i++) {
                adds[i] = new InternetAddress(url.get(i));
                System.out.println(url);
            }
            message.setRecipients(Message.RecipientType.TO, adds);
            // �����ʼ�����
            message.setSubject(theme);
            // �����ʼ���������
            message.setContent(payload, "text/html;charset=UTF-8");
            // �����ʼ�
            Transport.send(message);
        }
        catch (MessagingException |UnsupportedEncodingException e) {
            String err = e.getMessage();
            // �����ﴦ��message���ݣ� ��ʽ�ǹ̶���
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
            // ����MX��¼
            Lookup lookup = new Lookup(hostName, Type.MX);
            lookup.run();
            if (lookup.getResult() != Lookup.SUCCESSFUL) {
                return false;
            } else {
                result = lookup.getAnswers();
            }
            // ���ӵ����������
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
            //����2���Լ���д���ٵģ���Ч������
            client.login("[ͼƬ]163.com");
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

