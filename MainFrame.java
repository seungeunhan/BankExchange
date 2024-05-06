package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel centerPanel;
//    RateFrame rateFrame = new RateFrame();
    TestFrameTrade5 tradeFrame = new TestFrameTrade5();
    public MainFrame() {
    	setSize(1200,800);
        setLayout(new BorderLayout());

        // ��ư�� �߰��� �г��� �����մϴ�.
        JPanel buttonPanel = new JPanel(new GridLayout(7, 1));
        add(buttonPanel, BorderLayout.WEST);

        // ��ư���� �����ϰ� �߰��մϴ�.
        for (int i = 0; i < 7; i++) {
            JButton button = new JButton("Button " + (i));
            button.addActionListener(new PanelActionListener(i));
            buttonPanel.add(button);
        }

        // ó������ ù ��° �г��� ����մϴ�.
//        centerPanel = rateFrame.getMainPanel();
        add(centerPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class PanelActionListener implements ActionListener {
        private int index;

        public PanelActionListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // ���� ������� �г��� �����մϴ�.
            remove(centerPanel);
            System.out.println("����");

            // ��ư�� ���� ������ �г��� �����Ͽ� ����մϴ�.
            
            switch (index) {
            	
                case 1:
                    // ��ư 1�� Ŭ���ϸ� RateFrame�� rate_pan�� ����մϴ�.
//                    centerPanel = rateFrame.getMainPanel();
                    add(centerPanel, BorderLayout.CENTER);
                    
                    System.out.println("���");
                    break;
                case 2:
                	centerPanel = tradeFrame.getMainPanel();
                	add(centerPanel, BorderLayout.CENTER);
                	System.out.println("2");
                	break;
                // case 1, 2, 3, 4, 5, 6�� ���������� �����ؾ� �մϴ�.
            }

            // �г� ������ �ݿ��մϴ�.
            validate();
            repaint();
        }
    }

    public static void main(String[] args) {
//        new MainFrame();
    }
}