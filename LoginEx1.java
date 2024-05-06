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
        setBackground(new Color(169, 219, 208)); //패널 색 지정
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(logoIcon.getImage(), 60, 30, null); //이미지 위치 지정
    }
}

public class LoginEx1 extends JFrame implements ActionListener {
	// JDBC 데이터베이스 연결 정보
    Connection conn=null; //DB 커넥션 연결 객체
	private static final String DB_URL = "jdbc:mysql://localhost:3306/test"; // 데이터베이스 URL
	private static final String DB_USER = "root"; // 데이터베이스 사용자 이름
	private static final String DB_PASSWORD = "1234"; // 데이터베이스 암호
	
	TextField idTf, pwTf;
	Label idl, pwl, msgl;
	JButton logBtn = new JButton();
	JButton joinBtn = new JButton();
	JButton searchBtn = new JButton();
	Color customColor = new Color(169, 219, 208);
	Color customColor2 = new Color(62,192,196);
	String label[] = { "ID와 PW를 입력하세요.", "ID와 PW를 확인하세요.", "이중 접속입니다." };
	ImageIcon logoIcon;
	
	// 데이터베이스와 연결하는 메서드
    public void connectDatabase() {
        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
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

    // 로그인 기능을 처리하는 메서드
    public boolean login(String id, String password) {
        try {
            // SQL 쿼리 작성
            String sql = "SELECT * FROM member WHERE id = ? AND pwd = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, md5(password));
            
            // 쿼리 실행 및 결과 가져오기
            ResultSet rs = pstmt.executeQuery();

            // 결과가 있는 경우 로그인 성공
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // 로그인 실패
    }
    
    
	
	public LoginEx1() {
		connectDatabase(); //데이터베이스 연결
		
		getContentPane().setBackground(new Color(169, 219, 208)); //창 배경색 지정
		setSize(450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		ImageIcon logoIcon = new ImageIcon("test/logo.png"); // 이미지 로드
        ImagePanel imagePanel = new ImagePanel(logoIcon);
        imagePanel.setBounds(70, 20, getWidth(), getHeight()); //이미지 패널 위치 지정
		
		idl = new Label("아이디");
		idTf = new TextField("");
		idl.setBounds(70, 130, 50, 40);
		idTf.setBounds(120, 130, 200, 40);
		
		pwl = new Label("비밀번호");
		pwTf = new TextField("");
		pwl.setBounds(70, 180, 50, 40);
		pwTf.setBounds(120, 180, 200, 40);
		pwTf.setEchoChar('*'); // 비밀번호 입력 시 '*'로 표시
		
		logBtn = new JButton("로그인");
		logBtn.setBounds(120, 260, 200, 40);
		logBtn.setBackground(customColor2);
		
		joinBtn = new JButton("회원가입");
		joinBtn.setBackground(customColor);
		joinBtn.setBounds(195, 300, 50, 30);
		joinBtn.setBorder(BorderFactory.createEmptyBorder());
		
		searchBtn = new JButton("아이디/비밀번호 찾기");
		searchBtn.setBackground(customColor);
		searchBtn.setBounds(180, 215, 150, 40);
		searchBtn.setBorder(BorderFactory.createEmptyBorder());
		
		msgl = new Label(label[0]);
		
		logBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 로그인 버튼 클릭 시
                    String id = idTf.getText().trim();
                    String password = pwTf.getText().trim();

                    // 아이디와 비밀번호를 데이터베이스와 비교하여 로그인 처리
                    if (login(id, password)) {
                        // 로그인 성공 시 HomeEx1 페이지로 이동
                        dispose();
//                        new HomeEx1(id);
                        new MainUI(id);
                    } else {
                        // 로그인 실패 시 메시지 표시
                        msgl.setText("아이디 또는 비밀번호가 일치하지 않습니다.");
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
