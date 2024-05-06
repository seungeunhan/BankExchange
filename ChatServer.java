package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class ChatServer {
	String user_id;
	public static final int PORT = 8002;
	ServerSocket server;
	Vector<ClientThread> vc;
	
	public ChatServer(){
		try {
			server = new ServerSocket(PORT);
			vc = new Vector<ClientThread>(); //������ Ŭ���̾�Ʈ ���ͷ� ���� 
		} catch (Exception e) {
			System.err.println("Error in Server");
			System.exit(1);//���������� ����
		}
		System.out.println("**Ŭ���̾�Ʈ ������ ��ٸ��� �ֽ��ϴ�**");
		try {
			while(true) {
				// �ټ��� Ŭ���̾�Ʈ���� ���������� �����ϱ� ���� while �̿�
				//����ϴٰ� client ���� ���� client, server Socket ����
				Socket sock = server.accept(); //Ŭ���̾�Ʈ ���� ��û ����
				ClientThread ct = new ClientThread(sock);
				ct.start();//run
				vc.addElement(ct);//client�� ���ӵ� ������ vector�� ������
			}//--while--
			
		} catch (Exception e) {
			System.err.println("**ChatServer�� �����մϴ�**");
		}//--catch--
	}//--ChatServer--
	
	//���ӵ� ��� client���� �޽��� ����
	public void sendAllMessage(String msg) {
		for (int i = 0; i < vc.size(); i++) {
			ClientThread ct = vc.get(i);
			ct.sendMessage(msg);
		}
	}//--sendAllMessage--

	//client�� ���� ���� �� vector���� ����
	public void removeClient(ClientThread ct) {
		vc.remove(ct);
	}
	

	
	//���ÿ� ��� ��û ������ ���� ���
	class ClientThread extends Thread{
			
			Socket sock;
			BufferedReader in;
			PrintWriter out;
			String id;
			
			public ClientThread(Socket sock) {//in�� outStream
				try {
					this.sock = sock;
					//Ŭ���̾�Ʈ���� ����� ��Ʈ�� ����
					in = new BufferedReader(new InputStreamReader(
									sock.getInputStream()));
					out = new PrintWriter(
							sock.getOutputStream(), true);
					//Ŭ���̾�Ʈ ���� �ּ� + ���ӵǾ��ٴ� ���� ���
					System.out.println(sock + " ���ӵǾ����ϴ�.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}//--ClientThread--
			
			
			@Override
			public void run() {
				try {
					/*
					//client���� ���� ������ �޼���
					out.println("����Ͻ� ���̵� �Է��ϼ���");
					//client���� ���� ���̵� ����*/
					id = in.readLine();
					//������ ��� client���� welocome �޽��� ������
					sendAllMessage("[" + id + "]���� �����Ͽ����ϴ�.");
					String line = "";
					
					
					//Ŭ���̾�Ʈ�� �Է��� ��� ����
					while(true) {
						//client���� �޼��� �� ������ ������
						line = in.readLine();
						if (line==null) {
							removeClient(this);
							break;
						}
						sendAllMessage("[" + id + "]" + line);
				} //--while--
					in.close();
					out.close();
					sock.close();
				}catch (Exception e) {
					removeClient(this);
					System.err.println(sock + id + "���� ������ ���������ϴ�.");
					sendAllMessage(id + "���� ����� �����߽��ϴ�.");
				}
			}//--run--
			
			
			//Client���� �޽��� ������ ���
			public void sendMessage(String msg) {
				out.println(msg);
			}
		}//--ClientThread
	
	
	public static void main(String[] args) throws IOException {
		new ChatServer();
	}
}