package minesweeper;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Frame extends JFrame {
	JFrame frame=new JFrame();
	JPanel panel1=new JPanel();
	JPanel panel2=new JPanel();
	
	final int size=9;
	final int landmine=10;
	int board[][]=new int[size][size];
	JButton [][]btn=new JButton[size][size];
	public Frame()
    {
             setTitle("지뢰찾기");
             frame.setSize(800, 800);
             frame.setResizable(false);
             
             JLabel toplabel=new JLabel("재미있는 MINESWEEPER");
             toplabel.setHorizontalAlignment(SwingConstants.CENTER);
             //toplabel.setFont(toplabel.getFont().deriveFont(15.0f));글자크기​
            //GridLayout 적용
     		panel1.setLayout(new GridLayout(size,size));
     		
     		for(int i=0;i<size;i++) {
     			for(int j=0;j<size;j++) {
     				btn[i][j]=new JButton();
     				panel1.add(btn[i][j]);
     			}
     		}
     		frame.add(toplabel, BorderLayout.NORTH);
     		frame.add(panel1, BorderLayout.CENTER);
     		
     		frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	void setboard(){
		//지뢰 배정
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				board[i][j]=0;
			}
		}
		int mine_x, mine_y;
		for (int i = 0; i < landmine; i++) {
			mine_x = (int)Math.random()*size;
			mine_y = (int)Math.random()*size;
			board[mine_x][mine_y] = 8;
		}
		//숫자 배정
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int count = 0;
				if (board[i][j] != 8) {
					if (i - 1 != -1 && j - 1 != -1) { if (board[i - 1][j - 1] == 8) count++; }
					if (j - 1 != -1) { if (board[i][j - 1] == 8) count++; }
					if (i + 1 != size && j - 1 != -1) { if (board[i + 1][j - 1] == 8) count++; }
					if (i - 1 != -1) { if (board[i - 1][j] == 8) count++; }
					if (i + 1 != size) { if (board[i + 1][j] == 8) count++; }
					if (i - 1 != -1 && j + 1 != size) { if (board[i - 1][j + 1] == 8) count++; }
					if (j + 1 != size) { if (board[i][j + 1] == 8) count++; }
					if (i + 1 != size && j + 1 != size) { if (board[i + 1][j + 1] == 8) count++; }
					board[i][j] = count;
				}
			}
		}
		
	}//setboard

}
