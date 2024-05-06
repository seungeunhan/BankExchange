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

        // 버튼을 추가할 패널을 생성합니다.
        JPanel buttonPanel = new JPanel(new GridLayout(7, 1));
        add(buttonPanel, BorderLayout.WEST);

        // 버튼들을 생성하고 추가합니다.
        for (int i = 0; i < 7; i++) {
            JButton button = new JButton("Button " + (i));
            button.addActionListener(new PanelActionListener(i));
            buttonPanel.add(button);
        }

        // 처음에는 첫 번째 패널을 출력합니다.
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
            // 현재 출력중인 패널을 제거합니다.
            remove(centerPanel);
            System.out.println("제거");

            // 버튼에 따라 적절한 패널을 생성하여 출력합니다.
            
            switch (index) {
            	
                case 1:
                    // 버튼 1을 클릭하면 RateFrame의 rate_pan을 출력합니다.
//                    centerPanel = rateFrame.getMainPanel();
                    add(centerPanel, BorderLayout.CENTER);
                    
                    System.out.println("출력");
                    break;
                case 2:
                	centerPanel = tradeFrame.getMainPanel();
                	add(centerPanel, BorderLayout.CENTER);
                	System.out.println("2");
                	break;
                // case 1, 2, 3, 4, 5, 6도 마찬가지로 수정해야 합니다.
            }

            // 패널 변경을 반영합니다.
            validate();
            repaint();
        }
    }

    public static void main(String[] args) {
//        new MainFrame();
    }
}