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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class WithDrawFrame extends JFrame
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
	bankBean bank;
	String TradeNum;
	bankBean rate;
	JFrame frame;
	private JComboBox<String> currencyComboBox;
	private JComboBox<String> bankComboBox;
	String id;
	
	
	public WithDrawFrame(WalletPanel testFrameTrade6, String TradeNum) {
		frame = new JFrame();
		setSize(500,500);
		this.awt = testFrameTrade6;
		this.TradeNum = TradeNum;
		setTitle("Ï∂úÍ∏à ?ã†Ï≤?");
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
		
		id = (String) bean.getTraderAcc1();
		int No = bean.getTradeNum();
		bean.setTradeNum(No);
		JPanel p4 = new JPanel();
		p4.setBackground(Color.WHITE);
		p4.add(new JLabel("Ï∂úÍ∏à Í≥ÑÏ†ï ID  :"));
		tf0 = new JTextField(Integer.toString(No),15);
		tf1 = new JTextField(TradeNum,15);
//		tf1 = new JTextField(bean.getTraderAcc1(), 15);
		tf1.setEditable(false);
		p4.add(tf1);
		p1.add(p4);
		
		
		JPanel p5 = new JPanel();
		p5.setBackground(Color.WHITE);
		p5.add(new JLabel("Ï∂úÍ∏à ?Üµ?ôî  :"));
		String[] currencies = {"USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"}; 
		currencyComboBox = new JComboBox<>(currencies);
	    currencyComboBox.setBackground(Color.WHITE);
		p5.add(currencyComboBox);
		p1.add(p5);
		
	    JPanel p6 = new JPanel();
	    p6.setBackground(Color.WHITE);
	    JLabel AmtLabel = new JLabel("?òÑ?û¨ Í≥ÑÏ¢å ?ûî?ó¨?üâ: ");
	    p6.add(AmtLabel);
	    String AccMo = (String) currencyComboBox.getSelectedItem();
	    JTextField AmtTextField = new JTextField(String.valueOf(getAccAmt(tf1.getText(), AccMo)),5);
	    AmtTextField.setEditable(false);
	    p6.add(AmtTextField);
	    p1.add(p6);
	    
	    
	    currencyComboBox.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            updateRemainingAmount();
	        }

	        private void updateRemainingAmount() {
	            String currency = (String) currencyComboBox.getSelectedItem();
	            double remainingAmount = getAccAmt(tf1.getText(), currency);
	            AmtTextField.setText(Double.toString(remainingAmount));
	        }
	    });

		JPanel p7 = new JPanel();
		p7.setBackground(Color.WHITE);
		p7.add(new JLabel("Ï∂úÍ∏à ?àò?üâ  :"));
		tf3 = new JTextField(Double.toString(bean.getSellAmt()),10);
		p7.add(tf3);
		p1.add(p7);
		
	    JPanel p8 = new JPanel();
	    p8.setBackground(Color.WHITE);
	    JLabel totalLabel = new JLabel("?Ç©: ");
	    p8.add(totalLabel);
	    JTextField totalTextField = new JTextField(20);
	    totalTextField.setEditable(false);
	    p8.add(totalTextField);
	    p1.add(p8);

	    tf3.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	            updateTotalAmount();
	        }

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            updateTotalAmount();
	        }

	        @Override
	        public void changedUpdate(DocumentEvent e) {
	            updateTotalAmount();
	        }

	        private void updateTotalAmount() {
	            String exchangeCurrency = (String) currencyComboBox.getSelectedItem();
	            String exchangeAmountStr = tf3.getText().trim(); 
	            if (exchangeAmountStr.isEmpty()) {
	                return;
	            }
	            
	            double exchangeAmount = 0.0;
        
	            try {
	                exchangeAmount = Double.parseDouble(exchangeAmountStr);
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null,"?ò¨Î∞îÎ•∏ ?à´?ûêÎ•? ?ûÖ?†•?ïò?ó¨ Ï£ºÏÑ∏?öî.");
	                return; 
	            }

	            double exchangeRate = getExchangeRate(exchangeCurrency);
	            double totalAmount = exchangeAmount * exchangeRate;
	            DecimalFormat df = new DecimalFormat("#,###");
	            String TAmount = df.format(totalAmount);
	            totalTextField.setText("?ïΩ " + TAmount + " ?õê");
	            
	            double entAmount = Double.parseDouble(tf3.getText());
	            double AmtText = Double.parseDouble(AmtTextField.getText());
	            if (entAmount > AmtText) {
	                JOptionPane.showMessageDialog(null, "Î≥¥Ïú† ?àò?üâ?? ?ûî?ó¨ ?àò?üâÎ≥¥Îã§ ?Å¥ ?àò ?óÜ?äµ?ãà?ã§.");
	                SwingUtilities.invokeLater(() -> tf3.setText("")); 
	            
	            }
	        }
	    });
		

	    JPanel p9 = new JPanel();
	    p9.setBackground(Color.WHITE);
	    p9.add(new JLabel("ÍµêÌôò ???ñâ :"));
	    String[] exchangeCurrencies = {"BNKÍ≤ΩÎÇ®", "BNKÎ∂??Ç∞", "DGB??Íµ?", "IBKÍ∏∞ÏóÖ", "KBÍµ?ÎØ?", "KDB?Ç∞?óÖ", "NH?Üç?òë", "SC?†ú?ùº", "Sh?àò?òë", "Í¥ëÏ£º", "?ã†?ïú", "?ö∞Î¶?", "?†ÑÎ∂?","?†úÏ£?","?ïò?Çò","?ïúÍµ??î®?ã∞"};
	    bankComboBox = new JComboBox<>(exchangeCurrencies);
	    bankComboBox.setBackground(Color.WHITE);
	    p9.add(bankComboBox);
	    p1.add(p9);
	    
		rate = mgr.selectBank((String) bankComboBox.getSelectedItem());
		
		JPanel p10 = new JPanel();
		p10.setBackground(Color.WHITE);
		p10.add(new JLabel("Ï∂úÍ∏à ?àò?àòÎ£? : "));
		tf4 = new JTextField(Double.toString(rate.getCharge()),10);	
		tf4.setEditable(false);
		p10.add(tf4);
		p1.add(p10);
		
		
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    buttonPanel.setBackground(new Color(169, 219, 208));
	    
	    UpBtn = new JButton("Ï∂úÍ∏à?ã†Ï≤?");
	    UpBtn.addActionListener(this);
	    buttonPanel.add(UpBtn);
	    
	    EscBtn = new JButton("Ï∑®ÏÜå");
	    EscBtn.addActionListener(this);
	    buttonPanel.add(EscBtn);
	    
	    p1.add(buttonPanel);

	    add(p1, BorderLayout.CENTER);	

}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		if(obj==UpBtn/*Ï∂úÍ∏à ?ã†Ï≤?*/) {
			TradeBean bean = new TradeBean();
			AccountBean bean1 = new AccountBean();
			withDrawBean bean2 = new withDrawBean();
			Double Amt;
			
			String Currency = (String) currencyComboBox.getSelectedItem(); // Ï∂úÍ∏à?ôî?èê
		    String myAmt = tf3.getText(); // Ï∂úÍ∏à?àò?üâ
		    Amt = getAccAmt(TradeNum,Currency) - Double.parseDouble(tf3.getText());
		    System.out.println(Amt);
		    String SAmt = Double.toString(Amt);
		    
			bean2.setWithAcc(TradeNum);
			bean2.setWithCur((String) currencyComboBox.getSelectedItem());
			bean2.setWithAmt((int) Double.parseDouble(tf3.getText()));
			bean2.setbName((String) bankComboBox.getSelectedItem());
			bean2.setDay(java.sql.Date.valueOf(LocalDate.now()));
    
            
			if(mgr.updateMyAccAmt(Currency,SAmt,TradeNum) && mgr.insertWD(bean2)) {
				check = new DialogBox(null, "Ï∂úÍ∏à ?ã†Ï≤??ù¥ ?ôÑÎ£? ?êò?óà?äµ?ãà?ã§.", "?ôï?ù∏");
                awt.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "complete"));
				dispose();
				
	            removeAll();
	            WalletPanel walletPanel = new WalletPanel();
	            add(walletPanel);
	            revalidate();
	            repaint();
			}
		}else if(obj==EscBtn/*Ï∑®ÏÜå*/) {
			dispose();
		}
		
	}
	
	private double getExchangeRate(String currency) {
		
		   Double rate = mgr.selectRate(currency);
		    if (rate != null) {
		        return rate;
		    } else {
		        JOptionPane.showMessageDialog(null, "?ôî?èê ?†ïÎ≥¥Î?? Î∂àÎü¨?ò¨ ?àò ?óÜ?äµ?ãà?ã§.");
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
