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
		setTitle("?”ë³´ê¸°");
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
			
			
					    // ?¼ë²? ?„¤? •
					    label.setOpaque(true);
					    label.setText("No |   ?‘?„±?ID     |   ì¶œê¸ˆ ?†µ?™”   |   ì¶œê¸ˆ ?†µ?™” ?•¡?ˆ˜    |    ì¶œê¸ˆ ?‚ ì§?");
					    label.setBackground(new Color(169, 219, 208));
					    
					    // ê±°ë˜ ?‚´?—­ ê°?? ¸?˜¤ê¸?
					    vlist1 = mgr.DESCwithDrawBean();
					    
					    // ?ˆ«? ?¬ë§? ?„¤? •
					    DecimalFormat df = new DecimalFormat("#,###");
					    
					    // ë¦¬ìŠ¤?Š¸ ?ƒ?„± ë°? ê±°ë˜ ?‚´?—­ ì¶”ê?
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
					    
					    // ë¦¬ìŠ¤?Š¸?˜ ì²? ë²ˆì§¸ ?•­ëª? ?„ ?ƒ
					    int len = list.getItemCount();
					    if (len > 0)
					        list.select(0);
					    
					    // ë¦¬ìŠ¤?Š¸ë¥? ?”„? ˆ?„?— ì¶”ê?
					    add(list, BorderLayout.CENTER);
					    
					    // ë²„íŠ¼ ?Œ¨?„ ?ƒ?„± ë°? ?„¤? •
					    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					    buttonPanel.setBackground(new Color(169, 219, 208));
					    
					    // "?‹«ê¸?" ë²„íŠ¼ ?ƒ?„± ë°? ?´ë²¤íŠ¸ ë¦¬ìŠ¤?„ˆ ?“±ë¡?
					    EscBtn = new JButton("?‹«ê¸?");
					    EscBtn.addActionListener(this);
					    
					    // ë²„íŠ¼ ?Œ¨?„?— ë²„íŠ¼ ì¶”ê?
					    buttonPanel.add(EscBtn);
					    
					    // ë²„íŠ¼ ?Œ¨?„?„ ?”„? ˆ?„?— ì¶”ê?
					    add(buttonPanel, BorderLayout.SOUTH);
	    
	}

	
	public void ApplyForm2() {
		
		
						    // ?¼ë²? ?„¤? •
						    label.setOpaque(true);
						    label.setText("?‚ ì§? |   ê±°ë˜?ID     |   êµí™˜ ?†µ?™”   |   êµí™˜ ?•¡?ˆ˜    |    ?ƒ?ƒœ");
						    label.setBackground(new Color(169, 219, 208));
						    
						    // ê±°ë˜ ?‚´?—­ ê°?? ¸?˜¤ê¸?
						    vlist = mgr.selectAllDESC();
						    
						    // ?ˆ«? ?¬ë§? ?„¤? •
						    DecimalFormat df = new DecimalFormat("#,###");
						    
						    // ë¦¬ìŠ¤?Š¸ ?ƒ?„± ë°? ê±°ë˜ ?‚´?—­ ì¶”ê?
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
						    
						    // ë¦¬ìŠ¤?Š¸?˜ ì²? ë²ˆì§¸ ?•­ëª? ?„ ?ƒ
						    int len = list.getItemCount();
						    if (len > 0)
						        list.select(0);
						    
						    // ë¦¬ìŠ¤?Š¸ë¥? ?”„? ˆ?„?— ì¶”ê?
						    add(list, BorderLayout.CENTER);
						    
						    // ë²„íŠ¼ ?Œ¨?„ ?ƒ?„± ë°? ?„¤? •
						    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
						    buttonPanel.setBackground(new Color(169, 219, 208));
						    
						    // "?‹«ê¸?" ë²„íŠ¼ ?ƒ?„± ë°? ?´ë²¤íŠ¸ ë¦¬ìŠ¤?„ˆ ?“±ë¡?
						    EscBtn = new JButton("?‹«ê¸?");
						    EscBtn.addActionListener(this);
						    
						    // ë²„íŠ¼ ?Œ¨?„?— ë²„íŠ¼ ì¶”ê?
						    buttonPanel.add(EscBtn);
						    
						    // ë²„íŠ¼ ?Œ¨?„?„ ?”„? ˆ?„?— ì¶”ê?
						    add(buttonPanel, BorderLayout.SOUTH);
}
	
	
	public void ApplyForm3() {
		
		
				    // ?¼ë²? ?„¤? •
				    label.setOpaque(true);
				    label.setText("?‚ ì§? |   ê±°ë˜?ID     |   êµí™˜ ?†µ?™”   |   êµí™˜ ?•¡?ˆ˜    |    ?ƒ?ƒœ");
				    label.setBackground(new Color(169, 219, 208));
				    
				    // ê±°ë˜ ?‚´?—­ ê°?? ¸?˜¤ê¸?
				    vlist = mgr.selectAllDESC();
				    
				    // ?ˆ«? ?¬ë§? ?„¤? •
				    DecimalFormat df = new DecimalFormat("#,###");
				    
				    // ë¦¬ìŠ¤?Š¸ ?ƒ?„± ë°? ê±°ë˜ ?‚´?—­ ì¶”ê?
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
				    
				    // ë¦¬ìŠ¤?Š¸?˜ ì²? ë²ˆì§¸ ?•­ëª? ?„ ?ƒ
				    int len = list.getItemCount();
				    if (len > 0)
				        list.select(0);
				    
				    // ë¦¬ìŠ¤?Š¸ë¥? ?”„? ˆ?„?— ì¶”ê?
				    add(list, BorderLayout.CENTER);
				    
				    // ë²„íŠ¼ ?Œ¨?„ ?ƒ?„± ë°? ?„¤? •
				    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				    buttonPanel.setBackground(new Color(169, 219, 208));
				    
				    // "?‹«ê¸?" ë²„íŠ¼ ?ƒ?„± ë°? ?´ë²¤íŠ¸ ë¦¬ìŠ¤?„ˆ ?“±ë¡?
				    EscBtn = new JButton("?‹«ê¸?");
				    EscBtn.addActionListener(this);
				    
				    // ë²„íŠ¼ ?Œ¨?„?— ë²„íŠ¼ ì¶”ê?
				    buttonPanel.add(EscBtn);
				    
				    // ë²„íŠ¼ ?Œ¨?„?„ ?”„? ˆ?„?— ì¶”ê?
				    add(buttonPanel, BorderLayout.SOUTH);
}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		if(obj==EscBtn/*ì·¨ì†Œ*/) {
			dispose();
		}	
	}
	
	private Double getAccAmt(String id, String mo) {
		double AccMo = mgr.selectAccMo(id,mo);	
		return AccMo;
}	
	
}
