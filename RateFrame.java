package test;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

public class RateFrame extends JPanel{
	
	JButton logo, logout;
	JButton menu_btn[] = new JButton[5];
	JPanel menu_pan, rate_pan, major_rate_pan, all_rate_pan, p1;
	JTextArea major_rate_header, all_rate_header, major_rate[];
	JTable all_rate_table;
	


	public RateFrame(MainUI instance) {
		MainUI a = instance;
		setSize(500,400);
		setBackground(Color.white);
		
        RateDAO dao = new RateDAO();
        RateBean todayRate = dao.getTodayRate();
		
		// �޴� �г� ����
        menu_pan = new JPanel();
        menu_pan.setLayout(new GridLayout(7, 1));
        
        logo = new JButton("logo");
        logout = new JButton("�α׾ƿ�");
        
        menu_pan.add(logo);
        
        for (int i = 0; i < menu_btn.length; i++) {
            menu_btn[i] = new JButton("Menu " + (i + 1));
            menu_pan.add(menu_btn[i]);
        }
        menu_pan.add(logout);
        
        // ���� �г� ����
        rate_pan = new JPanel(new GridLayout(2,1));

        major_rate_pan = new JPanel(null);
        all_rate_pan = new JPanel(new BorderLayout());
        
        major_rate_header = new JTextArea("�ֿ� ���� ȯ�� ��Ȳ");
        major_rate_header.setEditable(false);
        
        p1 = new JPanel(null);
        p1.setBackground(Color.WHITE);
        
        major_rate_pan.setBackground(Color.white);
        
        major_rate = new JTextArea[6];
        
        major_rate[0] = new JTextArea(String.valueOf(todayRate.getUSD()));
		major_rate[1] = new JTextArea(String.valueOf(todayRate.getEUR()));
		major_rate[2] = new JTextArea(String.valueOf(todayRate.getJPY()));
		major_rate[3] = new JTextArea(String.valueOf(todayRate.getGBP()));
		major_rate[4] = new JTextArea(String.valueOf(todayRate.getCNY()));
		major_rate[5] = new JTextArea(String.valueOf(todayRate.getAUD()));
		
		major_rate[0].setBounds(130, 70, 100, 36);
		major_rate[1].setBounds(130, 180, 100, 36);
		major_rate[2].setBounds(340, 70, 100, 36);
		major_rate[3].setBounds(340, 180, 100, 36);
		major_rate[4].setBounds(550, 70, 100, 36);
		major_rate[5].setBounds(550, 180, 100, 36);
		
		JTextArea major_rate_name[];
		major_rate_name = new JTextArea[6];
		
		major_rate_name[0] = new JTextArea("�̱� USD");
		major_rate_name[1] = new JTextArea("�Ϻ� JPY");
		major_rate_name[2] = new JTextArea("�߱� CNY");
		major_rate_name[3] = new JTextArea("���� EUR");
		major_rate_name[4] = new JTextArea("���� GBP");
		major_rate_name[5] = new JTextArea("ȣ�� AUD");
		
		major_rate_name[0].setBounds(130,50,100,28);
		major_rate_name[1].setBounds(130,160,100,28);
		major_rate_name[2].setBounds(340,50,100,28);
		major_rate_name[3].setBounds(340,160,100,28);
		major_rate_name[4].setBounds(550,50,100,28);
		major_rate_name[5].setBounds(550,160,100,28);
		
		JTextArea major_rate_day[];
		
		major_rate_day = new JTextArea[6];
		for (int i = 0; i < major_rate_day.length; i++) {
			major_rate_day[i] = new JTextArea(String.valueOf(todayRate.getDAY()));	
		}
		
    	major_rate_day[0].setBounds(130,110,100,20);
		major_rate_day[1].setBounds(130,220,100,20);
		major_rate_day[2].setBounds(340,110,100,20);
		major_rate_day[3].setBounds(340,220,100,20);
		major_rate_day[4].setBounds(550,110,100,20);
		major_rate_day[5].setBounds(550,220,100,20);
		
        for (int i = 0; i < major_rate.length; i++) {

        	major_rate_day[i].setEditable(false);
        	major_rate_day[i].setFont(new Font("���� ���", Font.PLAIN, 14));
        	major_rate_pan.add(major_rate_day[i]);
        	
			major_rate[i].setEditable(false);
			major_rate[i].setFont(new Font("���� ���", Font.PLAIN, 28));
			major_rate[i].setForeground(Color.RED);
			major_rate_pan.add(major_rate[i]);
			
			major_rate_name[i].setEditable(false);
			major_rate_name[i].setFont(new Font("���� ���", Font.PLAIN, 16));
			major_rate_pan.add(major_rate_name[i]);
		}
        
        major_rate_header.setBounds(50,0,150,20);
        major_rate_pan.add(major_rate_header);

        major_rate_pan.add(p1,BorderLayout.CENTER);
        
        all_rate_header = new JTextArea("���� �� ȯ�� ����");
        all_rate_header.setEditable(false);
      
        if(todayRate != null) {
        	
        	//
        	String header[] = {"��ȭ�ڵ�", "�����", "��ȭ��", "������ȭ��"};
            String contents[][] = {        
            		{"USD", "�̱�", "�̱� �޷�", String.valueOf(todayRate.getUSD())},
            		{"JPY","�Ϻ�","�Ϻ� ��",String.valueOf(todayRate.getJPY())},
            		{"THB","�±�","�±� ��Ʈ",String.valueOf(todayRate.getTHB())},
            		{"AUD","ȣ��","ȣ�� �޷�",String.valueOf(todayRate.getAUD())},
            		{"CAD","ĳ����","ĳ���� �޷�",String.valueOf(todayRate.getCAD())},
            		{"CHF","������","������ ����",String.valueOf(todayRate.getCHF())},
            		{"CNY","�߱�","�߱� ����",String.valueOf(todayRate.getCNY())},
            		{"EUR","����","���� ����",String.valueOf(todayRate.getEUR())},
            		{"GBP","����","���� �Ŀ��",String.valueOf(todayRate.getGBP())},
            		{"HKD","ȫ��","ȫ�� �޷�",String.valueOf(todayRate.getHKD())},
            		{"NZD","��������","�������� �޷�",String.valueOf(todayRate.getNZD())},
            		{"SGD","�̰�����","�̰����� �޷�",String.valueOf(todayRate.getSGD())},
            		{"KRW","�ѱ�","�ѱ� ��",String.valueOf(todayRate.getKRW())},
            };
            
            
            DefaultTableModel model = new DefaultTableModel(contents,header) {
            	@Override
                public boolean isCellEditable(int row, int column) {
                   //all cells false
                   return false;
                }
            };
            all_rate_table = new JTable(model);
        }
        
        all_rate_table.setGridColor(Color.white);
        all_rate_table.setShowHorizontalLines(true);
        
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel tcm = all_rate_table.getColumnModel();
        
        tcm.getColumn(0).setCellRenderer(dtcr);
        tcm.getColumn(1).setCellRenderer(dtcr);
        tcm.getColumn(2).setCellRenderer(dtcr);
        tcm.getColumn(3).setCellRenderer(dtcr);
        
        all_rate_table.setRowHeight(30);
        
        rate_pan.setBounds(20,20,800,600);
        rate_pan.add(major_rate_pan);
        rate_pan.add(all_rate_pan, BorderLayout.CENTER);
        
        all_rate_pan.add(new JScrollPane(all_rate_table),BorderLayout.CENTER);
        all_rate_pan.add(all_rate_header,BorderLayout.NORTH);
        
        add(menu_pan, BorderLayout.WEST);
        add(rate_pan, BorderLayout.CENTER);

        setVisible(true);
        
        //���̺��� �ο� Ŭ���� ���λ��� ǥ��
        all_rate_table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(e.getClickCount() == 2) {
        			JPanel detail_p1 = new JPanel(null);
        			JPanel detail_p2 = new JPanel(null);
        			JButton move_btn = new JButton("ȯ���ϱ�");
        			JButton exit_btn = new JButton("�ݱ�");
        			JTable target = (JTable)e.getSource();
        			int row = target.getSelectedRow();
        			Date date = new Date(System.currentTimeMillis());
        			//Ŭ���� ���� ������ �޾ƿ�
        			String cur_unit = (String)target.getValueAt(row, 0);
        			String contury = (String)target.getValueAt(row, 1);
        			String cur_name = (String)target.getValueAt(row, 2);
        			double rate = Double.parseDouble((String)target.getValueAt(row, 3));
        			
        			List<RateBean> weekRates = dao.getWeekRates(date);
        			
        			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        			
        			for (RateBean rateInfo : weekRates) {
                        // �� ��¥�� ȯ���� �����ͼ¿� �߰��մϴ�.    
        				dataset.addValue(rateInfo.getRate(cur_unit), cur_unit, new SimpleDateFormat("MM-dd").format(rateInfo.getDAY()));
                    }

                    // �� �׷����� �����մϴ�.
                    JFreeChart lineChart = ChartFactory.createLineChart(
                            "", // ��Ʈ ����
                            "", // x�� ��
                            "", // y�� ��
                            dataset // �����ͼ�
                    );
                    
                    CategoryPlot plot = lineChart.getCategoryPlot();

                    double min = Double.MAX_VALUE;
                    double max = Double.MIN_VALUE;
                    for (RateBean rateInfo : weekRates) {
                        double rate1 = rateInfo.getRate(cur_unit);
                        min = Math.min(min, rate1);
                        max = Math.max(max, rate1);
                    }
                    
                    ValueAxis yAxis = plot.getRangeAxis();
                    yAxis.setRange(min - (min*0.001), max + (max*0.001));
                    
                    
                    String[] columnNames = {"��¥", "���� �Ÿ���"};
                    Object[][] data = new Object[weekRates.size()][2];
                    int i = 0;
                    for (RateBean rateInfo : weekRates) {
                        data[i][0] = new SimpleDateFormat("MM-dd").format(rateInfo.getDAY());
                        data[i][1] = rateInfo.getRate(cur_unit);
                        i++;
                    }

                    JTextArea rate1 = new JTextArea(contury + " " + cur_unit);
                    rate1.setEditable(false);
                    rate1.setFont(new Font("���� ���", Font.PLAIN, 14));
                    rate1.setForeground(Color.BLACK);
                    JTextArea rate2 = new JTextArea(data[6][1].toString());
                    rate2.setEditable(false);
                    rate2.setFont(new Font("���� ���", Font.BOLD, 22));
                    rate2.setForeground(Color.red);
                    
                    TableModel model = new DefaultTableModel(data, columnNames) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;  // ��� ���� ���� �Ұ����ϰ� ����ϴ�.
                        }
                    };
                    ChartPanel chartPanel = new ChartPanel(lineChart);
                    chartPanel.setDomainZoomable(false);  // x�� ���� ��Ȱ��ȭ�մϴ�.
                    chartPanel.setRangeZoomable(false);   // y�� ���� ��Ȱ��ȭ�մϴ�.
                    chartPanel.setEnabled(false);      // �д��� ��Ȱ��ȭ�մϴ�.
                    
                    // TableModel�� ����Ͽ� JTable�� ����ϴ�.
                    JTable table = new JTable(model);
                    table.setRowHeight(27);
                    table.setFont(new Font("���� ���", Font.PLAIN, 20));
                    
                    JScrollPane scrollPane = new JScrollPane(table);
                    
                    // ���� ������ ������ ���ο� JFrame�� �����ϰ� ǥ���մϴ�.
                    JFrame detailFrame = new JFrame(contury);
                    
                    DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();
                    dtcr1.setHorizontalAlignment(SwingConstants.CENTER);
                    TableColumnModel tcm1 = table.getColumnModel();
                    
                    tcm1.getColumn(0).setCellRenderer(dtcr);
                    tcm1.getColumn(1).setCellRenderer(dtcr);
                    
                    move_btn.setBackground(new Color(62,192,196));
                    move_btn.setFont(new Font("���� ���", Font.BOLD, 28));
                    move_btn.setForeground(Color.white);
                    
                    exit_btn.setBackground(Color.white);
                    exit_btn.setFont(new Font("���� ���", Font.BOLD, 28));
                    
                    chartPanel.setBounds(15,50,745,214);
                    rate1.setBounds(30,10,200,20);
                    rate2.setBounds(30,27,200,30);
                    
                    scrollPane.setBounds(30,00,530,214);
                    move_btn.setBounds(580,0,170,120);                   
                    exit_btn.setBounds(580,130,170,84);
                    
                    detail_p1.setBackground(Color.white);
                    detail_p2.setBackground(Color.white);
                    
                    exit_btn.addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							detailFrame.dispose();
						}
					});
                    
                    move_btn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							detailFrame.dispose();
							a.moveFrameToExchangePanel();//��������ȯ �޼��� ����
						}
					});
                    
                    detailFrame.setLayout(new GridLayout(2, 1));
                    detailFrame.add(detail_p1); // �� �׷����� �߰��մϴ�.
                    detail_p1.add(rate2);
                    detail_p1.add(rate1);
                    detail_p1.add(chartPanel);
                    detailFrame.add(detail_p2);
                    detail_p2.add(scrollPane);
                    detail_p2.add(move_btn);
                    detail_p2.add(exit_btn);
                    detailFrame.setSize(800, 510);
                    detailFrame.setLocationRelativeTo(RateFrame.this); // �θ� â�� �߾ӿ� ��ġ��ŵ�ϴ�.
                    detailFrame.setResizable(false);
                    detailFrame.setVisible(true);
        		}
        	}
		});
	}
	public JPanel getMainPanel() {
        return rate_pan;
    }
	
//	public static void main(String[] args) {
//		RateAPI ra = new RateAPI();
//		ra.main(args);
////		new RateFrame();
//	}
}