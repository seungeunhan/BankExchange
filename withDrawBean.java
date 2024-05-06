package test;

import java.sql.Date;

public class withDrawBean/*String : Dto, Vo*/ {

	private int with_num;
	private String account_id; //µî·ÏÀÚ
	private Date day;
	private String with_cur;
	private double with_amt;
	private String b_name;
	private boolean check;

	
	/*getXxx, setXxx*/
	public int getWithNum() {
		return with_num;
	}
	public void setWithNum(int with_num) {
		this.with_num = with_num;
	}
	public String getWithAcc() {
		return account_id;
	}
	public void setWithAcc(String account_id) {
		this.account_id = account_id;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public String getWithCur() {
		return with_cur;
	}
	public void setWithCur(String with_cur) {
		this.with_cur = with_cur;
	}
	public double getWithAmt() {
		return with_amt;
	}
	public void setWithAmt(double with_amt) {
		this.with_amt = with_amt;
	}

	public String getbName() {
		return b_name;
	}
	public void setbName(String b_name) {
		this.b_name = b_name;
	}
	public boolean getCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	public String toString() {
	    return "withDrawBean [tradeNum=" + with_num + ", traderAcc=" + account_id 
	            + ", day=" + day + ", sellCur=" + with_cur + ", sellAmt=" + with_amt + ", bName=" + b_name 
	            + ", check=" + check + "]";
	}
}
