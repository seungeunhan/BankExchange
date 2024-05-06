package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminUI extends JFrame implements ActionListener{
	
	String[] btnTitle = {"�ŷ� ����","��� ����"};
	JButton[] btn_menu = new JButton[2];
	JButton btn_logout;
	JPanel contentPane/*â*/, p_left/*���ʸ޴�*/, p_contents/*����*/;
	JLabel lbl_logo;
	ImageIcon img_logo;
	JTextArea instructions;
	
	public AdminUI(){
		setTitle("Simple Exchange"); //â Ÿ��Ʋ
		setSize(1050,700);  // â ������
		setLocationRelativeTo(null); // â ��� ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//â contentPane ����
		contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 219, 208)); //����
		setContentPane(contentPane);
		getContentPane().setLayout(null); //���̾ƿ� ������ ��Ȱ��ȭ �� ���� ��ġ
		
		
		/*���� �޴� �г�****************************************************/
		getContentPane().add(p_left = new JPanel());
		p_left.setBackground(new Color(62, 192, 196));
		p_left.setBounds(0, 0, 186, 663);
		p_left.setLayout(null); //���̾ƿ� ������ ��Ȱ��ȭ �� ���� ��ġ
		
		//�ΰ� �̹���
		p_left.add(lbl_logo = new JLabel(new ImageIcon("test/logo.png")));
		lbl_logo.setBounds(12, 20, 162, 127);

		
		// �޴� ��ư ����(ȯ��, ȯ��, �ŷ���, ������, ������, �α׾ƿ�)
		int y=0;
        for (int i=0; i<btn_menu.length; i++) { // ��ư �̸� ����
        	p_left.add(btn_menu[i] = new JButton(btnTitle[i]));
        	btn_menu[i].setBounds(0, 160+y, 186, 75); //��ġ, ������
        	btn_menu[i].setBackground(new Color(62, 192, 196)); //��ư ����
        	btn_menu[i].setForeground(new Color(255, 255, 255)); //��Ʈ ����
        	btn_menu[i].setFont(new Font("���� ���", Font.BOLD, 20)); // ��Ʈ ����
        	btn_menu[i].setBorderPainted(false); //��ư �׵θ� ���ֱ�
            y+=77; // �� ��ư y��(����) ����
            btn_menu[i].addActionListener(this);
            
        }//--for--
        
        //�α׾ƿ� ��ư
        p_left.add(btn_logout = new JButton("�α׾ƿ�"));
		btn_logout.setFont(new Font("���� ���", Font.BOLD, 17)); //��Ʈ ����
		btn_logout.setBackground(new Color(62, 192, 196)); // ��ư ����
		btn_logout.setIcon(new ImageIcon("test/logout.png")); // ��ư ������ ����
		btn_logout.setBounds(28, 595, 124, 39); // ��ġ, ������
		btn_logout.setBorderPainted(false); //��ư �׵θ� ���ֱ�
		
		
		/*���� Contents****************************************************/
		
		instructions = new JTextArea("������ ������");
		instructions.setFont(new Font("���� ���", Font.BOLD, 40));
		instructions.setBounds(313,270,300,100);
		
		//�г� ����
		setResizable(false);
		getContentPane().add(p_contents = new JPanel());
		p_contents.setBorder(null);
		p_contents.add(instructions);
		p_contents.setBackground(Color.white); //�г� ����
		p_contents.setBounds(198, 21, 826, 620);
		p_contents.setLayout(null); //���̾ƿ� ������ ��Ȱ��ȭ �� ���� ��ġ
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btn_menu[0]) { // ȯ�� ��ư �̺�Ʈ
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