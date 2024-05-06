package test;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JList;
//import javax.swing.AbstractListModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JButton;

public class ChatClient extends JFrame
implements ActionListener, Runnable {
	//JList li_ChatMb; //������
	JTextField tf_Write; // ä���ۼ�
	JButton btn_send, btn_close;  // ����, ������� ��ư
	JTextArea ta_Chat, ta_ChatMb; // ä�� ����â
	JLabel lbl_ChatMb; // ��(�����ڸ�, )
	Socket sock;
	BufferedReader in;
	PrintWriter out;
	String id;
	String[] name_list = {"user_name"};
	
	public ChatClient() {
		//������ â ����
		setTitle("ä�û��"); //â Ÿ��Ʋ
		setSize(388, 577); // â ������
        setLocationRelativeTo(null); // â ��� ���
		getContentPane().setBackground(new Color(169, 219, 208)); //��� ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���α׷� �����ϴ� ���
		getContentPane().setLayout(null); //���̾ƿ� ������ ��Ȱ��ȭ �� ���� ��ġ
		
		//����ڸ� ���
		JPanel p1 = new JPanel();
		p1.setBackground(new Color(169, 219, 208)); //�г� ����
		p1.setBounds(0, 0, 372, 87); //��ġ, ������
		p1.setLayout(null); //���̾ƿ� ������ ��Ȱ��ȭ �� ���� ��ġ
		add(p1);
		
		
		lbl_ChatMb = new JLabel("��ȭâ");
		lbl_ChatMb.setBounds(12, 10, 57, 15);  //��ġ, ������
		lbl_ChatMb.setFont(new Font("���� ���", Font.BOLD, 14)); //��Ʈ ����
		p1.add(lbl_ChatMb);
		
		//��� �ȳ� ����
		ta_ChatMb = new JTextArea(" �ȳ��ϼ���! Chat ����Դϴ�.\n ��ø� ��ٷ��ֽø� ���簡 ����˴ϴ�."); //ä�� ������
		ta_ChatMb.setBounds(0, 35, 372, 42); //��ġ, ������
		ta_ChatMb.setBackground(new Color(245,255,250)); //�г� ����
		ta_ChatMb.setFont(new Font("���� ���", Font.BOLD, 13)); //��Ʈ ����
		p1.add(ta_ChatMb);
		
		
		//ä��â
		JPanel p2 = new JPanel();
		p2.setBackground(new Color(238, 238, 238)); //�г� ����
		p2.setBounds(0, 86, 372, 395); // ��ġ, ������
		p2.setLayout(null); //���̾ƿ� ������ ��Ȱ��ȭ �� ���� ��ġ
		getContentPane().add(p2); 
		
		ta_Chat = new JTextArea(); //ä�� ������
		ta_Chat.setFont(new Font("���� ���", Font.PLAIN, 14)); //��Ʈ����
		ta_Chat.setBounds(0, 0, 371, 340); //������, ��ġ
		p2.add(ta_Chat);
		
		tf_Write = new JTextField(); //ä�� �Է� â
		tf_Write.setBounds(0, 350, 280, 35); //������, ��ġ
		p2.add(tf_Write);
		
		btn_send = new JButton("�� ��");
		btn_send.setBackground(new Color(255, 255, 255)); //��ư ����
		btn_send.setFont(new Font("���� ���", Font.BOLD, 14)); //��Ʈ ����
		btn_send.setBounds(286, 350, 80, 35); //������, ��ġ
		p2.add(btn_send);
		
		btn_close = new JButton("��� ����");
		btn_close.setBackground(new Color(62, 192, 196)); //��ư ����
		btn_close.setForeground(new Color(255, 255, 255));  //��Ʈ ��
		btn_close.setFont(new Font("���� ���", Font.BOLD, 14));  //��Ʈ ����
		btn_close.setBounds(142, 491, 97, 38); //������, ��ġ
		add(btn_close);
		
		//��ư �̺�Ʈ �޼ҵ� ȣ��
		tf_Write.addActionListener(this); //����Ű �׼Ǹ�����
		btn_send.addActionListener(this); //���� ��ư �׼� ������
		btn_close.addActionListener(this); // ��� ���� ��ư �׼� ������
				
		setVisible(true);
	}
	
	public void run() {
		try {
			while(true) {
				ta_Chat.append(in.readLine()+"\n");
				tf_Write.requestFocus();
			}
		} catch (Exception e) {
			System.err.println("Error in run");
			e.printStackTrace();
			System.exit(1);
		}
	}//--run--
	
	
	public void connect(String host, int port) {
		try {
			sock = new Socket(host, port);
			
			in = new BufferedReader(
					new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(),true);
			out.println(id);
			ta_Chat.append(in.readLine()+"\n");
			tf_Write.requestFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Thread Start -> run �޼��� ȣ��
		new Thread(this).start();
	}//--connect--
	
	//user_id �޾ƿ� ����Ʈ[0]��°�� ����
	public void setList(String user_id) {
		id = user_id;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj==btn_close) {
			 out.println("������ ����� �����߽��ϴ�.");
			 this.dispose();	
		}else if (obj==btn_send || obj==tf_Write) { //���� ��ư �Ǵ� �ؽ�Ʈ��(����)
			String str = tf_Write.getText().trim();
			if (str.isEmpty())
				return; //������ �������� �ʰ� �޼ҵ� ����
			if (id==null) {
				//ó�� ���̵� �Է�
				id=str;
				//setTitle(getTitle()+ "-" + "[" + id + "]");
				ta_Chat.setText("ä�û���� �����մϴ�.\n");
				}
			out.println(str);//��������
			tf_Write.setText("");
			tf_Write.requestFocus();
			}
		}//--actionPerformed--
	
	public static void main(String[] args) {
		new ChatClient();
	}
}