package myinfo;

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
        String[] header1 = { "계좌번호", "아이디", "미국 달러", "일본 엔화", "태국 바트", "호주 달러", "캐나다 달러", "스위스 프랑", "중국 위안", "유럽 유로", "영국 파운드", "홍콩 달러", "뉴질랜드 달러", "싱가포르 달러", "대한민국 원화" };
		String[][] contents = {};
		
		//table2 데이터 및 열 이름
		String[] header2 = { "환전번호", "날짜", "보유통화", "교환통화", "금액" };
		Object[][] data = {};
		
		//table3 데이터 및 열 이름
		String[] header3 = { "환전번호", "날짜", "보유통화", "교환통화", "금액" };
		Object[][] data2 = {};
		
		//table1 생성 및 설정
		table1 = new JTable(contents, header1);
		table1.setBounds(20, 55, 550, 50);
		
		//table2 생성 및 설정
		table2 = new JTable(data, header2);
		table2.setBounds(20, 335, 550, 70);
		
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

public class HomeEx1 extends JFrame implements ActionListener {
	
    private static final String URL = "jdbc:mysql://localhost:3306/hse";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static Connection connection = null;
    private String account_num;
    
	Label mymoneyl, exlistl, heldl, tradel, selll, exl, totall; // 내가 보유한 자산, 환전 내역, 보유 통화, 교환 통화, 기준 매매율, 환전 금액, 총 환전액
	TextField sellTf, exTf, totalTf, searchTf; // 기준 매매율, 환전 금액, 총 환전액
	Font font1 = new Font("Aharoni 굵게", Font.BOLD, 25);
	
	Color customColor = new Color(169, 219, 208);
	Color customColor2 = new Color(62,192,196);
	Color customColor3 = new Color(255, 255, 255);
	
	JButton rateBtn = new JButton("환율"); //환율 버튼
	JButton exchangeBtn = new JButton("환전"); //환전 버튼
	JButton walletBtn = new JButton("지갑"); //지갑 버튼
	JButton tradeBtn = new JButton("거래소"); //거래소 버튼
	JButton infoBtn = new JButton("내 정보"); //내정보 버튼
	JButton outBtn = new JButton("로그아웃"); //로그아웃 버튼
	JButton moreBtn = new JButton("더보기 >"); //더보기 버튼
	
	JButton searchBtn = new JButton("검색"); //검색 버튼
	JButton doexBtn = new JButton("환전하기"); //환전하기 버튼
	
	Choice ch1;
	String heldmoney[] = { "미국 달러", "일본 엔화", "태국 바트", "호주 달러", "캐나다 달러", "스위스 프랑", "중국 위안", "유럽 유로", "영국 파운드", "홍콩 달러", "뉴질랜드 달러", "싱가포르 달러", "한국 원화" };
	
	Choice ch2;
	String trademoney[] = { "미국 달러", "일본 엔화", "태국 바트", "호주 달러", "캐나다 달러", "스위스 프랑", "중국 위안", "유럽 유로", "영국 파운드", "홍콩 달러", "뉴질랜드 달러", "싱가포르 달러", "한국 원화" };
	
	public HomeEx1(String userId) {
		
		getContentPane().setBackground(new Color(169, 219, 208)); //창 배경색 지정
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		ch1 = new Choice();
		ch1.setBounds(110, 135, 120, 50);
		ch2 = new Choice();
		ch2.setBounds(330, 135, 120, 50);
		
		mymoneyl = new Label("내가 보유한 자산");	
		mymoneyl.setBounds(20, 20, 100, 35);
		
		exlistl = new Label("환전 내역");
		exlistl.setBounds(20, 300, 80, 35);
		
		heldl = new Label("보유 통화");
		heldl.setBounds(40, 130, 60, 35);
		
		tradel = new Label("교환 통화");
		tradel.setBounds(260, 130, 60, 35);
		
		selll = new Label("기준 매매율");
		selll.setBounds(30, 240, 80, 35);
		sellTf = new TextField("");
		sellTf.setEditable(false); // 기준 매매율은 사용자가 직접 입력하지 않음
		sellTf.setBounds(110, 245, 120, 25);
		
		exl = new Label("환전 금액");
		exl.setBounds(40, 180, 60, 35);
		exTf = new TextField("");
		exTf.setBounds(110, 185, 340, 25);
		
		totall = new Label("총 환전액");
		totall.setBounds(250, 240, 60, 35);
		totalTf = new TextField("");
		totalTf.setEditable(false); // 총 환전액은 사용자가 직접 입력하지 않음
		totalTf.setBounds(320, 245, 130, 25);

		searchTf = new TextField("");
		searchTf.setBounds(580, 135, 120, 25);

		rateBtn.setBackground(customColor2);
		rateBtn.setBounds(0, 0, 80, 35);
		rateBtn.setBorder(BorderFactory.createEmptyBorder());
		
		exchangeBtn.setBackground(customColor2);
		exchangeBtn.setBounds(0, 50, 80, 35);
		exchangeBtn.setBorder(BorderFactory.createEmptyBorder());
		
		walletBtn.setBackground(customColor2);
		walletBtn.setBounds(0, 100, 80, 35);
		walletBtn.setBorder(BorderFactory.createEmptyBorder());
		
		tradeBtn.setBackground(customColor2);
		tradeBtn.setBounds(0, 150, 80, 35);
		tradeBtn.setBorder(BorderFactory.createEmptyBorder());
		
		infoBtn.setBackground(customColor2);
		infoBtn.setBounds(0, 200, 80, 35);
		infoBtn.setBorder(BorderFactory.createEmptyBorder());
		
		outBtn.setBackground(customColor2);
		outBtn.setBounds(0, 250, 80, 35);
		outBtn.setBorder(BorderFactory.createEmptyBorder());
		
		moreBtn.setBackground(customColor3);
		moreBtn.setBounds(400, 300, 80, 35);
		moreBtn.setBorder(BorderFactory.createEmptyBorder());
		moreBtn.addActionListener(this);
		
		searchBtn.setBackground(customColor3);
		searchBtn.setBounds(460, 135, 100, 75);
		
		doexBtn.setBackground(customColor2);
		doexBtn.setBounds(460, 240, 100, 35);
		
		for (int i = 0; i < heldmoney.length; i++) {
			ch1.add(heldmoney[i]);
		}
		
		for (int i = 0; i < trademoney.length; i++) {
			ch2.add(trademoney[i]);
		}
		
		rateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Object obj = e.getSource();
					if(obj==rateBtn)/*환율 버튼 클릭 시*/ {
						dispose();
						new LoginEx1();
					}else {
					}
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		exchangeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Object obj = e.getSource();
					if(obj==exchangeBtn)/*환전 버튼 클릭 시*/ {
						dispose();
						new LoginEx1();
					}else {
					}
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		walletBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Object obj = e.getSource();
					if(obj==walletBtn)/*지갑 버튼 클릭 시*/ {
						dispose();
						new LoginEx1();
					}else {
					}
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		tradeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Object obj = e.getSource();
					if(obj==tradeBtn)/*거래소 버튼 클릭 시*/ {
						dispose();
						new LoginEx1();
					}else {
					}
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		infoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Object obj = e.getSource();
					if(obj==infoBtn)/*내 정보 버튼 클릭 시*/ {
						dispose();
						new LoginEx1();
					}else {
					}
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		outBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Object obj = e.getSource();
					if(obj==outBtn)/*로그아웃 버튼 클릭 시*/ {
						dispose();
						new LoginEx1();
					}else {
					}
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
	
		ExchangePanel exchangePanel = new ExchangePanel();
        exchangePanel.setLayout(null);
        exchangePanel.setBounds(110, 10, 600, 430);
        add(exchangePanel);
        
        TapPanel tapPanel = new TapPanel();
        tapPanel.setLayout(null);
        tapPanel.setBounds(0, 0, 100, 450);
        add(tapPanel);
        
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
        exchangePanel.add(searchBtn);
        exchangePanel.add(doexBtn);

        tapPanel.add(rateBtn);
        tapPanel.add(exchangeBtn);
        tapPanel.add(walletBtn);
        tapPanel.add(tradeBtn);
        tapPanel.add(infoBtn);
        tapPanel.add(outBtn);
        
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
            String[] header1 = { "계좌번호", "아이디", "미국 달러", "일본 엔화", "태국 바트", "호주 달러", "캐나다 달러", "스위스 프랑", "중국 위안", "유럽 유로", "영국 파운드", "홍콩 달러", "뉴질랜드 달러", "싱가포르 달러", "한국 원화" };
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
        
        List<String[]> accountData2 = fetchExchangeDataFromDatabase3();
        if (accountData2 != null) {
            String[][] accountArray2 = new String[accountData2.size()][];
            for (int i = 0; i < accountData2.size(); i++) {
                accountArray2[i] = accountData2.get(i);
            }
            String[] header3 = { "환전번호", "날짜", "보유통화", "교환통화", "금액" };
            exchangePanel.updateTable3(accountArray2, header3);
        }
        
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
	


	// 보유 통화와 교환 통화의 환율 가져오기 메소드 수정
	private double getExchangeRate(String heldCurrency, String targetCurrency) {
	    double exchangeRate = 0.0; // 기본값 설정

	    // 사용자가 선택한 보유 통화에 대한 통화 코드 가져오기
	    String heldCurrencyQuery = "SELECT cur_unit FROM currency_info WHERE cur_nm = ?";
	    // 사용자가 선택한 교환 통화에 대한 통화 코드 가져오기
	    String targetCurrencyQuery = "SELECT cur_unit FROM currency_info WHERE cur_nm = ?";
	    // 보유 통화와 교환 통화의 환율 정보 가져오기
	    String exchangeRateQuery = "SELECT " + heldCurrency + ", " + targetCurrency +
	                               " FROM exchange_rate " +
	                               " WHERE DAY = (SELECT MAX(DAY) FROM exchange_rate)";

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
	        }

	        // 기준 매매율 계산
	        exchangeRate = heldRate / targetRate;
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
		double tradeExchangeRate = getExchangeRate(tradeCurrency, heldCurrency);
	    
	    // 기준 매매율 계산
	    double standardRate = (heldExchangeRate / tradeExchangeRate);
	    

		// 기준 매매율을 기준 매매율 텍스트 필드에 표시합니다.
	    sellTf.setText(String.valueOf(standardRate));
	 
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
	        
	        // 소수점 두 자리까지 표시
	        String standardRateText = String.format("%.2f", standardRate);
	        
	        // 기준 매매율 텍스트 필드에 표시
	        sellTf.setText(standardRateText);
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
                String[] header1 = { "계좌번호", "아이디", "미국 달러", "일본 엔화", "태국 바트", "호주 달러", "캐나다 달러", "스위스 프랑", "중국 위안", "유럽 유로", "영국 파운드", "홍콩 달러", "뉴질랜드 달러", "싱가포르 달러", "대한민국 원화" };
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
	                String exchangeQuery = "SELECT * FROM ex_history WHERE account_num = ?";
	                PreparedStatement exchangeStatement = connection.prepareStatement(exchangeQuery);
	                exchangeStatement.setString(1, accountNum);
	                ResultSet exchangeResult = exchangeStatement.executeQuery();
	                // 환전 내역 데이터를 리스트에 추가
	                while (exchangeResult.next()) {
	                    String[] row = {
	                            exchangeResult.getString("account_num"),
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
	public List<String[]> fetchExchangeDataFromDatabase3() {
        List<String[]> exchangeData = new ArrayList<>();
        try {
            if (connection != null) {
                String query = "SELECT * FROM ex_history";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String[] row = {
                            resultSet.getString("account_num"),
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
	    List<String[]> exchangeData = fetchExchangeDataFromDatabase3();
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

	public static void main(String[] args) {
        String userId = "";
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomeEx1(userId);
            }
        });
    }
}