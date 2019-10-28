
package minesweeper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class gameway extends JPanel {

	ImageIcon way1=new ImageIcon("../images/게임방법-01.jpg");
	ImageIcon way2=new ImageIcon("../images/게임방법-02.jpg");
	ImageIcon background;
	ImageIcon button_img=new ImageIcon("../images/story_arrow.png");
	
	JButton next=new JButton();
	int count=1;
	Main mainFrame=null;
	gameway(Main mainFrame){
		this.mainFrame=mainFrame;
		background=way1;
		
		Image img =button_img.getImage() ;  
		Image newimg = img.getScaledInstance( 50,50,  java.awt.Image.SCALE_SMOOTH ) ;  
		button_img = new ImageIcon( newimg );
		
		
		next.setIcon(button_img);
		next.setBorderPainted(false);
		next.setContentAreaFilled(false);
		next.setFocusPainted(false);
		
		
		next.setBounds(700, 700, 50, 50);
		
		setLayout(null);
		next.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(count==1) {
					background=way2;
					revalidate();
					repaint();
					count++;
				}
				else mainFrame.change("MainPanel",0);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		add(next);
		
	}
	public void paintComponent(Graphics g) {
        g.drawImage(background.getImage(), 0, 0, null);
        setOpaque(false); //그림을 표시하게 설정,투명하게 조절
        super.paintComponent(g);
 }
	
}
