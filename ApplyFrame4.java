package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ApplyFrame4 extends JFrame
implements ActionListener{

	JLabel label;
	JButton CompleteBtn, EscBtn;
	List list;
	JTextField tf0, tf1, tf2, tf3, tf4, tf5, tf6;
	JPanel p1;
	TradeMgr mgr;
	DialogBox check;
	ShowFrame2 awt;
	TradeBean bean;
	String TradeNum;
	JFrame frame;
	

	public ApplyFrame4(ShowFrame2 showFrame2, String TradeNum) {
		frame = new JFrame();
		setSize(500,500);
		this.awt = showFrame2;
		this.TradeNum = TradeNum;
		setTitle("êµí™˜?‹ ì²?");
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		System.out.println("----------");
		System.out.println(TradeNum);
		mgr = new TradeMgr();
		bean = mgr.selectNO(TradeNum);
		p1=new JPanel();
	    label = new JLabel();
		ApplyForm(bean);
		add(label, BorderLayout.NORTH);	
		validate();
		
	}


	public void ApplyForm(TradeBean bean) {
			
		p1.setLayout(new GridLayout(0, 1));
		p1.setBackground(new Color(169, 219, 208));
		
		JLabel label = new JLabel();
		label.setBackground(new Color(169, 219, 208));
		p1.add(label);
		
		
		JPanel p6 = new JPanel();
		p6.setBackground(Color.WHITE);
		int No = bean.getTradeNum();
		p6.add(new JLabel("?“±ë¡ì ID: "));
		
		tf0 = new JTextField(Integer.toString(No),15);
		tf1 = new JTextField("admin2",15);
		tf0.setEditable(false);
		tf1.setEditable(false);
		p6.add(tf1);
		p1.add(p6);
		
		JPanel p7 = new JPanel();
		p7.setBackground(Color.WHITE);
		p7.add(new JLabel("?“±ë¡? ?‚ ì§?"));
		
        Date currentDate = Date.valueOf(LocalDate.now());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(currentDate);
		tf2 = new JTextField(dateString,15);
		tf2.setEditable(false);
		p7.add(tf2);
		p1.add(p7);
		
		JPanel p8 = new JPanel();
		p8.setBackground(Color.WHITE);
		p8.add(new JLabel("?‚´ êµí™˜ ?†µ?™”"));
		tf3 = new JTextField(bean.getBuyCur(),5);
		tf3.setEditable(false);
		p8.add(tf3);
		p1.add(p8);
		
		JPanel p9 = new JPanel();
		p9.setBackground(Color.WHITE);
		p9.add(new JLabel("?ƒ?? êµí™˜ ?†µ?™”"));
		tf4 = new JTextField(bean.getSellCur(),5);
		tf4.setEditable(false);
		p9.add(tf4);
		p1.add(p9);
		
		JPanel p10 = new JPanel();
		p10.setBackground(Color.WHITE);
		JLabel AmtLabel = new JLabel("?‚´ ë³´ìœ  ?ˆ˜?Ÿ‰");
		p10.add(AmtLabel);
		String AccMo = tf3.getText();
		JTextField AmtTextField = new JTextField(String.valueOf(getAccAmt(tf1.getText(), AccMo)),5);
		AmtTextField.setEditable(false);
		p10.add(AmtTextField);
		p1.add(p10);
		
		JPanel p11 = new JPanel();
		p11.setBackground(Color.WHITE);
		JLabel totalLabel = new JLabel("?‚©: ");
		p11.add(totalLabel);
		double TotalAmt = getAccAmt(tf1.getText(),AccMo) * getExchangeRate(AccMo);
        DecimalFormat df1 = new DecimalFormat("#,###");
        String TAmount1 = df1.format(TotalAmt);
		JTextField totalTextField = new JTextField("?•½ "+TAmount1 + " ?›",10);
		totalTextField.setEditable(false);
		p11.add(totalTextField);
		p1.add(p11);
		
		JPanel p12 = new JPanel();
		p12.setBackground(Color.WHITE);
		p12.add(new JLabel("êµí™˜ ?†µ?™” ?ˆ˜?Ÿ‰"));
		tf5 = new JTextField(String.valueOf(bean.getSellAmt()),10);
		tf5.setEditable(false);
		p12.add(tf5);
		p1.add(p12);
		
		JPanel p13 = new JPanel();
		p13.setBackground(Color.WHITE);
		JLabel totalLabel1 = new JLabel("?‚©: ");
		p13.add(totalLabel1);
		String AccMo1 = tf4.getText();
		double TotalAmt1 = bean.getSellAmt() * getExchangeRate(AccMo1);
        DecimalFormat df2 = new DecimalFormat("#,###");
        String TAmount2 = df2.format(TotalAmt1);
		JTextField totalTextField1 = new JTextField("?•½ "+TAmount2 + " ?›",10);
		totalTextField1.setEditable(false);
		p13.add(totalTextField1);
		p1.add(p13);
		
		
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    buttonPanel.setBackground(new Color(169, 219, 208));
	    
	    CompleteBtn = new JButton("?‹ ì²?");
	    CompleteBtn.addActionListener(this);
	    buttonPanel.add(CompleteBtn);
	    
	    EscBtn = new JButton("ì·¨ì†Œ");
	    EscBtn.addActionListener(this);
	    buttonPanel.add(EscBtn);
	    
	    p1.add(buttonPanel);
	    add(p1, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		if(obj==CompleteBtn/*?‹ ì²?ë²„íŠ¼*/) {
            int result = JOptionPane.showConfirmDialog(this, "?‹ ì²??•˜?‹œê² ìŠµ?‹ˆê¹?? \n ?™”?:  " + bean.getSellCur() + " => " + bean.getBuyCur() +"\n ?ˆ˜?Ÿ‰ :  " 
            		+ bean.getSellAmt() + " => " + bean.getBuyAmt() + "\n ?™”? ë°? ?ˆ˜?Ÿ‰?„ ? •?™•?ˆ ?™•?¸?•˜?‹œê³? ?™•?¸ ë²„íŠ¼?„ ?ˆŒ?Ÿ¬ì£¼ì„¸?š”.", "?™•?¸", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                check = new DialogBox(null, "?‹ ì²??´ ?™„ë£Œë˜?—ˆ?Šµ?‹ˆ?‹¤.", "?™•?¸");
                awt.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "complete"));
                bean.setCheck1(true);
                mgr.updateCheck(bean);
                dispose();
            }
		}else if(obj==EscBtn/*ì·¨ì†Œ*/) {
			dispose();
		}
		
	}
	
	private double getExchangeRate(String currency) {
		
		   Double rate = mgr.selectRate(currency);
		    if (rate != null) {
		        return rate;
		    } else {
		        JOptionPane.showMessageDialog(null, "?™”? ? •ë³´ë?? ë¶ˆëŸ¬?˜¬ ?ˆ˜ ?—†?Šµ?‹ˆ?‹¤.");
		        return 0.0;
		    }
		
}	
	
	private Double getAccAmt(String id, String mo) {
		double AccMo = mgr.selectAccMo(id,mo);	
		return AccMo;
}	
	

    class DialogBox extends JDialog {
        public DialogBox(Frame parent, String message, String buttonText) {
            super(parent, true);
            JPanel panel = new JPanel();
            JLabel label = new JLabel(message);
            panel.add(label);
            JButton button = new JButton(buttonText);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose(); 
                }
            });
            panel.add(button);
            getContentPane().add(panel);
            setSize(300, 150);
            setLocationRelativeTo(null);
            setVisible(true);
            }
    }
    
	
	
	
	
}
