package minesweeper;
import java.awt.Graphics;

import javax.swing.*;
public class background extends JPanel{
	ImageIcon background_img=new ImageIcon("../images/LOR_background2.jpg");
	Main mainFrame=null;
	background(Main mainFrame){
		this.mainFrame=mainFrame;
	}
	public void paint(Graphics g) {
		super.paintComponent(g);
		 g.drawImage(background_img.getImage(), 0, 0, this);
		 
	}
}
