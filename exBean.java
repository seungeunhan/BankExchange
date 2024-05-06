package test;

import java.sql.Date;

public class exBean/*String : Dto, Vo*/ {

	private int ex_num;
	private String ex_account_id; //µî·ÏÀÚ
	private Date day;
	private String sell_cur;
	private double sell_amt;
	private String buy_cur;
	private double buy_amt;
	
	/*getXxx, setXxx*/
	public int getExNum() {
		return ex_num;
	}
	public void setExNum(int ex_num) {
		this.ex_num = ex_num;
	}
	public String getExAcc() {
		return ex_account_id;
	}
	public void setExAcc(String ex_account_id) {
		this.ex_account_id = ex_account_id;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public String getSellCur() {
		return sell_cur;
	}
	public void setSellCur(String sell_cur) {
		this.sell_cur = sell_cur;
	}
	public double getSellAmt() {
		return sell_amt;
	}
	public void setSellAmt(double sell_amt) {
		this.sell_amt = sell_amt;
	}
	public String getBuyCur() {
		return buy_cur;
	}
	public void setBuyCur(String buy_cur) {
		this.buy_cur = buy_cur;
	}
	public double getBuyAmt() {
		return buy_amt;
	}
	public void setBuyAmt(double buy_amt) {
		this.buy_amt = buy_amt;
	}	
}
