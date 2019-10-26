package minesweeper;
import javax.swing.*;

import minesweeper.Frame.RightMouse;
import minesweeper.Frame.myActionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class Mine_game extends JPanel{
	
	JPanel MineBottom = new JPanel();
	JPanel MineTop = new JPanel();
	
	final int size = 12;
	final int landmine = 15;
	int Mine_count = landmine;
	int Mine_check = landmine;
	
	int board[][] = new int[size][size];
	int clickCount[][] = new int[size][size];
	JButton[][] btn = new JButton[size][size];
	int showboard[][] = new int[size][size];
	ImageIcon flagImage = new ImageIcon("../images/key.png");
	ImageIcon questionMark = new ImageIcon("../images/question.png");
	ImageIcon clearImage = new ImageIcon("../images/gold.png");
	ImageIcon treasureBox = new ImageIcon("../images/box.png");
	
	ImageIcon grass=new ImageIcon("../images/Ǯ.png");
	
	JLabel top_hidden_landmine = new JLabel("������ ����: " + landmine);
	JLabel top_remain_landmine = new JLabel("���� ����: " + Mine_count);
	JButton restart = new JButton("�ٽ� �����ϱ�");
	
	Main mainFrame=null;
	Mine_game(Main mainFrame){// ������
		this.mainFrame=mainFrame;
		
		MineTop.setLayout(new FlowLayout());
		MineTop.add(top_hidden_landmine);
		MineTop.add(restart);
		MineTop.add(top_remain_landmine);
		
		setLayout(new BorderLayout());
		setboard();
		
		
		
		restart.addActionListener(new ActionListener() {//MineTop �ٽý��� ��ư action �߰�
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				MineBottom.removeAll();
				setboard();
				Mine_count=landmine;
				top_remain_landmine.setText("���� ����: " + Mine_count);
				MineBottom.revalidate();
				MineBottom.repaint();
				
			}
			
		});
		add(MineBottom, BorderLayout.CENTER);
		add(MineTop, BorderLayout.NORTH);
		
	}
	public void board_remove(int x, int y) {// ��� �������� shownum ���� ������
		if (showboard[x][y] == 0) {
			if (board[x][y] == 0) {
				shownum(x, y);
				showboard[x][y] = 1;
				if (x - 1 != -1 && y - 1 != -1)
					board_remove(x - 1, y - 1);
				if (y - 1 != -1)
					board_remove(x, y - 1);
				if (x + 1 != size && y - 1 != -1)
					board_remove(x + 1, y - 1);
				if (x - 1 != -1)
					board_remove(x - 1, y);
				if (x + 1 != size)
					board_remove(x + 1, y);
				if (x - 1 != -1 && y + 1 != size)
					board_remove(x - 1, y + 1);
				if (y + 1 != size)
					board_remove(x, y + 1);
				if (x + 1 != size && y + 1 != size)
					board_remove(x + 1, y + 1);
			} else if (board[x][y] != 8 && board[x][y] > 0) {// ���ڵ� �ƴϰ� 0�� �ƴҶ�
				shownum(x, y);
				showboard[x][y] = 1;
			} else if (board[x][y] == 8) {// �����϶�
				gameover();
			}
		}
	}

	public void setboard() {
		MineBottom.setLayout(new GridLayout(size, size,2,2));
		//MineBottom �гο� ��ư �߰�
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					btn[i][j] = new JButton();
					MineBottom.add(btn[i][j]);
					btn[i][j].setIcon(grass);
					btn[i][j].addActionListener(new myActionListener());
					btn[i][j].addMouseListener(new RightMouse());
				}
			}
				
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				showboard[i][j] = 0;
				clickCount[i][j] = 0;
			}
		}
		// ���� ����
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = 0;
			}
		}
		int mine_x, mine_y;
		for (int i = 0; i < landmine; i++) {
			mine_x = (int) (Math.random() * size);
			mine_y = (int) (Math.random() * size);
			if (board[mine_x][mine_y] != 8) {
				board[mine_x][mine_y] = 8;
			} else {
				i--;
			}

		}
		// ���� ����
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int count = 0;
				if (board[i][j] != 8) {
					if (i - 1 != -1 && j - 1 != -1) {
						if (board[i - 1][j - 1] == 8)
							count++;
					}
					if (j - 1 != -1) {
						if (board[i][j - 1] == 8)
							count++;
					}
					if (i + 1 != size && j - 1 != -1) {
						if (board[i + 1][j - 1] == 8)
							count++;
					}
					if (i - 1 != -1) {
						if (board[i - 1][j] == 8)
							count++;
					}
					if (i + 1 != size) {
						if (board[i + 1][j] == 8)
							count++;
					}
					if (i - 1 != -1 && j + 1 != size) {
						if (board[i - 1][j + 1] == 8)
							count++;
					}
					if (j + 1 != size) {
						if (board[i][j + 1] == 8)
							count++;
					}
					if (i + 1 != size && j + 1 != size) {
						if (board[i + 1][j + 1] == 8)
							count++;
					}
					board[i][j] = count;
				}
			}
		}
	}// setboard

	public void flag(int i, int j) {
		if (showboard[i][j] != 1) {
			btn[i][j].setIcon(flagImage);
			Mine_count--;
			if (board[i][j] == 8) {
				Mine_check--;
			}
			top_remain_landmine.setText("���� ����: " + Mine_count);
		}
		if (Mine_check == 0) {
			gameClear();
		}
	}

	public void questionMark(int i, int j) {
		if (showboard[i][j] != 1) {
			Mine_count++;
			if (board[i][j] == 8) {
				Mine_check++;
			}
		}
		top_remain_landmine.setText("���� ����: " + Mine_count);
		btn[i][j].setIcon(questionMark);
	}

	public void gameClear() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == 8) {
					btn[i][j].setIcon(null);
					btn[i][j].setBackground(new Color(255, 128, 0));
					btn[i][j].setIcon(clearImage);
				}
			}
		}
		String gameclear_button[] = { "Ȯ��", "����ȭ������" };
		JOptionPane.showOptionDialog(null, "�����մϴ�! ��� ���ڸ� ���� ������ ������ϴ�. �ٽ� �����Ͻðڽ��ϱ�?", "GAMECLEAR",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, gameclear_button, "Ȯ��");
	}

	public void gameover() {// ���ӿ���
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == 8) {
					shownum(i, j);
				}
			}
		}
		String gameover_button[] = { "Ȯ��", "����ȭ������" };
		JOptionPane.showOptionDialog(null, "�������ڸ� ������� �ǵ�� ��� ��������ϴ�.. �ٽ� �����Ͻðڽ��ϱ�?", "GAMEOVER", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, gameover_button, "Ȯ��");
	}

	public void shownum(int x, int y) {// ��ư ��Ȱ��ȭ
		btn[x][y].setEnabled(false);
		if (board[x][y] != 0 && board[x][y] != 8) {
			btn[x][y].setIcon(null);
			btn[x][y].setText(Integer.toString(board[x][y]));
		}
			
		if (board[x][y] == 8) {
			btn[x][y].setEnabled(true);
			btn[x][y].setIcon(null);

			btn[x][y].setBackground(new Color(255, 128, 0));
			btn[x][y].setIcon(treasureBox);
		}
		if(board[x][y]==0) {
			btn[x][y].setIcon(null);
		}

	}

	public class RightMouse implements MouseListener {// ��ư ���콺 ��Ŭ�� �Ǿ�����
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getButton() == MouseEvent.BUTTON3) {
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						if (btn[i][j] == e.getSource()) {
							clickCount[i][j]++;
							if (clickCount[i][j] == 1)
								flag(i, j);
							else if (clickCount[i][j] == 2)
								questionMark(i, j);
							else {
								btn[i][j].setIcon(grass);
								clickCount[i][j] = 0;
							}
						}
					}
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public class myActionListener implements ActionListener {// ��ư Ŭ��
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (btn[i][j] == e.getSource()) {
						//btn[i][j].setIcon(null);
						board_remove(i, j);
					}
				}
			}
		}
	}
}
