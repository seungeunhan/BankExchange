package myinfo;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class JoinPanel extends JPanel {
    public JoinPanel() {
        setBackground(new Color(255, 255, 255)); // 패널 색 지정
    }

    // 패널에 그리기
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 그려질 내용이 있을 경우 이곳에 그리기 코드를 추가합니다.
    }
}

public class JoinEx1 extends JFrame implements ActionListener {

    TextField idTf, pwTf, checkpwTf, eTf, nameTf, phTf;
    Label idl, pwl, checkpwl, el, namel, phl, msgl;

    JButton duBtn = new JButton();
    JButton joinBtn = new JButton();
    JButton cancelBtn = new JButton();

    Color customColor = new Color(169, 219, 208);
    Color customColor2 = new Color(62, 192, 196);
    Color customColor3 = new Color(255, 255, 255);

    String title = "회원가입";
    Font font = new Font("Aharoni 굵게", Font.BOLD, 25);

    Choice ch;
    String telecom[] = { "통신사 선택", "KT", "LG U+", "SKT" };

    public JoinEx1() {
        getContentPane().setBackground(new Color(169, 219, 208)); // 창 배경색 지정
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        ch = new Choice();
        msgl = new Label(title);

        idl = new Label("아이디");
        pwl = new Label("비밀번호");
        checkpwl = new Label("비밀번호 확인");
        el = new Label("이메일");
        namel = new Label("이름");
        phl = new Label("전화번호");

        idTf = new TextField("");
        pwTf = new TextField("");
        pwTf.setEchoChar('*'); // 비밀번호 입력 시 '*'로 표시
        checkpwTf = new TextField("");
        checkpwTf.setEchoChar('*'); // 비밀번호 입력 시 '*'로 표시
        eTf = new TextField("");
        nameTf = new TextField("");
        phTf = new TextField("");

        duBtn = new JButton("중복");
        joinBtn = new JButton("가입");
        cancelBtn = new JButton("취소");

        ch.setBounds(285, 245, 90, 50);

        idl.setBounds(60, 40, 50, 35);
        idTf.setBounds(130, 40, 180, 35);

        pwl.setBounds(60, 80, 50, 35);
        pwTf.setBounds(130, 80, 245, 35);

        checkpwl.setBounds(40, 120, 80, 35);
        checkpwTf.setBounds(130, 120, 245, 35);

        el.setBounds(60, 160, 50, 35);
        eTf.setBounds(130, 160, 245, 35);

        namel.setBounds(60, 200, 50, 35);
        nameTf.setBounds(130, 200, 245, 35);

        phl.setBounds(60, 240, 50, 35);
        phTf.setBounds(130, 240, 150, 35);

        duBtn.setBounds(315, 40, 60, 35);
        duBtn.setBackground(customColor3);

        joinBtn.setBackground(customColor2);
        joinBtn.setBounds(130, 290, 160, 35);
        joinBtn.setBorder(BorderFactory.createEmptyBorder());

        cancelBtn.setBackground(customColor3);
        cancelBtn.setBounds(295, 290, 80, 35);
        cancelBtn.setBorder(BorderFactory.createEmptyBorder());

        msgl.setBounds(180, 30, 200, 35);
        msgl.setFont(font);
        msgl.setForeground(customColor3);
        joinBtn.addActionListener(this);

        for (int i = 0; i < telecom.length; i++) {
            ch.add(telecom[i]);
        }

        // JLabel을 추가하여 제목을 배경 위에 그림
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(font);
        titleLabel.setForeground(new Color(255, 255, 255)); // 제목 색상 지정
        titleLabel.setBounds(280, 50, 300, 35);
        add(titleLabel);

        JoinPanel joinPanel = new JoinPanel();
        joinPanel.setLayout(null);
        joinPanel.setBounds(100, 100, 450, 350);
        add(joinPanel);

        joinPanel.add(ch);

        joinPanel.add(idl);
        joinPanel.add(idTf);

        joinPanel.add(pwl);
        joinPanel.add(pwTf);

        joinPanel.add(checkpwl);
        joinPanel.add(checkpwTf);

        joinPanel.add(el);
        joinPanel.add(eTf);

        joinPanel.add(namel);
        joinPanel.add(nameTf);

        joinPanel.add(phl);
        joinPanel.add(phTf);

        joinPanel.add(duBtn);
        joinPanel.add(joinBtn);
        joinPanel.add(cancelBtn);

        joinPanel.add(msgl);

        setVisible(true);
        validate();

        joinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 아이디, 비밀번호, 이메일, 이름, 전화번호 모두 입력되었는지 확인
                String id = idTf.getText().trim();
                String pw = pwTf.getText().trim();
                String checkpw = checkpwTf.getText().trim();
                String email = eTf.getText().trim();
                String name = nameTf.getText().trim();
                String phone = phTf.getText().trim();
                String telecom = ch.getSelectedItem(); // 선택된 통신사 가져오기

                // 모든 입력 필드에 값이 있는지 확인
                if (id.isEmpty() || pw.isEmpty() || checkpw.isEmpty() || email.isEmpty() || name.isEmpty()
                        || phone.isEmpty() || telecom.equals("통신사 선택")) { // 통신사 선택 여부 확인 추가
                    // 입력 필드 중 하나라도 비어있을 경우 메시지 표시
                    msgl.setText("정보를 모두 입력해주세요.");
                    msgl.setForeground(Color.RED);
                } else {
                    // 데이터베이스에 사용자 정보 저장
                    DatabaseManager dbManager = new DatabaseManager();
                    boolean isSuccess = dbManager.insertUser(id, pw, checkpw, email, name, phone, telecom);

                    if (isSuccess) {
                        msgl.setText("가입이 완료되었습니다.");
                        msgl.setForeground(Color.GREEN);
                        // 가입 완료 후 로그인 화면으로 이동
                        redirectToLogin();
                    } else {
                        msgl.setText("가입에 실패했습니다. 다시 시도해주세요.");
                        msgl.setForeground(Color.RED);
                    }
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object obj = e.getSource();
                    if (obj == cancelBtn)/* 취소 버튼 클릭 시 */ {
                        dispose();
                        new LoginEx1();
                    } else if (obj == cancelBtn) {
                        System.out.println("취소 버튼이 아닙니다.");
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });

        // 중복 확인 버튼 리스너 설정
        duBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idTf.getText().trim(); // 입력된 아이디 가져오기

                // 데이터베이스 매니저 인스턴스 생성
                DatabaseManager dbManager = new DatabaseManager();

                // 중복 확인 수행
                boolean isDuplicate = dbManager.checkDuplicateId(id);

                // 결과에 따라 메시지 설정
                if (isDuplicate) {
                    msgl.setText("중복된 아이디입니다.");
                    msgl.setForeground(Color.RED);
                } else {
                    msgl.setText("사용 가능한 아이디입니다.");
                    msgl.setForeground(Color.GREEN);
                }
            }
        });
    }

    public class DatabaseManager {

        // JDBC 데이터베이스 연결 정보
        Connection conn = null; // DB 커넥션 연결 객체
        private static final String DB_URL = "jdbc:mysql://localhost:3306/hse"; // 데이터베이스 URL
        private static final String DB_USER = "root"; // 데이터베이스 사용자 이름
        private static final String DB_PASSWORD = "1234"; // 데이터베이스 암호

        // 아이디 중복 확인 메서드
        public boolean checkDuplicateId(String id) {
            boolean isDuplicate = false;
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {

                // JDBC 드라이버 로드
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 데이터베이스 연결
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                // SQL 쿼리 작성
                String sql = "SELECT COUNT(*) FROM member WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);

                // 쿼리 실행 및 결과 가져오기
                rs = pstmt.executeQuery();

                // 결과가 있는 경우 중복 여부 확인
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count > 0) {
                        isDuplicate = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 연결 및 리소스 해제
                try {
                    if (rs != null)
                        rs.close();
                    if (pstmt != null)
                        pstmt.close();
                    if (conn != null)
                        conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return isDuplicate;
        }

        // 사용자 정보를 데이터베이스에 저장하는 메서드
        public boolean insertUser(String id, String pw, String checkpw, String email, String name, String phone, String telecom) {
            try {
                // JDBC 드라이버 로드
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 데이터베이스 연결
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                // 난수를 생성하여 계좌번호로 사용
                String account_num = generateAccountNumber();

                // SQL 쿼리 작성
                String sql = "INSERT INTO member (id, pwd, checkpw, email, name, phone_num, telecom) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                pstmt.setString(2, pw);
                pstmt.setString(3, checkpw);
                pstmt.setString(4, email);
                pstmt.setString(5, name);
                pstmt.setString(6, phone);
                pstmt.setString(7, telecom);

                // 쿼리 실행
                int rowsInserted = pstmt.executeUpdate();

                // 삽입된 행이 있다면 성공
                if (rowsInserted > 0) {
                	// 계좌번호를 account 테이블에 저장
                    boolean accountInserted = insertAccount(account_num, id);
                    if (!accountInserted) {
                        return false; // 계좌번호 저장 실패 시 회원 정보도 롤백되어야 할 수 있음
                    }
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 연결 및 리소스 해제
                try {
                    if (conn != null)
                        conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false; // 실패
        }
        
        // 계좌번호를 account 테이블에 저장하는 메서드
        private boolean insertAccount(String account_num, String id) {
            try {
            	// JDBC 드라이버 로드
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 데이터베이스 연결
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                
                // SQL 쿼리 작성
                String sql = "INSERT INTO account (account_num, id) VALUES (?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, account_num);
                pstmt.setString(2, id);

                // 쿼리 실행
                int rowsInserted = pstmt.executeUpdate();

                // 삽입된 행이 있다면 성공
                if (rowsInserted > 0) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                // 연결 및 리소스 해제
                try {
                    if (conn != null)
                        conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false; // 실패
        }
    }

    // 난수를 생성하여 계좌번호로 사용하는 메서드
    public String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            accountNumber.append(random.nextInt(10)); // 0부터 9까지의 난수 생성
        }
        return accountNumber.toString();
    }
    
    // 로그인 화면으로 이동하는 메서드
    private void redirectToLogin() {
        dispose(); // 현재 창 닫기
        new LoginEx1(); // 로그인 화면 열기
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new JoinEx1();
    }
}
