package test;

public class MessageBean3 {
	
	private int ex_num;//��ȣ(�ڵ�����) 
	private String ex_account_num;//����� ����
	private String day;//�ŷ� ��¥
	private String sell_cur;//�Ǹ� ��ȭ
	private String sell_amt;//�Ǹ� ��ȭ��
	private String buy_cur;//���� ��ȭ
	private String buy_amt;//���� ��ȭ��
	private String rate;
	
	public int getNo() {
		return ex_num;
	}
	public void setNo(int ex_num) {
		this.ex_num = ex_num;
	}
	
	
	public String getex_account_num() {
		return ex_account_num;
	}
	public void setex_account_num(String ex_account_num) {
		this.ex_account_num = ex_account_num;
	}
	
	
	public String getday() {
		return day;
	}
	public void setday(String day) {
		this.day = day;
	}
	
	
	public String getsell_cur() {
		return sell_cur;
	}
	public void setsell_cur(String sell_cur) {
		this.sell_cur = sell_cur;
	}
	
	
	public String getbuy_cur() {
		return buy_cur;
	}
	public void setbuy_cur(String buy_cur) {
		this.buy_cur = buy_cur;
	}
	
	
	public String getbuy_amt() {
		return buy_amt;
	}
	public void setbuy_amt(String buy_amt) {
		this.buy_amt = buy_amt;
	}	
	
	
	public String getsell_amt() {
		return sell_amt;
	}
	public void setsell_amt(String sell_amt) {
		this.sell_amt = sell_amt;
	}	
	
	
	public String getrate() {
		return rate;
	}
	public void setrate(String rate) {
		this.rate = rate;
	}	
}
