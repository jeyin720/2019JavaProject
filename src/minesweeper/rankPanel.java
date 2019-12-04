package minesweeper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class rankPanel extends JPanel {

	ImageIcon showRank=new ImageIcon("images/rank.jpg");
	ImageIcon button_img=new ImageIcon("images/story_arrow.png");
	JLabel[][] rank=new JLabel[5][2];
	
	JButton next=new JButton();
	int score;
	Main mainFrame=null;
	rankPanel(Main mainFrame){
		this.mainFrame=mainFrame;
		
		Image img =button_img.getImage() ;  
		Image newimg = img.getScaledInstance( 50,50,  java.awt.Image.SCALE_SMOOTH ) ;  
		button_img = new ImageIcon( newimg );
		
		String a[][]=new String[5][2];
		mainFrame.gameRank.select(a);
		
		
		
		int x=0;
		int y=150;
		for(int i=0;i<5;i++) {
			x=300;
			for(int j=0;j<2;j++) {
				rank[i][j]=new JLabel();
				rank[i][j].setFont(new Font("HY견고딕",Font.BOLD,40));
				rank[i][j].setText(a[i][j]);
				rank[i][j].setBounds(x,y,200,100);
				x+=250;
				
			}
			y+=100;
		}
		
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
				mainFrame.change("gameclear",score);
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
		for(int i=0;i<5;i++) {
			for(int j=0;j<2;j++) {
				add(rank[i][j]);
			}
		}
		add(next);
		
	}
	public void getScore(int k) {
		score=k;
	}
	public void paintComponent(Graphics g) {
        g.drawImage(showRank.getImage(), 0, 0, null);
        setOpaque(false); //그림을 표시하게 설정,투명하게 조절
        super.paintComponent(g);
	}
	
}
