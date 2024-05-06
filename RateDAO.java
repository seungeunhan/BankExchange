package test;


import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RateDAO {
	
	private DBConnectionMgr pool;
	
	private static Connection conn;
	//Ŭ������ DB ����
	public RateDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
			conn = pool.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	
	//��¥�� ���� ȯ���� ���ڵ忡 �����ϴ� �޼ҵ�
	public static void insertRate(RateBean ExchangeRate) {
		String sql = "insert into exchange_rate values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, ExchangeRate.getDAY());
			pstmt.setDouble(2, ExchangeRate.getUSD());
			pstmt.setDouble(3, ExchangeRate.getJPY());
			pstmt.setDouble(4, ExchangeRate.getTHB());
			pstmt.setDouble(5, ExchangeRate.getAUD());
			pstmt.setDouble(6, ExchangeRate.getCAD());
			pstmt.setDouble(7, ExchangeRate.getCHF());
			pstmt.setDouble(8, ExchangeRate.getCNY());
			pstmt.setDouble(9, ExchangeRate.getEUR());
			pstmt.setDouble(10, ExchangeRate.getGBP());
			pstmt.setDouble(11, ExchangeRate.getHKD());
			pstmt.setDouble(12, ExchangeRate.getNZD());
			pstmt.setDouble(13, ExchangeRate.getSGD());
			pstmt.setDouble(14, ExchangeRate.getKRW());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
//			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	//���� �ֱٿ� ���Ե� ���ڵ带 �������� �޼ҵ�
	public RateBean getNewestRate() {
        RateBean exchange = null;
//    	String day = LocalDate.now().minusDays(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	String day = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	String sql = "SELECT * FROM exchange_rate ORDER BY DAY DESC LIMIT 1";
    	try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                exchange = new RateBean();
                exchange.setDAY(Date.valueOf(LocalDate.now()));
                exchange.setUSD(rs.getDouble("USD"));
                exchange.setJPY(rs.getDouble("JPY"));
                exchange.setTHB(rs.getDouble("THB"));
                exchange.setAUD(rs.getDouble("AUD"));
                exchange.setCAD(rs.getDouble("CAD"));
                exchange.setCHF(rs.getDouble("CHF"));
                exchange.setCNY(rs.getDouble("CNY"));
                exchange.setEUR(rs.getDouble("EUR"));
                exchange.setGBP(rs.getDouble("GBP"));
                exchange.setHKD(rs.getDouble("HKD"));
                exchange.setNZD(rs.getDouble("NZD"));
                exchange.setSGD(rs.getDouble("SGD"));
                exchange.setKRW(rs.getDouble("KRW"));
                
            }
		} catch (Exception e) {
//			e.printStackTrace();
		}
    	return exchange;
    }
	
    // ���ڵ� ���� ��ȯ�ϴ� �޼ҵ�
	public int getRecordCount() {
		int count = 0;
        String sql = "SELECT COUNT(*) FROM exchange_rate";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return count;
    }

	
    // ���� ������ ���ڵ带 �����ϴ� �޼ҵ�
    public void deleteOldestRecord() {
        String sql = "DELETE FROM exchange_rate ORDER BY DAY ASC LIMIT 1";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }
    
    // ���� �ֱ� ���ڵ带 �����ϴ� �޼ҵ�
    public void deleteNewestRecord() {
    	String sql = "DELETE FROM exchange_rate ORDER BY DAY DESC LIMIT 1";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.executeUpdate();
    	} catch (SQLException e) {
//    		e.printStackTrace();
    	}
    }
    
    //ȯ�� ǥ�� ����ϱ� ���� ȯ�� ���� �޾ƿ���
    public RateBean getTodayRate() {
        RateBean rateInfo = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
        	String sql = "SELECT * FROM exchange_rate WHERE DAY = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, Date.valueOf(LocalDate.now()));
            rs = pstmt.executeQuery();

            if (rs.next()) {
                rateInfo = new RateBean();
                rateInfo.setDAY(rs.getDate("DAY"));
                rateInfo.setUSD(rs.getDouble("USD"));
                rateInfo.setJPY(rs.getDouble("JPY"));
                rateInfo.setTHB(rs.getDouble("THB"));
                rateInfo.setAUD(rs.getDouble("AUD"));
                rateInfo.setCAD(rs.getDouble("CAD"));
                rateInfo.setCHF(rs.getDouble("CHF"));
                rateInfo.setCNY(rs.getDouble("CNY"));
                rateInfo.setEUR(rs.getDouble("EUR"));
                rateInfo.setGBP(rs.getDouble("GBP"));
                rateInfo.setHKD(rs.getDouble("HKD"));
                rateInfo.setNZD(rs.getDouble("NZD"));
                rateInfo.setSGD(rs.getDouble("SGD"));
                rateInfo.setKRW(rs.getDouble("KRW"));
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }

        return rateInfo;
    }
    
    public List<RateBean> getWeekRates(Date date) {
        List<RateBean> weekRates = new ArrayList<>();
        
        // �����ͺ��̽����� 1����ġ �����͸� �����ɴϴ�.
        // �� �κ��� ���� �����ͺ��̽��� SQL ������ ���� �����ؾ� �մϴ�.
        try {
	        String sql = "SELECT * FROM exchange_rate WHERE DAY >= DATE_SUB(?, INTERVAL 1 WEEK)";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setDate(1, date);
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            RateBean rateInfo = new RateBean();
	            rateInfo.setDAY(rs.getDate("DAY"));
	            rateInfo.setUSD(rs.getDouble("USD"));
	            rateInfo.setJPY(rs.getDouble("JPY"));
	            rateInfo.setTHB(rs.getDouble("THB"));
	            rateInfo.setAUD(rs.getDouble("AUD"));
	            rateInfo.setCAD(rs.getDouble("CAD"));
	            rateInfo.setCHF(rs.getDouble("CHF"));
	            rateInfo.setCNY(rs.getDouble("CNY"));
	            rateInfo.setEUR(rs.getDouble("EUR"));
	            rateInfo.setGBP(rs.getDouble("GBP"));
	            rateInfo.setHKD(rs.getDouble("HKD"));
	            rateInfo.setNZD(rs.getDouble("NZD"));
	            rateInfo.setSGD(rs.getDouble("SGD"));
	            rateInfo.setKRW(rs.getDouble("KRW"));
	            weekRates.add(rateInfo);
	        }
        
        }catch(Exception e){
//        	e.printStackTrace();
        }
        return weekRates;
    }
}