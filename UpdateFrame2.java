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

public class UpdateFrame2 extends JFrame
implements ActionListener{

	JLabel label;
	JButton UpBtn, EscBtn;
	List list;
	JTextField tf0, tf1, tf2, tf3, tf4, tf5, tf6;
	JPanel p1;
	TradeMgr mgr;
	DialogBox check;
	TestPanel awt;
	TradeBean bean;
	String TradeNum;
	JFrame frame;
	private JComboBox<String> currencyComboBox;
	private JComboBox<String> exchangeComboBox;
	String id;
	
	
	public UpdateFrame2(TestPanel testFrameTrade6, String TradeNum) {
		frame = new JFrame();
		setSize(500,500);
		this.awt = testFrameTrade6;
		this.TradeNum = TradeNum;
		setTitle("Í≤åÏãúÍ∏? ?àò?†ï");
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
		p4.add(new JLabel("?ì±Î°ùÏûê Í≥ÑÏ†ï ID  :"));
		tf0 = new JTextField(Integer.toString(No),15);
		tf1 = new JTextField("admin",15);
//		tf1 = new JTextField(bean.getTraderAcc1(), 15);
		tf1.setEditable(false);
		p4.add(tf1);
		p1.add(p4);
		
		
		JPanel p5 = new JPanel();
		p5.setBackground(Color.WHITE);
		p5.add(new JLabel("Î≥¥Ïú† ?Üµ?ôî  :"));
		String[] currencies = {"USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"}; 
		currencyComboBox = new JComboBox<>(currencies);
		String sellCurrency = bean.getSellCur();
	    currencyComboBox.setSelectedItem(sellCurrency);
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
		p7.add(new JLabel("Î≥¥Ïú† ?àò?üâ  :"));
		tf3 = new JTextField(Double.toString(bean.getSellAmt()),10);
		p7.add(tf3);
		p1.add(p7);
		
	    JPanel p8 = new JPanel();
	    p8.setBackground(Color.WHITE);
	    JLabel totalLabel = new JLabel("?Ç©: ");
	    p8.add(totalLabel);
	    JTextField totalTextField = new JTextField(20);
	    totalTextField.setEditable(false); //  Í∂? ?äú ?òÑÂ™?   ?†™ÔßûÎ¨ìÎ∏?  ?ãî  Îæ? Î£ÑÊø°   ÍΩ? ?†ô
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
	    p9.add(new JLabel("ÍµêÌôò ?Üµ?ôî :"));
	    String[] exchangeCurrencies = {"USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"};; // ?è¥Î®∞ÏÜö  ?Üï Î£? Ôßè‚ë∏Ï§?
	    exchangeComboBox = new JComboBox<>(exchangeCurrencies);
	    exchangeComboBox.setBackground(Color.WHITE);
	    
	    String buyCurrency = bean.getBuyCur();
	    exchangeComboBox.setSelectedItem(buyCurrency);
	    p9.add(exchangeComboBox);
	    p1.add(p9);
		
		JPanel p10 = new JPanel();
		p10.setBackground(Color.WHITE);
		p10.add(new JLabel("ÍµêÌôò ?àò?üâ  :"));
		tf5 = new JTextField(Double.toString(bean.getBuyAmt()),10);
		p10.add(tf5);
		p1.add(p10);
		
	    JPanel p11 = new JPanel();
	    p11.setBackground(Color.WHITE);
	    JLabel totalLabel1 = new JLabel("?Ç©: ");
	    p11.add(totalLabel1);
	    JTextField totalTextField1 = new JTextField(Double.toString(bean.getBuyAmt()),20);
	    totalTextField1.setEditable(false);
	    p11.add(totalTextField1);
	    p1.add(p11);

	    tf5.getDocument().addDocumentListener(new DocumentListener() {
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
	        	
	            String exchangeCurrency = (String) exchangeComboBox.getSelectedItem();
	            double exchangeAmount = 0.0;
	            try {
	                exchangeAmount = Double.parseDouble(tf5.getText());
	            } catch (NumberFormatException ex) {
	            	JOptionPane.showMessageDialog(null,"?ò¨Î∞îÎ•∏ ?à´?ûêÎ•? ?ûÖ?†•?ïò?ó¨ Ï£ºÏÑ∏?öî.");
	            }

	            double exchangeRate = getExchangeRate(exchangeCurrency);
	            double totalAmount = exchangeAmount * exchangeRate;
	            DecimalFormat df = new DecimalFormat("#,###");
	            String TAmount = df.format(totalAmount);
	            totalTextField1.setText("?ïΩ " + TAmount + " ?õê");
	        }
	    });



	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    buttonPanel.setBackground(new Color(169, 219, 208));
	    
	    UpBtn = new JButton("?àò?†ï???û•");
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
		if(obj==UpBtn/*?àò?†ï ???û•*/) {
			TradeBean bean = new TradeBean();
			String Currency = (String) currencyComboBox.getSelectedItem();
		    String exchange = (String) exchangeComboBox.getSelectedItem();
		    if(Currency.equals(exchange)) {
		    	JOptionPane.showMessageDialog(null, "Î≥¥Ïú† ?ô∏?ôî?? ÍµêÌôò ?ô∏?ôî?äî Í∞ôÏùÑ ?àò ?óÜ?äµ?ãà?ã§. ?àò?†ï?ïò?ó¨ Ï£ºÏã≠?ãú?ò§.");   	
		    }else {					
		    	bean.setTradeNum(Integer.parseInt(tf0.getText()));
				bean.setTraderAcc1(tf1.getText());
				bean.setSellCur((String) currencyComboBox.getSelectedItem());
				bean.setSellAmt((int) Double.parseDouble(tf3.getText()));
				bean.setBuyCur((String) exchangeComboBox.getSelectedItem());
				bean.setBuyAmt((int) Double.parseDouble(tf5.getText()));
		    }
            
			if(mgr.update(bean)) {
				check = new DialogBox(null, "Í≤åÏãúÍ∏? ?àò?†ï?ù¥ ?ôÑÎ£åÎêò?óà?äµ?ãà?ã§.", "?ôï?ù∏");
                awt.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "complete"));
				dispose();
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
