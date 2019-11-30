package minesweeper;

import java.awt.*;
import javax.swing.*;

class Timer extends Thread {
	private JLabel timerLabel;
	int n; 
	public Timer(JLabel timerLabel) { 
		this.timerLabel = timerLabel;
	}

	@Override
	public void run() {
		n=1;
		while(n<=10) {
			timerLabel.setText("�ð� : "+Integer.toString(n)); 
			n++; 
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				return;
			}
		} timerLabel.setText("�ð� �ʰ�");
		timerLabel.setForeground(Color.RED);
		
	}
	public void reset() {
		n=1;
	}
}

