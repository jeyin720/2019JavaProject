package minesweeper;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
public class mainpanel extends JPanel{
	JButton startButton = new JButton();
	JButton howToPlayButton=new JButton();
	
	ImageIcon background= new ImageIcon("../images/main_background.png");
	ImageIcon gameStart = new ImageIcon("../images/gamestart_btn.png");
	ImageIcon gameStart_press = new ImageIcon("../images/gamestart_btn_pressed.png");
	ImageIcon howToPlay=new ImageIcon("../images/howtoplay.png");
	ImageIcon howToPlay_press=new ImageIcon("../images/howtoplay_press.png");
	
	Main mainFrame=null;
	mainpanel(Main mainFrame){
		this.mainFrame=mainFrame;
		setLayout(null);
		add(startButton);
		add(howToPlayButton);
		startButton.setIcon(gameStart);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.setBounds(100, 620,270,81);
		
		howToPlayButton.setIcon(howToPlay);
		howToPlayButton.setBorderPainted(false);
		howToPlayButton.setContentAreaFilled(false);
		howToPlayButton.setFocusPainted(false);
		howToPlayButton.setBounds(450, 620, 271, 82);
		
		startButton.addMouseListener(new MouseListener() {//시작버튼 마우스 이벤트
	
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				startButton.setIcon(gameStart_press);
			}
	
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				startButton.setIcon(gameStart);
			}
	
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				mainFrame.change("LORPanel");
			}
	
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		howToPlayButton.addMouseListener(new MouseListener() {
	
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				howToPlayButton.setIcon(howToPlay_press);
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				howToPlayButton.setIcon(howToPlay);
			}
	
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				//MainPanel.setVisible(false);
			}
	
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	public void paintComponent(Graphics g) {
        g.drawImage(background.getImage(), 0, 0, null);
        setOpaque(false); //그림을 표시하게 설정,투명하게 조절
        super.paintComponent(g);
 }
}
