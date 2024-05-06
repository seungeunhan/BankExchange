package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class WalletPanel extends JPanel implements ActionListener{
	
			JButton withDrawBtn,moreBtn, moreBtn1, moreBtn2;
			JButton searchBtn, newBtn;
			JLabel label, sc1, sc2;
			JPanel WalletPanel, wallet1, wallet2, wallet3, w3_1, w3_2;
			JPanel p1, p2, p3, p4;
			JTextField tf0;
			JTextArea tf1,tf2, tf3, tf4, tf5;
			JScrollPane scrollPane;
			
			WithDrawFrame wdf;
			viewMoreFrame vmf;
			
			List list;
			Vector<TradeBean> vlist;
			TradeMgr mgr;
			String id = "admin";
			
			private JTable table1, table2, table3, table4;

	
		public WalletPanel(){
//			this.id = id;
			setLayout(null);
			setBackground(new Color(169, 219, 208));
			mgr = new TradeMgr();
			
			WalletPanel = new JPanel(new GridLayout(3,1,20,20));
			WalletPanel.setBackground(new Color(169, 219, 208));
			WalletPanel.setBounds(0,0,830,620);
			
			wallet1 = new JPanel();
			wallet1.setBackground(Color.WHITE);
			wallet1.setLayout(null);
			
			JLabel label = new JLabel("�� ������ �ڻ�");
			label.setBackground(new Color(169, 219, 208));
			label.setForeground(new Color(0,0,66));
			label.setBounds(20,0, 150,50);
			wallet1.add(label);
			
			
			wallet2 = new JPanel();
			wallet2.setBackground(Color.WHITE);
			wallet2.setLayout(null);
			JLabel label1 = new JLabel("��� ���� ��Ȳ");
			label1.setBackground(new Color(169, 219, 208));
			label1.setForeground(new Color(0,0,66));
			label1.setBounds(20,0, 150,50);
			wallet2.add(label1);
			
			
			wallet3 = new JPanel(new GridLayout(1,2,10,10));
			wallet3.setBackground(new Color(169, 219, 208));
			w3_1 = new JPanel();
			w3_1.setLayout(null);
			w3_1.setBackground(Color.WHITE);
			JLabel label2 = new JLabel("�ŷ� �Ϸ� ����");
			label2.setBackground(new Color(169, 219, 208));
			label2.setForeground(new Color(0,0,66));
			label2.setBounds(10,0, 150,50);
			w3_1.add(label2);
			
			
			w3_2 = new JPanel();
			w3_2.setLayout(null);
			w3_2.setBackground(Color.WHITE);
			JLabel label3 = new JLabel("�ŷ��� �̿� ����");
			label3.setBackground(new Color(169, 219, 208));
			label3.setForeground(new Color(0,0,66));
			label3.setBounds(10,0, 150,50);
			w3_2.add(label3);
			
			
			label.setFont(new Font("���� ���", Font.BOLD , 16));
			label1.setFont(new Font("���� ���", Font.BOLD , 16));
			label2.setFont(new Font("���� ���", Font.BOLD , 16));
			label3.setFont(new Font("���� ���", Font.BOLD , 16));
			
			Vector<AccountBean> data1 = mgr.selectAcc(id);
	        Vector<withDrawBean> data2 = mgr.DESCwithDrawBean();
	        Vector<TradeBean> data3 = mgr.selectAllDESC2();
	        Vector<TradeBean> data4 = mgr.selectAllDESC();
	        
	        JPanel panel1 = createTableAB(data1);
	        panel1.setBackground(Color.WHITE);
	        panel1.setBounds(0,80,840,41);
	        JPanel panel2 = createTableWD(data2);
	        panel2.setBackground(Color.WHITE);
	        panel2.setBounds(0,80,840,75);
	        JPanel panel3 = createTableTB2(data3);
	        panel3.setBackground(Color.WHITE);
	        panel3.setBounds(0,60,420,75);
	        JPanel panel4 = createTableTB(data4);
	        panel4.setBackground(Color.WHITE);
	        panel4.setBounds(0,60,420,75);

	        
//	        System.out.println("Data size: " + data1.size());
//	        for (AccountBean bean : data1) {
//	            System.out.println(bean.toString()); // TradeBean Ŭ������ toString �޼��尡 �ִٰ� �����մϴ�.
//	        }
//	        System.out.println("Data size: " + data3.size());
//	        for (withDrawBean bean : data2) {
//	        	System.out.println(bean.toString()); // TradeBean Ŭ������ toString �޼��尡 �ִٰ� �����մϴ�.
//	        }
//	        System.out.println("Data size: " + data3.size());
//	        for (TradeBean bean : data3) {
//	        	System.out.println(bean.toString()); // TradeBean Ŭ������ toString �޼��尡 �ִٰ� �����մϴ�.
//	        }
//	        System.out.println("Data size: " + data4.size());
//	        for (TradeBean bean : data4) {
//	        	System.out.println(bean.toString()); // TradeBean Ŭ������ toString �޼��尡 �ִٰ� �����մϴ�.
//	        }
	        
	        wallet1.add(panel1,BorderLayout.CENTER);
	        wallet2.add(panel2,BorderLayout.CENTER);
	        w3_1.add(panel3,BorderLayout.CENTER);
	        w3_2.add(panel4,BorderLayout.CENTER);
	        
	        withDrawBtn = new JButton("��ݽ�û");
	        moreBtn = new JButton("������");
	        moreBtn1 = new JButton("������");
	        moreBtn2 = new JButton("������");
	        
			withDrawBtn.setFont(new Font("���� ���", Font.BOLD , 16));
			moreBtn.setFont(new Font("���� ���", Font.BOLD , 16));
			moreBtn1.setFont(new Font("���� ���", Font.BOLD , 16));
			moreBtn2.setFont(new Font("���� ���", Font.BOLD , 16));
			
			withDrawBtn.setForeground(Color.WHITE);
			moreBtn.setForeground(Color.WHITE);
			moreBtn1.setForeground(Color.WHITE);
			moreBtn2.setForeground(Color.WHITE);
			
			withDrawBtn.setBackground(new Color(92,209,229));
			moreBtn.setBackground(new Color(97,219,240));
			moreBtn1.setBackground(new Color(97,219,240));
			moreBtn2.setBackground(new Color(97,219,240));
			
	        withDrawBtn.setBounds(350, 140 , 120, 40);
	        moreBtn.setBounds(758, 20 , 55, 30);
	        moreBtn1.setBounds(340, 15 , 55, 30);
	        moreBtn2.setBounds(340, 15 , 55, 30);
	        
	        withDrawBtn.setBorder(BorderFactory.createLineBorder(Color.CYAN));
	        moreBtn.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	        moreBtn1.setBorder(BorderFactory.createLineBorder(Color.RED));
	        moreBtn2.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
			
	    	withDrawBtn.addActionListener(this);
	    	moreBtn.addActionListener(this);
	    	moreBtn1.addActionListener(this);
	    	moreBtn2.addActionListener(this);
	      
			
			wallet1.add(withDrawBtn);
			wallet2.add(moreBtn);
			w3_1.add(moreBtn1);
			w3_2.add(moreBtn2);
			
			
			wallet3.add(w3_1);
			wallet3.add(w3_2);
			
			WalletPanel.add(wallet1);
			WalletPanel.add(wallet2);
			WalletPanel.add(wallet3);
			
					
			
			add(WalletPanel);
			
		}
		
		
	    private JPanel createTableTB(Vector<TradeBean> data) {
	    	
	    	String state = null;
	    	for (TradeBean bean : data) {
	    	    if (bean.getCheck2()) {
	    	        state = "�Ϸ�";
	    	    } else if (bean.getCheck1() && !bean.getCheck2()) {
	    	        state = "�ŷ����� �����";
	    	    } else if (!bean.getCheck1() && !bean.getCheck2()) {
	    	        state = "�Խñ� �����";
	    	    }
	    	    // ������ ���¸� �Ǻ��Ͽ� ������ ���� �����մϴ�.
	    	}
	        
	        
	        DefaultTableModel model = new DefaultTableModel();
	        model.addColumn("��¥");
	        model.addColumn("�ŷ���");
	        model.addColumn("��ȯ��ȭ");
	        model.addColumn("�ݾ�");
	        model.addColumn("����");
	        
	        
	        
	        for (TradeBean bean : data) {
	            model.addRow(new Object[] { 
	            		bean.getDay(), 
	            		bean.getTraderAcc1(),
	            		bean.getBuyCur(),
	            		bean.getBuyAmt(),
	            		state
	            		});
	            // Add more data as needed
	        }
	        

	        JTable table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        table.setEnabled(false);

	        JPanel panel = new JPanel(new BorderLayout()); // BorderLayout���� �г� ����
	        panel.add(scrollPane, BorderLayout.CENTER); // JScrollPane�� �гο� �߰�
	        return panel;
	    }
	    
	    
	    private JPanel createTableWD(Vector<withDrawBean> data) {
	    	
	        DefaultTableModel model = new DefaultTableModel();
	        model.addColumn("�����ȣ");
	        model.addColumn("��¥");
	        model.addColumn("�����ȭ");
	        model.addColumn("�ݾ�");
	        model.addColumn("����");
	        model.addColumn("����");
	        
	        String state;
	        
	        Vector<withDrawBean> bean1 = mgr.DESCwithDrawBean();
	        if (!bean1.isEmpty()) {
	            withDrawBean bean = bean1.get(0); // ������ ù ��° ��Ҹ� ������
	            if (bean.getCheck()) {
	                state = "�Ϸ�";
	            } else {
	                state = "ȯ�� �����";
	            }
	        } else {
	            state = "ȯ�� �����";
	        }

	        for (withDrawBean bean : data) {
	            model.addRow(new Object[] { 
	            		bean.getWithNum(), 
	            		bean.getDay(),
	            		bean.getWithCur(),
	            		bean.getWithAmt(),
	            		bean.getbName(),
	            		state
	            		});
	            // Add more data as needed
	        }

	        JTable table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        table.setEnabled(false);

	        JPanel panel = new JPanel(new BorderLayout()); // BorderLayout���� �г� ����
	        panel.add(scrollPane, BorderLayout.CENTER); // JScrollPane�� �гο� �߰�
	        return panel;
	    }
	    
	    
	    
	    private JPanel createTableAB(Vector<AccountBean> data) {
	    	
	        DefaultTableModel model = new DefaultTableModel();
	        model.addColumn("USD");
	        model.addColumn("JPY");
	        model.addColumn("THB");
	        model.addColumn("AUD");
	        model.addColumn("CAD");
	        model.addColumn("CHF");
	        model.addColumn("CNY");
	        model.addColumn("EUR");
	        model.addColumn("GBP");
	        model.addColumn("HKD");
	        model.addColumn("NZD");
	        model.addColumn("SGD");
	        model.addColumn("KRW");


	        for (AccountBean bean : data) {
	            model.addRow(new Object[] { 
	            		bean.getUSD(), 
	            		bean.getJPY(),
	            		bean.getTHB(),
	            		bean.getAUD(),
	            		bean.getCAD(),
	            		bean.getCHF(),
	            		bean.getCNY(),
	            		bean.getEUR(),
	            		bean.getGBP(),
	            		bean.getHKD(),
	            		bean.getNZD(),
	            		bean.getSGD(),
	            		bean.getKRW()
	            		});
	            // Add more data as needed
	        }

	        JTable table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        table.setEnabled(false);

	        JPanel panel = new JPanel(new BorderLayout()); // BorderLayout���� �г� ����
	        panel.add(scrollPane, BorderLayout.CENTER); // JScrollPane�� �гο� �߰�
	        return panel;
	    }
	    
	    private JPanel createTableTB2(Vector<TradeBean> data) {
	    	
	    	String state = "�Ϸ�";
	        
	        
	        DefaultTableModel model = new DefaultTableModel();
	        model.addColumn("��¥");
	        model.addColumn("�ŷ���");
	        model.addColumn("��ȯ��ȭ");
	        model.addColumn("�ݾ�");
	        model.addColumn("����");
	        
	        
	        
	        for (TradeBean bean : data) {
	            model.addRow(new Object[] { 
	            		bean.getDay(), 
	            		bean.getTraderAcc1(),
	            		bean.getBuyCur(),
	            		bean.getBuyAmt(),
	            		state
	            		});
	            // Add more data as needed
	        }
	        

	        JTable table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        table.setEnabled(false);

	        JPanel panel = new JPanel(new BorderLayout()); // BorderLayout���� �г� ����
	        panel.add(scrollPane, BorderLayout.CENTER); // JScrollPane�� �гο� �߰�
	        return panel;
	    }
		
		
		
		public void actionPerformed(ActionEvent e) {
			
			Object obj = e.getSource();
			String command = e.getActionCommand();
			
	      if (obj == withDrawBtn/*��� ��û*/) {
	            
					System.out.println("��ư ����");
					wdf = new WithDrawFrame(this, "admin");
					wdf.setVisible(true);					
			
		}else if(obj==moreBtn/*������-��� */) {
			
					System.out.println("��ư ����");
					vmf = new viewMoreFrame(this, "1");
					vmf.setVisible(true);
			
		}else if(obj==moreBtn1/*������-�ŷ�*/) {
					
					System.out.println("��ư ����");
					vmf = new viewMoreFrame(this, "2");
					vmf.setVisible(true);
					
		}else if(obj==moreBtn2/*������-�ŷ���*/) {
			
					System.out.println("��ư ����");
					vmf = new viewMoreFrame(this, "3");
					vmf.setVisible(true);
			
			
		}	else if("complete".equals(command)) {
			
            removeAll();
            WalletPanel walletPanel = new WalletPanel();
            add(walletPanel);
            revalidate();
            repaint();			
		}
	      
			validate();
		}
	    public JPanel getWalletPanel() {
	        return WalletPanel; // tradePanel�� ��ȯ�ϴ� �޼��� �߰�
	    }
}



