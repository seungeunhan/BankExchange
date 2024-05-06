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


public class UpdateFrame extends JFrame
implements ActionListener{

	JLabel label;
	JButton UpBtn, EscBtn;
	List list;
	JTextField tf0, tf1, tf2, tf3, tf4, tf5, tf6;
	JPanel p1;
	TradeMgr mgr;
	DialogBox check;
	TestFrameTrade5 awt;
	TradeBean bean;
	String TradeNum;
	JFrame frame;
	private JComboBox<String> currencyComboBox;
	private JComboBox<String> exchangeComboBox;
	String id;
	
	
	public UpdateFrame(TestFrameTrade5 testFrameTrade5, String TradeNum) {
		frame = new JFrame();
		setSize(500,500);
		this.awt = testFrameTrade5;
		this.TradeNum = TradeNum;
		setTitle("UpdateForm");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // X버튼?�� ?��?��?�� 창을 ?��?��?��?�� ?��로그?�� 종료
		setVisible(true);
		
		
		System.out.println("----------");
		System.out.println(TradeNum);
		mgr = new TradeMgr();
		bean = mgr.selectNO(TradeNum);
		p1=new JPanel();
	    label = new JLabel(); // label 초기?��
		ApplyForm(bean);
		add(label, BorderLayout.NORTH);	
		validate();
	}
	

	public void ApplyForm(TradeBean bean) {
			
		label.setText("게시�? ?��?��");
		label.setBackground(Color.YELLOW);
		p1.setLayout(new GridLayout(0, 1));
		id = (String) bean.getTraderAcc1();
		int No = bean.getTradeNum();
		bean.setTradeNum(No);
		JPanel p4 = new JPanel();
		p4.add(new JLabel("?��록자 계정 ID  :"));
		tf0 = new JTextField(Integer.toString(No),15);
		tf1 = new JTextField("admin",15);
//		tf1 = new JTextField(bean.getTraderAcc1(), 15);
		tf1.setEditable(false);
		p4.add(tf1);
		p1.add(p4);
		
		
		JPanel p5 = new JPanel();
		p5.add(new JLabel("보유 ?��?��  :"));
		String[] currencies = {"USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"}; 
		currencyComboBox = new JComboBox<>(currencies);
		String sellCurrency = bean.getSellCur();
	    currencyComboBox.setSelectedItem(sellCurrency);
		p5.add(currencyComboBox);
		p1.add(p5);
		
	    JPanel p6 = new JPanel();
	    JLabel AmtLabel = new JLabel("?��?�� 계좌 ?��?��?��: ");
	    p6.add(AmtLabel);
	    String AccMo = (String) currencyComboBox.getSelectedItem();
	    JTextField AmtTextField = new JTextField(String.valueOf(getAccAmt(tf1.getText(), AccMo)),5);
	    AmtTextField.setEditable(false); // ?��?��?���? ?��집할 ?�� ?��?���? ?��?��
	    p6.add(AmtTextField);
	    p1.add(p6);
	    
	    
	    currencyComboBox.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            updateRemainingAmount();
	        }

	        private void updateRemainingAmount() {
	            String currency = (String) currencyComboBox.getSelectedItem();
	            double remainingAmount = getAccAmt(tf1.getText(), currency); // ?��?�� ?��?�� 중인 ?��?��?��?��?�� 메서?�� ?���?
	            AmtTextField.setText(Double.toString(remainingAmount));
	        }
	    });

		JPanel p7 = new JPanel();
		p7.add(new JLabel("보유 ?��?��  :"));
		tf3 = new JTextField(Double.toString(bean.getSellAmt()),10);
		p7.add(tf3);
		p1.add(p7);
		
	    JPanel p8 = new JPanel();
	    JLabel totalLabel = new JLabel("?��: ");
	    p8.add(totalLabel);
	    JTextField totalTextField = new JTextField(20);
	    totalTextField.setEditable(false); // ?��?��?���? ?��집할 ?�� ?��?���? ?��?��
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
	            String exchangeAmountStr = tf3.getText().trim(); // 공백?�� ?��거한 ?�� 문자?��?�� �??��?��
	            if (exchangeAmountStr.isEmpty()) {
	                return; // �? 문자?��?���? 처리?���? ?��?��
	            }
	            
	            double exchangeAmount = 0.0;
        
	            try {
	                exchangeAmount = Double.parseDouble(exchangeAmountStr);
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null,"?��바른 ?��?���? ?��?��?��?�� 주세?��.");
	                return; // ?��?���? �??��?�� ?�� ?��?�� 경우 처리?���? ?��?��
	            }

	            double exchangeRate = getExchangeRate(exchangeCurrency);
	            double totalAmount = exchangeAmount * exchangeRate;
	            DecimalFormat df = new DecimalFormat("#,###");
	            String TAmount = df.format(totalAmount);
	            totalTextField.setText("?�� " + TAmount + " ?��");
	            
	            double entAmount = Double.parseDouble(tf3.getText());
	            double AmtText = Double.parseDouble(AmtTextField.getText());
	            if (entAmount > AmtText) {
	                JOptionPane.showMessageDialog(null, "보유 ?��?��?? ?��?�� ?��?��보다 ?�� ?�� ?��?��?��?��.");
	                SwingUtilities.invokeLater(() -> tf3.setText(""));  // ?��?��?�� 값을 �??��거나 ?���? 처리�? ?��?��?�� ?�� ?��?��?��?��.
	            
	            }
	        }
	    });
		

	    JPanel p9 = new JPanel();
	    p9.add(new JLabel("교환 ?��?�� :"));
	    String[] exchangeCurrencies = {"USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"};; // 교환 ?��?�� 목록
	    exchangeComboBox = new JComboBox<>(exchangeCurrencies);
	    String buyCurrency = bean.getBuyCur();
	    exchangeComboBox.setSelectedItem(buyCurrency);
	    p9.add(exchangeComboBox);
	    p1.add(p9);
		
		JPanel p10 = new JPanel();
		p10.add(new JLabel("교환 ?��?��  :"));
		tf5 = new JTextField(Double.toString(bean.getBuyAmt()),10);
		p10.add(tf5);
		p1.add(p10);
		
	    JPanel p11 = new JPanel();
	    JLabel totalLabel1 = new JLabel("?��: ");
	    p11.add(totalLabel1);
	    JTextField totalTextField1 = new JTextField(Double.toString(bean.getBuyAmt()),20);
	    totalTextField1.setEditable(false); // ?��?��?���? ?��집할 ?�� ?��?���? ?��?��
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
	            	JOptionPane.showMessageDialog(null,"?��바른 ?��?���? ?��?��?��?�� 주세?��.");
	            }

	            double exchangeRate = getExchangeRate(exchangeCurrency);
	            double totalAmount = exchangeAmount * exchangeRate;
	            DecimalFormat df = new DecimalFormat("#,###");
	            String TAmount = df.format(totalAmount);
	            totalTextField1.setText("?�� " + TAmount + " ?��");
	        }
	    });



	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    
	    UpBtn = new JButton("?��?��???��");
	    UpBtn.addActionListener(this);
	    buttonPanel.add(UpBtn);
	    
	    EscBtn = new JButton("취소");
	    EscBtn.addActionListener(this);
	    buttonPanel.add(EscBtn);
	    
	    p1.add(buttonPanel);

	    add(p1, BorderLayout.CENTER);	

}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		if(obj==UpBtn/*?��?��버튼*/) {
			TradeBean bean = new TradeBean();
			String Currency = (String) currencyComboBox.getSelectedItem();
		    String exchange = (String) exchangeComboBox.getSelectedItem();
		    if(Currency.equals(exchange)) {
		    	JOptionPane.showMessageDialog(null, "보유 ?��?��?? 교환 ?��?��?�� 같을 ?�� ?��?��?��?��. ?��?��?��?�� 주십?��?��.");   	
		    }else {					
		    	bean.setTradeNum(Integer.parseInt(tf0.getText()));
				bean.setTraderAcc1(tf1.getText());
				bean.setSellCur((String) currencyComboBox.getSelectedItem());
				bean.setSellAmt((int) Double.parseDouble(tf3.getText()));
				bean.setBuyCur((String) exchangeComboBox.getSelectedItem());
				bean.setBuyAmt((int) Double.parseDouble(tf5.getText()));
		    }
            
			if(mgr.update(bean)) {
				check = new DialogBox(null, "?��?��?�� ?��료되?��?��?��?��.", "?��?��");
                awt.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "complete"));
				dispose();
			}
		}else if(obj==EscBtn/*취소버튼*/) {
			dispose();
		}
		
	}
	
	private double getExchangeRate(String currency) {
		
		   Double rate = mgr.selectRate(currency);
		    if (rate != null) {
		        return rate;
		    } else {
		        JOptionPane.showMessageDialog(null, "?��?��?�� ?��?��?�� ???�� ?��?�� ?��보�?? 찾을 ?�� ?��?��?��?��.");
		        return 0.0;
		    }
		
}	
	
	private Double getAccAmt(String id, String mo) {
		double AccMo = mgr.selectAccMo(id,mo);	
		return AccMo;
}	
	
    // DialogBox ?��?��?��?�� ?��?��?�� ?��?�� ?��?��?��로그�? ?��???��?��?��.
    // ?��기서?�� 메시�??? 버튼 ?��?��?���? ?��?��?���? 받아 ?��?��?��로그�? ?��?��?��?��?��.
    class DialogBox extends JDialog {
        public DialogBox(Frame parent, String message, String buttonText) {
            super(parent, true);
            JPanel panel = new JPanel();
            JLabel label = new JLabel(message);
            panel.add(label);
            JButton button = new JButton(buttonText);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose(); // ?��?��?��로그�? ?��?��?��?��.
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