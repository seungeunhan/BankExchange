package test;

/*�ѱ� ���� �� : properties-MS949�� ����*/

public class ChatProtocol {
	
	//ID, CHAT, CHATALL
	
	//(C->S) ID:aaa;1234
	//(S->C) ID:T (����), ID:F(����), ID:C(��������)
	//(S->C) CHATLIST:aaa;bbb;ccc;ddd;
	public  static final String ID = "ID";
	
	//(C->S) CHAT:�޴¾��̵�;�޼��� ex) CHAT:bbb;�����
	//(S->C) CHAT:�������̵�;�޼��� ex) CHAT:aaa;�����
	public  static final String CHAT = "CHAT";
	
	//(C->S) CHATALL:�޼���
	//(S->C) CHATALL:[�������̵�]�޼���
	public  static final String CHATALL = "CHATALL";
	
	public static final String MODE = ":";
	
	//(S->C) CHATLIST:aaa;bbb;ccc;ȫ�浿;
	public static final String CHATLIST = "CHATLIST";
}





