package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//������ ���α׷��� �����Ҷ� main�Լ��� ù�κп� ����Ǿ� �����Ǿ� ����
//���α׷��� ����ɶ� ���� ���������� ������ ����Ǿ �ʿ��� ������ ��Źް�
//�ʿ��� ��ü���� ������ �����ϴ� ������ �Ѵ�.
public class TestSocket implements Runnable{
	
	private static TestSocket instance;
	BufferedReader in;
	PrintWriter out;
	private Socket socket;
	
	 // ���� �ּҿ� ��Ʈ ��ȣ
    private static final int SERVER_PORT = 8003;
	
	public TestSocket() {
		connect();
	}
	
    public void connect() {
    	if(socket==null) {
    		try {
    			Socket sock = new Socket("127.0.0.1",SERVER_PORT);
    			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    			out = new PrintWriter(sock.getOutputStream(),true);
    			System.out.println("���Ἲ���Դϴ�.");
    		} catch (Exception e) {
    			System.out.println("��������Դϴ�.");
    		}
    		new Thread(this).start();
    	}
    }
    
    
	@Override
	public void run() {
	}
	
	public static synchronized TestSocket getInstance() {
		if (instance == null) {
			instance = new TestSocket();
		}
		return instance;
	}
	// ���̵�� ��й�ȣ�� ������ ������ �̸��� ��ȯ�޴� �޼���
    public boolean sendCredentialsAndReceiveName(String id, String password) {
        if (out != null && in != null) {
            try {
                // ������ ���̵�� ��й�ȣ�� ���� ID:aaa;1234
            	
            	out.println(ChatProtocol3.ID+ChatProtocol3.MODE+id+";"+password);
            	System.out.println(ChatProtocol3.ID+id+ChatProtocol3.MODE+";"+password);
                
                
                
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
