package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class ImagePanel extends JPanel {
	
    private ImageIcon logoIcon;

    public ImagePanel(ImageIcon logoIcon) {
        this.logoIcon = logoIcon;
        setBackground(new Color(169, 219, 208)); //�г� �� ����
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(logoIcon.getImage(), 60, 30, null); //�̹��� ��ġ ����
    }
}

public class LoginEx1 extends JFrame implements ActionListener {
	// JDBC �����ͺ��̽� ���� ����
    Connection conn=null; //DB Ŀ�ؼ� ���� ��ü
	private static final String DB_URL = "jdbc:mysql://localhost:3306/test"; // �����ͺ��̽� URL
	private static final String DB_USER = "root"; // �����ͺ��̽� ����� �̸�
	private static final String DB_PASSWORD = "1234"; // �����ͺ��̽� ��ȣ
	
	TextField idTf, pwTf;
	Label idl, pwl, msgl;
	JButton logBtn = new JButton();
	JButton joinBtn = new JButton();
	JButton searchBtn = new JButton();
	Color customColor = new Color(169, 219, 208);
	Color customColor2 = new Color(62,192,196);
	String label[] = { "ID�� PW�� �Է��ϼ���.", "ID�� PW�� Ȯ���ϼ���.", "���� �����Դϴ�." };
	ImageIcon logoIcon;
	
	// �����ͺ��̽��� �����ϴ� �޼���
    public void connectDatabase() {
        try {
            // JDBC ����̹� �ε�
            Class.forName("com.mysql.cj.jdbc.Driver");

            // �����ͺ��̽� ����
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String md5(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // �α��� ����� ó���ϴ� �޼���
    public boolean login(String id, String password) {
        try {
            // SQL ���� �ۼ�
            String sql = "SELECT * FROM member WHERE id = ? AND pwd = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, md5(password));
            
            // ���� ���� �� ��� ��������
            ResultSet rs = pstmt.executeQuery();

            // ����� �ִ� ��� �α��� ����
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // �α��� ����
    }
    
    
	
	public LoginEx1() {
		connectDatabase(); //�����ͺ��̽� ����
		
		getContentPane().setBackground(new Color(169, 219, 208)); //â ���� ����
		setSize(450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		ImageIcon logoIcon = new ImageIcon("test/logo.png"); // �̹��� �ε�
        ImagePanel imagePanel = new ImagePanel(logoIcon);
        imagePanel.setBounds(70, 20, getWidth(), getHeight()); //�̹��� �г� ��ġ ����
		
		idl = new Label("���̵�");
		idTf = new TextField("");
		idl.setBounds(70, 130, 50, 40);
		idTf.setBounds(120, 130, 200, 40);
		
		pwl = new Label("��й�ȣ");
		pwTf = new TextField("");
		pwl.setBounds(70, 180, 50, 40);
		pwTf.setBounds(120, 180, 200, 40);
		pwTf.setEchoChar('*'); // ��й�ȣ �Է� �� '*'�� ǥ��
		
		logBtn = new JButton("�α���");
		logBtn.setBounds(120, 260, 200, 40);
		logBtn.setBackground(customColor2);
		
		joinBtn = new JButton("ȸ������");
		joinBtn.setBackground(customColor);
		joinBtn.setBounds(195, 300, 50, 30);
		joinBtn.setBorder(BorderFactory.createEmptyBorder());
		
		searchBtn = new JButton("���̵�/��й�ȣ ã��");
		searchBtn.setBackground(customColor);
		searchBtn.setBounds(180, 215, 150, 40);
		searchBtn.setBorder(BorderFactory.createEmptyBorder());
		
		msgl = new Label(label[0]);
		
		logBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // �α��� ��ư Ŭ�� ��
                    String id = idTf.getText().trim();
                    String password = pwTf.getText().trim();

                    // ���̵�� ��й�ȣ�� �����ͺ��̽��� ���Ͽ� �α��� ó��
                    if (login(id, password)) {
                        // �α��� ���� �� HomeEx1 �������� �̵�
                        dispose();
//                        new HomeEx1(id);
                        new MainUI(id);
                    } else {
                        // �α��� ���� �� �޽��� ǥ��
                        msgl.setText("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                        msgl.setForeground(Color.RED);
                        msgl.setBounds(105, 330, 250, 30);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
		
		msgl.setBounds(160, 330, 150, 40);
		logBtn.addActionListener(this);
		imagePanel.setLayout(null);
		
		add(idl);
		add(idTf);
		add(pwl);
		add(pwTf);
		add(logBtn);
		add(joinBtn);
		add(searchBtn);
		add(msgl);
		add(imagePanel);
		setVisible(true);
		setLocationRelativeTo(null);
		validate();

		joinBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Object obj = e.getSource();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				dispose();
				new JoinEx1();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	public static void main(String[] args) {
		new LoginEx1();
	}
}
