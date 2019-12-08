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
			timerLabel.setText("시간 : "+Integer.toString(n)); 
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				return;
			} 
			--n; 
		}
		timerLabel.setText("시간 종료");
		timerLabel.setForeground(Color.RED);
	}
}

