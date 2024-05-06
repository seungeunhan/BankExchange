package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainUI extends JFrame implements ActionListener{
	static RateAPI ra = new RateAPI();
	String[] btnTitle = {"ȯ��","ȯ��","�ŷ���","������","������"};
	JButton[] btn_menu = new JButton[5];
	JButton btn_logout;
	JPanel contentPane/*â*/, p_left/*���ʸ޴�*/, p_contents/*����*/,Myinfo;
	JLabel lbl_logo;
	ImageIcon img_logo;
	static String info_id;
	Myinfo myinfoPanel;
	RateFrame rate_pan;
	HomeEx1 exchangePanel;
	TestPanel tradePanel;
	WalletPanel walletPanel;
	private MainUI instance;

	public MainUI(String id) {
		this.instance = this;
		info_id = id;
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
		
		//�α׾ƿ� ��ư �̺�Ʈ
		btn_logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				info_id = null;
				exchangePanel = null;
				dispose();
				new LoginEx1();
			}
		});
		
		
		/*���� Contents****************************************************/
		
		//�г� ����
		
		getContentPane().add(p_contents = new JPanel());
		p_contents.setBorder(null);
		rate_pan = new RateFrame(instance);
		p_contents.add(rate_pan.getMainPanel());
		p_contents.setBackground(Color.white); //�г� ����
		p_contents.setBounds(198, 21, 826, 620);
		p_contents.setLayout(null); //���̾ƿ� ������ ��Ȱ��ȭ �� ���� ��ġ
		
		setVisible(true);
		
	}//--MainInfo--

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==btn_menu[0]) {
			//ȯ�� ��ư �̺�Ʈ
			p_contents.removeAll();
			if (rate_pan == null) { // TradeFrame�� ���� �������� �ʾҴٸ�
                rate_pan = new RateFrame(instance); // TestFrameTrade6 �ν��Ͻ� ����
                rate_pan.setVisible(false);
            }
			p_contents.add(rate_pan.getMainPanel());
            p_contents.revalidate();
            p_contents.repaint();
	        
		}else if (e.getSource()==btn_menu[1]) {
			//ȯ�� ��ư �̺�Ʈ
			p_contents.removeAll();
			if(exchangePanel == null) {
				exchangePanel = new HomeEx1(info_id);
			}
			p_contents.add(exchangePanel.getMainPanel());
			p_contents.revalidate();
			p_contents.repaint();
		}else if (e.getSource()==btn_menu[2]) {
			//�ŷ��� ��ư �̺�Ʈ
			p_contents.removeAll();
			if (tradePanel == null) { // TradeFrame�� ���� �������� �ʾҴٸ�
                tradePanel = new TestPanel(); // TestFrameTrade6 �ν��Ͻ� ����
            }
            // TestFrameTrade6�� tradePanel�� p_contents�� �߰�
            p_contents.add(tradePanel.getTradePanel());
            p_contents.revalidate();
            p_contents.repaint();
		
		}else if (e.getSource()==btn_menu[3]) {
			//������ ��ư �̺�Ʈ
			p_contents.removeAll();
			if (walletPanel == null) { // TradeFrame�� ���� �������� �ʾҴٸ�
                walletPanel = new WalletPanel(); // TestFrameTrade6 �ν��Ͻ� ����
            }
            // TestFrameTrade6�� tradePanel�� p_contents�� �߰�
            p_contents.add(walletPanel.getWalletPanel());
            p_contents.revalidate();
            p_contents.repaint();
			
		}else if (e.getSource()==btn_menu[4]) {
			//������ ��ư �̺�Ʈ
			p_contents.removeAll();
			if (myinfoPanel == null) {
                myinfoPanel = new Myinfo(info_id);
            }
	        // MainUI�� p_contents �гο� rate_pan �г� �߰�
			p_contents.add(myinfoPanel.getMainPanel());
            p_contents.revalidate();
            p_contents.repaint();
		}
	}//--actionPerformed--
	
	public void moveFrameToExchangePanel() {
		p_contents.removeAll();
		if(exchangePanel == null) {
			exchangePanel = new HomeEx1(info_id);
		}
		p_contents.add(exchangePanel.getMainPanel());
		p_contents.revalidate();
		p_contents.repaint();
	}
	
	public static void main(String[] args) {
		ra.main(args);
		new LoginEx1();
	}
}
