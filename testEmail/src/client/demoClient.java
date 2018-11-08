package client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.*;

import temp.ServiceInter;
import temp.ServiceInter_Service;

public class demoClient extends JFrame implements ActionListener
{
    private static ServiceInter sendMail;
    public static void main(String[] args)
    {
        ServiceInter_Service sendMailInterface_Service=new ServiceInter_Service();
        sendMail=sendMailInterface_Service.getSendEmailPort();
        mail_frame();
    }
    private static JFrame frame = new JFrame("�����ʼ�");
    private static Container c = frame.getContentPane();
    private static JTextField input1 = new JTextField();
    static JLabel a1 = new JLabel("���ŷ���ַ�ã�����");
    private static JTextField input2 = new JTextField();
    static JLabel a2 = new JLabel("����:");
    static JLabel a3=new JLabel("����:");
    private static JTextField input3=new JTextField();
    private static JButton send = new JButton("����");


    static void mail_frame()
    {


        frame.setSize(500,400);

        c.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);

        a1.setBounds(50,20,250,20);
        fieldPanel.add(a1);
        input1.setBounds(50,50,250,20);
        fieldPanel.add(input1);

        a2.setBounds(50,140,80,20);
        fieldPanel.add(a2);
        input2.setBounds(50,160,250,100);
        fieldPanel.add(input2);
        //c.add(fieldPanel,"Center");

        a3.setBounds(50,80,80,20);
        fieldPanel.add(a3);
        input3.setBounds(50,110,250,20);
        fieldPanel.add(input3);
        c.add(fieldPanel,"Center");

        demoClient client=new demoClient();
        send.addActionListener(client);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(send);
        c.add(buttonPanel,"South");

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO �Զ����ɵķ������
        if(e.getActionCommand()=="����")
        {
            String url=input1.getText();
            Vector<String> URL=new Vector<String>();
            StringTokenizer st = new StringTokenizer(url,";");
            int i=0;
            while(st.hasMoreTokens() )
            {
                URL.addElement(st.nextToken());
                System.out.println(URL.get(i));
                i++;
            }
            String payload=input2.getText();
            String theme=input3.getText();
            if(URL.size()>=1)
            {
                sendMail.sendEmailDemo(URL, payload,theme);
                JOptionPane.showMessageDialog( null , "���ͳɹ���" ,"֪ͨ" , JOptionPane.ERROR_MESSAGE) ;
                System.out.println("���ͳɹ�");
                //input2.setText("");
            }
            else {
                System.out.println("N");
            }
        }
    }
}
