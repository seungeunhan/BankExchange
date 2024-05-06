package test;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

class ExchangePanel extends JPanel {
	
	private JTable table1;
	private JTable table2;
	private JTable table3;
	
    public ExchangePanel() {
    	setBackground(new Color(255, 255, 255)); //패널 색 지정
    	setLayout(new BorderLayout()); //레이아웃 설정
    	
    	//table1 데이터 및 열 이름
        String[] header1 = { "계좌번호", "아이디", "USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW" };
		String[][] contents = {};
		
		//table2 데이터 및 열 이름
		String[] header2 = { "환전번호", "날짜", "보유통화", "교환통화", "금액" };
		Object[][] data = {};
		
		//table3 데이터 및 열 이름
		String[] header3 = { "환전번호", "날짜", "보유통화", "교환통화", "금액" };
		Object[][] data2 = {};
		
		//table1 생성 및 설정
		table1 = new JTable(contents, header1);
		table1.setRowHeight(30);
		table1.setBounds(30, 80, 770, 100);
		
		//table2 생성 및 설정
		table2 = new JTable(data, header2);
		table2.setRowHeight(30);
		table2.setBounds(10, 450, 800, 150);
		
		//table3 생성 및 설정
		table3 = new JTable(data2, header3);
		
		//테이블을 패널의 중앙에 추가
		add(table1, BorderLayout.SOUTH);
		add(table2, BorderLayout.CENTER);
		add(table3, BorderLayout.CENTER);
    } 
    
    // 패널에 그리기
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 그려질 내용이 있을 경우 이곳에 그리기 코드를 추가
    }
    
 // 표를 업데이트하는 메소드 추가
    public void updateTable1(String[][] contents, String[] header1) {
        DefaultTableModel model1 = new DefaultTableModel(contents, header1);
        table1.setModel(model1);
    }
    public void updateTable2(String[][] data, String[] header2) {
        DefaultTableModel model2 = new DefaultTableModel(data, header2);
        table2.setModel(model2);
    }
    public void updateTable3(String[][] data2, String[] header3) {
        DefaultTableModel model3 = new DefaultTableModel(data2, header3);
        table3.setModel(model3);
    }
}

class TapPanel extends JPanel {

    public TapPanel() {
    	setBackground(new Color(62,192,196)); //패널 색 지정
    } 
    
    // 패널에 그리기
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 그려질 내용이 있을 경우 이곳에 그리기 코드를 추가
    }
}

public class HomeEx1 extends JPanel implements ActionListener {
	
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static Connection connection = null;
    private String userId;
    
    
    JPanel exchange_Panel = new JPanel(null);
	Label mymoneyl, exlistl, heldl, tradel, selll, exl, totall; // 내가 보유한 자산, 환전 내역, 보유 통화, 교환 통화, 기준 매매율, 환전 금액, 총 환전액
	TextField sellTf, exTf, totalTf, searchTf; // 기준 매매율, 환전 금액, 총 환전액
	Font font1 = new Font("Aharoni 굵게", Font.BOLD, 25);
	
	Color customColor = new Color(169, 219, 208);
	Color customColor2 = new Color(62,192,196);
	Color customColor3 = new Color(255, 255, 255);
	
	JButton moreBtn = new JButton("더보기"); //더보기 버튼
	
	JButton doexBtn = new JButton("환전하기"); //환전하기 버튼
	
	Choice ch1;
	String heldmoney[] = { "미국 달러", "일본 엔화", "태국 바트", "호주 달러", "캐나다 달러", "스위스 프랑", "중국 위안", "유럽 유로", "영국 파운드", "홍콩 달러", "뉴질랜드 달러", "싱가포르 달러", "한국 원화" };
	
	Choice ch2;
	String trademoney[] = { "미국 달러", "일본 엔화", "태국 바트", "호주 달러", "캐나다 달러", "스위스 프랑", "중국 위안", "유럽 유로", "영국 파운드", "홍콩 달러", "뉴질랜드 달러", "싱가포르 달러", "한국 원화" };
	
//	Choice ch1;
//	String heldmoney[] = { "USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW" };
//	
//	Choice ch2;
//	String trademoney[] = { "USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW" };
	
	public HomeEx1(String userId) {
		
		this.userId = userId;
//		getContentPane().setBackground(new Color(169, 219, 208)); //창 배경색 지정
		setSize(800, 500);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		ch1 = new Choice();
		ch1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		ch1.setBounds(190, 180, 160, 80);
		ch2 = new Choice();
		ch2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		ch2.setBounds(510, 180, 160, 80);
		
		mymoneyl = new Label("내가 보유한 자산");	
		mymoneyl.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		mymoneyl.setBounds(30, 40, 300, 40);
		
		exlistl = new Label("환전 내역");
		exlistl.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		exlistl.setBounds(20, 400, 120, 50);
		
		heldl = new Label("보유 통화");
		heldl.setFont(new Font("맑은 고딕", Font.PLAIN,20));
		heldl.setBounds(80, 180, 90, 40);
		
		tradel = new Label("교환 통화");
		tradel.setFont(new Font("맑은 고딕", Font.PLAIN,20));
		tradel.setBounds(400, 180, 90, 40);
		
		selll = new Label("기준 매매율");
		selll.setFont(new Font("맑은 고딕", Font.PLAIN,20));
		selll.setBounds(80, 330, 90, 40);
		sellTf = new TextField("");
		sellTf.setEditable(false); // 기준 매매율은 사용자가 직접 입력하지 않음
		sellTf.setBounds(190, 330, 160, 40);
		
		exl = new Label("환전 금액");
		exl.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		exl.setBounds(80, 255, 90, 35);
		exTf = new TextField("");
		exTf.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		exTf.setBounds(190, 255, 480, 35);
		
		totall = new Label("총 환전액");
		totall.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		totall.setBounds(400, 330, 90, 40);
		totalTf = new TextField("");
		totalTf.setEditable(false); // 총 환전액은 사용자가 직접 입력하지 않음
		totalTf.setBounds(510, 330, 160, 40);

		searchTf = new TextField("");
		searchTf.setBounds(580, 135, 120, 25);
		
		moreBtn.setBackground(customColor2);
		moreBtn.setBounds(680, 390,120, 50);
		moreBtn.setBorder(BorderFactory.createEmptyBorder());
		moreBtn.addActionListener(this);
		
		doexBtn.setBackground(customColor2);
		doexBtn.setBounds(680, 330, 100, 35);
		
		for (int i = 0; i < heldmoney.length; i++) {
			ch1.add(heldmoney[i]);
		}
		
		for (int i = 0; i < trademoney.length; i++) {
			ch2.add(trademoney[i]);
		}
		
		
		
		
		//환전하기 버튼 액션리스너
		doexBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
		            // 사용자가 입력한 환전 금액과 선택한 보유 통화, 교환 통화 가져오기
		            double exchangeAmount = Double.parseDouble(exTf.getText());
		            String heldCurrency = ch1.getSelectedItem();
		            System.out.println("heldCurrency 값: " + heldCurrency);
		            String tradeCurrency = ch2.getSelectedItem();
		            String heldCurrencyCode = "";
		            String heldCurrencyQuery = "SELECT cur_unit FROM currency_info WHERE cur_nm = ?";
		            
		            try (PreparedStatement heldStatement = connection.prepareStatement(heldCurrencyQuery)) {
				        heldStatement.setString(1, heldCurrency);
				        try (ResultSet heldResult = heldStatement.executeQuery()) {
				        
				        if (heldResult.next()) {
				            heldCurrencyCode = heldResult.getString("cur_unit");
				        }
				    }
			            // 환전 금액이 보유한 금액보다 적은지 확인
			            // 만약 환전 금액이 보유한 금액보다 많으면 사용자에게 알림을 표시
			            if (exchangeAmount > getHeldBalance(userId, heldCurrencyCode)) {
			                throw new IllegalArgumentException("보유한 금액보다 환전 금액이 많습니다.");
			            }
			            else//성공했을때 
			            {
			            // 보유 통화에서 환전 금액 차감(아이디,차감지폐,양)
			            subtractFromHeldBalance(userId, heldCurrency, exchangeAmount);
			            
			            // 교환 통화에 해당하는 잔고 증가
			            addToTradeBalance(userId, tradeCurrency, exchangeAmount);
			            
			            // 데이터베이스 업데이트
			            updateAccountBalanceInDatabase(userId);
			            
			            // 성공 메시지 표시
			            JOptionPane.showMessageDialog(HomeEx1.this, "환전이 완료되었습니다.", "환전 완료", JOptionPane.INFORMATION_MESSAGE);	
		            }
		            
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(HomeEx1.this, "올바른 숫자를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
		        } catch (IllegalArgumentException ex) {
		            JOptionPane.showMessageDialog(HomeEx1.this, ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
		        } catch (SQLException e1) {
					//통화코드 받아오는데 실패했을때
					e1.printStackTrace();
				}
		            System.out.println("heldCurrencyCode 값: " + heldCurrencyCode); // 통화 코드 출력
			}
		
		// 데이터베이스에서 가져온 값을 double로 변환하여 반환하는 메소드
		private double getDoubleFromResultSet(ResultSet resultSet, String columnName) throws SQLException {
		    String valueAsString = resultSet.getString(columnName);
		    return Double.parseDouble(valueAsString);
		}
		
		// 보유한 금액을 가져오는 메소드
		private double getHeldBalance(String userId, String heldCurrency) {
		    double balance = 0.0; // 잔고 초기화
		    try {
		        if (connection != null) {
		            String query = "SELECT " + heldCurrency + " FROM account WHERE id = ?";
		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            preparedStatement.setString(1, userId);
		            ResultSet resultSet = preparedStatement.executeQuery();
		            if (resultSet.next()) {
		                // ResultSet에서 값을 가져와서 double로 변환
		                balance = getDoubleFromResultSet(resultSet, heldCurrency);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return balance; // 잔고 반환
		}

		private void subtractFromHeldBalance(String userId, String heldCurrency, double amount) {
		    try {
		        if (connection != null) {
		            // 보유 통화에서 환전 금액을 차감하는 쿼리 작성
		        	String query = "UPDATE account SET " + heldCurrency + " = " + heldCurrency + " - ? WHERE id = ?";

		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            preparedStatement.setDouble(1, amount);
		            preparedStatement.setString(2, userId);
		            
		            // 쿼리 실행
		            preparedStatement.executeUpdate();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		// 교환 통화에 해당하는 잔고 증가하는 메소드
		private void addToTradeBalance(String userId, String tradeCurrency, double amount) {
		    try {
		        if (connection != null) {
		            // 교환 통화에 해당하는 잔고를 증가시키는 쿼리 작성
		        	String query = "UPDATE account SET " + tradeCurrency + " = " + tradeCurrency + " + ? WHERE id = ?";

		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            preparedStatement.setDouble(1, amount);
		            preparedStatement.setString(2, userId);
		            
		            // 쿼리 실행
		            preparedStatement.executeUpdate();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		// 데이터베이스 업데이트 메소드
		private void updateAccountBalanceInDatabase(String userId) {
		    try {
		        if (connection != null) {
		            // 각각의 보유 통화 및 교환 통화에 대해 업데이트하는 쿼리 작성
		            String query = "UPDATE account SET USD = ?, JPY = ?, THB = ?, AUD = ?, CAD = ?, CHF = ?, " +
		                           "CNY = ?, EUR = ?, GBP = ?, HKD = ?, NZD = ?, SGD = ?, KRW = ? WHERE id = ?";
		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            
		            // 각각의 통화에 대해 잔고를 가져와서 설정
		            preparedStatement.setDouble(1, getHeldBalance(userId, "USD"));
		            preparedStatement.setDouble(2, getHeldBalance(userId, "JPY"));
		            preparedStatement.setDouble(3, getHeldBalance(userId, "THB"));
		            preparedStatement.setDouble(4, getHeldBalance(userId, "AUD"));
		            preparedStatement.setDouble(5, getHeldBalance(userId, "CAD"));
		            preparedStatement.setDouble(6, getHeldBalance(userId, "CHF"));
		            preparedStatement.setDouble(7, getHeldBalance(userId, "CNY"));
		            preparedStatement.setDouble(8, getHeldBalance(userId, "EUR"));
		            preparedStatement.setDouble(9, getHeldBalance(userId, "GBP"));
		            preparedStatement.setDouble(10, getHeldBalance(userId, "HKD"));
		            preparedStatement.setDouble(11, getHeldBalance(userId, "NZD"));
		            preparedStatement.setDouble(12, getHeldBalance(userId, "SGD"));
		            preparedStatement.setDouble(13, getHeldBalance(userId, "KRW"));
		            
		            // 사용자 ID 설정
		            preparedStatement.setString(14, userId);
		            
		            // 쿼리 실행
		            int rowsAffected = preparedStatement.executeUpdate();
		            
		            if (rowsAffected > 0) {
		                System.out.println("사용자의 계좌 잔고가 성공적으로 업데이트되었습니다.");
		            } else {
		                System.out.println("사용자의 계좌 잔고를 업데이트하는 데 실패했습니다.");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	});
		
		
		
		exchange_Panel.setBounds(0,0,800,600);
		exchange_Panel.setBackground(Color.white);
		
		ExchangePanel exchangePanel = new ExchangePanel();
        exchangePanel.setLayout(null);
        exchangePanel.setBounds(0, 0, 826, 620);
        exchange_Panel.add(exchangePanel);
        
        TapPanel tapPanel = new TapPanel();
        tapPanel.setLayout(null);
        tapPanel.setBounds(0, 0, 100, 450);
//        exchange_Panel.add(tapPanel);
        
        exchangePanel.add(ch1);
        exchangePanel.add(ch2);
        
        exchangePanel.add(mymoneyl);
        exchangePanel.add(exlistl);
        exchangePanel.add(heldl);
        exchangePanel.add(tradel);
        exchangePanel.add(selll);
        exchangePanel.add(exl);
        exchangePanel.add(totall);
        
        exchangePanel.add(sellTf);
        exchangePanel.add(exTf);
        exchangePanel.add(totalTf);
        
        exchangePanel.add(moreBtn);
        exchangePanel.add(doexBtn);
        
        add(exchange_Panel);
        exchange_Panel.setVisible(false);
        // 데이터베이스 연결
        connect();
        
        // 기준 매매율 업데이트
        updateStandardRate();
        
        // 데이터베이스에서 환전 내역 가져와서 테이블에 표시
        List<String[]> exchangeData = fetchExchangeDataFromDatabase1(userId);
        if (exchangeData != null) {
            String[][] exchangeArray = new String[exchangeData.size()][];
            for (int i = 0; i < exchangeData.size(); i++) {
                exchangeArray[i] = exchangeData.get(i);
            }
            String[] header1 = { "계좌번호", "아이디", "USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW" };
            exchangePanel.updateTable1(exchangeArray, header1);
        }
        
        List<String[]> accountData = fetchExchangeDataFromDatabase2(userId);
        if (accountData != null) {
            String[][] accountArray = new String[accountData.size()][];
            for (int i = 0; i < accountData.size(); i++) {
                accountArray[i] = accountData.get(i);
            }
            String[] header2 = { "환전번호", "날짜", "보유통화", "교환통화", "금액" };
            exchangePanel.updateTable2(accountArray, header2);
        }
        
        List<String[]> accountData2 = fetchExchangeDataFromDatabase3(userId);
        if (accountData2 != null) {
            String[][] accountArray2 = new String[accountData2.size()][];
            for (int i = 0; i < accountData2.size(); i++) {
                accountArray2[i] = accountData2.get(i);
            }
            String[] header3 = { "환전번호", "날짜", "보유통화", "교환통화", "금액" };
            exchangePanel.updateTable3(accountArray2, header3);
        }
        exchange_Panel.setVisible(true);
		setVisible(true);
		validate();
	}
	
	public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	


	// 보유 통화와 교환 통화의 환율 가져오기 메소드
	private double getExchangeRate(String heldCurrency, String targetCurrency) {
	    double exchangeRate = 0.0; // 기본값 설정

		// 사용자가 선택한 보유 통화에 대한 통화 코드 가져오기
	    String heldCurrencyQuery = "SELECT cur_unit FROM currency_info WHERE cur_nm = ?;";
	    // 사용자가 선택한 교환 통화에 대한 통화 코드 가져오기
	    String targetCurrencyQuery = "SELECT cur_unit FROM currency_info WHERE cur_nm = ?;";
	    // 보유 통화와 교환 통화의 환율 정보 가져오기
	    String exchangeRateQuery = "SELECT * FROM exchange_rate WHERE DAY = (SELECT MAX(DAY) FROM exchange_rate)";
	    
	    try {
	        // 보유 통화에 대한 통화 코드 가져오기
	        PreparedStatement heldStatement = connection.prepareStatement(heldCurrencyQuery);
	        heldStatement.setString(1, heldCurrency);
	        ResultSet heldResult = heldStatement.executeQuery();
	        String heldCurrencyCode = "";
	        if (heldResult.next()) {
	            heldCurrencyCode = heldResult.getString("cur_unit");
	        }

	        // 교환 통화에 대한 통화 코드 가져오기
	        PreparedStatement targetStatement = connection.prepareStatement(targetCurrencyQuery);
	        targetStatement.setString(1, targetCurrency);
	        ResultSet targetResult = targetStatement.executeQuery();
	        String targetCurrencyCode = "";
	        if (targetResult.next()) {
	            targetCurrencyCode = targetResult.getString("cur_unit");
	        }

	        // 보유 통화와 교환 통화의 환율 정보 가져오기
	        PreparedStatement exchangeRateStatement = connection.prepareStatement(exchangeRateQuery);
	        ResultSet exchangeRateResult = exchangeRateStatement.executeQuery();
	        double heldRate = 0.0;
	        double targetRate = 0.0;
	        if (exchangeRateResult.next()) {
	            heldRate = exchangeRateResult.getDouble(heldCurrencyCode);
	            targetRate = exchangeRateResult.getDouble(targetCurrencyCode);
	         // 기준 매매율 계산
		        exchangeRate = heldRate / targetRate;
	        }
	        
	     	// 콘솔에 환율 정보 출력
	        System.out.println("보유 통화 환율: " + heldRate);
	        System.out.println("교환 통화 환율: " + targetRate);
	        System.out.println("기준 매매율: " + exchangeRate);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // 예외 처리
	    }

	    return exchangeRate; // 계산된 기준 매매율 반환
	}
	
	// 기준 매매율 계산 메소드 추가
	private double calculateStandardRate(String heldCurrency, String tradeCurrency, double amount) {
		// 보유 통화와 교환 통화의 환율 가져오기
		double heldExchangeRate = getExchangeRate(heldCurrency, tradeCurrency);
	    
	    // 기준 매매율 계산
	    double standardRate = heldExchangeRate;
	    double totalRate = heldExchangeRate * amount;

		// 기준 매매율을 기준 매매율 텍스트 필드에 표시합니다.
	    sellTf.setText(String.valueOf(standardRate));
	    totalTf.setText(String.valueOf(totalRate));
	 
	    return standardRate;
	}
	
	// 텍스트 필드에서 숫자 입력 감지하여 기준 매매율 업데이트하는 메소드 추가
	private void updateStandardRate() {
	    try {
	        // 환전 금액 가져오기
	        double amount = Double.parseDouble(exTf.getText());
	        
	        // 보유 통화와 교환 통화 선택 값 가져오기
	        String heldCurrency = ch1.getSelectedItem();
	        String tradeCurrency = ch2.getSelectedItem();
	        
	        // 기준 매매율 계산
	        double standardRate = calculateStandardRate(heldCurrency, tradeCurrency, amount);
	        double totalRate = calculateStandardRate(heldCurrency, tradeCurrency, amount) * amount;
	        
	        // 소수점 두 자리까지 표시
	        String standardRateText = String.format("%.2f", standardRate);
	        String totalRateText = String.format("%.2f", totalRate);
	        
	        // 기준 매매율 텍스트 필드에 표시
	        sellTf.setText(standardRateText);
	        totalTf.setText(totalRateText);
	    } catch (NumberFormatException ex) {
	        // 숫자로 변환할 수 없는 경우 예외 처리
	        sellTf.setText(""); // 텍스트 필드를 비움
	    }
	    

	// 환전 금액 텍스트 필드에 키 이벤트 리스너 추가
	exTf.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(KeyEvent e) {
	        updateStandardRate();
	    }
	});
	
	// 보유 통화와 교환 통화 초이스박스에 아이템 리스너 추가
	ch1.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent e) {
	        updateStandardRate();
	    }
	});

	ch2.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent e) {
	        updateStandardRate();
	    }
	});
}
	
	
	
	public List<String[]> fetchExchangeDataFromDatabase1(String userId) {
        List<String[]> exchangeData = new ArrayList<>();
        try {
            if (connection != null) {
                String query = "SELECT * FROM account WHERE id = ?";
                String[] header1 = { "계좌번호", "아이디", "USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW" };
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userId);
                ResultSet resultSet = preparedStatement.executeQuery();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userId);
                while (resultSet.next()) {
                    String[] row = {
                        resultSet.getString("account_num"),
                        resultSet.getString("id"),
                        resultSet.getString("USD"),
                        resultSet.getString("JPY"),
                        resultSet.getString("THB"),
                        resultSet.getString("AUD"),
                        resultSet.getString("CAD"),
                        resultSet.getString("CHF"),
                        resultSet.getString("CNY"),
                        resultSet.getString("EUR"),
                        resultSet.getString("GBP"),
                        resultSet.getString("HKD"),
                        resultSet.getString("NZD"),
                        resultSet.getString("SGD"),
                        resultSet.getString("KRW")
                    };
                    exchangeData.add(header1);
                    exchangeData.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exchangeData;
    }
	//최신 3건의 환전 내역
	public List<String[]> fetchExchangeDataFromDatabase2(String userId) {
	    List<String[]> accountData = new ArrayList<>();
	    try {
	        if (connection != null) {
	            // 사용자의 계좌번호 가져오기
	            String accountQuery = "SELECT account_num FROM account WHERE id = ?";
	            String[] header2 = { "환전번호", "날짜", "보유통화", "교환통화", "금액" };
	            accountData.add(header2);
	            PreparedStatement accountStatement = connection.prepareStatement(accountQuery);
	            accountStatement.setString(1, userId);
	            ResultSet accountResult = accountStatement.executeQuery();
	            if (accountResult.next()) {
	                String accountNum = accountResult.getString("account_num");
	                // 계좌번호로 환전 내역 조회
	                String exchangeQuery = "SELECT * FROM ex_history WHERE ex_account_num = ?";
	                PreparedStatement exchangeStatement = connection.prepareStatement(exchangeQuery);
	                exchangeStatement.setString(1, accountNum);
	                ResultSet exchangeResult = exchangeStatement.executeQuery();
	                // 환전 내역 데이터를 리스트에 추가
	                while (exchangeResult.next()) {
	                    String[] row = {
	                            exchangeResult.getString("ex_account_num"),
	                            exchangeResult.getString("day"),
	                            exchangeResult.getString("sell_cur"),
	                            exchangeResult.getString("buy_cur"),
	                            exchangeResult.getString("buy_amt")
	                    };
	                    accountData.add(row);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return accountData;
	}
	//전체 환전 내역
	public List<String[]> fetchExchangeDataFromDatabase3(String userId) {
	    List<String[]> exchangeData = new ArrayList<>();
	    try {
	        if (connection != null) {
	            String query = "SELECT * FROM ex_history WHERE ex_account_num IN (SELECT account_num FROM account WHERE id = ?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, userId);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {
	                String[] row = {
	                        resultSet.getString("ex_account_num"),
	                        resultSet.getString("day"),
	                        resultSet.getString("sell_cur"),
	                        resultSet.getString("buy_cur"),
	                        resultSet.getString("buy_amt")
	                };
	                exchangeData.add(row);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return exchangeData;
	}
	
	// 검색 메소드 예시
	public List<String[]> searchExchangeData(List<String[]> data, String searchText) {
	    List<String[]> searchedData = new ArrayList<>();
	    for (String[] row : data) {
	        for (String value : row) {
	            if (value != null && value.contains(searchText)) {
	                searchedData.add(row);
	                break;
	            }
	        }
	    }
	    return searchedData;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 데이터베이스에서 전체 환전 내역 가져오기
	    List<String[]> exchangeData = fetchExchangeDataFromDatabase3(userId);
		if (exchangeData != null) {
		//표 모델 생성
		DefaultTableModel model3 = new DefaultTableModel();
		model3.setColumnIdentifiers(new String[] { "환전번호", "날짜", "보유통화", "교환통화", "금액" });
		for (String[] row : exchangeData) {
            model3.addRow(row);
        }
		
		//표 생성
		JTable table3 = new JTable(model3);
		
		//표를 담을 패널 생성
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.add(new JScrollPane(table3), BorderLayout.CENTER);
		
		//모달 창 생성
		JDialog dialog = new JDialog();
		dialog.setTitle("환전 내역");
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLayout(new BorderLayout());
		dialog.add(tablePanel, BorderLayout.CENTER);
		dialog.setSize(800, 500); //모달 창 크기 설정
		dialog.setLocationRelativeTo(null); //화면 중앙에 위치
		
		//표를 모달 창에 직접 추가하고 원하는 위치로 이동
		JScrollPane scrollPane = new JScrollPane(table3);
		dialog.setLayout(null);
		scrollPane.setBounds(0, 150, 750, 100); //모달 창 위 표 위치 및 크기 조정
		dialog.add(scrollPane);
		
		//닫기 버튼을 포함한 패널 생성
		JButton closeButton = new JButton("닫기");
		closeButton.setBounds(650, 400 , 100, 30);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose(); //모달 창 닫기
			}
		});
		
		// 텍스트 필드 생성
        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(450, 50, 180, 30);
		
		// 검색 버튼 동작
		JButton searchBtn = new JButton("검색");
		searchBtn.setBounds(650, 50 , 80, 30);
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchText = searchTextField.getText(); // 검색어 가져오기
                List<String[]> searchedExchangeData = searchExchangeData(exchangeData, searchText); // 검색 수행
                if (searchedExchangeData != null) {
                    // 검색된 데이터가 있을 경우 표 업데이트
                    DefaultTableModel searchModel = new DefaultTableModel();
                    searchModel.setColumnIdentifiers(new String[] { "환전번호", "날짜", "보유통화", "교환통화", "금액" });
                    for (String[] row : searchedExchangeData) {
                        searchModel.addRow(row);
                    }
                    table3.setModel(searchModel);
                } else {
                    // 검색된 데이터가 없을 경우 메시지 출력
                    JOptionPane.showMessageDialog(dialog, "검색 결과가 없습니다.", "검색 결과", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
			dialog.add(tablePanel);
			dialog.add(closeButton);
			dialog.add(searchBtn);
			dialog.add(searchTextField);
			dialog.setVisible(true);
		} else {
			// 데이터베이스에서 환전 내역을 가져오지 못한 경우에 대한 처리
			System.out.println("데이터베이스 연결 실패");
		}
	}
	
	public JPanel getMainPanel() {
		return exchange_Panel;
	}

	public static void main(String[] args) {
        String userId = "";
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomeEx1(userId);
            }
        });
    }
}