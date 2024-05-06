package test;

import java.util.HashMap;
import java.util.Map;
//�̰��� ȸ������ ������ �ϰ������� ��Ƽ� �������ִ� Ŭ�����̴�.
//���� ������ testSocket��ü���� ��û�Ͽ��� �������� ����ѵڿ� �������� ���Ϲ޴´�.
public class MemberManager {
	TestSocket testSocket = TestSocket.getInstance();
    private static MemberManager instance;
    private Map<Integer, String> members;
    //ȸ�� ����(�ӽ�)
    private int id;
    private String name;
    private int password;

    // private �����ڷ� �ܺο��� ���� �ν��Ͻ��� �������� ���ϵ��� ��
    private MemberManager() {
        members = new HashMap<>();
    }

    // �ν��Ͻ��� ��� ���� �޼���
    public static MemberManager getInstance() {
    	if (instance == null) {
    		instance = new MemberManager();
    	}
    	return instance;
    }
    
    public void addMember(int memberId, String memberInfo) {
        members.put(memberId, memberInfo);
    }

    public String getMember(int memberId) {
        return members.get(memberId);
    }

    public void printMembers() {
        for (Map.Entry<Integer, String> entry : members.entrySet()) {
            System.out.println("ȸ�� ID: " + entry.getKey() + ", ȸ�� ����: " + entry.getValue());
        }
    }
}