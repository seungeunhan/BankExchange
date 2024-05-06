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
    String selectedCurrency; // 선택한 보유 통화 저장 변수
    String selectedExchange; // 선택한 교환 통화 저장 변수
	String id;
	
	
	public TestPanel() {

		setLayout(null); // 레이아웃 관리자를 사용하지 않음
        
		mgr = new TradeMgr();
		
		label = new JLabel("");
		
		tradePanel = new JPanel(new BorderLayout());
        tradePanel.setBackground(new Color(169, 219, 208));
        tradePanel.setBounds(0, 0, 830, 620); // 위치 (50, 50), 크기 (300, 150)
		
        
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
		
		
		JLabel label1 = new JLabel("보유 통화  :");
		label1.setBackground(Color.WHITE);
		label1.setForeground(Color.DARK_GRAY);
		label1.setBounds(0, 20, 100, 40);
		trade1.add(label1);
		
		String[] currencies = {"","USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"};
		currencyComboBox = new JComboBox<>(currencies);
		currencyComboBox.setBackground(Color.WHITE);
		currencyComboBox.setBounds(100, 20, 150, 40);
		trade1.add(currencyComboBox);

		
		JLabel label2 = new JLabel("교환 통화 :");
		label2.setBackground(Color.WHITE);
		label2.setForeground(Color.DARK_GRAY);
		label2.setBounds(300, 20 ,100 ,40);
		trade1.add(label2);
		
		String[] exchangeCurrencies = {" ","USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"};
		exchangeComboBox = new JComboBox<>(exchangeCurrencies);
		exchangeComboBox.setBackground(Color.WHITE);
		exchangeComboBox.setBounds(400,20, 150, 40);
		trade1.add(exchangeComboBox);
	    
		
	    searchBtn = new JButton("검색");
	    newBtn = new JButton("새로고침");
	    
		searchBtn.setFont(new Font("맑은 고딕", Font.BOLD , 16));
		newBtn.setFont(new Font("맑은 고딕", Font.BOLD , 16));

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
		
		
		
		
		
		
		JLabel l1 = new JLabel("       No        |                  작성자ID                 |        보유 통화         |       교환 통화        |       교환 통화 금액         |            작성 날짜");
		l1.setBounds(0 , 160 , 800, 40);
		l1.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 12));
		l1.setForeground(Color.WHITE);
		trade1.add(l1);
		
		b1 = new JButton("게시글 작성");
		b2 = new JButton("수정");
		b3 = new JButton("삭제");
		b4 = new JButton("상세보기");
		
		b1.setFont(new Font("맑은 고딕", Font.BOLD , 16));
		b2.setFont(new Font("맑은 고딕", Font.BOLD , 16));
		b3.setFont(new Font("맑은 고딕", Font.BOLD , 16));
		b4.setFont(new Font("맑은 고딕", Font.BOLD , 16));
		
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
		
        JScrollPane scrollPane = new JScrollPane(); // JScrollPane 생성
        viewList(scrollPane,null,null); // JScrollPane에 리스트 추가
        trade2.add(scrollPane, BorderLayout.CENTER); // JScrollPane을 trade2 패널의 가운데에 추가
		
		
		tradePanel.add(trade1,BorderLayout.NORTH);	
		tradePanel.add(trade2,BorderLayout.CENTER);
		
        add(tradePanel); // JPanel을 TestPanel에 추가
		
		
		setVisible(true);
		
	}
	
	
	public void viewList(JScrollPane scrollPane, String object, String object2) {

	    if (list != null) {
	        scrollPane.setViewportView(list);
	    }
	    if (scrollPane == null) {
	        scrollPane = new JScrollPane(); // 새로운 JScrollPane 객체를 생성
	        // 기타 초기화 작업 수행
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
	        // 리스트가 비어있을 때의 처리
	        list.add("리스트가 비어 있습니다.");

	    }else {
	    	for (int i = 0; i < vlist.size(); i++) {
	        TradeBean bean = vlist.get(i);
	        // 선택한 보유 통화와 교환 통화에 맞는 거래만 리스트에 추가
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
	            list.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE , 18));
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
	        
	        if (obj == b1/*게시글작성 버튼*/) {
	            
					System.out.println("버튼 눌림");
					If = new InsertFrame2(this);
					If.setVisible(true);

			
		}else if(obj==b2/*수정버튼*/) {

				int i = list.getSelectedIndex();
				TradeBean bean = vlist.get(i);
				System.out.println("버튼 눌림");
				System.out.println(bean.getTradeNum());
				Uf = new UpdateFrame2(this, Integer.toString(bean.getTradeNum()));
				Uf.setVisible(true);

			
		}else if(obj==b3/*삭제버튼*/) {
					
					int i = list.getSelectedIndex();
					TradeBean bean = vlist.get(i);
					if(mgr.delete(bean.getTradeNum())) {
			            trade2.removeAll();
			            JScrollPane scrollPane = new JScrollPane(); // JScrollPane 생성
			            viewList(scrollPane,null,null);
			            trade2.add(scrollPane, BorderLayout.CENTER);
			            trade2.revalidate();
			            trade2.repaint();
					}
					
		}else if(obj==b4/*상세보기 버튼*/) {
			
			
						int i = list.getSelectedIndex();
						TradeBean bean = vlist.get(i);
						System.out.println("버튼 눌림");
						System.out.println(bean.getTradeNum());
						Sf = new ShowFrame2(this, Integer.toString(bean.getTradeNum()),trade2);
						Sf.setVisible(true);

		}else if("complete".equals(command)) {
			
			            trade2.removeAll();
			            JScrollPane scrollPane = new JScrollPane(); // JScrollPane 생성
			            viewList(scrollPane,null,null);
			            trade2.add(scrollPane, BorderLayout.CENTER);
			            trade2.revalidate();
			            trade2.repaint();
		    
	    }else if(obj== searchBtn/*검색*/) {
					
	        System.out.println("버튼 눌림");

	        String currency = (String) currencyComboBox.getSelectedItem();
	        String exchange = (String) exchangeComboBox.getSelectedItem();

	        if (currency.equals(exchange)) {
	            JOptionPane.showMessageDialog(null, "보유 외화와 교환 외화는 같을 수 없습니다. 수정하여 주십시오.");
	        } else {
	            System.out.println("검색 시작");

	            // 선택한 보유 통화와 교환 통화 저장
	            selectedCurrency = (String) currencyComboBox.getSelectedItem();
	            selectedExchange = (String) exchangeComboBox.getSelectedItem();
	            
	        }
	            
	            // JScrollPane을 생성하고 viewList 메서드 호출
	            JScrollPane scrollPane = new JScrollPane();
	            viewList(scrollPane, selectedCurrency, selectedExchange);
	            
	            // trade2에 JScrollPane 추가
	            trade2.removeAll();
	            trade2.add(scrollPane, BorderLayout.CENTER);
	            trade2.revalidate();
	            trade2.repaint();

		    
	    }else if(obj == newBtn/*새로고침*/) {
	    	
	    				System.out.println("버튼 눌림");
			            trade2.removeAll();
			            JScrollPane scrollPane = new JScrollPane(); // JScrollPane 생성
			            viewList(scrollPane,null,null);
			            trade2.add(scrollPane, BorderLayout.CENTER);
			            trade2.revalidate();
			            trade2.repaint();
	    }
		
		validate();
	}//--actionPerformed
	
	

    public JPanel getTradePanel() {
        return tradePanel; // tradePanel을 반환하는 메서드 추가
    }
} 
