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

public class ApplyFrame3 extends JFrame
implements ActionListener{

	JLabel label;
	JButton CompleteBtn, EscBtn;
	List list;
	JTextField tf0, tf1, tf2, tf3, tf4, tf5, tf6;
	JPanel p1;
	TradeMgr mgr;
	DialogBox check;
	ShowFrame awt;
	TradeBean bean;
	String TradeNum;
	JFrame frame;
	
	public ApplyFrame3(ShowFrame showFrame, String TradeNum) {
		frame = new JFrame();
		setSize(500,500);
		this.awt = showFrame;
		this.TradeNum = TradeNum;
		setTitle("κ΅ν ? μ²? ver3.0");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Xλ²νΌ? ??¬? μ°½μ ?«??? ?λ‘κ·Έ?¨ μ’λ£
		setVisible(true);
		
		
		System.out.println("----------");
		System.out.println(TradeNum);
		mgr = new TradeMgr();
		bean = mgr.selectNO(TradeNum);
		p1=new JPanel();
	    label = new JLabel(); // label μ΄κΈ°?
		ApplyForm(bean);
		add(label, BorderLayout.NORTH);	
		validate();
	}


	public void ApplyForm(TradeBean bean) {
			
		label.setText("κ΅ν ? μ²?");
		label.setBackground(Color.RED);
		p1.setLayout(new GridLayout(0, 1));
		JPanel p6 = new JPanel();
		int No = bean.getTradeNum();
		p6.add(new JLabel("? μ²??Έ ID: "));
		
		tf0 = new JTextField(Integer.toString(No),15);
		tf1 = new JTextField("admin2",15);
		tf0.setEditable(false);
		tf1.setEditable(false);
		p6.add(tf1);
		p1.add(p6);
		
		JPanel p7 = new JPanel();
		p7.add(new JLabel("? μ²? ? μ§?"));
		
        Date currentDate = Date.valueOf(LocalDate.now());

        // SimpleDateFormat? ?¬?©??¬ ??? ??? λ¬Έμ?΄λ‘? λ³??
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(currentDate);
		tf2 = new JTextField(dateString,15);
		tf2.setEditable(false);
		p7.add(tf2);
		p1.add(p7);
		
		JPanel p8 = new JPanel();
		p8.add(new JLabel("?΄ λ³΄μ  ?΅?"));
		tf3 = new JTextField(bean.getBuyCur(),5);
		tf3.setEditable(false);
		p8.add(tf3);
		p1.add(p8);
		
		JPanel p9 = new JPanel();
		p9.add(new JLabel("?? κ΅ν ?΅?"));
		tf4 = new JTextField(bean.getSellCur(),5);
		tf4.setEditable(false);
		p9.add(tf4);
		p1.add(p9);
		
		JPanel p10 = new JPanel();
		JLabel AmtLabel = new JLabel("?΄ λ³΄μ  ??");
		p10.add(AmtLabel);
		String AccMo = tf3.getText();
		JTextField AmtTextField = new JTextField(String.valueOf(getAccAmt(tf1.getText(), AccMo)),5);
		AmtTextField.setEditable(false);
		p10.add(AmtTextField);
		p1.add(p10);
		
		JPanel p11 = new JPanel();
		JLabel totalLabel = new JLabel("?©: ");
		p11.add(totalLabel);
		double TotalAmt = getAccAmt(tf1.getText(),AccMo) * getExchangeRate(AccMo);
        DecimalFormat df1 = new DecimalFormat("#,###");
        String TAmount1 = df1.format(TotalAmt);
		JTextField totalTextField = new JTextField("?½ "+TAmount1 + " ?",10);
		totalTextField.setEditable(false);
		p11.add(totalTextField);
		p1.add(p11);
		
		JPanel p12 = new JPanel();
		p12.add(new JLabel("κ΅ν ?΅? ??"));
		tf5 = new JTextField(String.valueOf(bean.getSellAmt()),10);
		tf5.setEditable(false);
		p12.add(tf5);
		p1.add(p12);
		
		JPanel p13 = new JPanel();
		JLabel totalLabel1 = new JLabel("?©: ");
		p13.add(totalLabel1);
		String AccMo1 = tf4.getText();
		double TotalAmt1 = bean.getSellAmt() * getExchangeRate(AccMo1);
        DecimalFormat df2 = new DecimalFormat("#,###");
        String TAmount2 = df2.format(TotalAmt1);
		JTextField totalTextField1 = new JTextField("?½ "+TAmount2 + " ?",10);
		totalTextField1.setEditable(false);
		p13.add(totalTextField1);
		p1.add(p13);
		
		
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    
	    CompleteBtn = new JButton("? μ²?");
	    CompleteBtn.addActionListener(this);
	    buttonPanel.add(CompleteBtn);
	    
	    EscBtn = new JButton("μ·¨μ");
	    EscBtn.addActionListener(this);
	    buttonPanel.add(EscBtn);
	    
	    p1.add(buttonPanel);
	    add(p1, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		if(obj==CompleteBtn/*? μ²?λ²νΌ*/) {
            int result = JOptionPane.showConfirmDialog(this, "? μ²???κ² μ΅?κΉ?? \n ??:  " + bean.getSellCur() + " => " + bean.getBuyCur() +"\n ?? :  " 
            		+ bean.getSellAmt() + " => " + bean.getBuyAmt() + "\n ?? λ°? ??? ? ?? ??Έ??κ³? ??Έ λ²νΌ? ??¬μ£ΌμΈ?.", "??Έ", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                check = new DialogBox(null, "? μ²??΄ ?λ£λ??΅??€.", "??Έ");
                awt.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "complete"));
                bean.setCheck1(true);
                mgr.updateCheck(bean);
                dispose();
            }
		}else if(obj==EscBtn/*μ·¨μλ²νΌ*/) {
			dispose();
		}
		
	}
	
	private double getExchangeRate(String currency) {
		
		   Double rate = mgr.selectRate(currency);
		    if (rate != null) {
		        return rate;
		    } else {
		        JOptionPane.showMessageDialog(null, "? ?? ??? ??? ??¨ ? λ³΄λ?? μ°Ύμ ? ??΅??€.");
		        return 0.0;
		    }
		
}	
	
	private Double getAccAmt(String id, String mo) {
		double AccMo = mgr.selectAccMo(id,mo);	
		return AccMo;
}	
	
    // DialogBox ?΄??€? ?¬?©? ? ? ?€?΄?Όλ‘κ·Έλ₯? ??????€.
    // ?¬κΈ°μ? λ©μμ§??? λ²νΌ ??€?Έλ₯? ?? ₯?Όλ‘? λ°μ ?€?΄?Όλ‘κ·Έλ₯? ??±?©??€.
    class DialogBox extends JDialog {
        public DialogBox(Frame parent, String message, String buttonText) {
            super(parent, true);
            JPanel panel = new JPanel();
            JLabel label = new JLabel(message);
            panel.add(label);
            JButton button = new JButton(buttonText);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose(); // ?€?΄?Όλ‘κ·Έλ₯? ?«?΅??€.
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