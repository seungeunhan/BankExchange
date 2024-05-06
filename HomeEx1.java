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
    	setBackground(new Color(255, 255, 255)); //�г� �� ����
    	setLayout(new BorderLayout()); //���̾ƿ� ����
    	
    	//table1 ������ �� �� �̸�
        String[] header1 = { "���¹�ȣ", "���̵�", "USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW" };
		String[][] contents = {};
		
		//table2 ������ �� �� �̸�
		String[] header2 = { "ȯ����ȣ", "��¥", "������ȭ", "��ȯ��ȭ", "�ݾ�" };
		Object[][] data = {};
		
		//table3 ������ �� �� �̸�
		String[] header3 = { "ȯ����ȣ", "��¥", "������ȭ", "��ȯ��ȭ", "�ݾ�" };
		Object[][] data2 = {};
		
		//table1 ���� �� ����
		table1 = new JTable(contents, header1);
		table1.setRowHeight(30);
		table1.setBounds(30, 80, 770, 100);
		
		//table2 ���� �� ����
		table2 = new JTable(data, header2);
		table2.setRowHeight(30);
		table2.setBounds(10, 450, 800, 150);
		
		//table3 ���� �� ����
		table3 = new JTable(data2, header3);
		
		//���̺��� �г��� �߾ӿ� �߰�
		add(table1, BorderLayout.SOUTH);
		add(table2, BorderLayout.CENTER);
		add(table3, BorderLayout.CENTER);
    } 
    
    // �гο� �׸���
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // �׷��� ������ ���� ��� �̰��� �׸��� �ڵ带 �߰�
    }
    
 // ǥ�� ������Ʈ�ϴ� �޼ҵ� �߰�
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
    	setBackground(new Color(62,192,196)); //�г� �� ����
    } 
    
    // �гο� �׸���
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // �׷��� ������ ���� ��� �̰��� �׸��� �ڵ带 �߰�
    }
}

public class HomeEx1 extends JPanel implements ActionListener {
	
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static Connection connection = null;
    private String userId;
    
    
    JPanel exchange_Panel = new JPanel(null);
	Label mymoneyl, exlistl, heldl, tradel, selll, exl, totall; // ���� ������ �ڻ�, ȯ�� ����, ���� ��ȭ, ��ȯ ��ȭ, ���� �Ÿ���, ȯ�� �ݾ�, �� ȯ����
	TextField sellTf, exTf, totalTf, searchTf; // ���� �Ÿ���, ȯ�� �ݾ�, �� ȯ����
	Font font1 = new Font("Aharoni ����", Font.BOLD, 25);
	
	Color customColor = new Color(169, 219, 208);
	Color customColor2 = new Color(62,192,196);
	Color customColor3 = new Color(255, 255, 255);
	
	JButton moreBtn = new JButton("������"); //������ ��ư
	
	JButton doexBtn = new JButton("ȯ���ϱ�"); //ȯ���ϱ� ��ư
	
	Choice ch1;
	String heldmoney[] = { "�̱� �޷�", "�Ϻ� ��ȭ", "�±� ��Ʈ", "ȣ�� �޷�", "ĳ���� �޷�", "������ ����", "�߱� ����", "���� ����", "���� �Ŀ��", "ȫ�� �޷�", "�������� �޷�", "�̰����� �޷�", "�ѱ� ��ȭ" };
	
	Choice ch2;
	String trademoney[] = { "�̱� �޷�", "�Ϻ� ��ȭ", "�±� ��Ʈ", "ȣ�� �޷�", "ĳ���� �޷�", "������ ����", "�߱� ����", "���� ����", "���� �Ŀ��", "ȫ�� �޷�", "�������� �޷�", "�̰����� �޷�", "�ѱ� ��ȭ" };
	
//	Choice ch1;
//	String heldmoney[] = { "USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW" };
//	
//	Choice ch2;
//	String trademoney[] = { "USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW" };
	
	public HomeEx1(String userId) {
		
		this.userId = userId;
//		getContentPane().setBackground(new Color(169, 219, 208)); //â ���� ����
		setSize(800, 500);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		ch1 = new Choice();
		ch1.setFont(new Font("���� ���", Font.PLAIN, 20));
		ch1.setBounds(190, 180, 160, 80);
		ch2 = new Choice();
		ch2.setFont(new Font("���� ���", Font.PLAIN, 20));
		ch2.setBounds(510, 180, 160, 80);
		
		mymoneyl = new Label("���� ������ �ڻ�");	
		mymoneyl.setFont(new Font("���� ���", Font.BOLD, 24));
		mymoneyl.setBounds(30, 40, 300, 40);
		
		exlistl = new Label("ȯ�� ����");
		exlistl.setFont(new Font("���� ���", Font.BOLD, 28));
		exlistl.setBounds(20, 400, 120, 50);
		
		heldl = new Label("���� ��ȭ");
		heldl.setFont(new Font("���� ���", Font.PLAIN,20));
		heldl.setBounds(80, 180, 90, 40);
		
		tradel = new Label("��ȯ ��ȭ");
		tradel.setFont(new Font("���� ���", Font.PLAIN,20));
		tradel.setBounds(400, 180, 90, 40);
		
		selll = new Label("���� �Ÿ���");
		selll.setFont(new Font("���� ���", Font.PLAIN,20));
		selll.setBounds(80, 330, 90, 40);
		sellTf = new TextField("");
		sellTf.setEditable(false); // ���� �Ÿ����� ����ڰ� ���� �Է����� ����
		sellTf.setBounds(190, 330, 160, 40);
		
		exl = new Label("ȯ�� �ݾ�");
		exl.setFont(new Font("���� ���", Font.PLAIN, 20));
		exl.setBounds(80, 255, 90, 35);
		exTf = new TextField("");
		exTf.setFont(new Font("���� ���", Font.PLAIN, 20));
		exTf.setBounds(190, 255, 480, 35);
		
		totall = new Label("�� ȯ����");
		totall.setFont(new Font("���� ���", Font.PLAIN, 20));
		totall.setBounds(400, 330, 90, 40);
		totalTf = new TextField("");
		totalTf.setEditable(false); // �� ȯ������ ����ڰ� ���� �Է����� ����
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
		
		
		
		
		//ȯ���ϱ� ��ư �׼Ǹ�����
		doexBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
		            // ����ڰ� �Է��� ȯ�� �ݾװ� ������ ���� ��ȭ, ��ȯ ��ȭ ��������
		            double exchangeAmount = Double.parseDouble(exTf.getText());
		            String heldCurrency = ch1.getSelectedItem();
		            System.out.println("heldCurrency ��: " + heldCurrency);
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
			            // ȯ�� �ݾ��� ������ �ݾ׺��� ������ Ȯ��
			            // ���� ȯ�� �ݾ��� ������ �ݾ׺��� ������ ����ڿ��� �˸��� ǥ��
			            if (exchangeAmount > getHeldBalance(userId, heldCurrencyCode)) {
			                throw new IllegalArgumentException("������ �ݾ׺��� ȯ�� �ݾ��� �����ϴ�.");
			            }
			            else//���������� 
			            {
			            // ���� ��ȭ���� ȯ�� �ݾ� ����(���̵�,��������,��)
			            subtractFromHeldBalance(userId, heldCurrency, exchangeAmount);
			            
			            // ��ȯ ��ȭ�� �ش��ϴ� �ܰ� ����
			            addToTradeBalance(userId, tradeCurrency, exchangeAmount);
			            
			            // �����ͺ��̽� ������Ʈ
			            updateAccountBalanceInDatabase(userId);
			            
			            // ���� �޽��� ǥ��
			            JOptionPane.showMessageDialog(HomeEx1.this, "ȯ���� �Ϸ�Ǿ����ϴ�.", "ȯ�� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);	
		            }
		            
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(HomeEx1.this, "�ùٸ� ���ڸ� �Է��ϼ���.", "����", JOptionPane.ERROR_MESSAGE);
		        } catch (IllegalArgumentException ex) {
		            JOptionPane.showMessageDialog(HomeEx1.this, ex.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
		        } catch (SQLException e1) {
					//��ȭ�ڵ� �޾ƿ��µ� ����������
					e1.printStackTrace();
				}
		            System.out.println("heldCurrencyCode ��: " + heldCurrencyCode); // ��ȭ �ڵ� ���
			}
		
		// �����ͺ��̽����� ������ ���� double�� ��ȯ�Ͽ� ��ȯ�ϴ� �޼ҵ�
		private double getDoubleFromResultSet(ResultSet resultSet, String columnName) throws SQLException {
		    String valueAsString = resultSet.getString(columnName);
		    return Double.parseDouble(valueAsString);
		}
		
		// ������ �ݾ��� �������� �޼ҵ�
		private double getHeldBalance(String userId, String heldCurrency) {
		    double balance = 0.0; // �ܰ� �ʱ�ȭ
		    try {
		        if (connection != null) {
		            String query = "SELECT " + heldCurrency + " FROM account WHERE id = ?";
		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            preparedStatement.setString(1, userId);
		            ResultSet resultSet = preparedStatement.executeQuery();
		            if (resultSet.next()) {
		                // ResultSet���� ���� �����ͼ� double�� ��ȯ
		                balance = getDoubleFromResultSet(resultSet, heldCurrency);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return balance; // �ܰ� ��ȯ
		}

		private void subtractFromHeldBalance(String userId, String heldCurrency, double amount) {
		    try {
		        if (connection != null) {
		            // ���� ��ȭ���� ȯ�� �ݾ��� �����ϴ� ���� �ۼ�
		        	String query = "UPDATE account SET " + heldCurrency + " = " + heldCurrency + " - ? WHERE id = ?";

		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            preparedStatement.setDouble(1, amount);
		            preparedStatement.setString(2, userId);
		            
		            // ���� ����
		            preparedStatement.executeUpdate();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		// ��ȯ ��ȭ�� �ش��ϴ� �ܰ� �����ϴ� �޼ҵ�
		private void addToTradeBalance(String userId, String tradeCurrency, double amount) {
		    try {
		        if (connection != null) {
		            // ��ȯ ��ȭ�� �ش��ϴ� �ܰ� ������Ű�� ���� �ۼ�
		        	String query = "UPDATE account SET " + tradeCurrency + " = " + tradeCurrency + " + ? WHERE id = ?";

		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            preparedStatement.setDouble(1, amount);
		            preparedStatement.setString(2, userId);
		            
		            // ���� ����
		            preparedStatement.executeUpdate();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		// �����ͺ��̽� ������Ʈ �޼ҵ�
		private void updateAccountBalanceInDatabase(String userId) {
		    try {
		        if (connection != null) {
		            // ������ ���� ��ȭ �� ��ȯ ��ȭ�� ���� ������Ʈ�ϴ� ���� �ۼ�
		            String query = "UPDATE account SET USD = ?, JPY = ?, THB = ?, AUD = ?, CAD = ?, CHF = ?, " +
		                           "CNY = ?, EUR = ?, GBP = ?, HKD = ?, NZD = ?, SGD = ?, KRW = ? WHERE id = ?";
		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            
		            // ������ ��ȭ�� ���� �ܰ� �����ͼ� ����
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
		            
		            // ����� ID ����
		            preparedStatement.setString(14, userId);
		            
		            // ���� ����
		            int rowsAffected = preparedStatement.executeUpdate();
		            
		            if (rowsAffected > 0) {
		                System.out.println("������� ���� �ܰ� ���������� ������Ʈ�Ǿ����ϴ�.");
		            } else {
		                System.out.println("������� ���� �ܰ� ������Ʈ�ϴ� �� �����߽��ϴ�.");
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
        // �����ͺ��̽� ����
        connect();
        
        // ���� �Ÿ��� ������Ʈ
        updateStandardRate();
        
        // �����ͺ��̽����� ȯ�� ���� �����ͼ� ���̺� ǥ��
        List<String[]> exchangeData = fetchExchangeDataFromDatabase1(userId);
        if (exchangeData != null) {
            String[][] exchangeArray = new String[exchangeData.size()][];
            for (int i = 0; i < exchangeData.size(); i++) {
                exchangeArray[i] = exchangeData.get(i);
            }
            String[] header1 = { "���¹�ȣ", "���̵�", "USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW" };
            exchangePanel.updateTable1(exchangeArray, header1);
        }
        
        List<String[]> accountData = fetchExchangeDataFromDatabase2(userId);
        if (accountData != null) {
            String[][] accountArray = new String[accountData.size()][];
            for (int i = 0; i < accountData.size(); i++) {
                accountArray[i] = accountData.get(i);
            }
            String[] header2 = { "ȯ����ȣ", "��¥", "������ȭ", "��ȯ��ȭ", "�ݾ�" };
            exchangePanel.updateTable2(accountArray, header2);
        }
        
        List<String[]> accountData2 = fetchExchangeDataFromDatabase3(userId);
        if (accountData2 != null) {
            String[][] accountArray2 = new String[accountData2.size()][];
            for (int i = 0; i < accountData2.size(); i++) {
                accountArray2[i] = accountData2.get(i);
            }
            String[] header3 = { "ȯ����ȣ", "��¥", "������ȭ", "��ȯ��ȭ", "�ݾ�" };
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
	


	// ���� ��ȭ�� ��ȯ ��ȭ�� ȯ�� �������� �޼ҵ�
	private double getExchangeRate(String heldCurrency, String targetCurrency) {
	    double exchangeRate = 0.0; // �⺻�� ����

		// ����ڰ� ������ ���� ��ȭ�� ���� ��ȭ �ڵ� ��������
	    String heldCurrencyQuery = "SELECT cur_unit FROM currency_info WHERE cur_nm = ?;";
	    // ����ڰ� ������ ��ȯ ��ȭ�� ���� ��ȭ �ڵ� ��������
	    String targetCurrencyQuery = "SELECT cur_unit FROM currency_info WHERE cur_nm = ?;";
	    // ���� ��ȭ�� ��ȯ ��ȭ�� ȯ�� ���� ��������
	    String exchangeRateQuery = "SELECT * FROM exchange_rate WHERE DAY = (SELECT MAX(DAY) FROM exchange_rate)";
	    
	    try {
	        // ���� ��ȭ�� ���� ��ȭ �ڵ� ��������
	        PreparedStatement heldStatement = connection.prepareStatement(heldCurrencyQuery);
	        heldStatement.setString(1, heldCurrency);
	        ResultSet heldResult = heldStatement.executeQuery();
	        String heldCurrencyCode = "";
	        if (heldResult.next()) {
	            heldCurrencyCode = heldResult.getString("cur_unit");
	        }

	        // ��ȯ ��ȭ�� ���� ��ȭ �ڵ� ��������
	        PreparedStatement targetStatement = connection.prepareStatement(targetCurrencyQuery);
	        targetStatement.setString(1, targetCurrency);
	        ResultSet targetResult = targetStatement.executeQuery();
	        String targetCurrencyCode = "";
	        if (targetResult.next()) {
	            targetCurrencyCode = targetResult.getString("cur_unit");
	        }

	        // ���� ��ȭ�� ��ȯ ��ȭ�� ȯ�� ���� ��������
	        PreparedStatement exchangeRateStatement = connection.prepareStatement(exchangeRateQuery);
	        ResultSet exchangeRateResult = exchangeRateStatement.executeQuery();
	        double heldRate = 0.0;
	        double targetRate = 0.0;
	        if (exchangeRateResult.next()) {
	            heldRate = exchangeRateResult.getDouble(heldCurrencyCode);
	            targetRate = exchangeRateResult.getDouble(targetCurrencyCode);
	         // ���� �Ÿ��� ���
		        exchangeRate = heldRate / targetRate;
	        }
	        
	     	// �ֿܼ� ȯ�� ���� ���
	        System.out.println("���� ��ȭ ȯ��: " + heldRate);
	        System.out.println("��ȯ ��ȭ ȯ��: " + targetRate);
	        System.out.println("���� �Ÿ���: " + exchangeRate);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // ���� ó��
	    }

	    return exchangeRate; // ���� ���� �Ÿ��� ��ȯ
	}
	
	// ���� �Ÿ��� ��� �޼ҵ� �߰�
	private double calculateStandardRate(String heldCurrency, String tradeCurrency, double amount) {
		// ���� ��ȭ�� ��ȯ ��ȭ�� ȯ�� ��������
		double heldExchangeRate = getExchangeRate(heldCurrency, tradeCurrency);
	    
	    // ���� �Ÿ��� ���
	    double standardRate = heldExchangeRate;
	    double totalRate = heldExchangeRate * amount;

		// ���� �Ÿ����� ���� �Ÿ��� �ؽ�Ʈ �ʵ忡 ǥ���մϴ�.
	    sellTf.setText(String.valueOf(standardRate));
	    totalTf.setText(String.valueOf(totalRate));
	 
	    return standardRate;
	}
	
	// �ؽ�Ʈ �ʵ忡�� ���� �Է� �����Ͽ� ���� �Ÿ��� ������Ʈ�ϴ� �޼ҵ� �߰�
	private void updateStandardRate() {
	    try {
	        // ȯ�� �ݾ� ��������
	        double amount = Double.parseDouble(exTf.getText());
	        
	        // ���� ��ȭ�� ��ȯ ��ȭ ���� �� ��������
	        String heldCurrency = ch1.getSelectedItem();
	        String tradeCurrency = ch2.getSelectedItem();
	        
	        // ���� �Ÿ��� ���
	        double standardRate = calculateStandardRate(heldCurrency, tradeCurrency, amount);
	        double totalRate = calculateStandardRate(heldCurrency, tradeCurrency, amount) * amount;
	        
	        // �Ҽ��� �� �ڸ����� ǥ��
	        String standardRateText = String.format("%.2f", standardRate);
	        String totalRateText = String.format("%.2f", totalRate);
	        
	        // ���� �Ÿ��� �ؽ�Ʈ �ʵ忡 ǥ��
	        sellTf.setText(standardRateText);
	        totalTf.setText(totalRateText);
	    } catch (NumberFormatException ex) {
	        // ���ڷ� ��ȯ�� �� ���� ��� ���� ó��
	        sellTf.setText(""); // �ؽ�Ʈ �ʵ带 ���
	    }
	    

	// ȯ�� �ݾ� �ؽ�Ʈ �ʵ忡 Ű �̺�Ʈ ������ �߰�
	exTf.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(KeyEvent e) {
	        updateStandardRate();
	    }
	});
	
	// ���� ��ȭ�� ��ȯ ��ȭ ���̽��ڽ��� ������ ������ �߰�
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
                String[] header1 = { "���¹�ȣ", "���̵�", "USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW" };
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
	//�ֽ� 3���� ȯ�� ����
	public List<String[]> fetchExchangeDataFromDatabase2(String userId) {
	    List<String[]> accountData = new ArrayList<>();
	    try {
	        if (connection != null) {
	            // ������� ���¹�ȣ ��������
	            String accountQuery = "SELECT account_num FROM account WHERE id = ?";
	            String[] header2 = { "ȯ����ȣ", "��¥", "������ȭ", "��ȯ��ȭ", "�ݾ�" };
	            accountData.add(header2);
	            PreparedStatement accountStatement = connection.prepareStatement(accountQuery);
	            accountStatement.setString(1, userId);
	            ResultSet accountResult = accountStatement.executeQuery();
	            if (accountResult.next()) {
	                String accountNum = accountResult.getString("account_num");
	                // ���¹�ȣ�� ȯ�� ���� ��ȸ
	                String exchangeQuery = "SELECT * FROM ex_history WHERE ex_account_num = ?";
	                PreparedStatement exchangeStatement = connection.prepareStatement(exchangeQuery);
	                exchangeStatement.setString(1, accountNum);
	                ResultSet exchangeResult = exchangeStatement.executeQuery();
	                // ȯ�� ���� �����͸� ����Ʈ�� �߰�
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
	//��ü ȯ�� ����
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
	
	// �˻� �޼ҵ� ����
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
		// �����ͺ��̽����� ��ü ȯ�� ���� ��������
	    List<String[]> exchangeData = fetchExchangeDataFromDatabase3(userId);
		if (exchangeData != null) {
		//ǥ �� ����
		DefaultTableModel model3 = new DefaultTableModel();
		model3.setColumnIdentifiers(new String[] { "ȯ����ȣ", "��¥", "������ȭ", "��ȯ��ȭ", "�ݾ�" });
		for (String[] row : exchangeData) {
            model3.addRow(row);
        }
		
		//ǥ ����
		JTable table3 = new JTable(model3);
		
		//ǥ�� ���� �г� ����
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.add(new JScrollPane(table3), BorderLayout.CENTER);
		
		//��� â ����
		JDialog dialog = new JDialog();
		dialog.setTitle("ȯ�� ����");
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLayout(new BorderLayout());
		dialog.add(tablePanel, BorderLayout.CENTER);
		dialog.setSize(800, 500); //��� â ũ�� ����
		dialog.setLocationRelativeTo(null); //ȭ�� �߾ӿ� ��ġ
		
		//ǥ�� ��� â�� ���� �߰��ϰ� ���ϴ� ��ġ�� �̵�
		JScrollPane scrollPane = new JScrollPane(table3);
		dialog.setLayout(null);
		scrollPane.setBounds(0, 150, 750, 100); //��� â �� ǥ ��ġ �� ũ�� ����
		dialog.add(scrollPane);
		
		//�ݱ� ��ư�� ������ �г� ����
		JButton closeButton = new JButton("�ݱ�");
		closeButton.setBounds(650, 400 , 100, 30);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose(); //��� â �ݱ�
			}
		});
		
		// �ؽ�Ʈ �ʵ� ����
        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(450, 50, 180, 30);
		
		// �˻� ��ư ����
		JButton searchBtn = new JButton("�˻�");
		searchBtn.setBounds(650, 50 , 80, 30);
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchText = searchTextField.getText(); // �˻��� ��������
                List<String[]> searchedExchangeData = searchExchangeData(exchangeData, searchText); // �˻� ����
                if (searchedExchangeData != null) {
                    // �˻��� �����Ͱ� ���� ��� ǥ ������Ʈ
                    DefaultTableModel searchModel = new DefaultTableModel();
                    searchModel.setColumnIdentifiers(new String[] { "ȯ����ȣ", "��¥", "������ȭ", "��ȯ��ȭ", "�ݾ�" });
                    for (String[] row : searchedExchangeData) {
                        searchModel.addRow(row);
                    }
                    table3.setModel(searchModel);
                } else {
                    // �˻��� �����Ͱ� ���� ��� �޽��� ���
                    JOptionPane.showMessageDialog(dialog, "�˻� ����� �����ϴ�.", "�˻� ���", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
			dialog.add(tablePanel);
			dialog.add(closeButton);
			dialog.add(searchBtn);
			dialog.add(searchTextField);
			dialog.setVisible(true);
		} else {
			// �����ͺ��̽����� ȯ�� ������ �������� ���� ��쿡 ���� ó��
			System.out.println("�����ͺ��̽� ���� ����");
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