package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import test.ApplyFrame4.DialogBox;

public class viewMoreFrame extends JFrame
implements ActionListener{

	JLabel label;
	JButton UpBtn, EscBtn;
	List list;
	JTextField tf0, tf1, tf2, tf3, tf4, tf5, tf6;
	JPanel p1;
	TradeMgr mgr;
	DialogBox check;
	WalletPanel awt;
	TradeBean bean;
	String TradeNum;
	JFrame frame;
	private JComboBox<String> currencyComboBox;
	private JComboBox<String> exchangeComboBox;
	String id;
	Vector<TradeBean> vlist;
	Vector<withDrawBean> vlist1;
	
	
	public viewMoreFrame(WalletPanel testFrameTrade6, String TradeNum) {
		frame = new JFrame();
		setSize(500,500);
		this.awt = testFrameTrade6;
		this.TradeNum = TradeNum;
		setTitle("?��보기");
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		System.out.println("----------");
		System.out.println(TradeNum);
		mgr = new TradeMgr();
		bean = mgr.selectNO(TradeNum);
		p1=new JPanel();
	    label = new JLabel();
	    
	    if("1".equals(TradeNum)) {
	    	ApplyForm();
	    }else if("2".equals(TradeNum)) {
	    	ApplyForm2();
	    }else if("3".equals(TradeNum)) {
	    	ApplyForm3();
	    }
		add(label, BorderLayout.NORTH);	
		validate();
	}
	


	public void ApplyForm() {
			
			
					    // ?���? ?��?��
					    label.setOpaque(true);
					    label.setText("No |   ?��?��?��ID     |   출금 ?��?��   |   출금 ?��?�� ?��?��    |    출금 ?���?");
					    label.setBackground(new Color(169, 219, 208));
					    
					    // 거래 ?��?�� �??��?���?
					    vlist1 = mgr.DESCwithDrawBean();
					    
					    // ?��?�� ?���? ?��?��
					    DecimalFormat df = new DecimalFormat("#,###");
					    
					    // 리스?�� ?��?�� �? 거래 ?��?�� 추�?
					    list = new List(vlist1.size(), false);
					    for (int i = 0; i < vlist1.size(); i++) {
					        withDrawBean bean1 = vlist1.get(i);
					        String SellAmt = df.format(bean1.getWithAmt());
					        String str = bean1.getWithNum() + "                "
					                + bean1.getWithAcc().trim() + "               "
					                + bean1.getWithCur().trim() + "                "
					                + SellAmt + "               "
					                + bean1.getDay();
					        list.add(str);
					    }
					    
					    // 리스?��?�� �? 번째 ?���? ?��?��
					    int len = list.getItemCount();
					    if (len > 0)
					        list.select(0);
					    
					    // 리스?���? ?��?��?��?�� 추�?
					    add(list, BorderLayout.CENTER);
					    
					    // 버튼 ?��?�� ?��?�� �? ?��?��
					    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					    buttonPanel.setBackground(new Color(169, 219, 208));
					    
					    // "?���?" 버튼 ?��?�� �? ?��벤트 리스?�� ?���?
					    EscBtn = new JButton("?���?");
					    EscBtn.addActionListener(this);
					    
					    // 버튼 ?��?��?�� 버튼 추�?
					    buttonPanel.add(EscBtn);
					    
					    // 버튼 ?��?��?�� ?��?��?��?�� 추�?
					    add(buttonPanel, BorderLayout.SOUTH);
	    
	}

	
	public void ApplyForm2() {
		
		
						    // ?���? ?��?��
						    label.setOpaque(true);
						    label.setText("?���? |   거래?��ID     |   교환 ?��?��   |   교환 ?��?��    |    ?��?��");
						    label.setBackground(new Color(169, 219, 208));
						    
						    // 거래 ?��?�� �??��?���?
						    vlist = mgr.selectAllDESC();
						    
						    // ?��?�� ?���? ?��?��
						    DecimalFormat df = new DecimalFormat("#,###");
						    
						    // 리스?�� ?��?�� �? 거래 ?��?�� 추�?
						    list = new List(vlist.size(), false);
						    for (int i = 0; i < vlist.size(); i++) {
						        TradeBean bean2 = vlist.get(i);					     

						        if (bean.getCheck1() && bean.getCheck2()) {
						            String SellAmt = df.format(bean2.getBuyAmt());
						            String str = bean2.getDay() + "                "
						                    + bean2.getTraderAcc1().trim() + "               "
						                    + bean2.getBuyCur().trim() + "                "
						                    + SellAmt + "               "
						                    + bean2.getCheck1();
						            list.add(str);
						        }
						    }
						    
						    // 리스?��?�� �? 번째 ?���? ?��?��
						    int len = list.getItemCount();
						    if (len > 0)
						        list.select(0);
						    
						    // 리스?���? ?��?��?��?�� 추�?
						    add(list, BorderLayout.CENTER);
						    
						    // 버튼 ?��?�� ?��?�� �? ?��?��
						    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
						    buttonPanel.setBackground(new Color(169, 219, 208));
						    
						    // "?���?" 버튼 ?��?�� �? ?��벤트 리스?�� ?���?
						    EscBtn = new JButton("?���?");
						    EscBtn.addActionListener(this);
						    
						    // 버튼 ?��?��?�� 버튼 추�?
						    buttonPanel.add(EscBtn);
						    
						    // 버튼 ?��?��?�� ?��?��?��?�� 추�?
						    add(buttonPanel, BorderLayout.SOUTH);
}
	
	
	public void ApplyForm3() {
		
		
				    // ?���? ?��?��
				    label.setOpaque(true);
				    label.setText("?���? |   거래?��ID     |   교환 ?��?��   |   교환 ?��?��    |    ?��?��");
				    label.setBackground(new Color(169, 219, 208));
				    
				    // 거래 ?��?�� �??��?���?
				    vlist = mgr.selectAllDESC();
				    
				    // ?��?�� ?���? ?��?��
				    DecimalFormat df = new DecimalFormat("#,###");
				    
				    // 리스?�� ?��?�� �? 거래 ?��?�� 추�?
				    list = new List(vlist.size(), false);
				    for (int i = 0; i < vlist.size(); i++) {
				        TradeBean bean = vlist.get(i);
				        String SellAmt = df.format(bean.getBuyAmt());
				        String str = bean.getDay() + "                "
				                + bean.getTraderAcc1().trim() + "               "
				                + bean.getBuyCur().trim() + "                "
				                + SellAmt + "               "
				                + bean.getCheck1();
				        list.add(str);
				    }
				    
				    // 리스?��?�� �? 번째 ?���? ?��?��
				    int len = list.getItemCount();
				    if (len > 0)
				        list.select(0);
				    
				    // 리스?���? ?��?��?��?�� 추�?
				    add(list, BorderLayout.CENTER);
				    
				    // 버튼 ?��?�� ?��?�� �? ?��?��
				    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				    buttonPanel.setBackground(new Color(169, 219, 208));
				    
				    // "?���?" 버튼 ?��?�� �? ?��벤트 리스?�� ?���?
				    EscBtn = new JButton("?���?");
				    EscBtn.addActionListener(this);
				    
				    // 버튼 ?��?��?�� 버튼 추�?
				    buttonPanel.add(EscBtn);
				    
				    // 버튼 ?��?��?�� ?��?��?��?�� 추�?
				    add(buttonPanel, BorderLayout.SOUTH);
}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		if(obj==EscBtn/*취소*/) {
			dispose();
		}	
	}
	
	private Double getAccAmt(String id, String mo) {
		double AccMo = mgr.selectAccMo(id,mo);	
		return AccMo;
}	
	
}
