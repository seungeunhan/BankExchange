package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminUI extends JFrame implements ActionListener{
	
	String[] btnTitle = {"거래 승인","상담 내역"};
	JButton[] btn_menu = new JButton[2];
	JButton btn_logout;
	JPanel contentPane/*창*/, p_left/*왼쪽메뉴*/, p_contents/*본문*/;
	JLabel lbl_logo;
	ImageIcon img_logo;
	JTextArea instructions;
	
	public AdminUI(){
		setTitle("Simple Exchange"); //창 타이틀
		setSize(1050,700);  // 창 사이즈
		setLocationRelativeTo(null); // 창 가운데 띄움
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//창 contentPane 설정
		contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 219, 208)); //배경색
		setContentPane(contentPane);
		getContentPane().setLayout(null); //레이아웃 관리자 비활성화 후 직접 배치
		
		
		/*왼쪽 메뉴 패널****************************************************/
		getContentPane().add(p_left = new JPanel());
		p_left.setBackground(new Color(62, 192, 196));
		p_left.setBounds(0, 0, 186, 663);
		p_left.setLayout(null); //레이아웃 관리자 비활성화 후 직접 배치
		
		//로고 이미지
		p_left.add(lbl_logo = new JLabel(new ImageIcon("test/logo.png")));
		lbl_logo.setBounds(12, 20, 162, 127);

		
		// 메뉴 버튼 생성(환율, 환전, 거래소, 내지갑, 내정보, 로그아웃)
		int y=0;
        for (int i=0; i<btn_menu.length; i++) { // 버튼 이름 지정
        	p_left.add(btn_menu[i] = new JButton(btnTitle[i]));
        	btn_menu[i].setBounds(0, 160+y, 186, 75); //위치, 사이즈
        	btn_menu[i].setBackground(new Color(62, 192, 196)); //버튼 배경색
        	btn_menu[i].setForeground(new Color(255, 255, 255)); //폰트 색상
        	btn_menu[i].setFont(new Font("맑은 고딕", Font.BOLD, 20)); // 폰트 설정
        	btn_menu[i].setBorderPainted(false); //버튼 테두리 없애기
            y+=77; // 각 버튼 y축(높이) 지정
            btn_menu[i].addActionListener(this);
            
        }//--for--
        
        //로그아웃 버튼
        p_left.add(btn_logout = new JButton("로그아웃"));
		btn_logout.setFont(new Font("맑은 고딕", Font.BOLD, 17)); //폰트 설정
		btn_logout.setBackground(new Color(62, 192, 196)); // 버튼 배경색
		btn_logout.setIcon(new ImageIcon("test/logout.png")); // 버튼 아이콘 삽입
		btn_logout.setBounds(28, 595, 124, 39); // 위치, 사이즈
		btn_logout.setBorderPainted(false); //버튼 테두리 없애기
		
		
		/*메인 Contents****************************************************/
		
		instructions = new JTextArea("관리자 페이지");
		instructions.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		instructions.setBounds(313,270,300,100);
		
		//패널 삽입
		setResizable(false);
		getContentPane().add(p_contents = new JPanel());
		p_contents.setBorder(null);
		p_contents.add(instructions);
		p_contents.setBackground(Color.white); //패널 배경색
		p_contents.setBounds(198, 21, 826, 620);
		p_contents.setLayout(null); //레이아웃 관리자 비활성화 후 직접 배치
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btn_menu[0]) { // 환율 버튼 이벤트
			p_contents.removeAll();
	        p_contents.revalidate();
	        p_contents.repaint();
	    }else if (e.getSource()==btn_menu[1]) {
	    	p_contents.removeAll();
	    	p_contents.revalidate();
	        p_contents.repaint();
		}
	}
	
	public static void main(String[] args) {
		new AdminUI();
	}
}