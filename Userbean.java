package test;

//�̸�, ���̵�, ��й�ȣ, �������, �޴��ȣ, ��Ż� ��

public class Userbean {
	String name, id, pw, account, phone, telecom;
	
	public String getName() { //�̸�
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() { //���̵�
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() { //��й�ȣ
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getAccount() { //�������
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPhone() { //�޴��ȣ
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTelecom() { //��Ż�
		return telecom;
	}
	public void setTelecom(String telecom) {
		this.telecom = telecom;
	}
	public void show() {
		System.out.println(name+ id+ pw+ account+ phone+ telecom);
	}
}
