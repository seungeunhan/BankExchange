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


public class InsertFrame extends JFrame
implements ActionListener{

	JLabel label;
	JButton insBtn, EscBtn;
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
	
	
	public InsertFrame(TestFrameTrade5 testFrameTrade5) {
		frame = new JFrame();
		setSize(500,500);
		this.awt = testFrameTrade5;
		setTitle("InsertForm");
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
			
		label.setText("κ²μκΈ? ??±");
		label.setBackground(Color.GRAY);
		p1.setLayout(new GridLayout(0, 1));

		JPanel p5 = new JPanel();
		p5.add(new JLabel("?±λ‘μ κ³μ  ID  :")); 
		tf1 = new JTextField("admin",15); // μΆν? λ³ΈμΈ λ‘κ·Έ?Έ IDκ°? ???Όλ‘? ?€?΄κ°??λ‘? ?? 
		tf1.setEditable(false);
		p5.add(tf1);
		p1.add(p5);
		
//		tf1 = new JTextField("admin2",15);
//		tf0.setEditable(false);
//		tf1.setEditable(false);
//		JPanel p6 = new JPanel();
//		p6.add(tf1);
//		p1.add(p6);
		
		JPanel p6 = new JPanel();
		p6.add(new JLabel("?±λ‘? ? μ§?"));
		
        Date currentDate = Date.valueOf(LocalDate.now());

        // SimpleDateFormat? ?¬?©??¬ ??? ??? λ¬Έμ?΄λ‘? λ³??
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(currentDate);
		tf2 = new JTextField(dateString,15);
		tf2.setEditable(false);
		p6.add(tf2);
		p1.add(p6);
		
		
		
		JPanel p7 = new JPanel();
		p7.add(new JLabel("λ³΄μ  ?΅?  :"));
		String[] currencies = {"USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"}; 
		currencyComboBox = new JComboBox<>(currencies);
		p7.add(currencyComboBox);
		p1.add(p7);
				
		
	    JPanel p8 = new JPanel();
	    JLabel AmtLabel = new JLabel("??¬ κ³μ’ ??¬?: ");
	    p8.add(AmtLabel);
	    String AccMo = (String) currencyComboBox.getSelectedItem();
	    JTextField AmtTextField = new JTextField(String.valueOf(getAccAmt(tf1.getText(), AccMo)),5);
	    AmtTextField.setEditable(false); // ?¬?©?κ°? ?Έμ§ν  ? ??λ‘? ?€? 
	    p8.add(AmtTextField);
	    p1.add(p8);
	    
	    currencyComboBox.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            updateRemainingAmount();
	        }

	        private void updateRemainingAmount() {
	            String currency = (String) currencyComboBox.getSelectedItem();
	            double remainingAmount = getAccAmt(tf1.getText(), currency); // ??¬ ?? μ€μΈ ?Έ?€?΄?€? λ©μ? ?ΈμΆ?
	            AmtTextField.setText(Double.toString(remainingAmount));
	        }
	    });
	    
		
	    
		
		JPanel p9 = new JPanel();
		p9.add(new JLabel("?±λ‘? ?? :"));
		tf3 = new JTextField(10);
		p9.add(tf3);
		p1.add(p9);
		
	    JPanel p10 = new JPanel();
	    JLabel totalLabel = new JLabel("?©: ");
	    p10.add(totalLabel);
	    JTextField totalTextField = new JTextField(20);
	    totalTextField.setEditable(false); // ?¬?©?κ°? ?Έμ§ν  ? ??λ‘? ?€? 
	    p10.add(totalTextField);
	    p1.add(p10);

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
	            String exchangeAmountStr = tf3.getText().trim(); // κ³΅λ°±? ? κ±°ν ? λ¬Έμ?΄? κ°?? Έ?΄
	            if (exchangeAmountStr.isEmpty()) {
	                return; // λΉ? λ¬Έμ?΄?΄λ©? μ²λ¦¬?μ§? ??
	            }
	            
	            double exchangeAmount = 0.0;
//	            try {
//	                exchangeAmount = Double.parseDouble(tf3.getText());
//	            } catch (NumberFormatException ex) {
//	            	JOptionPane.showMessageDialog(null,"?¬λ°λ₯Έ ?«?λ₯? ?? ₯??¬ μ£ΌμΈ?.");
//	            }
//	            
	            try {
	                exchangeAmount = Double.parseDouble(exchangeAmountStr);
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null,"?¬λ°λ₯Έ ?«?λ₯? ?? ₯??¬ μ£ΌμΈ?.");
	                return; // ?«?λ‘? λ³???  ? ?? κ²½μ° μ²λ¦¬?μ§? ??
	            }

	            double exchangeRate = getExchangeRate(exchangeCurrency);
	            double totalAmount = exchangeAmount * exchangeRate;
	            DecimalFormat df = new DecimalFormat("#,###");
	            String TAmount = df.format(totalAmount);
	            totalTextField.setText("?½ " + TAmount + " ?");
	            
	            double entAmount = Double.parseDouble(tf3.getText());
	            double AmtText = Double.parseDouble(AmtTextField.getText());
	            if (entAmount > AmtText) {
	                JOptionPane.showMessageDialog(null, "λ³΄μ  ???? ??¬ ??λ³΄λ€ ?΄ ? ??΅??€.");
	                SwingUtilities.invokeLater(() -> tf3.setText(""));  // ?? ₯? κ°μ μ§??°κ±°λ ?€λ₯? μ²λ¦¬λ₯? ???  ? ??΅??€.
	            
	            }
	        }
	        
	    });
			
	    JPanel p11 = new JPanel();
	    p11.add(new JLabel("κ΅ν ?΅? :"));
	    String[] exchangeCurrencies = {"USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"};; // κ΅ν ?? λͺ©λ‘
	    exchangeComboBox = new JComboBox<>(exchangeCurrencies);
	    p11.add(exchangeComboBox);
	    p1.add(p11);
		
		JPanel p12 = new JPanel();
		p12.add(new JLabel("κ΅ν ?? :"));
		tf5 = new JTextField(10);
		p12.add(tf5);
		p1.add(p12);			

	    JPanel p13 = new JPanel();
	    JLabel totalLabel1 = new JLabel("?©: ");
	    p13.add(totalLabel1);
	    JTextField totalTextField1 = new JTextField(20);
	    totalTextField1.setEditable(false); // ?¬?©?κ°? ?Έμ§ν  ? ??λ‘? ?€? 
	    p13.add(totalTextField1);
	    p1.add(p13);

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
	            	JOptionPane.showMessageDialog(null,"?¬λ°λ₯Έ ?«?λ₯? ?? ₯??¬ μ£ΌμΈ?.");
	            }

	            double exchangeRate = getExchangeRate(exchangeCurrency);
	            double totalAmount = exchangeAmount * exchangeRate;
	            DecimalFormat df = new DecimalFormat("#,###");
	            String TAmount = df.format(totalAmount);
	            totalTextField1.setText("?½ " + TAmount + " ?");
	        }
	    });
		
		
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    
	    insBtn = new JButton("???₯");
	    insBtn.addActionListener(this);
	    buttonPanel.add(insBtn);
	    
	    EscBtn = new JButton("μ·¨μ");
	    EscBtn.addActionListener(this);
	    buttonPanel.add(EscBtn);
	    
	    p1.add(buttonPanel);


	    add(p1, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		if(obj==insBtn/*?±λ‘λ²?Ό*/) {
			
			TradeBean bean = new TradeBean();
			String Currency = (String) currencyComboBox.getSelectedItem();
		    String exchange = (String) exchangeComboBox.getSelectedItem();
		    if(Currency.equals(exchange)) {
		    	JOptionPane.showMessageDialog(null, "λ³΄μ  ?Έ??? κ΅ν ?Έ?? κ°μ ? ??΅??€. ?? ??¬ μ£Όμ­??€.");   	
		    }else {					
			bean.setTraderAcc1(tf1.getText());
			bean.setSellCur((String) currencyComboBox.getSelectedItem());
			bean.setSellAmt((int) Double.parseDouble(tf3.getText()));
			bean.setBuyCur((String) exchangeComboBox.getSelectedItem());
			bean.setBuyAmt((int) Double.parseDouble(tf5.getText()));
			bean.setDay(java.sql.Date.valueOf(LocalDate.now()));
		    }
            
			if(mgr.insert(bean)) {
				check = new DialogBox(null, "?±λ‘μ΄ ?λ£λ??΅??€.", "??Έ");
                awt.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "complete"));
				dispose();
//	            trade2.removeAll();
//				viewList(trade2);
//	            trade2.revalidate();
//	            trade2.repaint();
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