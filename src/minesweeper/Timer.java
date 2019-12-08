package minesweeper;

import java.awt.*;
import javax.swing.*;

class Timer extends Thread {
	private JLabel timerLabel;
	int n; 
	boolean wait=false;
	public Timer(JLabel timerLabel) { 
		this.timerLabel = timerLabel;
	}

	@Override
	public void run() {
		n=40;
		while(n>0) {
			timerLabel.setText("�ð� : "+Integer.toString(n)); 
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				return;
			} 
			--n; 
		}
		timerLabel.setText("�ð� ����");
		timerLabel.setForeground(Color.RED);
	}
}

