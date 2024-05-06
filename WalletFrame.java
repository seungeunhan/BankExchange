//package test02;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.GridLayout;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//public class WalletFrame extends JFrame{
//	
//	TradeMgr mgr = new TradeMgr();
//	AccountBean accMo = mgr.getAccMo("asd");
//	JButton logo, logout;
//	JButton menu_btn[] = new JButton[5];
//	JPanel menu_pan, wallet_pan, cur_pan, with_pan, trade_pan;
//	JTable cur_table, with_table, trade_table1, trade_table2;
//	
//	public WalletFrame(String id) {
//		setBackground(Color.white);
//		setSize(500,400);
//		
//		menu_pan = new JPanel();
//		menu_pan.setLayout(new GridLayout(7,1));
//		
//		logo = new JButton("logo");
//		logout = new JButton("로그아웃");
//		
//		menu_pan.add(logo);
//		
//		for (int i = 0; i < menu_btn.length; i++) {
//            menu_btn[i] = new JButton("Menu " + (i + 1));
//            menu_pan.add(menu_btn[i]);
//        }
//        menu_pan.add(logout);
//        
//        wallet_pan = new JPanel(new GridLayout(3,1));
//        cur_pan = new JPanel(null);
//        with_pan = new JPanel(null);
//        trade_pan = new JPanel(null);
//        
//        String[] curNames = {"USD", "JPY", "THB", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "NZD", "SGD", "KRW"};
//        String[] curContents = {String.valueOf(mgr.getAccMo(id).getUSD()),String.valueOf(mgr.getAccMo(id).getUSD()),String.valueOf(mgr.getAccMo(id).getUSD()),String.valueOf(mgr.getAccMo(id).getUSD()),String.valueOf(mgr.getAccMo(id).getUSD()),String.valueOf(mgr.getAccMo(id).getUSD()),String.valueOf(mgr.getAccMo(id).getUSD()),String.valueOf(mgr.getAccMo(id).getUSD()),String.valueOf(mgr.getAccMo(id).getUSD()),String.valueOf(mgr.getAccMo(id).getUSD()),String.valueOf(mgr.getAccMo(id).getUSD()),String.valueOf(mgr.getAccMo(id).getUSD()),String.valueOf(mgr.getAccMo(id).getUSD())};
//        
//        DefaultTableModel tableModel = new DefaultTableModel();
//        tableModel.setColumnIdentifiers(curNames);
//        tableModel.addRow(curContents);
//        
//        JTable table = new JTable(tableModel);
//        table.setPreferredScrollableViewportSize(new Dimension(700, 50));
//        table.setFillsViewportHeight(true);
//        
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        // 패널에 스크롤 페인을 추가합니다.
//        cur_pan.add(scrollPane);
//        
//        wallet_pan.add(cur_pan);
//        wallet_pan.add(with_pan);
//        wallet_pan.add(trade_pan);
//        
//        add(menu_pan, BorderLayout.WEST);
//        add(wallet_pan, BorderLayout.CENTER);
//        wallet_pan.setVisible(true);
//        pack();
//        setVisible(true);
//	}
//	
//	public JPanel getMainPanel() {
//        return wallet_pan;
//    }
//	
//	public static void main(String[] args) {
//		String id = "aaa";
//		new WalletFrame(id);
//	}
//}
package test;

