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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;


public class TestPanel extends JPanel implements ActionListener{

	JButton b1, b2, b3, b4;
	JButton searchBtn, newBtn;
	JLabel label, sc1, sc2;
	JPanel menuPanel, tradePanel, trade1, trade2, trade3;
	JPanel p1, p2, p3, p4;
	JTextField tf0;
	JTextArea tf1,tf2, tf3, tf4, tf5;
	JScrollPane scrollPane;
	
	ApplyFrame4 af;
	InsertFrame2 If;
	UpdateFrame2 Uf;
	ShowFrame2 Sf;
	
	List list;
	Vector<TradeBean> vlist;
	TradeMgr mgr;
	
	private JComboBox<String> currencyComboBox;
	private JComboBox<String> exchangeComboBox;
    String selectedCurrency; // ������ ���� ��ȭ ���� ����
    String selectedExchange; // ������ ��ȯ ��ȭ ���� ����
	String id;
	
	
	public TestPanel() {

		setLayout(null); // ���̾ƿ� �����ڸ� ������� ����
        
		mgr = new TradeMgr();
		
		label = new JLabel("");
		
		tradePanel = new JPanel(new BorderLayout());
        tradePanel.setBackground(new Color(169, 219, 208));
        tradePanel.setBounds(0, 0, 830, 620); // ��ġ (50, 50), ũ�� (300, 150)
		
        
		trade1 = new JPanel();
		trade1.setPreferredSize(new Dimension(300, 200));
		trade2 = new JPanel();
		trade2.setLayout(new BorderLayout());
		

//		tf1 = new JTextArea("testest");
//		tf1.setBounds(130,70,500,156);
//		tf1.setBackground(Color.WHITE);
//		tf1.setEditable(false);

//		trade1.setBackground(new Color(169, 219, 208));
//		trade1.add(tf1);
		
		trade1.setLayout(null);
		trade1.setBackground(new Color(169, 219, 208));
		
		
		JLabel label1 = new JLabel("���� ��ȭ  :");
		label1.setBackground(Color.WHITE);
		label1.setForeground(Color.DARK_GRAY);
		label1.setBounds(0, 20, 100, 40);
		trade1.add(label1);
		
		String[] currencies = {"","USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"};
		currencyComboBox = new JComboBox<>(currencies);
		currencyComboBox.setBackground(Color.WHITE);
		currencyComboBox.setBounds(100, 20, 150, 40);
		trade1.add(currencyComboBox);

		
		JLabel label2 = new JLabel("��ȯ ��ȭ :");
		label2.setBackground(Color.WHITE);
		label2.setForeground(Color.DARK_GRAY);
		label2.setBounds(300, 20 ,100 ,40);
		trade1.add(label2);
		
		String[] exchangeCurrencies = {" ","USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"};
		exchangeComboBox = new JComboBox<>(exchangeCurrencies);
		exchangeComboBox.setBackground(Color.WHITE);
		exchangeComboBox.setBounds(400,20, 150, 40);
		trade1.add(exchangeComboBox);
	    
		
	    searchBtn = new JButton("�˻�");
	    newBtn = new JButton("���ΰ�ħ");
	    
		searchBtn.setFont(new Font("���� ���", Font.BOLD , 16));
		newBtn.setFont(new Font("���� ���", Font.BOLD , 16));

		searchBtn.setForeground(Color.WHITE);
		newBtn.setForeground(Color.WHITE);

		searchBtn.setBackground(new Color(92,209,229));
		newBtn.setBackground(new Color(97,219,240));

        searchBtn.setBounds(600, 20 ,  100 , 40);
        newBtn.setBounds(710, 20,  100 , 40);
        
        searchBtn.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        newBtn.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
	    
	    searchBtn.addActionListener(this);
	    newBtn.addActionListener(this);
	    
	    trade1.add(searchBtn);
	    trade1.add(newBtn);
		
		
		
		
		
		
		JLabel l1 = new JLabel("       No        |                  �ۼ���ID                 |        ���� ��ȭ         |       ��ȯ ��ȭ        |       ��ȯ ��ȭ �ݾ�         |            �ۼ� ��¥");
		l1.setBounds(0 , 160 , 800, 40);
		l1.setFont(new Font("���� ���", Font.CENTER_BASELINE, 12));
		l1.setForeground(Color.WHITE);
		trade1.add(l1);
		
		b1 = new JButton("�Խñ� �ۼ�");
		b2 = new JButton("����");
		b3 = new JButton("����");
		b4 = new JButton("�󼼺���");
		
		b1.setFont(new Font("���� ���", Font.BOLD , 16));
		b2.setFont(new Font("���� ���", Font.BOLD , 16));
		b3.setFont(new Font("���� ���", Font.BOLD , 16));
		b4.setFont(new Font("���� ���", Font.BOLD , 16));
		
		b1.setForeground(Color.WHITE);
		b2.setForeground(Color.WHITE);
		b3.setForeground(Color.WHITE);
		b4.setForeground(Color.WHITE);
		
        b1.setBackground(new Color(92,209,229));
        b2.setBackground(new Color(97,219,240));
        b3.setBackground(new Color(97,219,240));
        b4.setBackground(new Color(97,219,240));
		
        b1.setBounds(80+50, 110 , 120, 40);
        b2.setBounds(230+50, 110 , 120, 40);
        b3.setBounds(380+50, 110 , 120, 40);
        b4.setBounds(530+50, 110 , 120, 40);
        
    	b1.setBorder(BorderFactory.createLineBorder(Color.CYAN));
    	b2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	b3.setBorder(BorderFactory.createLineBorder(Color.RED));
    	b4.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
      
		
		trade1.add(b1);
		trade1.add(b2);
		trade1.add(b3);
		trade1.add(b4);
		
		
		
		trade2.setBackground(Color.RED);
		
        JScrollPane scrollPane = new JScrollPane(); // JScrollPane ����
        viewList(scrollPane,null,null); // JScrollPane�� ����Ʈ �߰�
        trade2.add(scrollPane, BorderLayout.CENTER); // JScrollPane�� trade2 �г��� ����� �߰�
		
		
		tradePanel.add(trade1,BorderLayout.NORTH);	
		tradePanel.add(trade2,BorderLayout.CENTER);
		
        add(tradePanel); // JPanel�� TestPanel�� �߰�
		
		
		setVisible(true);
		
	}
	
	
	public void viewList(JScrollPane scrollPane, String object, String object2) {

	    if (list != null) {
	        scrollPane.setViewportView(list);
	    }
	    if (scrollPane == null) {
	        scrollPane = new JScrollPane(); // ���ο� JScrollPane ��ü�� ����
	        // ��Ÿ �ʱ�ȭ �۾� ����
	    }
		
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
	    
	    if (vlist.isEmpty()) {
	        // ����Ʈ�� ������� ���� ó��
	        list.add("����Ʈ�� ��� �ֽ��ϴ�.");

	    }else {
	    	for (int i = 0; i < vlist.size(); i++) {
	        TradeBean bean = vlist.get(i);
	        // ������ ���� ��ȭ�� ��ȯ ��ȭ�� �´� �ŷ��� ����Ʈ�� �߰�
	        if (selectedCurrency == null && selectedExchange == null
	                || (bean.getSellCur().equals(selectedCurrency) && bean.getBuyCur().equals(selectedExchange))) {
	            String SellAmt = df.format(bean.getSellAmt());
	            String str = "    " + bean.getTradeNum() + "                     "
	                    + bean.getTraderAcc1().trim() + "                     "
	                    + bean.getSellCur().trim() + "                 "
	                    + bean.getBuyCur().trim() + "                 "
	                    + SellAmt + "                       "
	                    + bean.getDay();
	            list.add(str);
	            list.setFont(new Font("���� ���", Font.CENTER_BASELINE , 18));
	            list.setForeground(new Color(75, 75, 75));
	        	}
	    	}
	    }
	    
		int len = list.getItemCount();
		if(len>0)
			list.select(0);
		scrollPane.setViewportView(list);
	    
	}
		
	
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		String command = e.getActionCommand();
	        
	        if (obj == b1/*�Խñ��ۼ� ��ư*/) {
	            
					System.out.println("��ư ����");
					If = new InsertFrame2(this);
					If.setVisible(true);

			
		}else if(obj==b2/*������ư*/) {

				int i = list.getSelectedIndex();
				TradeBean bean = vlist.get(i);
				System.out.println("��ư ����");
				System.out.println(bean.getTradeNum());
				Uf = new UpdateFrame2(this, Integer.toString(bean.getTradeNum()));
				Uf.setVisible(true);

			
		}else if(obj==b3/*������ư*/) {
					
					int i = list.getSelectedIndex();
					TradeBean bean = vlist.get(i);
					if(mgr.delete(bean.getTradeNum())) {
			            trade2.removeAll();
			            JScrollPane scrollPane = new JScrollPane(); // JScrollPane ����
			            viewList(scrollPane,null,null);
			            trade2.add(scrollPane, BorderLayout.CENTER);
			            trade2.revalidate();
			            trade2.repaint();
					}
					
		}else if(obj==b4/*�󼼺��� ��ư*/) {
			
			
						int i = list.getSelectedIndex();
						TradeBean bean = vlist.get(i);
						System.out.println("��ư ����");
						System.out.println(bean.getTradeNum());
						Sf = new ShowFrame2(this, Integer.toString(bean.getTradeNum()),trade2);
						Sf.setVisible(true);

		}else if("complete".equals(command)) {
			
			            trade2.removeAll();
			            JScrollPane scrollPane = new JScrollPane(); // JScrollPane ����
			            viewList(scrollPane,null,null);
			            trade2.add(scrollPane, BorderLayout.CENTER);
			            trade2.revalidate();
			            trade2.repaint();
		    
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
	            
	        }
	            
	            // JScrollPane�� �����ϰ� viewList �޼��� ȣ��
	            JScrollPane scrollPane = new JScrollPane();
	            viewList(scrollPane, selectedCurrency, selectedExchange);
	            
	            // trade2�� JScrollPane �߰�
	            trade2.removeAll();
	            trade2.add(scrollPane, BorderLayout.CENTER);
	            trade2.revalidate();
	            trade2.repaint();

		    
	    }else if(obj == newBtn/*���ΰ�ħ*/) {
	    	
	    				System.out.println("��ư ����");
			            trade2.removeAll();
			            JScrollPane scrollPane = new JScrollPane(); // JScrollPane ����
			            viewList(scrollPane,null,null);
			            trade2.add(scrollPane, BorderLayout.CENTER);
			            trade2.revalidate();
			            trade2.repaint();
	    }
		
		validate();
	}//--actionPerformed
	
	

    public JPanel getTradePanel() {
        return tradePanel; // tradePanel�� ��ȯ�ϴ� �޼��� �߰�
    }
} 
