package test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//select, updata ���


public class MyinfoDAO {
	Userbean userData = new Userbean();
	DB_Connect dbcon = new DB_Connect();
	PreparedStatement pstmt=null;
	

	//�� ���� �ؽ�Ʈ �ʵ忡 ȸ������ �ѷ���
	public Userbean select_user(String userId) throws ClassNotFoundException, SQLException {
		//���� ����
		String id = "'"+userId+"'";
		System.out.println(id);
		Connection conn = dbcon.getConnection();
		String sql = "SELECT member.id, member.pwd, member.name, member.phone_num, member.telecom, account.account_num FROM member INNER JOIN account ON member.id=account.id WHERE member.id="+id;
		System.out.println(sql);
		//PreparedStatement ����
		pstmt = conn.prepareStatement(sql);
		//����� ���� ResultSet ���� �� ��� ���
		ResultSet rs = pstmt.executeQuery(sql);
		
		//ResultSet�� ��� ����� ArrayList�� ���
		ArrayList<Userbean> user_list = new ArrayList<Userbean>();
		while(rs.next()) {
			userData.setName(rs.getString("name"));
			userData.setId(rs.getString("id"));
			userData.setPw(rs.getString("pwd"));
			userData.setAccount(rs.getString("account_num"));
			userData.setPhone(rs.getString("phone_num"));
			userData.setTelecom(rs.getString("telecom"));
			}

		pstmt.close();
		rs.close();
		return userData;	//userData ��ȯ
	}
	
	//�ؽ�Ʈ �ʵ� �Է� �� db ����
	public void update_user(ArrayList<String> Update_data) {
		Connection conn = dbcon.getConnection();
		//�ʿ��� ��ü�� ���� ���� ���� �̸� �����
				try {
					//���� ����
					String sql = "UPDATE member SET name=?, pwd=?, phone_num=?, telecom=?"
							+"WHERE id='"+Update_data.get(4)+"'";
					//PreparedStatement ����
					pstmt = conn.prepareStatement(sql);
					//?�� �ʿ��Ѱ� ���ε��ϱ� 
					pstmt.setString(1,Update_data.get(0));
					pstmt.setString(2, Update_data.get(1));
					pstmt.setString(3, Update_data.get(2));
					pstmt.setString(4, Update_data.get(3));
		
					//sql �� �����ϱ� (UPDATE)
					System.out.println("�Է� ������:"+Update_data.get(0)+"2:"+Update_data.get(1)
					+"3:"+Update_data.get(2)+"4:"+Update_data.get(3)+"5:"+Update_data.get(4));
					pstmt.executeUpdate();
				} catch (Exception e) {
					System.out.println("Exception ���� �߻� : MyinfoDAO");
					e.printStackTrace();
				} finally {
					try {
						if (pstmt != null)
							pstmt.close();
						if (conn != null)
							conn.close();
					} catch (SQLException e) {
						System.out.println("SQLException ���� �߻� : MyinfoDAO");
					}
				}
	}
		

}
