package pub;
import javax.swing.*;
import javax.xml.ws.Endpoint;
import temp.sendEmail;


public class publish {
    public static void main(String []args){
        //String address="http://172.16.20.113:8888/Webservice";
        String address="http://localhost:8888/Webservice";
        Endpoint.publish(address,new sendEmail());
        JOptionPane.showMessageDialog( null , "发布成功！" ,"提示" , JOptionPane.ERROR_MESSAGE) ;
       System.out.println("已发布");

    }
}
