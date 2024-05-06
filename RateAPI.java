package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RateAPI {
    public static void main(String[] args) {
    	JSONArray result = null;
    	HttpURLConnection conn = null;
    	StringBuilder sb = new StringBuilder();
    	
    	//API �ҷ�����
    	try {
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    		URL url = new URL("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=syKXlVngjVbdfOa5WuYszqGrcD3vcgeR&searchdate="+today+"+&data=AP01");
    		conn = (HttpURLConnection)url.openConnection();
    		conn.setRequestMethod("GET");
    		conn.setRequestProperty("content-type", "application/json");
    		conn.setDoOutput(true);
    		
    		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); 
    		String line;
    		while((line = br.readLine()) != null) {
    		    sb.append(line);
    		}
    		conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	try {
			result = (JSONArray) new JSONParser().parse(sb.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	//�����ؾ� �ϴ� ȭ���ڵ�
        List<String> excludedCurrencies = Arrays.asList("AED", "BHD", "BND", "DKK", "IDR(100)", "KWD", "MYR", "NOK", "SAR", "SEK");
    	
    	RateDAO dao = new RateDAO();
    	RateBean exchange = new RateBean();
    	RateBean exchange1 = new RateBean();
    	
    	//RateInfo Ÿ���� ���� exchange�� DAY ���� ������ ��¥ (yyyy-MM-dd) �Է� 
    	exchange.setDAY(Date.valueOf(LocalDate.now()));
    	
    	//RateInfo Ÿ���� ���� exchange�� ȭ�� �ڵ� ���� API���� �޾ƿ� ������ ���� ȯ�� �Է�
    	if(result != null) {
            for (Object aResult : result) {
                JSONObject jsonObject = (JSONObject) aResult;
                if(!excludedCurrencies.contains(jsonObject.get("cur_unit"))) {
                	switch(jsonObject.get("cur_unit").toString()) {
                		case "USD": 
                			String usdRateStr = jsonObject.get("deal_bas_r").toString().replace(",", "");
                			exchange.setUSD(Double.parseDouble(usdRateStr)); 
                			break;
                		case "JPY(100)": 
                		    String jpyRateStr = jsonObject.get("deal_bas_r").toString().replace(",", "");
                		    exchange.setJPY(Double.parseDouble(jpyRateStr)); 
                		    break;
                		case "THB": 
                			String thbRateStr = jsonObject.get("deal_bas_r").toString().replace(",", "");
                			exchange.setTHB(Double.parseDouble(thbRateStr)); 
                			break;
                		case "AUD": 
                			String audRateStr = jsonObject.get("deal_bas_r").toString().replace(",", "");
                			exchange.setAUD(Double.parseDouble(audRateStr)); 
                			break;
                		case "CAD": 
                			String cadRateStr = jsonObject.get("deal_bas_r").toString().replace(",", "");
                			exchange.setCAD(Double.parseDouble(cadRateStr)); 
                			break;
                		case "CHF": 
                			String chfRateStr = jsonObject.get("deal_bas_r").toString().replace(",", "");
                			exchange.setCHF(Double.parseDouble(chfRateStr)); 
                			break;
                		case "CNH": 
                			String cnhRateStr = jsonObject.get("deal_bas_r").toString().replace(",", "");
                			exchange.setCNY(Double.parseDouble(cnhRateStr)); 
                			break;
                		case "EUR": 
                			String eurRateStr = jsonObject.get("deal_bas_r").toString().replace(",", "");
                			exchange.setEUR(Double.parseDouble(eurRateStr)); 
                			break;
                		case "GBP": 
                			String gbpRateStr = jsonObject.get("deal_bas_r").toString().replace(",", "");
                			exchange.setGBP(Double.parseDouble(gbpRateStr)); 
                			break;
                		case "HKD": 
                			String hkdRateStr = jsonObject.get("deal_bas_r").toString().replace(",", "");
                			exchange.setHKD(Double.parseDouble(hkdRateStr)); 
                			break;
                		case "NZD": 
                			String nzdRateStr = jsonObject.get("deal_bas_r").toString().replace(",", "");
                			exchange.setNZD(Double.parseDouble(nzdRateStr)); 
                			break;
                		case "SGD": 
                			String sgdRateStr = jsonObject.get("deal_bas_r").toString().replace(",", "");
                			exchange.setSGD(Double.parseDouble(sgdRateStr)); 
                			break;
                		case "KRW": 
                			String krwRateStr = jsonObject.get("deal_bas_r").toString().replace(",", "");
                			exchange.setKRW(Double.parseDouble(krwRateStr)); 
                			break;
                	}
                }
            }
            
            //API���� �޾ƿ� ���� ȯ���� ���� NULL �̸� default ���� ���� 0���� ��ȯ
            //������ ����ȯ���� 0���� �˻�
            boolean isAllZero = exchange.getUSD() == 0 && exchange.getJPY() == 0 && exchange.getTHB() == 0 && exchange.getAUD() == 0 && exchange.getCAD() == 0 && exchange.getCHF() == 0 && exchange.getCNY() == 0 && exchange.getEUR() == 0 && exchange.getGBP() == 0 && exchange.getHKD() == 0 && exchange.getNZD() == 0 && exchange.getSGD() == 0 && exchange.getKRW() == 0;
            
            //RateInfo Ÿ���� ���� exchange1�� DAY �÷����� ������ ��¥, ���� ȯ�� �÷����� ���� �ֱ� ���ڵ��� ���� ȯ�� �Է�
        	exchange1 = dao.getNewestRate();
            
        	//API�� ���� NULL ���� ���� ��� 
            if(isAllZero) {
            	System.out.println("����1: �ֱ� ���� ȯ���� �޾ƿɴϴ�.");
            	dao.insertRate(exchange1);
            	while(dao.getRecordCount() > 7) {
                    dao.deleteOldestRecord();
                }
            }
            
            //�ֱ� �Էµ� ���ڵ��� ���� ȯ���� API���� �޾ƿ� ���� ȯ���� ���� ��� 
            else if(exchange.equals(exchange1)) {
            	System.out.println("����2: ���ڵ带 �������� �ʽ��ϴ�.");
            }
            //�ֱ� �Էµ� ���ڵ��� ���� ȯ���� API���� �޾ƿ� ���� ȯ���� �ٸ� ���
            else if(!exchange.equals(exchange1)) {
            	System.out.println("����3: ������ ���� ȯ���� �ٽ� �޾ƿɴϴ�.");
            	dao.deleteNewestRecord();
            	dao.insertRate(exchange);
            }
            else {
            	System.out.println("���ڵ带 �������� �ʽ��ϴ�.");
            }
        } else {
            System.out.println("API ȣ�� ����� �����ϴ�.");
        }
    	conn.disconnect();
    }
}