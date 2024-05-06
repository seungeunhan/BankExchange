package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Dimension;

import java.awt.FlowLayout;
import java.text.DecimalFormat;
//�ŷ��� �Խ���(TradeMgr, TradeBean, MFrame) - ���,����,���� : �ϼ�
//�߰��ؾ��Ұ�
//0. ������������ �� ���� �ݾ� �Է� ���ϰ� ���� + ���¿��� �������� ������ - �ϼ�
//1. ��û��ư Ȱ��ȭ + ��� -> �󼼺��� ��ư : ������ �����Ҽ����� ���� �ŷ� ���� �����ְ�
//�ȿ� ��û ��ư�� �־ ������ ���� �������� ��û ���� - �ϼ�
//2. ȭ�� ��(\)���� �ٲ����� �뷫�� ���� ǥ�� - �ϼ�
/**3. �𸣰ڴ��� : ��°�� ������ Ȯ�� ��ư�� �������� �ٷ� list(check1=true)�� �ʱ�ȭ �����ʴ���
          + �󼼺��� ��ư ���� �ѹ��� ����Ʈ�� �ʱ�ȭ �Ǳ������� ����ε� �ڵ尡 ��������ʴ���*/
//4. �������� �ŷ� ��û�� ������ DB �۵��� ���� - �ϼ�
//5.���̵� �ٸ��� ����, ������ ���Ͼ��   �󼼺��� ��ư�� ���� â�� ���� ���� �ű�� 
//-> �ű�� ���� ����ڿ� ���� ���̵� �϶� (���� ���� - Ȱ��ȭ, ��ȯ - ��Ȱ), ����ڿ� �ٸ� ���̵��϶� (���� ���� - ��Ȱ, ��ȯ - Ȱ��ȭ)
//6. UI ���� - ������ ���� �ܰ�, �˻�
//7. ���� ��ȭ�϶� ���� �ȵǰ� - �ϼ�

public class TestFrameTrade5 extends JFrame implements ActionListener {

	JButton b1, b2, b3, b4, b5, b6, b7,b8, b9, b10;
	List list;
	JLabel label;
	JPanel menuPanel;
	JPanel tradePanel;
	JPanel trade1;
	JPanel trade2;
	JPanel trade3;
	JTextField tf0, tf1, tf2, tf3, tf4, tf5;
	JPanel p1, p2, p3, p4;
	JButton insBtn, upBtn, EscBtn, AplBtn;
	Vector<TradeBean> vlist;
	TradeMgr mgr;
	String id;
	private JComboBox<String> currencyComboBox;
	private JComboBox<String> exchangeComboBox;
	ApplyFrame3 af;
	InsertFrame If;
	UpdateFrame Uf;
	ShowFrame Sf;
	JFrame frame;
	
    String selectedCurrency; // ������ ���� ��ȭ ���� ����
    String selectedExchange; // ������ ��ȯ ��ȭ ���� ����
	
	
	JButton rateBtn; //ȯ�� ��ư
	JButton exchangeBtn; //ȯ�� ��ư
	JButton walletBtn; //���� ��ư
	JButton tradeBtn; //�ŷ��� ��ư
	JButton infoBtn; //������ ��ư
	JButton outBtn; //�α׾ƿ� ��ư
	JButton moreBtn; //������ ��ư
	JButton newBtn; // ���ΰ�ħ ��ư
	JButton searchBtn; //�˻� ��ư
	JButton doexBtn; //ȯ���ϱ� ��ư

	
	
	public TestFrameTrade5() {
		
		frame = new JFrame();
		setSize(1200,800);
//		setTitle("TestFrame Trade Ver5.0");
//		setLocationRelativeTo(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE); // X��ư�� ������ â�� �ݾ����� ���α׷� ����
		setVisible(true); // â�� ����� ���� ���θ� ����
		
		mgr = new TradeMgr();
		
		frame.setLayout(new GridLayout(1, 2));
		
	    menuPanel =  new JPanel(new GridLayout(8, 1));
		tradePanel = new JPanel(new GridLayout(3, 1));
		
//		frame.add(menuPanel);
//		frame.add(tradePanel);

	    rateBtn = new JButton("ȯ��");
	    exchangeBtn = new JButton("ȯ��");
	    walletBtn = new JButton("����");
	    tradeBtn = new JButton("�ŷ���");
	    infoBtn = new JButton("������");
	    outBtn = new JButton("�α׾ƿ�");
	    
	    rateBtn.addActionListener(this);
	    exchangeBtn.addActionListener(this);
	    walletBtn.addActionListener(this);
	    tradeBtn.addActionListener(this);
	    infoBtn.addActionListener(this);
	    outBtn.addActionListener(this);

	    
	    menuPanel.add(new JLabel("Logo"));
	    menuPanel.add(rateBtn);
	    menuPanel.add(exchangeBtn);
	    menuPanel.add(walletBtn);
	    menuPanel.add(tradeBtn);
	    menuPanel.add(infoBtn);
	    menuPanel.add(new JLabel());
	    menuPanel.add(outBtn);
		
		
		trade1 = new JPanel();
		trade2 = new JPanel(new BorderLayout());
		trade3 = new JPanel();
		
		tradePanel.add(trade1,BorderLayout.NORTH);
		tradePanel.add(trade2,BorderLayout.CENTER);
		tradePanel.add(trade3,BorderLayout.SOUTH);
		
		label = new JLabel("");
		
		JPanel sc1 = new JPanel();
		sc1.add(new JLabel("���� ��ȭ  :"));
		String[] currencies = {"","USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"}; 
		currencyComboBox = new JComboBox<>(currencies);
		sc1.add(currencyComboBox);
		trade1.add(sc1);
		
		JPanel sc2 = new JPanel();
	    sc2.add(new JLabel("��ȯ ��ȭ :"));
	    String[] exchangeCurrencies = {" ","USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"};; // ��ȯ ȭ�� ���
	    exchangeComboBox = new JComboBox<>(exchangeCurrencies);
	    sc2.add(exchangeComboBox);
	    trade1.add(sc2);
	    
	    searchBtn = new JButton("�˻�");
	    newBtn = new JButton("���ΰ�ħ");
	    
	    searchBtn.addActionListener(this);
	    newBtn.addActionListener(this);
	    
	    trade1.add(searchBtn);
	    trade1.add(newBtn);
		
		b1 = new JButton("�Խñ� �ۼ�");
		b2 = new JButton("����");
		b3 = new JButton("����");
		b4 = new JButton("�󼼺���");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		
		trade3.add(b1);
		trade3.add(b2);
		trade3.add(b3);
		trade3.add(b4);
		
		viewList(trade2,null, null);
		
	    add(menuPanel, BorderLayout.WEST);
		add(tradePanel, BorderLayout.CENTER);
		
		validate();
	}
	
	
	public void viewList(JPanel panel, String object, String object2) {
		
		vlist = mgr.selectAllDESC();
        DecimalFormat df = new DecimalFormat("#,###");

		list = new List(vlist.size(), false);
		
	    if ("".equals(object) && " ".equals(object2)) {
	        selectedCurrency = null;
	        selectedExchange = null;
	    } else {
	        selectedCurrency = object;
	        selectedExchange = object2;
	    }

	    for (int i = 0; i < vlist.size(); i++) {
	        TradeBean bean = vlist.get(i);
	        // ������ ���� ��ȭ�� ��ȯ ��ȭ�� �´� �ŷ��� ����Ʈ�� �߰�
	        if (selectedCurrency == null && selectedExchange == null
	                || (bean.getSellCur().equals(selectedCurrency) && bean.getBuyCur().equals(selectedExchange))) {
	            String SellAmt = df.format(bean.getSellAmt());
	            String str = bean.getTradeNum() + "    "
	                    + bean.getTraderAcc1().trim() + "       "
	                    + bean.getSellCur().trim() + "       "
	                    + bean.getBuyCur().trim() + "       "
	                    + SellAmt + "       "
	                    + bean.getDay();
	            list.add(str);
	        }
	    }
		
		
		int len = list.getItemCount();
		if(len>0)
			list.select(0);
		panel.add(list, BorderLayout.CENTER);
	}		
	
	
			
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		String command = e.getActionCommand();
	        
	        if (obj == b1/*�Խñ��ۼ� ��ư*/) {
				If = new InsertFrame(this);
				If.setVisible(true);
			
		}else if(obj==b2/*������ư*/) {

				int i = list.getSelectedIndex();
				TradeBean bean = vlist.get(i);
				System.out.println("��ư ����");
				System.out.println(bean.getTradeNum());
				Uf = new UpdateFrame(this, Integer.toString(bean.getTradeNum()));
				Uf.setVisible(true);
			
		}else if(obj==b3/*������ư*/) {
					
					int i = list.getSelectedIndex();
					TradeBean bean = vlist.get(i);
					if(mgr.delete(bean.getTradeNum())) {
			            trade2.removeAll();
						viewList(trade2, null, null);
			            trade2.revalidate();
			            trade2.repaint();
					}
					
		}else if(obj==b4/*�󼼺��� ��ư*/) {
			
			
						int i = list.getSelectedIndex();
						TradeBean bean = vlist.get(i);
						System.out.println("��ư ����");
						System.out.println(bean.getTradeNum());
						Sf = new ShowFrame(this, Integer.toString(bean.getTradeNum()),trade2);
						Sf.setVisible(true);

//		}else if(obj == AplBtn /*��ȯ��û ��ư*/) {
//			
//					System.out.println("��ư ����");
//					tf0.getText();
//					System.out.println(tf0.getText());
//					af = new ApplyFrame3(this, tf0.getText());
//					af.setVisible(true);
						

		}else if("complete".equals(command)) {
			            trade2.removeAll();
						viewList(trade2,null,null);
			            trade2.revalidate();
					    viewList(trade2, null, null);
		    
	    }else if(obj== searchBtn/*�˻�*/) {
					
	    	
	        System.out.println("��ư ����");

	        String currency = (String) currencyComboBox.getSelectedItem();
	        String exchange = (String) exchangeComboBox.getSelectedItem();

	        if (currency.equals(exchange)) {
	            JOptionPane.showMessageDialog(null, "���� ��ȭ�� ��ȯ ��ȭ�� ���� �� �����ϴ�. �����Ͽ� �ֽʽÿ�.");
	        } else {
	            System.out.println("�˻� ����");

	            // ������ ���� ��ȭ�� ��ȯ ��ȭ ����
	            selectedCurrency = (String) currencyComboBox.getSelectedItem();
	            selectedExchange = (String) exchangeComboBox.getSelectedItem();

	            // ������ ���� ��ȭ�� �ŷ� ����� ������
	            if (selectedCurrency.equals("")||selectedExchange.equals(" ")) {
	                viewList(trade2, null, null); // ��ü ���� �ɼ��� ��, selectedCurrency�� selectedExchange�� null�� ����
	            } else {
	                viewList(trade2, selectedCurrency, selectedExchange);
	            }
	        }
				    
		            trade2.removeAll();
					viewList(trade2, selectedCurrency , selectedExchange);
		            trade2.revalidate();
		            trade2.repaint();
		    
	    }else if(obj == newBtn/*���ΰ�ħ*/) {
	    	
	    				System.out.println("��ư ����");
			            trade2.removeAll();
						viewList(trade2, null, null);
			            trade2.revalidate();
			            trade2.repaint();
	    }
		
		validate();
	}//--actionPerformed
	public JPanel getMainPanel() {
        return this.tradePanel;
    }
	
	public static void main(String[] args) {
		new TestFrameTrade5();
	}
} 